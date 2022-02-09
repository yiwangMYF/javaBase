package Java.day_multithread_framework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author myf
 * @CreateDate 2022/2/9 10:29
 * @Version 1.0
 **/
public class Task implements Runnable {
    /**
     * 阻塞队列
     */
    private final BlockingQueue queue;

    private final CountDownLatch countDownLatch;

    public Task(final BlockingQueue queue, final CountDownLatch countDownLatch) {
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while(!queue.isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //取出任务
            Job job = (Job) queue.poll();
            System.out.println("取出的任务："+job);
            //防止队列只剩一个任务时出现NPE
            if (job!=null) {
                job.Process();
            }
        }
        this.countDownLatch.countDown();

    }
}
