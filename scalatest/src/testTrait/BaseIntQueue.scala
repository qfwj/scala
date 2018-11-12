package testTrait

import scala.collection.mutable.ArrayBuffer


class BaseIntQueue extends IntQueue {

  val queue:ArrayBuffer[Int] =  new ArrayBuffer[Int]

  override def get(): Int = queue.remove(0)

  override def put(ss: Int): Unit = {
    queue += ss
  }
}
