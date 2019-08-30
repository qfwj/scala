import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

import org.apache.flink.api.common.accumulators.{Histogram, IntCounter}
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.extensions._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/6 16:40
  */
object FlinkSocketStream {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment()


    val text1 = env.socketTextStream("localhost", 8888)

    val counts1 = text1.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)

    counts1.print()

    env.execute("Window Stream WordCount")

  }
}
