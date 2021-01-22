package javatest.seriaalize;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.javakaffee.kryoserializers.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationHandler;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/10/30 8:38
 */
public class TestKryo {
    static KryoPool pool = new KryoPool.Builder(new KryoFactory() {
        @Override
        public Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
            kryo.register(GregorianCalendar.class, new GregorianCalendarSerializer());
            kryo.register(InvocationHandler.class, new JdkProxySerializer());
            kryo.register(BigDecimal.class, new DefaultSerializers.BigDecimalSerializer());
            kryo.register(BigInteger.class, new DefaultSerializers.BigIntegerSerializer());
            kryo.register(Pattern.class, new RegexSerializer());
            kryo.register(BitSet.class, new BitSetSerializer());
            kryo.register(URI.class, new URISerializer());
            kryo.register(UUID.class, new UUIDSerializer());
            UnmodifiableCollectionsSerializer.registerSerializers(kryo);
            SynchronizedCollectionsSerializer.registerSerializers(kryo);
            kryo.register(HashMap.class);
            kryo.register(ArrayList.class);
            kryo.register(LinkedList.class);
            kryo.register(HashSet.class);
            kryo.register(TreeSet.class);
            kryo.register(Hashtable.class);
            kryo.register(Date.class);
            kryo.register(Calendar.class);
            kryo.register(ConcurrentHashMap.class);
            kryo.register(SimpleDateFormat.class);
            kryo.register(GregorianCalendar.class);
            kryo.register(Vector.class);
            kryo.register(BitSet.class);
            kryo.register(StringBuffer.class);
            kryo.register(String.class);
            kryo.register(StringBuilder.class);
            kryo.register(Object.class);
            kryo.register(Object[].class);
            kryo.register(String[].class);
            kryo.register(byte[].class);
            kryo.register(char[].class);
            kryo.register(int[].class);
            kryo.register(float[].class);
            kryo.register(double[].class);
            return kryo;
        }
    }).build();


    static Kryo kryo = pool.borrow();

    public static void main(String[] args) throws Exception {


        /*消耗字节的量大*/
        ObjectMapper om = new ObjectMapper();
        byte[] re0=  om.writeValueAsBytes(new TestKyroObject());
        TestKyroObject test  = om.readValue(re0, TestKyroObject.class);

        /**
         * 一定要加上 可以减少字节码的数量 包括内嵌的类
         */
        kryo.register(TestKyroObject2.class);
        kryo.register(TestKyroObject.class);
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        Output output1 = new Output(outputStream1);
        kryo.writeObject(output1, new TestKyroObject());
        output1.close();
        byte[] re1 = outputStream1.toByteArray();

        Input input1 = new Input();
        input1.setBuffer(re1);
        TestKyroObject out1 = (TestKyroObject) kryo.readObject(input1, TestKyroObject.class);
        //TestKyroObject out1 = (TestKyroObject) kryo.readObject(input1, TestKyroObject.class);
        input1.close();
        //HashMap out =(HashMap) kryo.readClassAndObject(input);
        System.out.println(out1);



    }
}

@DefaultSerializer(CompatibleFieldSerializer.class)
class TestKyroObject {
    public int ii = 14;


    public HashMap map = new HashMap();

    public TestKyroObject2 tt = new TestKyroObject2();

    public TestKyroObject() {
        map.put(12, 13);
    }
}


class TestKyroObject2 {
    public int ii = 14;

    public HashMap t2mp = new HashMap();

    public TestKyroObject2() {
        t2mp.put(12, 13);
    }
}