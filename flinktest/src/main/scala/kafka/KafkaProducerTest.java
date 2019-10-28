package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import sun.misc.Launcher;
import sun.misc.Service;

import java.lang.reflect.Proxy;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.Future;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/8/26 16:52
 */
public class KafkaProducerTest {

    public static void main(String[] args) throws Exception {

        Thread.sleep(12);
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        /**
         * acks=0 客户端不会等待服务端的确认; acks=1 只会等待leader分区的确认; acks=all或者acks=-1
         * 等待leader分区和follower分区的确认
         *
         */
        props.put("acks", "all");
        /**
         * 设置大于0的值将使客户端重新发送任何数据，一旦这些数据发送失败。
         * 注意，这些重试与客户端接收到发送错误时的重试没有什么不同。允许重试将潜在的改变数据的顺序，如果这两个消息记录都是发送到同一个partition，
         * 则第一个消息失败第二个发送成功，则第二条消息会比第一条消息出现要早。
         */
        props.put("retries", 0);

        props.put("batch.size", 16384);

        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(props);

        // send()方法是异步的
        Future ff = producer.send(new ProducerRecord<String, String>
                ("test", null, "{\"name\":\"小明1\",\"age\":1245,\"createTime\":"+ (System.currentTimeMillis() - 2500)+"}"));


        ff = producer.send(new ProducerRecord<String, String>
                ("test", null, "{\"name\":\"小二毛\",\"age\":122,\"createTime\":"+ (System.currentTimeMillis() - 2000)+"}"));

        ff = producer.send(new ProducerRecord<String, String>
                ("test", null, "{\"name\":\"小四毛\",\"age\":522,\"createTime\":"+ (System.currentTimeMillis() + 3000)+"}"));
//
      ff = producer.send(new ProducerRecord<String, String>
                ("test", null, "{\"name\":\"丫丫\",\"age\":112,\"createTime\":"+ System.currentTimeMillis()+"}"));
//
   //    Thread.sleep(6000);
//        ff = producer.send(new ProducerRecord<String, String>
//                ("test", null, "{\"name\":\"小明\",\"age\":10,\"createTime\":102}"));
//        ff = producer.send(new ProducerRecord<String, String>
//                ("test", null, "{\"name\":\"小明\",\"age\":101,\"createTime\":101}"));

        // ff = producer.send(new ProducerRecord<String, String>("test", null, "{\"name\":\"小明\",\"age\":2}"));
        Object oo = ff.get();
       // ff = producer.send(new ProducerRecord<String, String>("test", null, "{\"name\":\"小小\",\"age\":0}"));
       // ff = producer.send(new ProducerRecord<String, String>("test", null, "{\"name\":\"小小\",\"age\":4}"));


        oo = ff.get();
        System.out.println("dsd");


    }
}
