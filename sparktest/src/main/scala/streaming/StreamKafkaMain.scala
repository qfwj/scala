package main.scala.streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/1711:19
  */
object StreamKafkaMain {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("kafka-stream").setMaster("local[4]")


    val scc = new StreamingContext(conf, Seconds(1))



  }

}
