package checkpoint

import java.util.Properties

import kafka.User
import org.apache.flink.runtime.state.memory.MemoryStateBackend
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/10/23 11:01
  */
object CheckpointTestMain {
  def main(args: Array[String]): Unit = {

    val properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    // only required for Kafka 0.8
    properties.setProperty("zookeeper.connect", "localhost:2181");
    properties.setProperty("group.id", "test");
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    val consumer = new FlinkKafkaConsumer[ObjectNode]("test",
      new JSONKeyValueDeserializationSchema(true), properties)


    val streamWindow: DataStream[ObjectNode] = env.addSource(consumer)

    val streamUserstreamWindow = streamWindow.map(f => {

      new User(f.findValue("value").get("name").asText(),
        f.findValue("value").get("age").asInt(), f.findValue("value").get("createTime").asLong())
    })


    env.enableCheckpointing(1000)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    env.getCheckpointConfig.setCheckpointInterval(5000)
    env.getCheckpointConfig.setCheckpointTimeout(60000)
    env.getCheckpointConfig.setFailOnCheckpointingErrors(false)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    env.setStateBackend(new MemoryStateBackend())

   // streamUserstreamWindow.print()

    streamUserstreamWindow.keyBy("name").print()
    env.execute()
  }

}
