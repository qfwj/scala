package scalaspark.test191028

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


    val sparkConf = new SparkConf().setMaster("local").setAppName("testHhh")
    val context = new SparkContext(sparkConf)
    val rdd = context.parallelize(List("1 wedwe", "wewe ewre")).repartition(3)


    /**/


    /**
      *
      * reduceByKey, treeAggregate, aggregateByKey, and foldByKey—are
      * implemented to use map-side combinations
      *
      * 测试groupByKey*/
    rdd.flatMap(_.split(" ")).map()




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
      * */
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
