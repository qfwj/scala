package javatest.concurrent;

import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.*;
import java.util.concurrent.*;

/**
 * description:
 * date: 2021/1/22 9:22
 * author: zhbo
 */
public class ConcurrentCollectionTest {

    public static void main(String[] args) {

    }

    public static void testQueue() {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue(); //无界不阻塞 使用CAS
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue(); //无界 对读阻塞 使用CAS
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(12); //有界或者无界  读写阻塞  使用lock来实现
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(12);//有界 读写阻塞 使用lock来实现
        SynchronousQueue synchronousQueue = new SynchronousQueue(); // 无值 读写阻塞 数据传递
        DelayQueue delayQueue = new DelayQueue(); //延迟队列 阻塞  使用堆来实现
        PriorityQueue priorityQueue = new PriorityQueue(); //无界 不阻塞 使用堆来实现

    }


    public static void testMap() {
        HashMap hashMap = new HashMap();  // 数组 链表 红黑树 线程不安全
        Hashtable hashtable = new Hashtable(); //线程安全 线程安全  key value不为null
        TreeMap treeMap = new TreeMap(); //优先级
        LinkedHashMap linkedHashMap = new LinkedHashMap(); //插入顺序 实现LRU
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(); //数组节点加锁  所粒度变小
        ConcurrentReferenceHashMap concurrentReferenceHashMap = new ConcurrentReferenceHashMap();
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap(); //跳跃表CAS

    }

    public static void testList() {

    }


    public static void testSet() {

    }
}
