package test191028

import org.apache.spark.{SparkConf, SparkContext}

object RddTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val sc = new SparkContext(conf)
   // val rdd = sc.textFile("C:\\Users\\擎风\\Desktop\\test.txt")
    val rdd = sc.parallelize(3::4::5::Nil)
    val rdd2 = sc.parallelize(6::7::8::Nil)
     // rdd2.map(f=> (f,f)).reduceByKey()


       // .sample(true, 0.5)

      .collect()
      .foreach(println)
    println("")

  }

}
