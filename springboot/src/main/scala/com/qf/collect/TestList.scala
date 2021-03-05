package com.qf.collect

/**
 * description:  
 * date: 2021/3/4 14:36 
 * author: zhbo 
 */
object TestList  extends  App {

  val lis = 12::13::12::Nil
  val map =  lis.groupBy(_.self)
  println(map)
}
