package repartitionAndSortWithinPartition

import org.apache.spark.Partitioner


/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/4/16 19:33
  */
class PrimaryPartitioner[K,S](partition:Int) extends Partitioner{

  override def numPartitions: Int = partition

  override def getPartition(key: Any): Int = {
    //val k = key.asInstanceOf[(Int,S)]
      1
  }



}
