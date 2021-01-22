package javatest.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * description:  测试CyclicBarrier；是用lock condition来完成等待、通知；
 * count计数；计数为0之后，进行重置，进入下一次循环
 *
 * date: 2020/11/27 11:18 <br>
 * author: zhbo <br>
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) throws Exception {

        new Thread(()-> {
            try {
                System.out.println("1 in");
                cyclicBarrier.await();
                System.out.println("1 out");
            } catch (Exception e){
            }
        }).start();
        new Thread(()-> {
            try {
                System.out.println("2 in");
                cyclicBarrier.await();
                System.out.println("2 out");
            } catch (Exception e){
            }
        }).start();
        System.out.println("3 in");
        Thread.sleep(3000);
        cyclicBarrier.await();
        System.out.println("3 end");
        Thread.sleep(3000);
    }
}
