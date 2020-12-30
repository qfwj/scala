package com.qf.sinal


import sun.misc.{Signal, SignalHandler}

/**
 * description:  <br>
 * date: 2020/12/30 13:03 <br>
 * author: zhbo <br>
 */
object TestSinal  extends App{
  try {
    println(121)
    Signal.handle(new Signal("INT"), new MySinal)
    Thread.sleep(303000)
  } catch {
    case ex:Throwable =>{
      println(ex)
    }
  }

}

class MySinal extends SignalHandler
{
  override def handle(signal: Signal): Unit = {
    println(signal)
    Runtime.getRuntime.addShutdownHook(new Thread(){
      println(12+"dadasd")
    })
    System.exit(0)
  }
}