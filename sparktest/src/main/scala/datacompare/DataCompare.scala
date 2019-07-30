package scala.datacompare

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/6/2010:21
  */
object DataCompare {

  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("").setMaster("local")
    val sc = new SparkContext(config)

      var rdd1 = sc.textFile("C:\\Users\\nefu_\\Desktop\\nohup-old1.out")
      var rdd11 = rdd1.filter(_.contains("下载成功第")).map(f => (f.substring(f.indexOf(" @@@") + 4, f.indexOf("@@@ ")), f.substring(f.indexOf("耗时") +2 ).toLong ))

      var rdd2 = sc.textFile("C:\\Users\\nefu_\\Desktop\\nohup-new1.out")
      var rdd22 = rdd2.filter(_.contains("下载成功第")).map(f => (f.substring(f.indexOf(" @@@") + 4, f.indexOf("@@@ ")), f.substring(f.indexOf("耗时") +2 ).toLong ))



    var re = rdd11.join(rdd22)
/*

    var compare = re.map(f=>(_._1,_._2._1-._2._2))
    compare.map(_._2).reduce(_+_)
*/

  }
}
