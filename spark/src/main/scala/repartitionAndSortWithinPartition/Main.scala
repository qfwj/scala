package repartitionAndSortWithinPartition

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/*
* 遇到的问题：
* 1.Partitioner的getPartition方法导致的indexOut
* 2.作为key的类，student需要实现Serializable
* 3.implicit的Ordering主要针对PairRDD
* 4.Ordering的实现 Ordering.by Ordering.on 都是实现compare方法
*
* */


object Main {
  def main(args: Array[String]): Unit = {


    val config = new SparkConf().setMaster("local").setAppName("TEST")
    val sc = new SparkContext(config)
    val data = sc.parallelize(List((1,2),(1,3),(2,2)  ,(3,2)))
    //implicit def ordering: Ordering[(Int, Int)] = Ordering.by(f=>(f._1 * -1,f._2))


    /*
    * 主要针对Pair中的key进行排序
    * 如果实现了Ordered的compare不用指定
    *
    *
    * */
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
