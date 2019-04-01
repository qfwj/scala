package testTrait

trait Update extends ReadOnly {

  def addOne (x:Int) = list.map(_+1)

  override def readFirst(): Int = 2
}
