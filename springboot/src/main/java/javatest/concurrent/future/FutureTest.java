package javatest.concurrent.future;

import java.util.concurrent.FutureTask;

/**
 * description:
 * date: 2021/1/27 17:13
 * author: zhbo
 */
public class FutureTest {

    public static void main(String[] args) throws Exception {
        testInter();
        System.out.println(12);
    }

    public static void testInter() throws Exception {
        Thread dd = new Thread(() -> {
            try {
                while (true) {
                    System.out.println(11111);
                    //  Thread.sleep(30000);
                    System.out.println(Thread.currentThread().isInterrupted());
                    System.out.println(22222);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        dd.start();

        Thread.sleep(3000);
        dd.interrupt();
    }


    public static void testFutureTask() {
        try {
            FutureTask futureTask = new FutureTask(() -> {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        System.out.println("t0");
                    } catch (Exception e) {
                        System.out.println("inner" + e);
                    }
                }

            }, 12);
            new Thread(futureTask).start();
            Thread.sleep(3000);
            futureTask.cancel(true);
            futureTask.get();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("end");
    }
}

