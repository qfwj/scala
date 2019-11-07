package scalaspark

import org.apache.spark.{SparkConf, SparkContext}

object TestMulti {
  def main(args: Array[String]): Unit = {


    val config = new SparkConf().setAppName("local-dd").setMaster("local")
    val sc = new SparkContext(config)
    val rddA = sc.parallelize(List(("a", 2), ("b", 2), ("b", 22), ("c", 2), ("b", 22)))
    val sorted = rddA.sortByKey()
    val count = sorted.count()
    val sample: Long = count / 2
    sorted.take(sample.toInt)
    sorted.foreach(println)

    val data1 = sc.parallelize(List(1, 2, 3))

    val dataMap = data1.map((_, 1)).map(f => {
      Thread.sleep(500)
      (f._1, 2 * f._2)
    }).sortByKey()
    dataMap.persist()

    dataMap.sortByKey().zipWithIndex()


  }

}
