package javatest.threadlocal;


import javatest.reference.TestReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/12/4 16:29
 */
public class ThreadLocalTest {


    public static void main(String[] args) throws Exception {


        /**
         *
         * 实际上就是每个thread的threadLocals:ThreadLocalMap会存放
         * 对应Entry(key, value)（ThreadLocal和value）信息 Entry extends WeakReference
         *
         * 存在table数组中，数组的每个index对应的是ThreadLocal的threadLocalHashCode&table.length
         *
         * 外面引用threadlocal被置为null后，调用System.gc可以导致ThreadLocalMap中entry的key被回收掉；但是entry还在
         */




//        new Thread(()->{
//            ThreadLocal threadLocal = new ThreadLocal();
//            threadLocal.set(new TestRe().setJ(12));
//            System.out.println( threadLocal.get());
//        }).start();

        TestThreadLocal threadLocal = new TestThreadLocal();
        threadLocal.set(new TestRe().setJ(12));
        System.out.println( threadLocal.get());
        threadLocal = null;
      ThreadLocal threadLocal1 = new ThreadLocal();
        threadLocal1.remove();
//        threadLocal1.set(new TestRe().setJ(13));
        System.gc();
        Thread.sleep(3000);

        System.gc();
        Thread.sleep(30000);
    }
}

class TestRe{
    int j;

    public TestRe setJ(int j) {
        this.j = j;
        return this;
    }

    @Override
    protected void finalize() throws Throwable {
        TestReference.re1 = this;
        TestReference.re  = new WeakReference(new Object(), TestReference.queue) ;


        System.out.println("调用了final");
        //super.finalize();
    }
}

class TestThreadLocal extends ThreadLocal {


    @Override
    protected void finalize() throws Throwable {
        System.out.println("TestThreadLocal");
    }
}