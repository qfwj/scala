package com.qf.boot

object TestMain1 extends App {
  val list = List(1, 2, 2, 3)
  println(list.groupBy(identity))
  println(list.groupBy(identity).toMap)
}
