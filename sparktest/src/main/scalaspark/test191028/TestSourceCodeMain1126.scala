package scalaspark.test191028

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/11/26 14:18
  */
object TestSourceCodeMain1126 {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[3]").setAppName("testHhh")

    val sc = new SparkContext(sparkConf)
     sc.hadoopConfiguration.set("dfs.client.use.datanode.hostname","true")
    val tt = sc.hadoopConfiguration.get("dfs.client.use.datanode.hostname")
    val rdd = sc.textFile("C:\\Users\\nefu_\\Desktop\\jiebadict.dic")

    val rddHdfs = sc.textFile("hdfs://10.50.162.184:9000/user/test.txt")
    rddHdfs.flatMap(_.split("_")).collect().foreach(println(_))
    println("")

  }

}
