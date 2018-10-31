import java.io.{File, PrintWriter}
import java.util

import scala.io.Source
import scala.collection.mutable.Set

class  TestField{
  var acc = 1

  private var priField = 0
 /*标准写法*/
 def testPriFieldStan(aa :Int):Unit = {
   priField  += aa
 }
  def testPriFieldIntStan(aa :Int):Int ={
    return priField + aa
  }
  /*简便写法*/
  def testPriField(aa :Int):Unit = priField  += aa
  def testPriFieldInt(aa :Int):Int = priField + aa


  def testPriFieldConsi(aa :Int) = priField  += aa
  def testPriFieldIntConsi(aa :Int) = priField + aa
  def testStrig() = "hahah"
  def testStringCons() :Unit = "hahahh"
  def testStrigUnit() {"hahah"}
  def testStrigequal() = {
    "hahah"
    2 + 2
    priField  += 2
  }

}



object HelloWorld {
  var $$ds = 2
  def main(args: Array[String]): Unit = {
   val $ = new Rational(2,3)
   val test_ :Int= 12
   val rational1 = new Rational(2,3)
   val rational2 = new Rational(4,6)
   val rational3 = rational1 + rational2
    /*Symbol*/
   val symbol = 'testSym
   val symbol1 = Symbol("testSym")
    println(symbol == symbol1)
    println('testSym == symbol1)
    /**/
    TestCompanionObject.test
    TestCompanionObject.out(2)

    var calssField1 =new TestField
    calssField1.acc =2
    var calssField2 =new TestField
    calssField2.acc =3

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
    for(line <- Source.fromFile("test.txt").getLines()){
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
    def   fromArgs(agrs : Array[String]) = agrs.mkString("\n")
    println(fromArgs(Array("12", "1234")))
    /*Map */
    val mutMap = scala.collection.mutable.Map(1->"11",2->"22")

    mutMap += (2->"33")

    var immutMap = scala.collection.immutable.Map(1->"11",2->"22")

    immutMap += (2->"33")

    /*Set*/
    val set = scala.collection.mutable.Set("12","12")
    set  +=  "34"


    var imset = scala.collection.immutable.Set("12","12")
    imset  +=  "34" // new set
    /*tuples*/
    var tuples = ("1234s","as2",  12)
    print(tuples)

    /*lsit*/
    var list = List(1,"3er",4)
    list(0)
    var list2 = List(1,"3",4)

    var list3 = list ::: list2
    var list4 = 1 :: list3
    var list5 = 1::Nil::3::Nil
    /*数组*/
    var arr = new Array[String](2)
    arr(0) = "tre"
    arr(1) = "eer"
    for(i <- 0 to 1) {
      println(arr.apply(i))
    }
    var arr2 = Array(1, 2,4)


    /*map*/
    var testMap =
      Map("湖北"-> "武汉","浙江"->"杭州")
    testMap += "湖南"->"长沙"
    println(testMap("湖北"))
    println("hello world!")

    val test = "wewew   |"

    var map = new util.HashMap[Int , String]()
    map.put(2,"67")

  }
}


