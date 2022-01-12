package Java.day_lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @program: backEnd
 * @description: 函数式编程练习：
 *              1.lambda表达式的本质是函数式接口的实例
 *                  形式：(a,b)->{}
 *                  左边是函数式接口中唯一方法的形参列表（省略了参数类型），{}中是抽象方法的实现的方法体，当只有一行语句时大括号可省略
 *              2.方法引用：
 *                  本质是lambda表达式
 *                  当要传递给lambda表达体的操作已经有实现的方法时，可以使用方法引用；
 *                  同时要求接口中抽象方法的形参列表与返回值与已存在实现方法一样时。
 *                  形式：类（或对象）:: 方法名
 *                      对象 :: 非静态方法
 *                      类 :: 静态方法
 *                      类 :: 非静态方法
 *
 * @author: myf
 * @create: 2020/03/15 14:35
 **/
public class LambdaTest {
    public static void showMessage(int level,IShowMessage iShowMessage){
        if (level>1){
            System.out.println(iShowMessage.showMessage());
        }
    }

    /**
     * 获取数组的最大值（Supplier接口的使用）
     * @param supplier
     * @return
     */
    public static int getMax(Supplier<Integer> supplier){
            return supplier.get();
    }

    /**
     * Consumer接口的使用
     * @param message
     * @param consumer
     */
    public  static void printLog(String message, Consumer<String> consumer){
        consumer.accept(message);
    }

    public static void main(String[] args) {
//        showMessage(1,()->{
//            System.out.println("show the message!");
//            return "good";
//        });
//        int[] arr={1,3,5,7,9};
//        int _max=getMax(()->{
//            int max=arr[0];
//            for (int i = 1; i < arr.length; i++) {
//                if (arr[i] > max){
//                    max=arr[i];
//                }
//            }
//            return max;
//        });
//        System.out.println(_max);
//        Scanner scanner = new Scanner(System.in);
//        String mes=scanner.next();
//        printLog(mes,System.out::println);
        printLog("123", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);

            }
        });

        printLog("456",s -> System.out.println(s));
    }

}
