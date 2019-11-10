package scalaspark.test191028

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description: 测试spark相关代码
  * @author: zhbo
  * @date 2019/10/28 10:07
  */
object TestMain191028 {
  def main(args: Array[String]): Unit = {

    def gg(x: Int)(y: Int) = {
      x + y
    }

    def groupBy[T, K](f: T => K) = {
    }


    val sparkConf = new SparkConf().setMaster("local[3]").setAppName("testHhh")
    val context = new SparkContext(sparkConf)
    val rdd = context.parallelize(List("1 ", "2 ", "3", "4", "5", "6"), 6)
    val rdd2 = context.parallelize(List(1, 2, 2, 3, 4, 5, 6), 6)


    val rdd1 = context.parallelize(List("2", "2", "3", "22 ", "5", "6"), 4)


    /**
      * aggregateByKey
      *
      */
    val pairRddaggregate: RDD[(Int, Int)] = context.parallelize(Array((5, 1), (5, 4),(2, 2), (2, 4), (3, 3),(3, 2), (3, 1), (4, 4)), 2)
    pairRddaggregate.mapPartitionsWithIndex((x,y)=>y.map((x,_)))
      .collect()
      .foreach(println(_))

    pairRddaggregate.aggregateByKey("ggg")((x, y) => {
      println("seq" + x + y)
      x + y
    },
      (x, y) => {
        println("comb" +  x + y)
        x + y
      })
      .collect()
      .foreach(println)

    pairRddaggregate.aggregateByKey(0)((x: Int, y: Int) => x + y, (x: Int, y: Int) => x + y)
      .collect()
      .foreach(println)


    /**
      * groupBy 根据指定的指定的计算出key，
      * (6,CompactBuffer((5,1), (3,3)))
      * (4,CompactBuffer((2,2)))
      * (8,CompactBuffer((4,4)))
      */
    val pairRdd: RDD[(Int, Int)] = context.parallelize(Array((5, 1), (2, 2), (3, 3), (4, 4)), 3)
    pairRdd.groupBy(f => f._2 + f._1).collect().foreach(println)


    rdd2.map(f => (f, f)).groupByKey()
      .map(f => (f._1, f._2.reduce(_ + _)))
      .collect().foreach(println)

    /**
      * 测试 集合操作
      * sample ：是否有放回的抽样？保证 不会被重复抽到
      * union: 会将原有的rdd的partition直接原封不动的拿过来
      * intersection: 交集 partiton 为两个rdd 分区最大者 可以设置 分区大小
      * distinct:  背后基于map reduceByKey实现
      */

    rdd1.distinct().mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    rdd.intersection(rdd1).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      println(index)
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    rdd.union(rdd1).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))



    rdd.sample(false, 1, 2).collect().foreach(println)
    rdd.sample(false, 0, 2).collect().foreach(println)

    rdd.sample(true, 1, 2).collect().foreach(println)

    /**
      * 测试reparptition 即使是 分区相等 仍然会 shuffle
      */
    rdd.mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    rdd.repartition(3).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    rdd.repartition(2).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    rdd.repartition(4).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    /**
      * 测试coalesce 窄 宽依赖问题
      **/
    rdd.coalesce(4, true).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    rdd.coalesce(4, false).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    /**/
    val text = context.wholeTextFiles("C:\\test")
    text.foreach(println)


    /**
      *
      * reduceByKey, treeAggregate, aggregateByKey, and foldByKey—are
      * implemented to use map-side combinations
      *
      * 测试groupByKey*/
    rdd.flatMap(_.split(" ")).foreach(println)


    /* 测试偏函数  偏函数 顾名思义只是偏好部分输入值 根据scala 的模式匹配*/
    val ff: PartialFunction[String, Int] = {
      case "1" => 1
      //case _=>2
    }
    rdd.collect({
      case "1" => 1
    }
    ).foreach(f => println((f + "dsdsdsd")))

    /**
      *
      * 测试mapPartitionsWithIndex 会带上分区的index信息
      * map与mapPartitions区别 前者会 执行n次 function 而 mapPartitions传入的入参是一个Iterator function只会执行一次
      * map: 比如一个partition中有1万条数据；那么你的function要执行和计算1万次。
      * MapPartitions:一个task仅仅会执行一次function，function一次接收所有的partition数据。只要执行一次就可以了，性能比较高。
      *
      * 缺点：MapPartitions一次数据加载过多会有内存OOM问题
      *
      **/
    rdd.mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.flatMap(_.split(" ")).map(_ + "##" + index)
    }).foreach(println(_))
    /*测试partition*/
    rdd
      //      .mapPartitions((ss:Iterator[String]) => ss.flatMap(f=> f.split(" ")))
      .mapPartitions(TestMain191028.tet)
      .collect() //收集到driver上打印
      .foreach(println(_))


    // rdd.collect().foreach(println(_))

  }

  def tet(ss: Iterator[String]) = {
    ss.flatMap(f => f.split(" "))
  }

}
