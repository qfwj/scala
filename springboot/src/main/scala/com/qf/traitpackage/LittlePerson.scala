package com.qf.traitpackage

/**
 * description:  
 * date: 2021/3/4 16:26 
 * author: zhbo 
 */
class LittlePerson(var pet1:Animal, val pp:Dog)  extends Person(pet1) {
  override   val pet: Animal = new Dog

  override def eat(): Unit = {
    println("LittlePerson eat little food")
    pet.eat()
  }
}
