package com.qf.partialfunction

import scala.util.Try

/**
 * description:  <br>
 * date: 2020/12/30 14:56 <br>
 * author: zhbo <br>
 */
class TestPartial extends PartialFunction[Try[Int], Int] {
  override def isDefinedAt(x: Try[Int]): Boolean = {
    true
  }

  override def apply(v1: Try[Int]): Int = {
    val t = Thread.currentThread().getName

    println(s"$t partition ")
    12
  }
}

object TestPartialO {
  def main(args: Array[String]): Unit = {


   val mm =   (x:Any)  => {
      x match {
        case x:Int=> 12
      }
    }

    val f1 = (x:Int) => x+1
    f1.andThen(mm)

    // 4 偏函数的简写，执行顺序 => 遍历List => isDefinedAs() => apply构建Int对象 => *2
    def pf: PartialFunction[Any, Int] = {
      case a: Int => a * 2
      case b: Double => b.toInt * 2
    }


    val vaaa = 0 to 10
    val even: PartialFunction[Int, String] = {
      case x if x % 2 == 0 => x + " is even"
      case f: Int => f + " is odd"
    }
    val odd: PartialFunction[Int, String] = {
      case x if x % 2 != 0 => x + " is odd"
    }

    vaaa.map(even).foreach(println)
  }
}
