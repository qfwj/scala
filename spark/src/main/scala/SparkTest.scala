import org.apache.spark.rdd.{PairRDDFunctions, RDD}
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {


  def main(args: Array[String]): Unit = {


    /*     val list = List("we fd", "12 dsd","eew werwe", "wew wwew")
         val out = list.flatMap(f => {
           f.split(" ")
         } )
         val out2 = list.map(_.split(" "))
         val out3 = out2.flatMap(_.toList)
           .map((_,1))
           .filter( _._1 == "12" )
           .map(f=>f)*/
    val config = new SparkConf().setAppName("local-1553848694056").setMaster("local")
    val sc = new SparkContext(config)


    /*
    *
    *
    *
    *
    * subtract
    * intersection RDD差值
    * union
    *
    * Return an RDD with the elements from `this` that are not in `other`.
    * 利用的map的key进行对比
    * */

    val a=List((1,2),2,3,4,4,4,4)
    val b = List((1,1),3, 4)
    val rddA = sc.parallelize(a)
    val rddB = sc.parallelize(b)
    val rddC = rddA.subtract(rddB).foreach(println)  //仅剩 1 2

    val a1=Array(1,2,3,4,4,4,4)
    val b1 = Array(3, 4)
    val rddA1 = sc.parallelize(a1)
    val rddB1 = sc.parallelize(b1)
    val intersection = rddA1.intersection(rddB1) //去重后的交集
    val subtraction = rddA1.subtract(rddB1) //去重后的差集
    val union = intersection.union(subtraction) //去重后的union 合并;单独的union是不去重的


    val testComSeq = sc.parallelize(List(("qw","we are happy but not hungery"),("qa", "zhe ge shi jie shizenmela"),("qz", "chun tian laile ya  hahah "),("qa","wobuzhidaoaaaa ya zenmelll ")))

    val  out = new ReportCardMetrics(0,0,0).calculateReportCardStatistics(testComSeq)
    out.foreach(f=> {
      println(f)
    })
    val dataDE = sc.parallelize(List(1,2,3))
    val dataDE2 = dataDE.map((_,1))





    val data1 = sc.parallelize(
      List(
        ("13909029812", ("20170507", "http://www.baidu.com")),
        ("18089376778", ("20170401", "http://www.google.com")),
        ("18089376778", ("20170508", "http://www.taobao.com")),
        ("13909029812", ("20170507", "http://www.51cto.com"))
      ),3
    )

    val partitions = data1.partitions

    //val file = sc.textFile("/Users/finup/Desktop/样例.txt")

    val  file = sc.textFile("C:\\Users\\擎风\\Desktop\\qw.txt")
    val flatmap = file.map(f => f.split(" "))
    val map = flatmap.flatMap(m => m).map((_, 1))



    /*
    *
    * 将RDD1与RDD2每个元素相映射 对应
    * eg:rdd1 = (1,2) rdd2 =(a,b)
    *
    * result = (1,a),(1,b),(2,b),(3,b)
    * */
    map.cartesian(map)


    /*cogroup  (c,(CompactBuffer(1, 11),CompactBuffer(2, 22))) 返回结果key，第一个CompactBuffer是data11的value Set，第二个CompactBuffer是data21的value set*/
    val data111 = sc.parallelize(List(("a",2),("b",2),("b",22),("c",2),("b",22)))
    val data21 = sc.parallelize(List(("a",2),("b",2),("b",22),("c",2),("c",22)))
    data111.cogroup(data21)

    /*join 将两个RDD中相同key的join到一起(key,(value, value))*/
    val joinMap = map.join(map)
    /*Sort*/
    map.sortBy(f => f._2)
    map.sortByKey()

    /*
     *
     * 参数：
     *  1=》每个key对应的初始值(类型定义)
     *  2=》seqOp 单个partition中的操作 类似于map
     *  3=》针对2中多个partition中的结果进行combOp
     *
     *
     * 返回结果：
     * (18089376778,Set((20170401,http://www.google.com), (20170508,http://www.taobao.com)))
     * (13909029812,Set((20170507,http://www.51cto.com), (20170507,http://www.baidu.com)))
     * */
    val data = sc.parallelize(
      List(
        ("13909029812", (20170507, "http://www.baidu.com")),
        ("18089376778", (20170401, "http://www.google.com")),
        ("18089376778", (20170508, "http://www.taobao.com")),
        ("13909029812", (20170507, "http://www.51cto.com"))
      )
    )

      val data11 = sc.parallelize(List(("a", 1), ("b", 1), ("b", 11), ("c", 1)))
      val data2 = sc.parallelize(List(("a", 2), ("b", 2), ("b", 22), ("c", 2), ("b", 22)))
      data.aggregateByKey(scala.collection.mutable.Set[(Int, String)]())((set, item) => {
        set += item
      }, (set1, set2) => set1 union set2).mapValues(x => x.toIterable).collect

      val aggregateByKey = map.aggregateByKey(0)((a: Int, b: Int) => a + b, (a: Int, b: Int) => a + b)


      val group = map.groupByKey() // (k,v)v为Iterable

      val groupEnd = group.map(f => (f._1, f._2.reduce((a, b) => a + b)))

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




    }




}
