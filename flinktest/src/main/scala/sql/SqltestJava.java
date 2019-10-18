package sql;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import org.apache.flink.api.java.tuple.*;
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

        /*POJO*/
        DataStream<Student> streamPOJO = env.fromElements(new Student("小明", 12), new Student("小红",13));
        Table tablePOJO = tableEnv.fromDataStream(streamPOJO, "age as myAge");
        tablePOJO.printSchema();


        /*Tuple*/
        DataStream<Tuple2> streamTuple = env.fromElements(new Tuple2<String, Integer>("小明", 12), new Tuple2<String, Integer>("小红",13));
        Table tableTuple = tableEnv.fromDataStream(streamTuple, "f0 as my");
        tableTuple.printSchema();

// convert DataStream into Table with default field names "name", "age"
        //Table table = tableEnv.fromDataStream(stream);

// convert DataStream into Table with renamed field names "myName", "myAge" (position-based)
        //Table table2 = tableEnv.fromDataStream(stream, "myName, myAge");

// convert DataStream into Table with renamed fields "myName", "myAge" (name-based)

// convert DataStream into Table with projected field "name" (name-based)
        //Table table4 = tableEnv.fromDataStream(stream, "name");

// convert DataStream into Table with projected and renamed field "myName" (name-based)
       // Table table5 = tableEnv.fromDataStream(stream, "name as myName");
        env.execute();
    }
}


