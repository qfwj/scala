package scala.Monad


/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/7/2315:16
  */
object MonadTest {

  def main(args: Array[String]): Unit = {
    val list = 1::2::3::Nil

    val n = list.foldLeft(0)(_+_)
    println(n)
    scala.Function1

  }

}
