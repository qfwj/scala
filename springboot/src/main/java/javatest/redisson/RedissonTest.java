package javatest.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonBlockingQueue;
import org.redisson.RedissonPriorityBlockingQueue;


import java.util.Comparator;
import java.util.function.Function;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/6/8 10:17
 */


public class RedissonTest {
    private static Redisson redisson = RedissonManager.getRedisson();

    public static void main(String[] args) throws Exception {


        RedissonPriorityBlockingQueue qq = (RedissonPriorityBlockingQueue)redisson.getPriorityBlockingQueue("testqueue");
        qq.trySetComparator(new MyComparator());
        qq.put(12);
        qq.addAfter(12,22);
        qq.addBefore(12,11);

        /*new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("eeeeee");
                }
                qq.add("gg");

            }
        }).start();*/
        Object oo = qq.take();



        String key = "test123";
        //加锁
        //执行具体业务逻辑
        new TaskTest((String lockKey)->{
            while(!DistributedRedisLock.acquire(key)){

            }

            return lockKey;
        });
        //释放锁
        DistributedRedisLock.release(key);

    }


}

class TaskTest implements Runnable {
    Function<String, String> f1;
    TaskTest(Function<String, String> ff) {
        this.f1 =ff;
    }
    @Override
    public void run() {

    }
}