import java.io.{File, PrintWriter}
import java.util

import scala.io.Source
import scala.collection.mutable.Set
object HelloWorld {
  def main(args: Array[String]): Unit = {

    /*Map*/
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

    /*Reader*/
    Source.fromFile("test.txt").foreach(
      print
    )

    /*writer*/
    val write = new PrintWriter(new File("test.txt"))
    write.write("ceshi数据")
    write.close()

    /*Reader*/
    Source.fromFile("test.txt").foreach(
      println
    )


  }
}
