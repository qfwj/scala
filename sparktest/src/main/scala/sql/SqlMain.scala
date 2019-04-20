package sql

import org.apache.spark.sql.SparkSession

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/4/1913:07
  */
object SqlMain {

  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("My Spark Application").master("local[*]").enableHiveSupport().getOrCreate()

    val panda = Panda(1,12,Array(1.2,1.1), "xixi", "yellow",true)
    val pandaPlace = PandaPlace("beijing",Array(panda))
    val df = session.createDataFrame(Seq(pandaPlace))
    df.printSchema()
  }
}


case class Panda(id:Long, age:Int, arttribute: Array[Double], name:String, color:String, happy:Boolean)
case class PandaPlace(name:String, pandas:Array[Panda])