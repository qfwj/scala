package scala.scala

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/2817:09
  */
object ClosureTest {

  def main(args: Array[String]): Unit = {
    val x = 10


    val ttt =new Function1[Function1[Int,Int],Int] {
      def apply(a:Function1[Int,Int]) = a(x)
    }

    val dd = ttt(Testss.addone)
    println(dd)
  }


  def add2 = new Function1[Int,Int]{
    def apply(x:Int):Int = x+1;
  }


}

object Testss {

  def addone(y:Int)=y +1

}