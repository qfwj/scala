package javatest.collection;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Description:
 *
 * @author: zhbo
 * @date 2020/10/9 9:59
 */
public class QueueTest {

    public static void main(String[] args) throws Exception {

        LinkedTransferQueue queue = new LinkedTransferQueue();

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("start");
                    System.out.println(queue.take());
                    System.out.println("end");

                } catch (Exception e) {

                }
            }
        }.start();
        Thread.sleep(5000);
        queue.put(12);


       // testSyncQueue();

        DelayQueue delayQueue = new DelayQueue(); //无界、阻塞队列、线程安全、基于lock condition await signal、不能为null
        PriorityQueue priorityQueue = new PriorityQueue(12); //无界  不能为null 基于数组 堆

        LinkedList linkedList = new LinkedList(); //双向队列 无界非线程安全

        /**
         * 使用SynchronousQueue：put时需要等待；使用park，被其他线程take消费后才会被unpark
         * 当队列为空时，take需要等待，有其他线程放入时unpark；类似于生产消费者的概念；peek为null
         */
         SynchronousQueue synchronousQueue = new SynchronousQueue();
        new Thread(()->{
            try {
                int i =0;
                synchronousQueue.put(12);
                System.out.println("hh");
            } catch (Exception e) {
            }
        }).start();
        Thread.sleep(5000);
        System.out.println("hh1");

        synchronousQueue.take();
       // Thread.sleep(5000);

        System.out.println("hh2");
        synchronousQueue.take();
        System.out.println("hh3");

        /**
         *  线程安全 无界 不能为null 基于CAS 基于head tail两个节点的cas分别进行
         *  避免CAS ABA问题，CAS时是将head item设置为null；存入的值不存在未null的情况CAS(null,)
         *  MS-queue算法，
         */
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        /**
         * 阻塞队列  线程安全 不能为null
         * add 满的时候 抛异常  remove
         * offer 满的时候返回 false  poll返回null
         * put 等待   take 等待
         * peek 返回队列首部
         */
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(12); //有界队列、线程安全、不能为null、数组实现,fair可选
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();//有界可选、线程安全、不能为null、数组实现,fair可选
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();//无界 线程安全  不能为null  数组 堆实现
    }


    public static void testSyncQueue() throws Exception {
        SynchronousQueue synchronousQueue = new SynchronousQueue(true);
        new Thread( ()-> {
            try {
                System.out.println("begin 1");
                synchronousQueue.put(12);
                System.out.println("end 1");
            } catch (Exception e ){
            }
        }).start();
        new Thread( ()-> {
            try {
                System.out.println("begin 4");
                synchronousQueue.put(12);
                System.out.println("end 4");
            } catch (Exception e ){
            }
        }).start();
        Thread.sleep(3000);
        System.out.println("2 end");
        new Thread( ()-> {
            try {
                System.out.println("begin 3");
                synchronousQueue.poll();
                System.out.println("end 3");
            } catch (Exception e ){

            }
        }).start();

    }


}


class DelayTest implements Delayed {
    long time = System.currentTimeMillis();
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(System.currentTimeMillis() - time, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}