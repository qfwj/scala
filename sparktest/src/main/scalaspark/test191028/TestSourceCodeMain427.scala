package scalaspark.test191028

import java.util.concurrent.Executors

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/11/26 14:18
  */
object TestSourceCodeMain427 {


  case class Student(name:String, age:Int)


  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[5]").setAppName("testHhh")
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    sparkConf.registerKryoClasses(Array(classOf[Student], classOf[Student]))
    val sc = new SparkContext(sparkConf)
    // sc.hadoopConfiguration.set("dfs.client.use.datanode.hostname","true")

  //  val tt = sc.hadoopConfiguration.get("dfs.client.use.datanode.hostname")
    //val rdd2 = sc.textFile("C:\\Users\\nefu_\\Desktop\\sum_dics.txt")
    val rdd2 = sc.parallelize(Student("das", 12)::Student("das", 12)::Student("das", 12)::Nil)

    rdd2.cache().repartition(2).foreach(println(_))


    println("")










  }

}
