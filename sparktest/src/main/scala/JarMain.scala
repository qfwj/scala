package main.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/289:48
  */
object JarMain {

  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("test-object").setMaster("yarn")
    val scc = new SparkContext(config );
    val list = List(1,2,3,4,5,6)
    val rdd = scc.parallelize(list)
    rdd.filter(ttt.isnotOdd(_)).map(2*_).foreach(println)
  }

}

object ttt {
  def isOdd(x:Int):Boolean={
    return x % 2 == 0
  }

  def isnotOdd(x:Int):Boolean={
    return x % 2 == 0
  }
}
