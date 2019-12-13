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
    //val rdd = sc.textFile("C:\\Users\\nefu_\\Desktop\\jiebadict.dic")
    val rdd = sc.parallelize(List(12,23,45,6,23,6))



    val rdd1 = sc.parallelize(List(12,23,12,45,61)).repartition(20)
    val mapRdd = rdd.map((_,1))


    rdd1.sample(true,2).foreach(println(_))

    mapRdd.countByKey()



    /**
      *  进行相同key的value聚合
      * */
    mapRdd.reduceByKey((_+_)) //实际上还是借用的aggregator
    mapRdd.groupByKey(6)
    mapRdd.aggregateByKey[String]("开始数据:")( (x:String,y:Int)=> {
      x +":" + y
    },_.concat(_)).collect().foreach(println(_))
    mapRdd.sortByKey()




    /**
      * 笛卡尔积
      */
    rdd.cartesian(rdd1).collect().foreach(println(_))






    /**
      * (61,(None,Some(1)))
      * (23,(Some(1),Some(1)))
      * (23,(Some(1),Some(1)))
      * (45,(Some(1),Some(1)))
      * (6,(Some(1),None))
      * (6,(Some(1),None))
      * (12,(Some(1),Some(1)))
      * (12,(Some(1),Some(1)))
      * */
    rdd.map((_,1)).fullOuterJoin[Int](
      rdd1.map((_,1))
    )

    /**
    (61,(None,1))
(23,(Some(1),1))
(23,(Some(1),1))
(45,(Some(1),1))
(12,(Some(1),1))
(12,(Some(1),1))
      * */
    rdd.map((_,1)).rightOuterJoin[Int](
      rdd1.map((_,1))
    )

    /**
      * (23,(1,Some(1)))
      * (23,(1,Some(1)))
      * (45,(1,Some(1)))
      * (6,(1,None))
      * (6,(1,None))
      * (12,(1,Some(1)))
      * (12,(1,Some(1)))
      * */
    rdd.map((_,1)).leftOuterJoin[Int](
      rdd1.map((_,1))
    )

    /**
      *
      * (23,(1,1))
      * (23,(1,1))
      * (45,(1,1))
      * (12,(1,1))
      * (12,(1,1))
      *
      */
    rdd.map((_,1)).join[Int](
      rdd1.map((_,1))
    )



    rdd.intersection(rdd1).collect().foreach(println(_)) //交集的原理就是map 的key 来快速进行对比 cogroup





    val rddHdfs = sc.textFile("hdfs://10.50.162.184:9000/user/test.txt")
    rddHdfs.flatMap(_.split(" ").filter(_.nonEmpty)).collect().foreach(println(_))
    //rdd.union(rddHdfs)//实质上就是两个合并为一个RDD

    println("")

  }

}
