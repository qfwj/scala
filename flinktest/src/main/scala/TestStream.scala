import java.util.Properties

import kafka.FlinkKafkaStream
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.extensions._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

object TestStream {
  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    // only required for Kafka 0.8
    properties.setProperty("zookeeper.connect", "localhost:2181")
    properties.setProperty("group.id", "test")
    val env = StreamExecutionEnvironment.createLocalEnvironment()
    val stream = env.addSource(new FlinkKafkaConsumer[String]("topic", new SimpleStringSchema(), properties))
    //stream.keyBy(1).mapWithState().print()
    //stream.keyBy().sum().
    //stream.windowAll()
   // stream.getSideOutput()
   // stream.map()
    env.execute()
  }
}
