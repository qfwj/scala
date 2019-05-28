package scala.scala

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/2817:09
  */
object ClosureTest {

  def main(args: Array[String]): Unit = {
    val x = 1
    val closure = (x:Int, y:Int):Int => x +y

  }

}
