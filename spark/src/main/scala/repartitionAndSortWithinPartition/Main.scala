package repartitionAndSortWithinPartition

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.math.Ordering

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/4/1619:33
  */
object Main {
  def main(args: Array[String]): Unit = {

    val config = new SparkConf().setMaster("local").setAppName("TEST")
    val sc = new SparkContext(config)
    val data = sc.parallelize(List(Tuple2(1,2),Tuple2(1,3),Tuple2(2,2)  ,Tuple2(3,2)))
    implicit val ordering: Ordering[(Int, Int)] =  new Ordering[(Int, Int)]{
      def compare(x: (Int, Int), y: (Int, Int)): Int = {
        val compare1 = ord1.compare(x._1, y._1)
        if (compare1 != 0) return compare1
        val compare2 = ord2.compare(x._2, y._2)
        if (compare2 != 0) return compare2
        0
      }
    }


    val sortedWithinParts =
      data.repartitionAndSortWithinPartitions(new PrimaryPartitioner[Int,Int](2))

   // sortedWithinParts.mapPartitions( iter => groupSorted[Int, Int](iter) )
    sortedWithinParts.foreach(println)
    println("sdasd")

  }

  def groupSorted[K,S](
                          it: Iterator[((K, S), Int)]): Iterator[(K, List[(S, Int)])] = {
    val res = List[(K, ArrayBuffer[(S,Int)])]()
    it.foldLeft(res)((list, next) => list match {
      case Nil =>
        val ((firstKey, secondKey), value) = next
        List((firstKey, ArrayBuffer((secondKey, value))))
      case head :: rest =>
        val (curKey, valueBuf) = head
        val ((firstKey, secondKey), value) = next
        if (!firstKey.equals(curKey) ) {
          (firstKey, ArrayBuffer((secondKey, value))) :: list
        } else {
          valueBuf.append((secondKey, value))
          list
        }
    }).map { case (key, buf) => (key, buf.toList) }.iterator
  }

}
