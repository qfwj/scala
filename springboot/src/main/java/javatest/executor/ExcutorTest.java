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
    /**
     * 线程池执行过程：
     * 当worker数小于corepool size时直接创建新的corepool size
     * 执行完成后，通过queue.take获取新的task；
     * 如果此时没有task要执行，则worker数量小于corepool size就take，大于就超时take
     *  timed ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : workQueue.take();
     */
    public static void main(String[] args) throws Exception {
        ForkJoinPool.commonPool().submit(()->{});

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
