package Java.day_streamApi;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: backEnd
 * @description: Stream流:
 *                  1.stream 自己不会存储元素
 *                  2.stream不会改变源对象，每次返回都是新的stream
 *                  3.stream的操作都是延迟的，只有执行终止操作时才会执行中间的操作（会返回新的stream的操作
 *                  4.创建stream实例
 *                      集合对象.stream() -创建顺序流；
 *                      集合对象.parallelStream()-创建并行流；
 *                      Arrays.stream(T[] t);
 *                      Stream.of(T ...values);
 *                   5.stream的中间操作：
 *                      filter(Predicate p)-用于过滤；
 *                      limit(n)-截断流，使其元素不超过给定数量；
 *                      skip(n)-跳过前面n个元素;
 *                      distinct()-根据集合元素的hashcode和equeals方法进行去重
 *                   6.stream的终止操作：
 *                      allMatch(Predicate p)-检查是否匹配所有元素；
 *                      anyMatch(Predicate p)-检查是否存在匹配；
 *                      noneMatche(Predicate p-检查是否不存在匹配)；
 *                      findFirst()-返回第一个元素（Optional 实例）；
 *                      findAny()-返回流中的任意一个元素；
 *                      count()-返回流中元素的个数；
 *                      max(Comparator c)-获取最大值；
 *                      min(Comparator c)-获取最小值；
 *                      forEach(Consumer c)-遍历;
 *                      reduce(BinaryOperator bi)或reduce(initdata,BinaryOperator bi)-归约操作,将流中元素反复结合得到一个结果；
 *                      collect(Collector c)-用于给流中元素进行汇总，返回集合；Collectors工具类
 *                   7.Optional类
 *                      是一个容器类，保存T类型的数据表示数据存在，保存null表示值不存在；
 *                      1）：创建Optional类对象
 *                          Optional.of(T t)-t必须非空；
 *                          Optional.empty()-创建一个空的Optional实例；
 *                          Optional.ofNullable(T t)-t可以为空；
 *                      2）:判断Optional容器中是否包含对象
 *                          boolean isPresent();
 *                          void ifPresent(Consumer c):如果有值就将值传递给Consumer接口实例，并执行；
 *                      3）：获取Optional容器的对象
 *                          T get()-如果调用对象包含值就返回值，否则抛异常；
 *                          T orElse(T other)-如果调用对象有值就将其返回，否则返回指定的默认值；
 *                          T orElseGet(Supplier s)-如果有值就将其返回，否则返回Supplier接口实例提供的对象；
 *                          T orElseThrow(Supplier exceptionS)-如果有值就返回，否则抛出由Supplier接口实例提供的异常；
 *
 * @author: myf
 * @create: 2020/03/16 09:42
 **/
public class StreamTest {
    public static void main(String[] args) {
        String[] arr={"myf","mxy"};
        Stream<String> stream=Stream.of(arr);
        long count=stream.filter(name->name.endsWith("f")).count();
        //System.out.println(count);
        Stream.of(arr).map(str->str.length()).forEach(System.out ::println);
        Integer reduce = Stream.of(arr).map(str -> str.length()).reduce(0, Integer::sum);
        System.out.println(reduce);
        System.out.println("***************************");
        List<Integer> collect = Stream.of(arr).map(str -> str.length()).collect(Collectors.toList());
        collect.forEach(System.out::println);


        System.out.println("***************************");
        HashMap<Object, Object> collect1 = Stream.of(arr).map(str -> str.length()).collect(HashMap::new, (map, ele) -> map.put(Math.random(), ele), HashMap::putAll);
        System.out.println(collect1);
    }
}
