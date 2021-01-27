package javatest.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.FutureTask;

/**
 * https://www.jianshu.com/p/8d7e3cc892cf
 * <p>
 * 核心思想：每个线程 一个等待队列  work-stealing
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinTask forkJoinTask = ForkJoinTask.adapt(() -> {
            try {
                Thread.sleep(1111);
                return 12;
            } catch (Exception e) {

            }
            return 11;

        });

        forkJoinTask.join();
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        ForkJoinTask<Integer> end1 = forkJoinPool.submit(() -> 12);
        List<Callable<Integer>> runnables = new ArrayList<>();
        runnables.add(() -> 12);
        runnables.add(() -> 13);
        runnables.add(() -> 14);
        runnables.parallelStream().map(f -> {
            try {
                return f.call();
            } catch (Exception e) {
            }
            return 0;
        }).reduce((f, g) -> {
            try {
                return f + g;
            } catch (Exception e) {
            }
            return 12;
        });


    }
}
