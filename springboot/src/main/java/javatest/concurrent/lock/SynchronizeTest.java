package javatest.concurrent.lock;

/**
 * description:  <br>
 * date: 2021/1/13 10:07 <br>
 * author: zhbo <br>
 */
public class SynchronizeTest {

    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        synchronized (lock) {
            new Thread(()->{
                System.out.println("in 1");
                synchronized (lock){
                    System.out.println("in 2 ");
                    lock.notify();
                }
            }) .start();
            System.out.println("wait1");
            Thread.sleep(2222);
            lock.wait();
            System.out.println("wait2");
            Thread.sleep(2222);
            System.out.println("end");
        }

    }
}
