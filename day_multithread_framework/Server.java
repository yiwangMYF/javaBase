package Java.day_multithread_framework;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 多线程服务类，用于启动多线程
 * @Author myf
 * @CreateDate 2022/2/9 9:53
 * @Version 1.0
 **/
public class Server {

    /**
     * 阻塞队列，用于多线程存取数据
     */
    private  BlockingQueue queue;
    /**
     *
     */
    private CountDownLatch countDownLatch;

    /**
     * 多线程服务使用的线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 要处理的任务
     */
    private Set<Job> jobs;

    public Server(final ThreadPoolExecutor threadPoolExecutor, final Set<Job> jobs) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.jobs = jobs;
        this.queue=new ArrayBlockingQueue(jobs.size(),false,jobs);
    }

    /**
    * @Author myf
    * @Description //TODO 多线程服务启动方法
    * @Date 2022/2/9 10:06
    * @Param
    * @return
    */
    public void start() throws InterruptedException {
        //获取cpu核心数
        int cpus = Runtime.getRuntime().availableProcessors();
        /**
         * 设定开启的最小线程数，线程池的核心线程数的设置上，
         * 在cpu密集的任务时设置为cpus+1
         * IO密集的任务时设置为2*cpus+1
         */
        int minThreads=cpus+1;
        //获取线程池的核心线程数，线程数小于2*cpus+1时，将线程数提升为该值
        int threads=this.threadPoolExecutor.getCorePoolSize()<minThreads?minThreads:this.threadPoolExecutor.getCorePoolSize();

        this.countDownLatch=new CountDownLatch(threads);
        /**
         * 开启指定线程数的线程进行任务处理
         */
        for (int i = 0; i < threads; i++) {
            Task task = new Task(this.queue, this.countDownLatch);
            this.threadPoolExecutor.execute(task);
        }
        this.countDownLatch.await();

    }
}
