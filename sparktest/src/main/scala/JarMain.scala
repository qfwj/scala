package main.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/289:48
  */
object JarMain {

  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("test-object").setMaster("local")
    val scc = new SparkContext(config );
    val list = List(1,2,3,4,5,6)
    val rdd = scc.parallelize(list)
    new JarMain(12)
        .mmap(rdd)
    rdd.filter(ttt.isnotOdd(_)).map(ttt.aa *_).foreach(println)

  }

}

class JarMain(aa:Int) {

  def mmap(x:RDD[Int]) =  x.map(_*aa)

}

object ttt {
  def isOdd(x:Int):Boolean={
    return x % 2 == 0
  }

  val aa = 3
  def isnotOdd(x:Int):Boolean={
    return x % 2 == 0
  }
}
