object TestDefult {
  def main(args: Array[String]): Unit = {
    println(defualt())
    println(defualt(2))
    val ss =  new TestDefult

    TestDefult.defualt()
    val ddd = dd(2)
    println(ddd)
   println(dd(2)(3))
  }
  def defualt(x:Int =3) ={
    1
  }

  def dd(x:Int) = (y:Int) => -x+y

  def cury(x:Int)(y:Int)  = -x+y

}


class TestDefult{
  def test = 1
}