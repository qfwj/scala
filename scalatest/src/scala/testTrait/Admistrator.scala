package testTrait

trait Admistrator extends ReadOnly {

  def union(x:List[Int]) = print(x)

  override def readFirst(): Int = 3

}
