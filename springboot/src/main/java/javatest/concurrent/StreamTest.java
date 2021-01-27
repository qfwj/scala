package javatest.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * description:  <br>
 * date: 2021/1/19 8:52 <br>
 * author: zhbo <br>
 */
public class StreamTest {
    public static void main(String[] args) {
        System.out.println( IntStream.range(2,10).distinct().max());
        List<Integer> ll = new ArrayList<>();
        ll.add(1);  ll.add(2);  ll.add(3); // ll.add(4);
        Spliterator spliterator1 =  ll.spliterator();

       // spliterator1.forEachRemaining(f-> System.out.println(f));
        Spliterator s2 = spliterator1.trySplit();

   //     s2.forEachRemaining(f-> System.out.println(f));
    //    spliterator1.forEachRemaining(f-> System.out.println(f));

        System.out.println(spliterator1.estimateSize());
        System.out.println(s2.estimateSize());

        for(int i=0; i< 20;i++){
            ll.add(i);
        }
      //  ll.stream().map(f->f++).forEach(f-> System.out.println(f));
        /**
         * 并行流基于ForkJoinTask来实现
         */
        ll.parallelStream().forEach(f-> System.out.println(Thread.currentThread() + ":"+f));
    }
}
