package sql

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.api.scala._
//import org.apache.flink.api.scala._

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/9/19 17:19
  */
object SqlTest {


  def main(args: Array[String]): Unit = {

       val ss = (1,2)


    val env = StreamExecutionEnvironment.createLocalEnvironment()

    val tableEnv = StreamTableEnvironment.create(env)
   // val envStream = env.fromElements(new Person("小明",12),new Person("小红",13))
    var envStream: DataStream[Person] = env.fromElements(new Person("小明",12),new Person("小红",13))

     var envStream1 : DataStream[(String, Int)] = env.fromElements( ("小明",12), ("小红",13))



    val table = tableEnv.fromDataStream( envStream, 'age as 'myage)
   // val  table1 = tableEnv.fromDataStream(envStream, "name,age ")

    table.printSchema()
    env.execute()
  }
}

 case class Person( name:String,   age:Int)