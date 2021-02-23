package com.qf.concurrent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent._

/**
 * description:   https://docs.scala-lang.org/overviews/core/futures.html#the-global-execution-context
 * date: 2021/1/22 9:21 
 * author: zhbo 
 */
object TestFuture extends App {

  val ff = Future {
    println(Thread.currentThread() + "" + 12)
    12
  }
  ff map(f=>f+"sds")
  ff onComplete {
    case Failure(exception) => println(s"some Exception: ${exception}")
    case Success(r) => println(s"some result: ${r}")
  }
  val re = ff andThen {
    case Success(value) => 33
    case Failure(exception) => 0
  }
    re.foreach(f => println(f))

  Await.result(ff, 12 nanos)
}
