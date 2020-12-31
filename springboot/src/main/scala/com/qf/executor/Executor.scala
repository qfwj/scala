package com.qf.executor

import java.util.concurrent.{Executors, ThreadPoolExecutor, TimeUnit}

import com.qf.partialfunction.TestPartial

import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.Try
import scala.concurrent.duration._

/**
 * description:  <br>
 * date: 2020/12/30 14:14 <br>
 * author: zhbo <br>
 */
object Executor extends App {
 val executors =   Executors.newFixedThreadPool(12).asInstanceOf[ThreadPoolExecutor]
  executors.setKeepAliveTime(5, TimeUnit.SECONDS)
  executors.allowCoreThreadTimeOut(true)
  implicit val ec: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(executors)

  val f = Future {
    val t = Thread.currentThread().getName
    println(s"$t: future is coming")
    123
  }.andThen({
    case x:Try[Int] if x.get >12 => 12
    case x:Try[Int] if x.get <12 => 0
  })

  val re = f.map(r => {
    val t = Thread.currentThread().getName
    println(s"$t: mapping")
    r * r
  })
  re.onComplete { case x: Try[Int] =>    {
    val t = Thread.currentThread().getName
    println(s"$t: mapping")
  } }

  Await.result(f, 3.seconds)
}
