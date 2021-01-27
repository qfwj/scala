package javatest.unsafetest.bloomtest;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/11/20 9:00
 */
public class bloomtest {

    public static void main(String[] args) {
       BloomFilter<Integer> bloomFilter=  BloomFilter.create(Funnels.integerFunnel(),20,0.1);
        bloomFilter.put(12);
        System.out.println(bloomFilter.mightContain(12));
    }
}
