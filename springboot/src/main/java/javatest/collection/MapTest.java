package javatest.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/10/9 9:59
 */
public class MapTest {

    public static void main(String[] args) {


        HashMap hashMap = new HashMap(); //非线程安全、可以为null
        LinkedHashMap linkedHashMap = new LinkedHashMap();// afterNodeInsertion 实现LRU
        Hashtable hashtable = new Hashtable(); //线程安全 不能为null
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(); //afterNodeInsertion 实现LRU
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap(); //key value不能为null
        concurrentSkipListMap.put("sds",12);
        concurrentSkipListMap.put("mm",12);
        TreeMap map = new TreeMap(); //红黑树

        HashSet hashSet = new HashSet();
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();//按插入顺序
        TreeSet treeSet = new TreeSet(); //按小到大排序
        linkedHashSet.add(3);
        linkedHashSet.add(34);
        linkedHashSet.add(2);
        linkedHashSet.stream().forEach(f-> System.out.println(f));


        Stack stack  = new Stack(); //Vector List AbstractList
        stack.push(12);
        stack.pop();



    }
}
