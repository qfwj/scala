package com.qf.traitpackage

/**
 * description:  
 * date: 2021/3/4 15:58 
 * author: zhbo 
 */
object TestTrait extends App {
  val cat = new Cat
  val xiao = new LittlePerson(cat, new Dog)
  xiao.eat

}
