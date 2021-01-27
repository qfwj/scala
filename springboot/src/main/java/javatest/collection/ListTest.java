package javatest.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 */
public class ListTest {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(12);
        Iterator<Integer> d = arrayList.iterator();
        while (d.hasNext()) {
            d.next();
            //d.remove();
        }

        arrayList.remove(12);
        LinkedList linkedList = new LinkedList();
    }
}
