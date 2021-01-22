package javatest.designmodel;

/**
 * description:  https://segmentfault.com/a/1190000010755849
 * 饿汉模式：使用类的加载
 * 饱汉模式：双重检查
 * 枚举：
 * 静态内部类：
 * date: 2021/1/19 15:01 <br>
 * author: zhbo <br>
 */
public class Singleton {
    private static volatile Object singleton = null;

    public static Object getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null)
                    singleton = new Object();
            }
        }
        return singleton;

    }

}
