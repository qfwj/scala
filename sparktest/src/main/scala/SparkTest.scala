import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {
  def main(args: Array[String]): Unit = {
    val list:List[List[String]]= List(List("Ssd dsd","dfd vf"),List("Ssd qqq","32 33"))
    list.flatMap(f => {

    })
      //.flatMap(n => (n,1))
      .foreach(m => {
      println(m)
    })

    val conf = new SparkConf().setMaster("local").setAppName("SparkTest")
    val sc: SparkContext = new SparkContext(conf)
    val lines = sc.textFile("C:\\Users\\擎风\\Desktop\\qw.txt")
    val flatMap = lines.flatMap(f => f.split(" "))
    flatMap.map(m => (m, 1))
      .filter(n => n._1 != "")
      .reduceByKey((a, b) => a + b)
      .foreach(println)
    flatMap.mapPartitions( f => {
      f
    })

  }
def flatMap(): List[Any] ={

}

 /* def partition(f:List[String]):List[(String, Int)] ={
    f.flatMap(m => ())
  }*/
}
