import java.io.{File, PrintWriter}
import java.util

import scala.io.Source
import testTrait._
import testTrait.package0.package11.TestPackage

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class Time {
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h

  def hour(x: Int) {
    h = x
  }

  def minute: Int = m

  def minute_=(x: Int) {
    m = x
  }
}

class TestField {
  var acc = 1

  private var priField = 0

  /*标准写法*/
  def testPriFieldStan(aa: Int): Unit = {
    priField += aa
  }

  def testPriFieldIntStan(aa: Int): Int = {
    return priField + aa
  }

  /*简便写法*/
  def testPriField(aa: Int): Unit = priField += aa

  def testPriFieldInt(aa: Int): Int = priField + aa


  def testPriFieldConsi(aa: Int) = priField += aa

  def testPriFieldIntConsi(aa: Int) = priField + aa

  def testStrig() = "hahah"

  def testStringCons(): Unit = "hahahh"

  def testStrigUnit() {
    "hahah"
  }

  def testStrigequal() = {
    "hahah"
    2 + 2
    priField += 2
  }

}

sealed abstract class Expr

case class Number(num: Double) extends Expr

case class UpOp(op: String, vars: Expr) extends Expr

case class BinOp(op: String, left: Expr, right: Expr) extends Expr

case class Var(aa: String) extends Expr


object HelloWorld {


  def simplfyAll(expr: Expr): Expr = expr match {

    case UpOp("-", UpOp("-", e)) => simplfyAll(e)
    case BinOp("+", Number(0), e) => simplfyAll(e)
    case BinOp("+", e, Number(0)) => simplfyAll(e)
    case BinOp("-", e, Number(0)) => simplfyAll(e)
    case BinOp("*", e, Number(1)) => simplfyAll(e)
    case BinOp("*", Number(1), e) => simplfyAll(e)
    case BinOp(op, l, r) => BinOp(op, simplfyAll(l), simplfyAll(r))
    case _ => expr
  }


  /*test */


  def divide(x: Int, y: Int): Int = if (y != 0) x / y else sys.error("sds")

  def sum = (_: Int) + (_: Int) + (_: Int)

  def sum2(a: Int, b: Int, c: Int) = a + b + c

  def listInsert(x: Int, y: List[Int]): List[Int] = {
    if (y.isEmpty || x >= y.head) x :: y
    else y.head :: listInsert(x, y.tail)
  }


  def mSort[T](less: (T, T) => Boolean, x: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (hx :: tx, hy :: ty) => if (less(hx, hy)) hx :: merge(tx, ys) else hy :: merge(xs, ty)
    }

    val n = x.length / 2
    if (n == 0) x
    else {
      val (tmpx, tmpy) = x.splitAt(n)
      merge(mSort(less, tmpx), mSort(less, tmpy))
    }

  }
  class Publition(val title: String)
  class Book(title :String) extends Publition(title)

  object library {
    val set:Set[Book] = Set(new Book("历史"), new Book("杂志"))
    def printBookList( info:Book => AnyRef) {
      set.foreach(f => println(info(f)))
    }
  }
  def main(args: Array[String]): Unit = {
    /*test contravariance && covariance*/
    val func = (book:Publition)=> book.title  //是info:Book => AnyRef的子类
    library.printBookList(func)
    /*test Collections*/
    val testTuple = (2, "qwq", 'q')
    testTuple._3

    mutable.TreeSet(7, 4, 5)
    val testStack = new mutable.Stack[Int]

    val testQueue = new mutable.Queue[Int]
    testQueue ++= List(12, 12)

    val testArrayBuffer = new ArrayBuffer[Int]
    testArrayBuffer += 2
    val testLisBuffrt = new ListBuffer[Int]
    testLisBuffrt += 2
    /*test List*/
    val testList: List[Int] = 1 :: 2 :: 3 :: Nil

    List(1, 2, 3) ::: List(4)

    testList map (_ * 2)

    List.range(1, 5).map(i => List.range(1, i).map(j => (i, j)))
    List.range(1, 5).flatMap(i => List.range(1, i).map(j => (i, j)))

    def sum23(xs: List[Int]): Int = (0 /: xs) (_ + _) //元素相加
    //def sum(xs: List[Int]): Int = (1 /: xs) (_ * _) //元素相乘

    /*test case match */
    val second: PartialFunction[List[Int], Int] = {
      case x :: y :: _ => y
    }
    /*先进行检测*/
    second.isDefinedAt(List(1)) //false

    val testOptin: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }
    testOptin(Option.empty)
    PartialFunction
    /*Patterns everywhere*/
    val (age, name) = ("zh", 12)
    println(age, name)

    for ((country, capital) <- Map("china" -> "bejing", "Japan" -> "toko")) {
      println(country, capital)
    }
    /*test package*/
    val testPackage11 = new TestPackage


    /*test Trait*/
    val testWith = new BaseIntQueue with IncrementTrait with DoubleTrait
    testWith.put(12)
    println(testWith.get()) //返回25 从右至左的执行
    val testTrait = new BaseIntQueue with DoubleTrait
    testTrait.put(12)
    println(testTrait.get())
    val testTrait1 = new BaseIntQueue with IncrementTrait
    testTrait1.put(12)
    println(testTrait1.get())
    val testTrait2 = new BaseIntQueue with DoubleTrait
    testTrait2.put(12)
    println(testTrait2.get())
    val testTrait3 = new BaseIntQueue with DoubleTrait with FilterTrait
    testTrait3.put(12)
    testTrait3.put(-12)
    println(testTrait3.get())
    println(testTrait3.get())

    /*new control loan pattern 贷出模式*/
    def testFunction(op: Int => Int, x: Int) = op(x)

    val testFun = testFunction((_ + 2), 2)
    val testFun2 = testFunction(_ + 3, 2)

    def loanPattern(file: File)(op: PrintWriter => Unit) { //  def loanPattern(file:File, op:PrintWriter =>Unit) {
      val write = new PrintWriter(file)
      try {
        op(write)
      } finally {
        write.close() //资源由我提供 、关闭 客户端只负责用
      }
    }

    /*花括号 括号测试*/
    val testLoan = loanPattern {
      new File("data.txt")
    } { w => w.println("不是sds的bb吧") } // val testLoan = loanPattern(new File("data.txt"), w => w.println("不是sds的bb吧"))


    /*curry*/
    def testcurry(x: Int)(y: Int) = x + y

    val testcurry1 = testcurry(2) _

    /*test high order*/
    val listHigh = List("we", "vfded", "wewded")
    var sdsd = listHigh.exists(_.endsWith("ded"))

    def highOrderList(list: List[String]) = (term: String, matcher: (String, String) => Boolean) => {
      for (tem <- list; if matcher(tem, term))
        yield tem
    }

    def testhginew = highOrderList(listHigh)

    var ss = testhginew("ded", _.endsWith(_))


    def highOrder(term: String, matcher: (String, String) => Boolean) = {
      for (tem <- listHigh; if matcher(tem, term))
        yield tem
    }

    def testEnd(query: String) = highOrder(query, _.endsWith(_))

    def testStart(query: String) = highOrder(query, _.startsWith(_))

    /*test tail recursion*/
    var a = 1
    var b = 1
    var n = 10
    while (n > 2) {
      val tem = b
      b = a + b
      a = tem
      n = n - 1
      print(b + ",")
    }
    println()

    def fibonacci(a: Int, b: Int, n: Int): Int = {
      if (n == 0) b
      else {
        print(b + ",")
        fibonacci(b, a + b, n - 1)
      }
    }

    val testFibo = fibonacci(1, 1, 10)
    /*closure*/
    var more = 2
    val testClosure = (_: Int) + more

    def close(x: Int) = x + more

    val testClosure2 = close _
    println(testClosure(22))
    println(testClosure2(1))
    more = 4
    println(testClosure(22))
    println(testClosure2(1))
    /*partitial apply function*/
    val partifunc = sum(2, _: Int, 3)
    println(partifunc(3))
    val partifunc2 = sum2(2, _: Int, 3)
    println(partifunc2(3))

    /*下划线测试*/
    val f = (_: Int) + (_: Int) //类型推断
    f(3, 4)
    val listss = 2 :: 3 :: 4 :: Nil
    val reList = listss.filter(_ > 2)
    /*match*/
    val matchtest = "hahha"
    matchtest match {
      case "hahha" => println("asas")
      case _ => println("default")
    }


    /*iteration*/
    for (value <- 1 to 4 if value != 3) {
      println(value)
    }

    val $ = new Rational(2, 3)
    val test_ : Int = 12
    val rational1 = new Rational(2, 3)
    val rational2 = new Rational(4, 6)
    val rational3 = rational1 + rational2
    /*Symbol*/
    val symbol = 'testSym
    val symbol1 = Symbol("testSym")
    println(symbol == symbol1)
    println('testSym == symbol1)
    /**/
    TestCompanionObject.test
    TestCompanionObject.out(2)

    var calssField1 = new TestField
    calssField1.acc = 2
    var calssField2 = new TestField
    calssField2.acc = 3

    print(calssField2.testStrig())
    print(calssField2.testStrigUnit())
    print(calssField2.testStringCons())
    print(calssField2.testStrigequal())

    print(calssField2.testPriField(2))
    print(calssField2.testPriFieldConsi(2))
    print(calssField2.testPriFieldStan(2))
    print(calssField2.testPriFieldInt(2))
    print(calssField2.testPriFieldIntStan(2))
    print(calssField2.testPriFieldIntConsi(2))


    TestCompanionObject.out(1);
    def widthOfLength(s: String) = s.length.toString.length


    /*    val lines = Source.fromFile("test.txt").getLines.toList
        val longestLine = lines.reduceLeft(
          (a, b) => if (a.length > b.length) a else b
        )
        val maxWidth = widthOfLength(longestLine)
        for (line <- lines){
          val numSpaces = maxWidth - widthOfLength( line)
          val padding = " " * numSpaces
          print(padding + line.length +" | "+ line)
        }*/


    /*IO*/
    for (line <- Source.fromFile("test.txt").getLines()) {
      println(line.length + " " + line)
    }
    /*Reader*/
    Source.fromFile("test.txt").foreach(
      print
    )
    val padding = " " * 2
    /*writer*/
    val write = new PrintWriter(new File("test.txt"))
    write.write("ceshi数据")
    write.close()

    /*Reader*/
    Source.fromFile("test.txt").foreach(
      println
    )

    /*function*/
    def fromArgs(agrs: Array[String]) = agrs.mkString("\n")

    println(fromArgs(Array("12", "1234")))
    /*Map */
    val mutMap = scala.collection.mutable.Map(1 -> "11", 2 -> "22")

    mutMap += (2 -> "33")

    var immutMap = scala.collection.immutable.Map(1 -> "11", 2 -> "22")

    immutMap += (2 -> "33")

    /*Set*/
    val set = scala.collection.mutable.Set("12", "12")
    set += "34"


    var imset = scala.collection.immutable.Set("12", "12")
    imset += "34" // new set
    /*tuples*/
    var tuples = ("1234s", "as2", 12)
    print(tuples)

    /*lsit*/
    var list = List(1, "3er", 4)
    list(0)
    var list2 = List(1, "3", 4)

    var list3 = list ::: list2
    var list4 = 1 :: list3
    var list5 = 1 :: Nil :: 3 :: Nil
    /*数组*/
    var arr = new Array[String](2)
    arr(0) = "tre"
    arr(1) = "eer"
    for (i <- 0 to 1) {
      println(arr.apply(i))
    }
    var arr2 = Array(1, 2, 4)


    /*map*/
    var testMap =
      Map("湖北" -> "武汉", "浙江" -> "杭州")
    testMap += "湖南" -> "长沙"
    println(testMap("湖北"))
    println("hello world!")

    val test = "wewew   |"

    var map = new util.HashMap[Int, String]()
    map.put(2, "67")

  }
}


trait Queue[T] {
  def head: T

  def tail: Queue[T]

  def append(x: T): Queue[T]
}

object Queue {
  def apply[T](xs: T*): Queue[T] =
    new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T](
                              private val leading: List[T],
                              private val trailing: List[T]
                            ) extends Queue[T] {
    def mirror =
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else
        this

    def head: T = mirror.leading.head

    def tail: QueueImpl[T] = {
      val q = mirror
      new QueueImpl(q.leading.tail, q.trailing)
    }

    def append(x: T) =
      new QueueImpl(leading, x :: trailing)
  }

}