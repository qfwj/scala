

import java.io.Serializable
import java.util

import org.apache.flink.api.common.accumulators.{Histogram, IntCounter}
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.extensions._

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/7/26 11:17
  */
object FlinkMain {

  def main(args: Array[String]): Unit = {


    val env = StreamExecutionEnvironment.createLocalEnvironment()

    val testIteration = env.fromElements(1,2,3,4,5)
   // testIteration.iterate()


    /*test extension*/
    val testExten = env.fromElements("1", "2", "3", "4", "2")
    val textParti = testExten

      .mapWith {
        case x: String => (x, x.toInt)
      }
    //mapWith(new MyPartialFunction)

    textParti.print()
    env.execute()


    /*test counter  histogram*/
    val testCounter = env.fromElements("1", "2", "3", "4", "2")
    testCounter
      .map(new MyFunction)
      .flatMap(_.split(" "))
      .filter(_.nonEmpty)
      .map((_, 1))
      .keyBy(0)
      .sum(1)
      .print()
      .setParallelism(1)
    val result = env.execute()
    val num = result.getAccumulatorResult[util.TreeMap[Int, Int]]("histogram")
    val num1 = result.getAccumulatorResult[Int]("linesNumber")


    /*test case class*/
    val caseClass = env.fromElements(WorfCount("hello", 1), WorfCount("world", 2), WorfCount("hh", 2), WorfCount("hh", 3))
    //caseClass.keyBy("word").print()
    // caseClass.keyBy(0).print()
    caseClass.keyBy(_.word).print()
    env.execute()


    ///val text = env.socketTextStream("localhost",80)
    //C:\Users\nefu_\Desktop
    val text = env.readTextFile("C:\\Users\\nefu_\\Desktop\\test.txt")
    val counts = text.flatMap(_.split(" ")).filter(_.nonEmpty)
      .map((_, 1))
      .keyBy(_._1)
      //.timeWindow(Time.seconds(5))
      .sum(1)


    counts.print()

    env.execute("Window Stream WordCount")


    val fromCollection = env.fromCollection(List("ww", "cd", "we", "sdwew", "we"))
    val fromCount = fromCollection.map((_, 1)).keyBy(0).sum(1)
    fromCount.print()
    env.execute()

    /*保存文件*/
    //val fromElement = env.fromElements("32", "23","12","12","12","23")
    // val fromElementsCount = fromElement.map((_,1)).keyBy(0).sum(1).writeAsText("C:\\Users\\nefu_\\Desktop\\test1.txt")
    //fromElementsCount.print()
    //env.execute()


    val someIntegers: DataStream[Long] = env.generateSequence(0, 1000)

    val iteratedStream = someIntegers.iterate(
      iteration => {
        val minusOne = iteration.map(v => v - 1)
        // minusOne.print()
        val stillGreaterThanZero = minusOne.filter(_ > 0)
        //  stillGreaterThanZero.print()
        val lessThanZero = minusOne.filter(_ <= 1)
        //lessThanZero.print()
        (stillGreaterThanZero, lessThanZero)
      }
    )
    //  someIntegers.print()
    iteratedStream.print()

    env.execute()

  }

}


case class WorfCount(val word: String, val count: Int)

class MyFunction extends RichMapFunction[String, String] {
  override def map(value: String) = {
    histogram.add(value.trim.toInt)
    linesNumber.add(1)
    value
  }

  val linesNumber = new IntCounter
  val histogram = new Histogram

  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
    getRuntimeContext.addAccumulator("linesNumber", linesNumber)
    getRuntimeContext.addAccumulator("histogram", histogram)
  }
}


trait Par extends Serializable

class MyPartialFunction extends PartialFunction[Any, Int] with Par {
  override def apply(v1: Any): Int = v1 match {
    case a: String => a.toInt
    case a: Int => a
  }

  override def isDefinedAt(x: Any): Boolean = x match {
    case _: String => true
    case _ => false
  }
}