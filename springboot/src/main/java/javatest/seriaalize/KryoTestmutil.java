package javatest.seriaalize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
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

public class KryoTestmutil {
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
        //write();
        read();




    }
    public static Object read() throws Exception {
        kryo.register(TestKyroObject22.class);
        kryo.register(TestKyroObject1.class);
        FileInputStream inputStream = new FileInputStream("kyro.txt");
        Input input1 = new Input(inputStream);
        TestKyroObject1 out1 = (TestKyroObject1) kryo.readObject(input1, TestKyroObject1.class);
        //TestKyroObject out1 = (TestKyroObject) kryo.readObject(input1, TestKyroObject.class);
        input1.close();
        //HashMap out =(HashMap) kryo.readClassAndObject(input);
        System.out.println(out1);
        return  out1;
    }

    public static void write() throws Exception {
        /**
         * 一定要加上 可以减少字节码的数量 包括内嵌的类
         */
        kryo.register(TestKyroObject22.class);
        kryo.register(TestKyroObject1.class);
        FileOutputStream outputStream1 = new FileOutputStream("kyro.txt");
        Output output1 = new Output(outputStream1);
        kryo.writeObject(output1, new TestKyroObject1());
        output1.close();
    }
}

class TestKyroObject1 {
    public int ii = 14;
    public int jj ;
    public Object jj12 ;
    public Object jj122 ;


    public HashMap map = new HashMap();

    public TestKyroObject22 tt = new TestKyroObject22();

    public TestKyroObject1() {
        map.put(12, 13);
    }
}


class TestKyroObject22 {
    public int ii = 14;

    public HashMap t2mp = new HashMap();

    public TestKyroObject22() {
        t2mp.put(12, 13);
    }
}