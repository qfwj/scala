package com.qf.concurrent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise, _}

/**
 * description:   https://docs.scala-lang.org/overviews/core/futures.html#the-global-execution-context
 * date: 2021/1/22 9:21 
 * author: zhbo 
 */
object TestPromise extends App {
  val promise = Promise[Int]()
  val f = promise.future
  val producer = Future {
    promise success 12
    println("f1 start")
    Thread.sleep(300)
    println("f1 end")

  }
  val consumer = Future {

      f.foreach(f => println(s"f: ${f}"))
      Thread.sleep(500)

  }
  //Await.result(producer, 5000 microseconds)
  Await.result(consumer, 5 seconds)
}
