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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @Description: 测试兼容性问题https://blog.csdn.net/hchaoh/article/details/106626924
 * FieldSerializer:兼容性无法保证
 * VersionFieldSerializer
 *TaggedFieldSerializer
 * CompatibleFieldSerializer
 * BeanSerializer
 *
 * @author: zhbo
 * @date 2020/10/30 8:38
 */
public class TestKryochange {
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

   static CompatibleFieldSerializer compatibleFieldSerializer = new CompatibleFieldSerializer(kryo, TestKyroObjectc.class);

    /**
     * 测试1减少字段：
     * 测试二增加字段：
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

       write();
        read();

    }

    public static void write() throws Exception {
        kryo.register(TestKyroObject2c.class);
        kryo.register(TestKyroObjectc.class);
        FileOutputStream outputStream1 = new FileOutputStream("kyro.txt");
        Output output1 = new Output(outputStream1);
        kryo.writeObject(output1, new TestKyroObjectc(),compatibleFieldSerializer);
        output1.close();

    }




    public static void read() throws Exception {
        kryo.register(TestKyroObject2c.class);
        kryo.register(TestKyroObjectc.class);
        Input input1 = new Input(new FileInputStream("kyro.txt"));
        TestKyroObjectc out1 = (TestKyroObjectc) kryo.readObject(input1,TestKyroObjectc.class, compatibleFieldSerializer);
        //TestKyroObject out1 = (TestKyroObject) kryo.readObject(input1, TestKyroObject.class);
        input1.close();
        //HashMap out =(HashMap) kryo.readClassAndObject(input);
        System.out.println(out1);
    }



}

//@DefaultSerializer(CompatibleFieldSerializer.class)
class TestKyroObjectc {
    public Object iim;

   // public Object jj=14;


    public TestKyroObject2c tt = new TestKyroObject2c();

    public TestKyroObjectc() {

    }
}


class TestKyroObject2c {
    public int ii = 14;

    public HashMap t2mp = new HashMap();

    public TestKyroObject2c() {
        t2mp.put(12, 13);
    }
}