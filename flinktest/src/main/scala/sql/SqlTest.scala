package sql

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.table.api.Types
import org.apache.flink.table.api.scala._
import org.apache.flink.table.sinks.CsvTableSink
import org.apache.flink.table.sources.{CsvTableSource, TableSource}


/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/9/19 17:19
  */
object SqlTest {


  def main(args: Array[String]): Unit = {


    /*类型 指定*/
    val fieldNames: Array[String] = Array("myname", "myAge")
    val fieldType :Array[TypeInformation[_]] = Array(Types.STRING,Types.INT)

    val env = StreamExecutionEnvironment.createLocalEnvironment()

    val tableEnv = StreamTableEnvironment.create(env)
   // val envStream = env.fromElements(new Person("小明",12),new Person("小红",13))
    val envStream: DataStream[Person] = env.fromElements( Person("小明",12), Person("小红",13),Person("小红",12),Person("小红",13))

    val table = tableEnv.fromDataStream(envStream)


    val env1 = ExecutionEnvironment.getExecutionEnvironment
    val tableDataEnv = BatchTableEnvironment.create(env1)
    val dataSet:DataSet[Person] = env1.fromElements( Person("小明",12), Person("小红",13),Person("小红",12),Person("小红",13))

    val tableDataSet = tableDataEnv.registerDataSet("testDataSet", dataSet)
    tableDataEnv.scan("testDataSet").select('name ,'age)
      .groupBy('name)
      .select('name, 'age.sum as 'age)
      .toDataSet[Person] // conversion to DataSet
      .print()

    val csvSink: CsvTableSink = new CsvTableSink("C:\\Users\\nefu_\\Desktop\\testcvs23")

    tableDataEnv.registerTableSink("RubberOrders", fieldNames, fieldType, csvSink)


    tableDataEnv.sqlQuery(
      """
        |select name, sum(age) age from testDataSet
        |group by name
      """.stripMargin)
      .toDataSet[Person]
      .print()

    tableDataEnv.sqlUpdate(
      """
        |insert into RubberOrders
        |select name as myname, sum(age) as myAge from testDataSet
        |group by name
        |
      """.stripMargin)
    //  .toDataSet[Person] // conversion to DataSet
    //  .print()
    env1.execute()


    /*打印表结构*/
    table.printSchema()

    /*table api 调用*/
    val result = table.filter('name === "小红").select('name as 'myname,'age as 'myAge).where('myAge === 12)




    /*注册csv*/
    val csvSource: TableSource[_] = new
        CsvTableSource("C:\\Users\\nefu_\\Desktop\\testcvs",fieldNames,fieldType,fieldDelim = "|")
    tableEnv.registerTableSource("CsvTable", csvSource)


    /*注册输出*/
    val sink = new CsvTableSink("C:\\Users\\nefu_\\Desktop\\testcvs22", fieldDelim = "|")
    tableEnv.registerTableSink("CsvSinkTable", fieldNames, fieldType , sink)

    tableEnv.scan("CsvTable")
      .filter('myname === "小红")
      .select( 'myname, 'myAge)
      .where('myAge === 12)
     // .insertInto("CsvSinkTable")

   // table.insertInto("CsvSinkTable")

    tableEnv.registerDataStream("testTable",envStream)
    /*sql api*/

/*
  这种写法 无效  insert into
  tableEnv.sqlQuery(
      """
        |select name as myname , sum(age) as myAge
        |from testTable
        |group by name
      """.stripMargin).insertInto("CsvSinkTable")*/

    tableEnv.scan("testTable")
        //.groupBy('name)
        .select('name as 'myname, 'age as 'myAge)
      .insertInto("CsvSinkTable")

    /* 没有group可以成功*/
/*
 tableEnv.sqlUpdate(
      """
        |INSERT INTO CsvSinkTable
        |select name as myname , age as myAge
        |from testTable
      """.stripMargin)
*/




    env.execute()
  }
}

 case class Person( name:String,   age:Int)