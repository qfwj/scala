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
        conf.set("dfs.client.use.datanode.hostname", "true");
        //或者使用下面的方式设置客户端身份
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"),conf,"root");
        fs.copyToLocalFile(false,new Path("/user/test.txt"),new Path("C:\\Users\\nefu_\\Desktop\\tttt"));
        fs.close();
        System.out.println("");
    }

}
