package Java.day_multithread_framework;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author myf
 * @CreateDate 2022/2/9 10:48
 * @Version 1.0
 **/
public class JobOne implements Job {

    private List<String> names;

    public JobOne(List<String> names) {
        this.names = names;
    }

    @Override
    public void Process() {
        this.names.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    @Override
    public String toString() {
        return this.names.stream().collect(Collectors.joining(","));
    }
}
