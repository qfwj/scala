import org.apache.spark.rdd.PairRDDFunctions
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {





  def main(args: Array[String]): Unit = {


    /* val list = List("we fd", "12 dsd","eew werwe", "wew wwew")
     val out = list.flatMap(f => {
       f.split(" ")
     } )
     val out2 = list.map(f => f.split(" ")).flatMap(m => m)
     */

    val config = new SparkConf().setAppName("local-1553848694056").setMaster("local")
    val sc = new SparkContext(config)
    val file = sc.textFile("/Users/finup/Desktop/样例.txt")
    val flatmap = file.map(f=> f.split(" "))
    flatmap.flatMap(m=>m).map(n => (n,1))
      .reduceByKey((a,b) =>a+b)
      .foreach(println)
    val reRdd = flatmap.repartition(12)
    val partitionMap = file.mapPartitions(f => {
      f.flatMap(m => m.split(" "))
    }, true)
    println("")

    val pairs = flatmap.flatMap(m=>m).map(n => (n,1))
    val sample = reRdd.sample(true, 0.2,1).foreach(println)


     //new PairRDDFunctions(flatmap)

  }
  /*
    def func(parma:List[String]): Iterator[(String,Int)]={
      parma.map(m => (m,1)).collect()
    }
  */




}
