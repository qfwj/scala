

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/7/26 11:17
  */
object FlinkMain {
  def main(args: Array[String]): Unit = {


    val env = StreamExecutionEnvironment.createLocalEnvironment()
    val text = env.socketTextStream("localhost",80)
    val counts = text.flatMap(_.split(" ") ).filter(_.nonEmpty)
      .map((_,1))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)



    counts.print()

    env.execute("Window Stream WordCount")
  }

}
