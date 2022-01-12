package Java.day_interface;


/**
 * @program: backEnd
 * @description:
 * @author: myf
 * @create: 2020/04/08 09:50
 **/
public class MyImpl implements MyInterface {
    @Override
    public void printCount() {
        System.out.println(count);
    }

    @Override
    public int getCount() {
        return count*10+20;
    }

    public static void main(String[] args){
        MyImpl my=new MyImpl();
        my.printCount();
        System.out.println(my.getCount());

        System.out.println(MyInterface.getSum(1, 2));
    }
}
