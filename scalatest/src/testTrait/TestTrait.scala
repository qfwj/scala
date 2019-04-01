package testTrait

object TestTrait {


  def ad(l:List[Int])  = new TestTrait(l) with Admistrator

  def up(l:List[Int]) = new TestTrait(l) with Update


  def main(args: Array[String]): Unit = {
    val list= 1::2::Nil
    val re = new TestTrait( list)
    println(re.readFirst())
    val up1 = up(list)
    println(up1.addOne(1))
    val ad1  = ad(list)
    println(ad1.union(5::Nil))

    val beTest = new TestTrait(list) with Admistrator with Update
    println(beTest.readFirst())
    val afterTest = new TestTrait(list)  with Update with Admistrator
    println(afterTest.readFirst())

  }
}




class TestTrait( val list: List[Int]) extends ReadOnly