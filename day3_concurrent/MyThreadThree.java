package Java.day3_concurrent;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @Description 测试线程池、callable、future和CountDownLatch的使用
 *              线程池原理：当任务提交到线程池后，先判断核心线程是否足够如果足够则创建核心线程进行处理，
 *                          如果核心线程不足，则判断阻塞队列是否满了，若没满则丢入阻塞队列，
 *                          阻塞队列满了则判断最大线程数是否达到了，若没达到则创建线程处理，若已达到最大线程数
 *                          则按照预定的拒绝策略处理
 * @Author myf
 * @CreateDate 2021/12/2 10:27
 * @Version 1.0
 **/
public class MyThreadThree implements Callable<ResponseResult> {

    private CountDownLatch countDownLatch;

    public MyThreadThree() {
    }

    public MyThreadThree(final CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public ResponseResult call() throws Exception {

        ResponseResult<String> responseResult = new ResponseResult<>();
        responseResult.setCode(1);
        responseResult.setMsg("success");
        responseResult.setData(new Date().toString());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+":"+responseResult);

        if (countDownLatch!=null) {
            countDownLatch.countDown();
        }
        return responseResult;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("my-thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,10, 1000,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(2),nameThreadFactory);
        long start=System.currentTimeMillis();
        //CountDownLatch countDownLatch= new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            Future<ResponseResult> future = threadPoolExecutor.submit(new MyThreadThree());
            if (future.isDone()) {
                //get方法会阻塞当前线程知道获取到返回结果
                ResponseResult responseResult = future.get();
                System.out.println(responseResult);
            }

        }
//        threadPoolExecutor.submit(new MyThreadThree(countDownLatch));
//        countDownLatch.await();
        System.out.println("=======================");

    }
}

class ResponseResult<T> implements Serializable{

    private static final long serialVersionUID = 0L;

    private int code;

    private String msg;

    private T data;

    public ResponseResult() {
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
