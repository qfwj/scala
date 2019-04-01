

object TestPart {

  def addOne: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = true

    override def apply(x: Int): Int = {
      x + 1
    }

  }

  def minusOne: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = true

    override def apply(x: Int): Int = {
      x - 1
    }
  }



  def main(args: Array[String]): Unit = {

    //ff(new MM)
    val str:String = "dad sds   "
    val sds =  str.split(" ")

    val list = List("asd dsd", "12 ddfd")

    list.flatMap(f => f.split(" ")).map(m => (m,1)).reduce

    val dd = new AAA[MM,Int] {
      override def apply(x: MM): Int = {
        x.m1(2)
      }
    }
    val compose1 = dd.compose((x:Int)=> new MMM2)
    println(compose1(1))
    val andThen1 = dd.andThen((x:Int)=> new MM)
    println(andThen1(new MM).m1(23))



    println(dd.testHign(new MM,(x:MM)=> x.m(2)))
   // println(dd.testHign(2,(_+1)))

   // print((addOne andThen minusOne andThen addOne andThen (addOne) andThen (addOne)) (1))


  }

 def ff[AAA <:MM](x: AAA) = {
    x.m(2)
    1
  }
}

trait AAA[-A,+B] {
  def testHign[A1 <:A, B1>:B](x:A1, ff:A1=>B1):B1 = {
    ff(x)
  }
  def aa(x: A): Int = 1

  def apply(x:A):B
  def compose[D](g:D=>A): D=>B = {
    x => apply(g(x))
  }

  def andThen[D](g:B=>D): A=>D= {
    x:A => g(apply(x))
  }
}


class M {

  def m(x: Int) = x + 1
}

class MM extends M {
  def m1(x: Int) = x + 2
}

class MMM1 extends MM {
  def m21(x: Int) = x + 21
}

class MMM2 extends MM {
  def m22(x: Int) = x + 22
}

trait Function11[+T1, +R] extends AnyRef {
  self =>

  def apply[TT >: T1](v1: TT): R


  def compose[TT >: T1, A](g: A => TT): A => R = { x => apply(g(x)) }


  //def andThen[A](g: R => A): T1 => A = { x => g(apply(x)) }

  override def toString() = "<function1>"
}
