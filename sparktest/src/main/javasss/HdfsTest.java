package javasss;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileInputStream;
import java.net.URI;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/12/9 13:27
 */
public class HdfsTest {

    public static void main(String[] args)  throws Exception {
        Configuration conf = new Configuration();

        //这里指定使用的是 hdfs文件系统
        //conf.set("fs.defaultFS", "hdfs://master:9000");

        conf.set("dfs.client.use.datanode.hostname", "true");
        //通过这种方式设置java客户端身份
        // System.setProperty("HADOOP_USER_NAME", "root");
        //FileSystem fs = FileSystem.get(conf);
        //或者使用下面的方式设置客户端身份
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"),conf,"root");

        // fs.create(new Path("/helloByJava")); //创建一个目录

        //文件下载到本地 如果出现0644错误或找不到winutils.exe,则需要设置windows环境和相关文件.
        //fs.copyToLocalFile(new Path("/zookeeper.out"), new Path("D:\\test\\examplehdfs"));


        //使用Stream的形式操作HDFS，这是更底层的方式
      fs.copyToLocalFile(false,new Path("/user/test.txt"),new Path("C:\\Users\\nefu_\\Desktop\\tttt"));

        fs.close();
        System.out.println("");
    }

}
