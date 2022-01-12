package Java.day_lambda;

/**
 * @program: backEnd
 * @description: 函数式接口: 接口中只有一个抽象方法
 *                      java提供的函数式接口都在java.util.function包中
 *                      4大核心函数式接口：
 *                          1.Consumer<T> void accept(T t) 消费型接口
 *                          2.Supplier<T> T get() 供给型接口
 *                          3.Function<T,R> R apply(T t) 函数型接口
 *                          4.Predicate<T> boolean test(T t)断定型接口
 * @author: myf
 * @create: 2020/03/15 14:37
 **/
@FunctionalInterface
public interface IShowMessage {
    String showMessage();
}
