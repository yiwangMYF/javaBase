package Java.day_multithread_framework;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author myf
 * @CreateDate 2022/2/9 10:51
 * @Version 1.0
 **/
public class Test {

    @org.junit.jupiter.api.Test
    public void testOne() throws InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(4,10,30L,TimeUnit.SECONDS,new LinkedBlockingDeque<>(1000));
        Set<Job> jobs= new HashSet<>();
        Job jobOne = new JobOne(Arrays.asList("a,b,c"));
        Job a = new JobOne(Arrays.asList("a,b,c"));
        Job b = new JobOne(Arrays.asList("a,b,c"));
        Job c = new JobOne(Arrays.asList("a,b,c"));
        Job d = new JobOne(Arrays.asList("a,b,c"));
        Job e = new JobOne(Arrays.asList("a,b,c"));
        Job f = new JobOne(Arrays.asList("a,b,c"));

        jobs.add(jobOne);
        jobs.add(a);
        jobs.add(b);
        jobs.add(c);
        jobs.add(d);
        jobs.add(e);
        jobs.add(f);

        Server server = new Server(executor,jobs);
        server.start();


    }
}
