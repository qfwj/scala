

import java.io.Serializable
import java.util

import org.apache.flink.api.common.accumulators.{Histogram, IntCounter}
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.runtime.io.network.api.writer.RecordWriter
import org.apache.flink.runtime.jobmanager.scheduler.SlotSharingGroup
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.co.{CoFlatMapFunction, CoProcessFunction}
import org.apache.flink.streaming.api.operators.{AbstractUdfStreamOperator, OneInputStreamOperator}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.extensions._
import org.apache.flink.streaming.api.windowing.assigners.{TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.runtime.tasks.StreamTask
import org.apache.flink.util.Collector

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/7/26 11:17
  */


object FlinkMain {

  case class Person( var name:String,var  age:Int, var son:Son)
  case class Son(val name:String, val age:Int)

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.createLocalEnvironment(4)
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)

    /*test extension*/
 /*   val testExten = env.fromElements(Person("1",2), Person("1",9),Person("8",5),Person("7",5),
      Person("8",2),Person("4",1),Person("2",5),Person("2",2))*/
 val testExten = env.fromElements(Person("1",2, Son("1",1)), Person("3",5, Son("11",1)), Person("2",1, Son("11",1)))

    val testExten1 = env.fromElements(Son("2",2))


    testExten.map(new RichMapFunction[Person] {
      map()={
        getRuntimeContext.getState().update()
      }
    })
    testExten.keyBy(1).broadcast


    testExten.connect(testExten1)
      .flatMap(  new CoFlatMapFunction[Person, Son, Person] {
         var person:Map[String,Person]  = Map()
        def flatMap1  (value:Person, out:Collector[Person])= {
          person += (value.name-> value)
        }
        def flatMap2   (value:Son, out:Collector[Person])= {
          person(value.name).son= value
          val oo =  person(value.name)
          person -= value.name
          out.collect(oo)
        }
      }
    ).print()
    env.execute


    testExten.union(testExten).print()
    env.execute
    val textParti = testExten.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
   // textParti.reduce((a,b)=>Person(a.name+b.name, a.age+ b.age)).print()
   /* val textParti1 = textParti.foldWith(2) ((a, b)=>b.age+ a)
      //.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5))).reduce(_+_)
    textParti1.print()*/
    env.execute

      //textParti.coGroup(testExten).where(_.name).equalTo(_.name)


  //  textParti.print(



    val text1 = env.socketTextStream("localhost", 8888)

    val counts1 = text1.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)

    counts1.print()

    env.execute("Window Stream WordCount")

    val testIteration = env.fromElements(1,2,3,4,5)
   // testIteration.iterate()







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




    /*保存文件*/
    //val fromElement = env.fromElements("32", "23","12","12","12","23")
    // val fromElementsCount = fromElement.map((_,1)).keyBy(0).sum(1).writeAsText("C:\\Users\\nefu_\\Desktop\\test1.txt")
    //fromElementsCount.print()
    //env.execute()


/*    val someIntegers: DataStream[Long] = env.generateSequence(0, 1000)

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

    env.execute()*/

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