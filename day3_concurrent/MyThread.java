package Java.day3_concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: backEnd
 * @description: 自定义线程类
 * @author: myf
 * @create: 2020/03/16 11:11
 **/
public class MyThread extends Thread {
    private final static byte[] b= new byte[10*1024*1024];
    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    private static int anInt=0;
    @Override
    public void run() {
        super.run();
        atomicInteger.incrementAndGet();
        byte[] bytes=new byte[5*1024*1024];
        System.out.println("5M memory has been allocated");
        System.out.println("JVM堆最大内存："+Runtime.getRuntime().maxMemory()/(1024*1024)+"M");
        System.out.println("JVM堆可用内存："+Runtime.getRuntime().freeMemory()/(1024*1024)+"M");
        System.out.println("JVM堆已经使用的内存："+Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
        System.out.println("值："+atomicInteger);
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.lock();
        System.out.println("值2："+(++anInt));
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
    }

    public static void main(String[] args) throws Exception{
        ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(new MyThreadTwo(),1000,1000, TimeUnit.MILLISECONDS);
    }

}
