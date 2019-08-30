package sourceFunction

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/30 15:38
  */
object TestSourceFunctionStream {
  def main(args: Array[String]): Unit = {
   // implicit val typeInfo = TypeInformation.of(classOf[String])
  //  implicit val typeInfo1 = TypeInformation.of(classOf[Integer])

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val stream:DataStream[Integer] = env.addSource(new TestSourceFunction())
    stream.map( f => (f,f)).keyBy(0).window(SlidingEventTimeWindows.of(Time.seconds(5), Time.seconds(2)))
    //val stream:DataStream[String] = env.addSource(new RandomWordSource())
    stream.print()
    env.execute()
  }

}
