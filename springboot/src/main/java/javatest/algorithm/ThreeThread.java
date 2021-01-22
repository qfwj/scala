package javatest.algorithm;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: KMP算法实现
 * @author: zhbo
 * @date 2020/7/17 14:37
 */
public class ThreeThread {


    public static void main(String[] args) throws Exception {
        synchronizedTest();

    }


    public static void lock() throws Exception {
        AtomicInteger mark = new AtomicInteger(0);
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        ReentrantLock lock3 = new ReentrantLock();
        Condition con1 = lock1.newCondition();
        Condition con2 = lock2.newCondition();
        Condition con3 = lock3.newCondition();
        new Thread(() -> {
            while (true) {
                lock1.lock();
                try {
                    con1.await();
                    Thread.sleep(1000);
                    System.out.println("t1:" + mark.incrementAndGet());
                    lock2.lock();
                    con2.signal();
                    lock2.unlock();
                } catch (Exception e) {

                }
                lock1.unlock();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                lock2.lock();
                try {
                    con2.await();
                    Thread.sleep(1000);
                    System.out.println("t2:" + mark.incrementAndGet());
                    lock3.lock();
                    con3.signal();
                    lock3.unlock();
                } catch (Exception e) {

                }
                lock2.unlock();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                lock3.lock();
                try {
                    con3.await();
                    Thread.sleep(1000);
                    System.out.println("t3:" + mark.incrementAndGet());
                    lock1.lock();
                    con1.signal();
                    lock1.unlock();
                } catch (Exception e) {

                }
                lock3.unlock();
            }
        }).start();
        Thread.sleep(1000);
        lock1.lock();
        con1.signal();
        lock1.unlock();
    }


    public static void synchronizedTest() throws Exception {
        AtomicInteger mark = new AtomicInteger(0);
        Object lock1 = new Object();
        Object lock2 = new Object();
        Object lock3 = new Object();
        new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    try {
                        lock1.wait();
                        Thread.sleep(1000);
                        System.out.println("t1:" + mark.incrementAndGet());
                        synchronized (lock2) {
                            lock2.notify();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                synchronized (lock2) {
                    try {
                        lock2.wait();
                        Thread.sleep(1000);
                        System.out.println("t2:" + mark.incrementAndGet());
                        synchronized (lock3) {
                            lock3.notify();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (lock3) {
                    try {
                        lock3.wait();
                        Thread.sleep(1000);
                        System.out.println("t3:" + mark.incrementAndGet());
                        synchronized (lock1) {
                            lock1.notify();
                        }

                    } catch (Exception e) {

                    }
                }
            }
        }).start();
        synchronized (lock1) {
            Thread.sleep(1000);
            lock1.notify();
        }

    }


    public static void queue() throws Exception {
        SynchronousQueue<AtomicInteger> queue1 = new SynchronousQueue();
        SynchronousQueue<AtomicInteger> queue2 = new SynchronousQueue();
        SynchronousQueue<AtomicInteger> queue3 = new SynchronousQueue();
        AtomicInteger mark = new AtomicInteger(0);
        new Thread(() -> {
            while (true)
                try {
                    Thread.sleep(1000);
                    System.out.println("t1:" + queue3.take().get());
                    mark.incrementAndGet();
                    queue1.put(mark);

                } catch (Exception e) {

                }
        }).start();
        new Thread(() -> {
            while (true)
                try {
                    Thread.sleep(1000);
                    System.out.println("t2:" + queue1.take().get());
                    mark.incrementAndGet();
                    queue2.put(mark);

                } catch (Exception e) {

                }
        }).start();
        new Thread(() -> {
            while (true)
                try {
                    Thread.sleep(1000);
                    System.out.println("t3:" + queue2.take().get());
                    mark.incrementAndGet();
                    queue3.put(mark);

                } catch (Exception e) {

                }
        }).start();

        queue3.put(mark);
    }


}
