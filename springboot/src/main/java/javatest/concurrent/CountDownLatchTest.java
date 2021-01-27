package javatest.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * description:
 * 是用共享锁，state初始值为count，每次countDown就减一
 * await去获取锁，状态不等于0就获取失败，进入等待队列；
 *
 *
 * date: 2020/11/27 12:14 <br>
 * author: zhbo <br>
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args)  throws Exception {
        new Thread(()-> {
            try {
                countDownLatch.countDown();
                System.out.println("1 end");

            } catch (Exception e){
            }
        }).start();
        new Thread(()-> {
            try {
                Thread.sleep(3000);
                countDownLatch.countDown();
                System.out.println("2 end");
            } catch (Exception e){
            }
        }).start();

        new Thread(()-> {
            try {
                System.out.println("4  in");
                countDownLatch.await();
                System.out.println("4 get");
            } catch (Exception e){
            }
        }).start();
        System.out.println("3 in");
        countDownLatch.await();
        System.out.println("3 get");
        Thread.sleep(3000);

    }
}
