package sql;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.api.java.*;
import org.apache.flink.streaming.api.*;


/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/18 10:52
 */
public class SqltestJava {

    public static void main(String[] args) throws Exception{
        // get a StreamTableEnvironment, works for BatchTableEnvironment equivalently
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

// DataStream of Row with two fields "name" and "age" specified in `RowTypeInfo`
        DataStream<Student> stream = env.fromElements(new Student("小明", 12), new Student("小红",13));

// convert DataStream into Table with default field names "name", "age"
        //Table table = tableEnv.fromDataStream(stream);

// convert DataStream into Table with renamed field names "myName", "myAge" (position-based)
        //Table table2 = tableEnv.fromDataStream(stream, "myName, myAge");

// convert DataStream into Table with renamed fields "myName", "myAge" (name-based)
        Table table3 = tableEnv.fromDataStream(stream, "name as myName, age as myAge");

// convert DataStream into Table with projected field "name" (name-based)
        //Table table4 = tableEnv.fromDataStream(stream, "name");

// convert DataStream into Table with projected and renamed field "myName" (name-based)
       // Table table5 = tableEnv.fromDataStream(stream, "name as myName");
        table3.printSchema();
        env.execute();
    }
}


