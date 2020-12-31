package com.qf.curry

/**
 * description:  <br>
 * date: 2020/12/31 13:45 <br>
 * author: zhbo <br>
 */
object CurryingTest  extends  App {

  def mm(x:Int)(y:Int,z:Int) ={
    x+y+z
  }

  val curry = mm(_:Int)(12,_:Int)
  println(curry(2,4))

}
