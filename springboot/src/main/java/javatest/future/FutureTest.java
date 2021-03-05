package javatest.future;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FutureTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.completedFuture(120);

       long start = System.currentTimeMillis();
        AtomicLong end = new AtomicLong(0);
       int i =1;
//       while (i++<  100*1000){
        future.thenAcceptAsync(f->  System.out.println(Thread.currentThread().getName() +": 111" )).whenCompleteAsync((f, t)->{
                end.set( System.currentTimeMillis());
           System.out.println(Thread.currentThread().getName() +": num : " );}).get();
      // };

        System.out.println(end.get()- start);
        System.out.println(end.get()- start);

//        comp(12);
//        System.out.println(12121212);
//        Thread.sleep(12000);
//        List<Integer> list = new ArrayList(){{add(13);add(13);add(13);}};
//        list.parallelStream().forEach(f->comp(f));





//        System.out.println("0" + Thread.currentThread().getName());
//        CompletableFuture<Integer> future = CompletableFuture.completedFuture(12);
//
//        CompletableFuture future2 = future.thenAcceptAsync(f -> comp(), executorService);
//        future2.get();




//        CompletableFuture future1 = future.thenComposeAsync(f -> CompletableFuture.completedFuture(11).thenAccept(m -> {
//                    int kkk = f + m;
//            System.out.println("0: " +Thread.currentThread());
//                    return;
//                }
//        ), executorService);
//        future1.get();
//
//
//        CompletableFuture futureEnd = future.thenAcceptAsync(f -> {
//            System.out.println("1:" + Thread.currentThread().getName());
//            System.out.println(f);
//        });
       /* future1.thenCombineAsync(future2,(f1,f2) -> f1/f2).whenComplete((f1,f2)-> {
            System.out.println(f1 + "哈哈"+ f2);
        });*/
//
//        futureEnd.whenCompleteAsync((f, throwable) -> {
//            try {
//                Thread.sleep(3000);
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            System.out.println("end:" + Thread.currentThread().getName());
//            System.out.println("2:" + Thread.currentThread().getName());
//            System.out.println(22);
//        }).get();

    }


    public static void comp(int f1) {
        CompletableFuture<Integer> future = CompletableFuture.completedFuture(120);
        System.out.println(Thread.currentThread().getName() +": init " );

        CompletableFuture fff = future.thenComposeAsync(m -> CompletableFuture.completedFuture(12).thenAccept(f -> {
                    System.out.println(Thread.currentThread().getName() +": 111" );
                }
        ));
        CompletableFuture fff2 = future.thenComposeAsync(m ->    {
                    System.out.println(Thread.currentThread().getName() + ": " + " 222 ");
                   return CompletableFuture.completedFuture(12).thenAcceptAsync(f -> {
                                System.out.println(Thread.currentThread().getName() +": 333 " );
                            }
                    );
                }

            );
//        try {
//            fff2.get();
//
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//        }
        System.out.println( Thread.currentThread().getName() +": 444");
        CompletableFuture.allOf(fff, fff2).whenCompleteAsync((m, f) -> {
            try{
                System.out.println( Thread.currentThread().getName() +": 555");
                Thread.sleep(3000);
                System.out.println("complete");
            } catch (Exception e) {

            }

        });

    }
}
