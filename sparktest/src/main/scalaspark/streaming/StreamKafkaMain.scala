package scalaspark.streaming

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/1711:19
  */
object StreamKafkaMain {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("kafka-stream").setMaster("local[4]")
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "10.50.162.207:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (true: java.lang.Boolean)
    )


    val scc = new StreamingContext(conf, Seconds(10))
    val topics = Array("testweili")
    val stream = KafkaUtils.createDirectStream[String, String](
      scc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    stream.map(_.value).map((_,1))
      .reduceByKey((_ + _))
      .print()

   // scc.socketStream()
    scc.start()
    scc.awaitTermination()
  }

}
