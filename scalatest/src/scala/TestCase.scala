

object TestCase {

  def testOp(a:Int)(op:Int=>Int) ={
    op(a)
  }


  def main(args: Array[String]): Unit = {
    val sss = new TestCo[parent,down](new down())
    PartialFunction

    sss.mm(new son())
 1::Nil
    List(1,2,3) map (_+1)
    val test = new TestVal("sds")
    println(test.name)

  }
}

sealed abstract class Maybe[+A] {
  def get:A
  def getOrElse[D](default: D):A  = {
    get
  }
}

 class TestCase(val name:String, val age:Int)


class TestVal (val name:String)extends AnyVal
abstract  class List1[+A]   {
  def map[C >:A, B ](f:  A => C) : List[B]
}

class TestCo[P,+R](val ss:R){
  def mm[m <:P, u >:R](x:m):u = { //编译时require less provide more
    ss
  }
}
class grand
class parent extends grand {
  def pp(): Unit = {
    print("sdssd")
  }
}
class son extends parent

