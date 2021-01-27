package javatest.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonBlockingQueue;
import org.redisson.config.Config;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/6/8 10:08
 */
public class RedissonManager {
    private static Config config = new Config();
    //声明redisso对象
    private static Redisson redisson = null;
    //实例化redisson
    static{
        config.useSingleServer().setAddress("redis://10.50.162.243:6379").setPassword("XVF6n9xB5L5uN7WxWOTW").setDatabase(3);
        //得到redisson对象
        redisson = (Redisson) Redisson.create(config);

    }

    //获取redisson对象的方法
    public static Redisson getRedisson(){
        return redisson;
    }
}
