package javatest.unsafetest;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/8/5 14:00
 */
public class UnsafeTEst {

    public static void main(String[] args) {
        AtomicReferenceArray array = new AtomicReferenceArray<Integer>(2);
        //array.lazySet();
    }
}
