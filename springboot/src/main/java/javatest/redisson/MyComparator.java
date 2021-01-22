package javatest.redisson;

import java.util.Comparator;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/6/16 16:00
 */
public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return (int)o1 - (int)o2;
    }
}