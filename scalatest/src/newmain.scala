
import JoesPrefs._



object newmain {

  def main(args: Array[String]): Unit = {
    val apples:List[Apple] = new Apple :: Nil
    val fruits = new Orange :: apples
    new son :: Nil
    val supList:List[sup] = List(new sup, new sup)
    val sons: son = new son
    supList.::[sup](sons)

    Greeter.greet("sds")

    val outer1 = new Outer
    val inner1 = new outer1.Inner
    val outer2 = new Outer
    val inner2 = new outer2.Inner

    outer1.test(inner2)
   // outer1.test2(inner2)//报错 类型不匹配
    new TestAbstract
    val x = 10
    new {
      override val denomArg: Int = x/2
      override val numerArg: Int = x
    } with RationalTrait
  }

}

class PreferredPrompt(val preference: String)


object Greeter {

  def greet(name: String)(implicit prompt: PreferredPrompt) {
    println("Welcome, "+ name +". The system is ready.")
    println(prompt.preference)
  }
}

class Outer {
  class Inner
  def test(x: Outer#Inner): Unit = {

  }

  def test2(x: Inner): Unit = {

  }
}


trait Abstract {
  type T
  var ss: T
  val valAbstr: T

  def test(x: T): T
}

object  testOPre extends {
  val numerArg = 12
  val  denomArg = 13
} with RationalTrait
class  testPre (x:Int, y:Int) extends {
  val numerArg = x
    val  denomArg = y
} with RationalTrait

trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  /*require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  override def toString = numer +"/"+ denom*/

}


class Food
abstract class Anymimal {
  type SuitableFood <:Food
  def eat(x: SuitableFood)
}
class Grass extends Food
class Cow extends Anymimal {
   type  SuitableFood = Grass
  override def eat(x: SuitableFood): Unit = {

  }
}

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g
  override def toString = numer +"/"+ denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}

class TestAbstract extends Abstract {
  type T = String
  var ss = ""
  val valAbstr = ""

  def test(x: T): T = x
}


abstract class Fruit

abstract class Fruit1

 class Apple extends Fruit

 class Orange extends Fruit1