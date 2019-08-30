package sourceFunction

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/30 15:38
  */
object TestSourceFunctionStream {
  def main(args: Array[String]): Unit = {
    implicit val typeInfo = TypeInformation.of(classOf[String])

    implicit val typeInfo1 = TypeInformation.of(classOf[Integer])

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)

    val stream:DataStream[Integer] = env.addSource(new TestSourceFunction())
    //val stream:DataStream[String] = env.addSource(new RandomWordSource())
    stream.print()
    env.execute()
  }

}
