package scalaspark.streaming

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
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
      "bootstrap.servers" -> "localhost:9092,anotherhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )


    val scc = new StreamingContext(conf, Seconds(1))
    val topics = Array("topicA", "topicB")
    val stream1 = KafkaUtils.createDirectStream[String, String](
      scc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    val stream = scc.textFileStream("C:\\Users\\nefu_\\Desktop\\sum_dics.txt")

   // stream.map(record => (record + 1, 1)).groupByKey().persist().foreachRDD()

   // scc.socketStream()
    scc.start()
  }

}
