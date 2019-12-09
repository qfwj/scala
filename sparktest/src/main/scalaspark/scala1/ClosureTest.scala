package scalaspark.scala1

import scala.util.Try

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/2817:09
  */
object ClosureTest {

  def createRDDWithLocalProperties[U](time: Int)(body: => U): U = {
    println("hhhh" )
    body
  }

  def foreachFunc(dsd:Int) = {
    println("asds" + dsd)
    "sdsds"
  }

  def main(args: Array[String]): Unit = {
    val te = 12
    val jobFunc = () => createRDDWithLocalProperties(te) {
      foreachFunc(te)
    }
    Try(jobFunc())
    val x = 10


    val ttt = new Function1[Function1[Int, Int], Int] {
      def apply(a: Function1[Int, Int]) = a(x)
    }

    val dd = ttt(Testss.addone)
    println(dd)
  }


  def add2 = new Function1[Int, Int] {
    def apply(x: Int): Int = x + 1;
  }


}

object Testss {

  def addone(y: Int) = y + 1

}

class ClosureTest {
  def createRDDWithLocalProperties()(): Unit = {

  }
}