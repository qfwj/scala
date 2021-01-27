package javatest.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description: 测试线程状态
 * @author: zhbo
 * @date 2020/10/10 13:00
 */
public class ConcurrentTest {


   public static Semaphore semaphore = new Semaphore(3);

   public static synchronized  void test() throws Exception {
       Thread.sleep(12222);
   }

    public static void main(String[] args) {

        Thread rr2 =  new Thread(()->{
            try {
                    test();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        rr2.start();
        Thread rr3 =  new Thread(()->{
            try {
                test();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        rr3.start();
        System.out.println(rr3.getState()); //BLOCKED



        Object oo =new Object();
        Thread rr1 =  new Thread(()->{
            try {
                synchronized (oo) {
                    oo.wait(1111);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(rr1.getState()); //NEW

        rr1.start();
        System.out.println(rr1.getState()); //TIMED_WAITING

       Thread rr =  new Thread(()->{
            try {
                Thread.sleep(11111);
            } catch (Exception e) {

            }
        });
       rr.start();
        System.out.println(rr.getState()); //TIMED_WAITING




        for (int i = 0; i< 10000;i++) {
            new Thread(()->{
                try {

                    semaphore.acquire(1);
                    System.out.println("sleep start!");
                    Thread.sleep(5000);
                    System.out.println("sleep end!");
                    semaphore.release(1);
                    System.out.println("wancheng");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
