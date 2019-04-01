import org.apache.spark.rdd.PairRDDFunctions
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {


  def main(args: Array[String]): Unit = {




     val list = List("we fd", "12 dsd","eew werwe", "wew wwew")
     val out = list.flatMap(f => {
       f.split(" ")
     } )
     val out2 = list.map(_.split(" "))
     val out3 = out2.flatMap(_.toList)
       .map((_,1))
       .filter( _._1 == "12" )
       .map(f=>f)


    val config = new SparkConf().setAppName("local-1553848694056").setMaster("local")
    val sc = new SparkContext(config)
    val file = sc.textFile("/Users/finup/Desktop/样例.txt")
    val flatmap = file.map(f => f.split(" "))
    flatmap.flatMap(m => m).map(n => (n, 1))
      .reduceByKey((a, b) => a + b)
      .foreach(println)

    val reRdd = flatmap.repartition(12)
    val partitionMap = file.mapPartitions(f => {
      f.flatMap(m => m.split(" "))
    }, true)
    println("")

    val pairs = flatmap.flatMap(m => m).map(n => (n, 1))
    val sample = reRdd.sample(true, 0.2, 1).foreach(println)
    val takeSample = reRdd.takeSample(true, 2).foreach(println)

   // flatmap.intersection() //参数是

    //new PairRDDFunctions(flatmap)

  }

  /*
    def func(parma:List[String]): Iterator[(String,Int)]={
      parma.map(m => (m,1)).collect()
    }
  */


}
