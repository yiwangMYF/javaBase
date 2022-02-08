package Java.day3_concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: CompletableFuture:JAVA异步编程
 *              1.实现了Future 和 CompletionStage接口
 *              2.
 *
 *
 * @Author myf
 * @CreateDate 2022/2/8 13:57
 * @Version 1.0
 **/
public class CompletableFutureTest {

    @Test
    public  void testSupplyAsync() throws ExecutionException, InterruptedException {
        /**
         *  supplyAsync 与runAsync 方法都是执行异步方法，但是supplyAsync有返回值；
         *  串行执行;
         *  不指定线程池时默认使用ForkJoinPool.commonPool();
         *  thenApply方法会将前面方法的返回值作为参数
         */
        CompletableFuture completableFuture =CompletableFuture.supplyAsync(()->{
            return "my";
        }).thenApply(result->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result+" name is";
        }).thenApply(result->result+" myf");

        /**
         * join和get方法都是获取异步执行结果，但是get方法会抛出checkedException，需要自己处理
         */
        //System.out.println(completableFuture.join());
        System.out.println("111");
        System.out.println(completableFuture.get());
        System.out.println("222");
    }

    @Test
    public void testRunAsync(){
        /**
         *  runAsync(Runnable task)
         *  runAsync(Runnable task,Executor executor)
         *  thenRunAsync方法串行执行
         *  exceptionally 用于处理异常，相当于try{}catch(){}中的catch代码块
         *  whenComplete 相当于finally代码块
         */
        CompletableFuture<Void> runAsync =
                CompletableFuture.runAsync(()-> System.out.println("hello"))
                .thenRunAsync(
                        ()->{
                            try {
                                TimeUnit.SECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("world");
                        }).exceptionally(e->{throw new RuntimeException("GGGGGG");})
                        .whenComplete((s,e)->{
                            System.out.println("s:"+s);
                            System.out.println("e:"+e);
                        });
        System.out.println(runAsync.join());
        System.out.println("====");
    }

    @Test
    public void testCombine(){
        /**
         * completable1.thenCombine(Completable completable2,BiFunction bifunction)
         *      -等待completable1和completable2执行完然后执行本方法中的代码，
         *      -bifunction中的参数为completable1和completable2
         *      -的返回值
         */
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("future1");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(8);
                System.out.println("future2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future2";
        });
        System.out.println("-----");
        CompletableFuture<String> combine = future1.thenCombine(future2, (a, b) -> {
            System.out.println("===================");
            System.out.println(a);
            System.out.println(b);
            System.out.println("===================");
            return "a:"+a+"b:"+b+"->combine";
        });
        System.out.println(combine.join());
    }


}
