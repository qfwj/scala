package javatest.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:  <br>
 * date: 2021/1/13 10:07 <br>
 * author: zhbo <br>
 */
public class LockTest {

    static ReentrantLock lock = new ReentrantLock();
    static Condition con1 = lock.newCondition();
    static Condition con2 = lock.newCondition();
    public static void main(String[] args) throws Exception {
        lock.lock();
        con1.signal();


        new Thread(()-> {
        //    while (true) {
              try {
                  lock.lock();
                  //Thread.sleep(3000);
                  System.out.println("entr1");
                  con1.await(); //释放锁的占用  不再占有锁 将自己加到wait队列中
                  System.out.println("await1 ");

                //  con2.signal(); //signal 不释放锁
                  lock.unlock();
              } catch (Exception e ){

              }
            //}
        }).start();

        Thread.sleep(1111);
        new Thread(()-> {
         //   while (true) {
                try {
                    lock.lock();
                    System.out.println("entr2");
                    con1.signal();
                    con2.await();
                    Thread.sleep(3000);
                    System.out.println("singnal end ");
                    lock.unlock();
                    System.out.println("singnal2 end ");
                } catch (Exception e ){

                }
           // }
        }).start();
        Thread.sleep(10000);

        lock.unlock();

        lock.lock();
        System.out.println("dsdsd");
        lock.unlock();
    }
}
