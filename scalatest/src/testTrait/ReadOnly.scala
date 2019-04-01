package testTrait

trait ReadOnly {

  val list:List[Int]
  def readFirst() = list.head

}
