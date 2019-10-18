package stateAndFault

import java.util.Properties

import kafka.User
import org.apache.flink.api.common.functions.{AggregateFunction, RichFunction}
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/9/2 17:32
  */
object FlinkStreamState {

  def main(args: Array[String]): Unit = {


    val properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    // only required for Kafka 0.8
    properties.setProperty("zookeeper.connect", "localhost:2181");
    properties.setProperty("group.id", "test");
    val env = StreamExecutionEnvironment.getExecutionEnvironment




    env.fromCollection(List(
      (1L, 3L),
      (1L, 5L),
      (1L, 7L),
      (1L, 4L),
      (1L, 2L)
    )).keyBy(_._1)
      .flatMap(new CountWindowAverage())
      .print()
    // the printed output will be (1,4) and (1,5)

    env.execute("ExampleManagedState")












    //env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)


    val mt = new FlinkKafkaConsumer[ObjectNode]("test",
      new JSONKeyValueDeserializationSchema(true), properties)


    /*测试 window*/
    val streamWindow: DataStream[ObjectNode] = env.addSource(new FlinkKafkaConsumer[ObjectNode]("test",
      new JSONKeyValueDeserializationSchema(true), properties))



    val streamUserstream = streamWindow.map(f => {

      new User(f.findValue("value").get("name").asText(),
        // f.findValue("value").get("age").asInt(), System.currentTimeMillis())
        f.findValue("value").get("age").asInt(), f.findValue("value").get("createTime").asLong())

    })

    val keyedStream = streamUserstream.keyBy("name")


    keyedStream.print()

    env.execute()

  }

}
