package sql

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.Types
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.api.scala._
import org.apache.flink.table.sinks.CsvTableSink
/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/9/19 17:19
  */
object SqlTest {


  def main(args: Array[String]): Unit = {



    val env = StreamExecutionEnvironment.createLocalEnvironment()

    val tableEnv = StreamTableEnvironment.create(env)
   // val envStream = env.fromElements(new Person("小明",12),new Person("小红",13))
    val envStream: DataStream[Person] = env.fromElements( Person("小明",12), Person("小红",13),Person("小红",12),Person("小红",13))




    val table = tableEnv.fromDataStream( envStream)
   // val  table1 = tableEnv.fromDataStream(envStream, "name,age ")

    /*打印表结构*/
    table.printSchema()

    /*table api 调用*/
    val result = table.filter('name === "小红").select('name as 'myname,'age as 'myAge).where('myAge === 12)

    /*注册输出*/
    val sink = new CsvTableSink("C:\\Users\\nefu_\\Desktop\\testcvs", fieldDelim = "|")
    val fieldNames: Array[String] = Array("myname", "myAge")

    /*类型 指定*/
    val fieldType :Array[TypeInformation[_]] = Array(Types.STRING,Types.INT)
    tableEnv.registerTableSink("CsvSinkTable", fieldNames, fieldType , sink)
    result.insertInto("CsvSinkTable")
    env.execute()
  }
}

 case class Person( name:String,   age:Int)