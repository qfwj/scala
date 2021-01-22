package javatest.seriaalize;

/**
 * @Description: 测试序列化反序列化
 * @author: zhbo
 * @date 2020/10/28 9:30
 */
public class testserilize {

    public static void main(String[] args)  throws Exception {








//
//      new TestSer().setAge(15);
//        long ll1 = System.currentTimeMillis();
//        Output output = new Output(new FileOutputStream("kryo.txt"));
//        Kryo kryo = new Kryo();
//        kryo.register(TestSer.class);
//        kryo.writeObject(output,  new TestSer().setAge(15));
//
//
//        System.out.println(System.currentTimeMillis()- ll1 +"kryo");
//
//        long ll0 = System.currentTimeMillis();
//        Input input = new Input(new FileInputStream("kryo.txt"), 10000000);
//        Kryo kryo1 = new Kryo();
//        kryo1.register(TestSer.class);
//
//        TestSer map2 = (TestSer)kryo1.readClassAndObject(input);
//
//        System.out.println(System.currentTimeMillis()- ll0 +"kryo read");
//
//
//
//
//
//        HashMap<Integer,Integer> map = new HashMap();
//        for(int i =0; i++< 100;){
//            map.put(i,i);
//        }
//
//        new Thread(()->{
//
//            try {
//                long ll = System.currentTimeMillis();
//                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("jdk.txt")));
//                outputStream.writeObject(map);
//                outputStream.close();
//
//                System.out.println(System.currentTimeMillis()- ll +"jdk");
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//
//
//        }) .start();
//
//
//        new Thread(()->{
//
//            try {
//                long ll = System.currentTimeMillis();
//                Output output1 = new Output(new FileOutputStream("kryo.txt"));
//                Kryo kryo11 = new Kryo();
//                kryo11.writeObject(output, map);
//
//
//                System.out.println(System.currentTimeMillis()- ll +"kryo");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }) .start();






    }
}


class TestSer{
    public int age = 13;

    public TestSer setAge(int age) {
        this.age = age;
        return this;
    }
}