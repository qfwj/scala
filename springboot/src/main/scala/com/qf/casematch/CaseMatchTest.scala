package com.qf.casematch

import java.io.{FileNotFoundException, IOException}

/**
 * description:  <br>
 * date: 2020/12/31 9:53 <br>
 * author: zhbo <br>
 */
object CaseMatchTest extends  App{
val  c1 = (x:Any) => x match {
    /*针对Array*/
   case Array(1,_)=>println("Array")
   case Array(_, x1) =>println(x1 + " singnle")
   case Array(x,_*) =>println(x + " singnle")

     /*针对List*/
   case x::Nil => println(x+ "single List")
   case x1 :: x2 ::x3 :: Nil =>     // 包含三个元素
   case value1 :: tail =>     // 第一个元素为value1


     /*针对异常*/
   case e1: IllegalArgumentException =>  // 语句1
   case e2: FileNotFoundException =>  // 语句2
   case e3: IOException =>  // 语句3

     /*针对类型*/
   case e:Int if e >2 =>  //


   case _ => println(12)
 }


  c1(12::Nil)

  c1(Array(12,2))
}
