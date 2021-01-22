package javatest.reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.Cleaner;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/8/11 8:53
 */
public class TestReference {
    public   static ReferenceQueue queue = new ReferenceQueue();

    public static WeakReference re =  new WeakReference(new TestRe(), queue) ;

    public static Object re1;
    public static void main(String[] args) throws Exception {


        /*强引用*/
        //Object object=new Object();
       // Object[] objArr=new Object[Integer.MAX_VALUE];


        System.out.println(re.get());
        System.out.println(queue.poll());
        System.out.println(re1);

        System.gc();
        Thread.sleep(3000);
        System.out.println(re.get());
        System.out.println(queue.poll());
        System.out.println(re1);


        /*finalize之后测试*/
        System.out.println(re.get());

        System.gc();
        Thread.sleep(3000);
        System.out.println(re1);
        System.out.println(queue.poll());

    }
}

class TestRe  {
//    @Override
//    protected void finalize() throws Throwable {
//       TestReference.re1 = this;
//        TestReference.re  = new WeakReference(new Object(), TestReference.queue) ;
//
//
//        System.out.println("调用了final");
//        //super.finalize();
//    }
}