package javatest.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;

/**
 * description:  <br>
 * date: 2020/11/27 17:32 <br>
 * author: zhbo <br>
 */
public class ExcutorTest {

    public static void main(String[] args) throws Exception {

//        fixed().execute(()-> {
//            System.out.println(12);
//        });
        ExecutorService cache = cache();
        cache.submit(()->{
            System.out.println(12);
        });
        cache.execute(()->{
            System.out.println(12);
        });
//        while (true) {
//            cache.execute(()->{ try {
//                System.out.println(Thread.currentThread());
//                Thread.sleep(3000);
//
//            } catch (Exception e) {
//
//            }
//            });
//            Thread.sleep(1000);
//        }
    }
     //   ScheduledExecutorService schedule = schedule();


    public static ExecutorService fixed () {
       return Executors.newFixedThreadPool(12);
    }
    public static ExecutorService forkJoin () {
       return new ForkJoinPool(12);
    }
    public static ScheduledExecutorService schedule () {
       return Executors.newScheduledThreadPool(12);
    }
    public static ExecutorService cache () {
        /**
         * 使用SynchronousQueue：当队列中有元素时，put时需要等待；
         * 当队列为空时，remove需要等待，类似于生产消费者的概念；不支持peek
         */

       return Executors.newCachedThreadPool();
    }
    public static ExecutorService single () {
       return Executors.newSingleThreadExecutor();
    }
    public static ScheduledExecutorService singleThreadScheduled () {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
