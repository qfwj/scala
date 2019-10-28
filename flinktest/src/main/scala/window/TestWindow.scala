package window

import java.util.Properties

import kafka.User
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema
import org.apache.flink.api.common.ExecutionConfig
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/10/28 9:26
  */
object TestWindow {
  def main(args: Array[String]): Unit = {
    val properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    // only required for Kafka 0.8
    properties.setProperty("zookeeper.connect", "localhost:2181");
    properties.setProperty("group.id", "test");
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)


    val mt = new FlinkKafkaConsumer[ObjectNode]("test",
      new JSONKeyValueDeserializationSchema(true), properties)


    /*测试 window*/
    val streamWindow: DataStream[ObjectNode] = env.addSource(new FlinkKafkaConsumer[ObjectNode]("test",
      new JSONKeyValueDeserializationSchema(true), properties))

    val streamUserstreamWindow = streamWindow.map(f => {

      new User(f.findValue("value").get("name").asText(),
        // f.findValue("value").get("age").asInt(), System.currentTimeMillis())
        f.findValue("value").get("age").asInt(), f.findValue("value").get("createTime").asLong())

    })


    streamUserstreamWindow.keyBy("name").sum("age").print()

    env.execute()





  }

}
