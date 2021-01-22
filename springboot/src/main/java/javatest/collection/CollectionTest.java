package javatest.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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



        LinkedHashSet set = new LinkedHashSet();
        set.add(12);
    }
}
