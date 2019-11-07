package scalaspark.streaming

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object StreamFileMain {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("fileStream").setMaster("local[2]")
    val stream = new StreamingContext(conf, Seconds(10))

    /**
      * 注意点：
      * 1.textFileStream的参数是文件目录
      * 2.只是在目录中有新建文件时才会触发rdd action
      * 3.saveAsTextFiles在windows下使用会报错
      * 4.乱码问题 直接设置文件编码格式为UTF-8
      */
    val lines = stream.textFileStream("C:\\Users\\nefu_\\Desktop\\test")
    val  nn = lines.flatMap((_.split(" "))).map((_,1)).reduceByKey((_+_))
   // lines.saveAsTextFiles("C:\\Users\\nefu_\\output.txt")

    nn.print()

    stream.start()




    stream.awaitTermination()


  }

}
