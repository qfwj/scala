package com.qf.concurrent

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * description:  
 * date: 2021/1/22 9:21 
 * author: zhbo 
 */
object TestFuture  extends App{

  val ff =  Future {
    println(Thread.currentThread() + "" + 12)
    12
  }

  ff foreach( f => {
    println(f)
  })
}
