package javatest.testwaitnotify;

/**
 * @Description: TODO
 * https://www.jianshu.com/p/25e243850bd2?appinstall=0
 *
 * 1 注意生产者消费者的while死循环
 * 2 先线程状态测试
 * @author: zhbo
 * @date 2020/8/28 8:44
 */
public class WaitTest {

    private int limit;
    private volatile  int count;
    public synchronized void put() throws Exception {

        while(count > limit) {
            wait();
        }
        count ++;
        System.out.println(count);

        notify();

    }

    public synchronized int get() throws Exception {
        while (count == 0 ) {
            wait();
        }
        int num = --count;
        System.out.println(num);
        notify();
        return num;
    }


    public static void main(String[] args) throws Exception {
        Object oo = new Object();
        Thread rrr = new Thread(()->{
            try{
                synchronized (oo) {
                System.out.println("bbb");
                //Thread.sleep(5000);
                System.out.println("bbb1");

                oo.wait(9000);
                System.out.println("end");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(1222);
        });
        rrr.start();
        //  rrr.interrupt();
        System.out.println(rrr.getState());
/*        new Thread(()->{
            try{
                //synchronized (oo) {
                    System.out.println("bbbb2");
                    Thread.sleep(4000);
                   // oo.notify();
                    System.out.println("eddnd");
                //}
            } catch (Exception e) {

            }
        }).start();*/
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"C:\\Users\\nefu_\\Desktop\\test");
        //Thread.sleep(3000);
        // SpringApplication.run(JavatestApplication.class, args);
        System.out.println(rrr.getState());

        System.out.println("enddddd");
    }
}
