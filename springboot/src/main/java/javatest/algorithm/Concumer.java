package javatest.algorithm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:  <br>
 * date: 2021/1/13 9:05 <br>
 * author: zhbo <br>
 */
public class Concumer {

    public static ReentrantLock lock = new ReentrantLock();
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    public static Condition c = lock.newCondition();
    public static Condition c1 = lock1.newCondition();

    public static void main(String[] args)  throws Exception {
        AtomicInteger mark = new AtomicInteger(0);

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    c.await();
                    Thread.sleep(1000);
                    System.out.println("t1:" + mark.incrementAndGet());
                    lock1.lock();
                    c1.signal();
                    lock1.unlock();
                    lock.unlock();
                } catch (Exception e) {

                }
            }

        }).start();
        new Thread(() -> {
            while (true)
                try {
                    lock1.lock();
                    c1.await();
                    Thread.sleep(1000);
                    System.out.println("t2:" + mark.incrementAndGet());
                    lock.lock();
                    c.signal();
                    lock.unlock();
                    lock1.unlock();
                } catch (Exception e) {

                }
        }).start();
        Thread.sleep(1000);
        lock.lock();
        c.signal();
        lock.unlock();
    }
}
