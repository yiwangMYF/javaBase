package Java.day_interface;

/**
 * @program: backEnd
 * @description: jdk1.8：接口中可以有静态方法和默认实现
 *               hdk1.9:接口中可以有私有方法
 * @author: myf
 * @create: 2020/04/08 09:48
 **/
public interface MyInterface {
    //接口中的变量都是public static final修饰的（可省略）
    int count=0;

    void printCount();

    //静态方法
    static int getSum(int a,int b) {
        return a+b;
    }

    //默认实现方法
    default int getCount(){
        return count;
    }
    //私有方法 1.9后允许
//    private void printLog(){
//        System.out.println("执行接口中私有方法");
//    }

}
