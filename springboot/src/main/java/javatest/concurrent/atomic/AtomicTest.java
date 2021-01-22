package javatest.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * description:  存在ABA的问题
 * date: 2021/1/19 13:04 <br>
 * author: zhbo <br>
 */
public class AtomicTest {

   static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) {
        integer.incrementAndGet();

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.longValue();
        AtomicStampedReference stampedReference = new AtomicStampedReference(12,1);
        stampedReference.compareAndSet(12,13,stampedReference.getStamp(), stampedReference.getStamp() +1);
    }
}
