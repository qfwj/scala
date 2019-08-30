import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.TypeInformationSerializationSchema;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/8/27 14:31
 */
public class TestSchema extends  AbstractDeserializationSchema<User> implements SerializationSchema<User> {

    @Override
    public User deserialize(byte[] message) {
        User u = JSONObject.parseObject(new String(message), User.class);

        return u;
    }

    @Override
    public byte[] serialize(User element){
        String dd = JSONObject.toJSONString(element);
        return dd.getBytes();
    }
}
