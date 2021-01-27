package javatest.collection;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/10/9 9:59
 */
public class CollectionTest {

    public static void main(String[] args) {


        /*测试*/
        List ss = new ArrayList(12);
        ss.add("12");
        Iterator it = ss.iterator();
        it.next();
        it.remove();
        System.out.println(12);

        Vector vector = new Vector();

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        LinkedList linkedList = new LinkedList();

        LinkedHashSet set = new LinkedHashSet();
        set.add(12);
    }
}
