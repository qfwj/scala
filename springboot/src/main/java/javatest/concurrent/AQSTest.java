package javatest.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description: 测试线程状态
 * @author: zhbo
 * @date 2020/10/10 13:00
 */
public class AQSTest {

    public static void main(String[] args) throws Exception {

        Thread rr2 = new Thread(() -> {
            try {
                int i = 0;
                while (i > -1) {
                    LockSupport.park(12);
                    System.out.println("park1");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        rr2.start();
        LockSupport.unpark(rr2);
        LockSupport.unpark(rr2);
        System.out.println("unpark");
    }
}
