package com.qf.traitpackage

/**
 * description:  
 * date: 2021/3/4 15:53 
 * author: zhbo 
 */
class Person(pet1:Animal) extends Animal with Owner {
  override val pet: Animal = pet1
  override def eat(): Unit = {
    println("eat bread")
    pet1.eat()
  }
}
