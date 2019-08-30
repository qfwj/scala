import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/29 16:10
  */
object FlinkWatermarkStream {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)

    val stream:DataStream[Int] = env.addSource(new TestSourceFunction[Int])
    stream.print()
  }


}

case class Mytpe(val name: String, val age: Int) {

  def next() = Mytpe(this.name + "", this.age + 1)
}
