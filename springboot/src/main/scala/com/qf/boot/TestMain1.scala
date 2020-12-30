package com.qf.boot

object TestMain1 extends App {
  val list = List((1,1), (2,2), (2,5), (3,4),(1,6))
  println(list.maxBy(f=>f._2))
  println(list.groupBy(f=>f._1).map(f=>(f._1,f._2.maxBy(f2=>f2._2))))

  println(list.groupBy(identity))
  println(list.groupBy(identity).toMap)


  val list1 = List(List(1,3),List(2,6))
  println(list1.flatMap(identity).sortBy(identity))

}
