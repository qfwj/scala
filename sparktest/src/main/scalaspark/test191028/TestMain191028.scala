package scalaspark.test191028

import com.alibaba.fastjson._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable


/**
  * @Description: 测试spark相关代码
  * @author: zhbo
  * @date 2019/10/28 10:07
  */
object TestMain191028 {
  def main(args: Array[String]): Unit = {

    val oldMap = Map(1 ->"科技"
    ,2->"美食"
    ,3->"汽车"
    ,4->"历史"
    ,5->"时尚"
    ,6->"要闻"
    ,7->"探索"
    ,8->"社会"
    ,9->"娱乐"
    ,10->"体育"
    ,11->"教育"
    ,12->"健康"
    ,13->"军事"
    ,14->"财经"
    ,15->"旅游"
    ,16->"topNews"
    ,18->"专题"
    ,19->"视频"
    ,20->"热点"
    ,21->"World"
    ,22->"天气"
    ,23->"智慧词条")

    val newMap = Map(
    //,"推荐"->20
    6->"财经"
    ,19->"视频"
    ,13->"科技"
    ,20->"体育"
    ,17->"汽车"
    ,21->"娱乐"
    ,12->"军事"
    ,2->"国际"
    ,14->"旅游"
    ,9->"房产"
    ,24->"游戏"
    ,4->"健康"
    ,15->"美食"
    ,26->"生活"
    ,11->"教育"
    ,30->"历史"
    ,18->"文化"
    ,19->"时尚"
    ,31->"动漫"
    ,27->"育儿"
    ,1->"时政"
    ,3->"社会"
    ,10->"搞笑"
    ,25->"三农"
    ,29->"自然地理"
    ,28->"职场"
    ,16->"美图")

    val ss = "{\"oldCategories\":[\"normal\",\"8\",\"card\"],\"newCategories\":[\"normal\",\"18\",\"card\"],\"id\":\"57b0f2b5dfca49ac\",\"on\":1}"
    val nns = JSON.parseObject(ss).getJSONArray("oldCategories")

    val sparkConf1 = new SparkConf().setMaster("local[3]").setAppName("testHhh")
    val context1 = new SparkContext(sparkConf1)
    val text23 = context1.textFile("C:\\Users\\nefu_\\Desktop\\数据统计\\info (1).2019-11-25")
    val sum = text23.filter(_.contains("上线")).map(f => {
      JSON.parseObject(f.substring(f.indexOf("上线") + 2))
    }).flatMap(f => {
      val list: mutable.MutableList[JSONObject] = new mutable.MutableList()


      val oldCategories = f.getJSONArray("oldCategories")
      val newCategories = f.getJSONArray("newCategories")
      oldCategories.forEach(fff => {
        val temp: JSONObject = f.clone().asInstanceOf[JSONObject]
        temp.put("oldCategories", fff)
        temp.remove("newCategories")
        list += temp
      })

      newCategories.forEach(fff => {
        val temp: JSONObject = f.clone().asInstanceOf[JSONObject]
        temp.put("newCategories", fff)
        temp.remove("oldCategories")
        list += temp
      })
      list
    }).filter(f => !(f.containsKey("oldCategories") && f.containsKey("newCategories")))
      .map(f => {
        var end: String = ""
        f.get("oldCategories") match {
          case "normal" => end = "oldCategories:" + "normal"
          case "card" => end = "oldCategories:" + "card"
          case "commonhead" => end = "oldCategories:" + "commonhead"
         case f: String => end = "oldCategories:" + oldMap.get(f.toInt).get
          //case f: String => end =  if (oldMap.get(f.toInt)!= None)  oldMap.get(f.toInt).get else "emptyOld"
          case _ =>
        }
        f.get("newCategories") match {
          case "normal" => end = "newCategories:" + "normal"
          case "card" => end = "newCategories:" + "card"
          case "commonhead" => end = "newCategories:" + "commonhead"
          //case f: String => end =  if (newMap.get(f.toInt)!= None)  newMap.get(f.toInt).get else "emptyNew"
         case f: String => end = "newCategories:" + newMap.get(f.toInt).get
          case _ =>
        }
        (end, 1)

      })
      .reduceByKey(_+_).foreach(println(_))

    // val old = sum.groupBy(f=> f.get("oldCategories"))


    //sa("C:\\Users\\nefu_\\Desktop\\info.2019-11-23-filter")


    val rdd0 = context1.parallelize(List("1 ", "2 ", "3", "4", "5", "6"), 6)
    val rdd2 = context1.parallelize(List(1, 2, 2, 3, 4, 5, 6), 6)


    val rdd1 = context1.parallelize(List("2", "2", "3", "22 ", "5", "6"), 4)

    rdd1.map(f => (1, 2)).groupByKey().collect()

    /**
      * aggregateByKey
      *
      */
    val pairRddaggregate: RDD[(Int, Int)] = context1.parallelize(Array((5, 1), (5, 4), (2, 2), (2, 4), (3, 3), (3, 2), (3, 1), (4, 4)), 2)
    pairRddaggregate.mapPartitionsWithIndex((x, y) => y.map((x, _)))
      .collect()
      .foreach(println(_))

    pairRddaggregate.aggregateByKey("ggg")((x, y) => {
      println("seq" + x + y)
      x + y
    },
      (x, y) => {
        println("comb" + x + y)
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
    val pairRdd: RDD[(Int, Int)] = context1.parallelize(Array((5, 1), (2, 2), (3, 3), (4, 4)), 3)
    pairRdd.collect()
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


    rdd1.intersection(rdd1).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      println(index)
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    rdd1.union(rdd1).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    rdd1.sample(false, 1, 2).collect().foreach(println)
    rdd1.sample(false, 0, 2).collect().foreach(println)

    rdd1.sample(true, 1, 2).collect().foreach(println)

    /**
      * 测试reparptition 即使是 分区相等 仍然会 shuffle
      */
    rdd1.mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    rdd1.repartition(3).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    rdd1.repartition(2).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    rdd1.repartition(4).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))


    /**
      * 测试coalesce 窄 宽依赖问题
      **/
    rdd1.coalesce(4, true).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    rdd1.coalesce(4, false).mapPartitionsWithIndex((index: Int, list: Iterator[String]) => {
      list.map(_ + "##" + index)
    }).collect().foreach(println(_))

    /**/
    val text = context1.wholeTextFiles("C:\\test")
    text.foreach(println)

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("testHhh")
    val context = new SparkContext(sparkConf)
    val rdd = context.textFile("C:\\Users\\nefu_\\Desktop\\sparktest.txt").repartition(10)
    val start = System.currentTimeMillis()
    rdd.mapPartitions(f => f.map((_, 1))).reduceByKey(_ + _).foreach(println)
    rdd.mapPartitions(f => f.map((_, 1)))
      .groupByKey()
      .foreach(println)

    println("总耗时" + (System.currentTimeMillis() - start))
    rdd.mapPartitionsWithIndex((index, va) => {
      va.map(index + "asdsd" + _)
    }).foreach(println(_))


    /**
      *
      * reduceByKey, treeAggregate, aggregateByKey, and foldByKey—are
      * implemented to use map-side combinations
      *
      * map
      *
      * 测试groupByKey*/

    rdd.flatMap(_.split(" ")).foreach(println)

    //rdd.flatMap(_.split(" ")).map((_)=>(_,_))


    /**
      *
      *
      * 测试偏函数  偏函数 顾名思义只是偏好部分输入值 根据scala 的模式匹配
      *
      *
      *
      **/
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
