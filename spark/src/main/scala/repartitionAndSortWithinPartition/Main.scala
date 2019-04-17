package repartitionAndSortWithinPartition

import org.apache.spark.{Partitioner, SparkConf, SparkContext}




object Main {
  def main(args: Array[String]): Unit = {

    val config = new SparkConf().setMaster("local").setAppName("TEST")
    val sc = new SparkContext(config)
    val data = sc.parallelize(List((1,2),(1,3),(2,2)  ,(3,2)))
    //implicit def ordering: Ordering[(Int, Int)] = Ordering.by(f=>(f._1 * -1,f._2))


    /*主要针对Pair中的key进行排序*/
    implicit val my_self_Ordering = new Ordering[Student] {
      override def compare(a: Student, b: Student): Int = {
        b.age -  a.age
      }
    }

    val rdd121 = data.map(x => (new Student(x._1+"-"+x._2,x._1+x._2, "afds"),x._2))
    rdd121.foreach(println)
    val rdd222 = rdd121.repartitionAndSortWithinPartitions(new PrimaryPartitioner(1))
    rdd222.foreach(println)
    println("sdfds")


    sc.stop()



  }


}
