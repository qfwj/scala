package kafka

import java.util.Properties
import  User

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.windowing.assigners._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.{CountTrigger, ProcessingTimeTrigger}
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema
/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/9 16:57
  */
object FlinkKafkaStream {

  def main(args: Array[String]): Unit = {


    val properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    // only required for Kafka 0.8
    properties.setProperty("zookeeper.connect", "localhost:2181");
    properties.setProperty("group.id", "test");
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)



    /*测试 window*/
    val streamWindow: DataStream[ObjectNode] = env.addSource(new FlinkKafkaConsumer[ObjectNode]("test",  new JSONKeyValueDeserializationSchema(true), properties))

    val streamUserstreamWindow = streamWindow.map(f => {

      new User(f.findValue("value").get("name").asText(), f.findValue("value").get("age").asInt())

    })




    /*GlobalWindows */
    val globalWindowedStream = streamUserstreamWindow.keyBy("name")
      .window(GlobalWindows.create())
      .trigger(CountTrigger.of(4) )
    globalWindowedStream.max("age").print()
    //.window(TumblingEventTimeWindows.of(Time.seconds(5))


    env.execute()

    /* ProcessingTimeSessionWindows session*/
    val sessionWindowedStream = streamUserstreamWindow.keyBy("name").window(ProcessingTimeSessionWindows.withGap(Time.seconds(10)))
        .trigger(ProcessingTimeTrigger.create())
    sessionWindowedStream.max("age").print()
    //.window(TumblingEventTimeWindows.of(Time.seconds(5))

    env.execute()


   /*slidingProcessing Time Windows*/
    val windowedStream = streamUserstreamWindow.keyBy("name").window(SlidingProcessingTimeWindows.of(Time.seconds(20), Time.seconds(15)))
    windowedStream.max("age").print()
      //.window(TumblingEventTimeWindows.of(Time.seconds(5))

    env.execute()


    /*测试 max 指定keyname*/
    val streamJsonmax: DataStream[ObjectNode] = env.addSource(new FlinkKafkaConsumer[ObjectNode]("test",  new JSONKeyValueDeserializationSchema(true), properties))

    val streamUserMax = streamJsonmax.map(f => {

      new User(f.findValue("value").get("name").asText(), f.findValue("value").get("age").asInt())

    })


    /* sum*/
    streamUserMax.keyBy("name").sum("age").print()

    streamUserMax.keyBy("name").reduce((a:User,b:User) => new User(a.name, a.age+b.age) ).print()


    /* sum*/
    streamUserMax.keyBy("name").maxBy("age").print()

    streamUserMax.keyBy("name").max("age" ).print()


    env.execute()

    /*测试     TypeInformationSerializationSchema */

/*

    IntegerTypeInfo

    val streamType: DataStream[ObjectNode] = env.addSource(new FlinkKafkaConsumer[ObjectNode]("test",
      new TypeInformationSerializationSchema(TypeInformation.of(), new TypeSerializer()), properties))
*/



    /*测试JSONKeyValueDeserializationSchema*/
    val streamJson: DataStream[ObjectNode] = env.addSource(new FlinkKafkaConsumer[ObjectNode]("test",  new JSONKeyValueDeserializationSchema(true), properties))

    val streamUser = streamJson.map(f => {

      new User(f.findValue("value").get("name").asText(), f.findValue("value").get("age").asInt())

    })

    val  myProducer2 = new FlinkKafkaProducer[User](
      "localhost:9092",            // broker list
      "test2",                  // target topic
      new TestSchema());   // serialization schema
    myProducer2.setWriteTimestampToKafka(true);
    streamUser.addSink(myProducer2)
    env.execute()

    /*测试 序列化 反序列化*/
    val stream1: DataStream[User] = env.addSource(new FlinkKafkaConsumer[User]("test",  new TestSchema(), properties))

    val  myProducer1 = new FlinkKafkaProducer[User](
      "localhost:9092",            // broker list
      "test2",                  // target topic
      new TestSchema());   // serialization schema
    myProducer1.setWriteTimestampToKafka(true);
    stream1.addSink(myProducer1)

    env.execute()
    stream1.print()
    env.execute()

    // alternatively:
    // env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime)
    // env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val stream: DataStream[String] = env.addSource(new FlinkKafkaConsumer[String]("test",  new SimpleStringSchema(), properties))
    val ss =  stream.flatMap(f=> f.split(" ")).map((_,1)) //.flatMap((_,1))

    //ss.keyBy(0).print()

    /*max maxBy*/
    ss.keyBy(0).maxBy(1).print()

    val dsd:(String,Int) = ("",23)
    val  myProducer = new FlinkKafkaProducer[String](
      "localhost:9092",            // broker list
      "test2",                  // target topic
      new SimpleStringSchema());   // serialization schema
    myProducer.setWriteTimestampToKafka(true);
    stream.addSink(myProducer)
    env.execute("Window Stream WordCount")




    /*reduce */
    ss.keyBy(0).reduce((x,y) => (x._1, x._2 +y._2))



    val keyedStream = ss.keyBy(0)




    /*sum*/
    keyedStream.sum(1).print()


     // keyedStream.sum(1).print()
    println("")
  }

}

