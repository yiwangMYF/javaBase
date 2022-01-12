package Java.day3_concurrent;

/**
 * @program: backEnd
 * @description: 线程类，runnable接口实现
 * @author: myf
 * @create: 2020/04/03 11:37
 **/
public class MyThreadTwo implements Runnable {
    private static int count=0;
    @Override
    public void run() {
        String a="ababsksfasaaasaff";
        System.out.println("before replace:"+a);
        String b=a.replace("/ab/","/ZZ/");
        System.out.println("after replace:"+b);
        System.out.println("count:"+(++count));
    }
}
