package person.companion.javacore.generic.basic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：自定义一个泛型类
 *
 * @author companion
 * @date 2021/7/28 20:15
 */
@Data
public class GenericClass<K,V> {
    private K k;

    private V value;

    // 静态方法可以使用自定义的类型
    public static <P> void test(P p){

    }

    // 静态方法不能使用类上面定义的泛型，因为需要在类实例化的时候才能确定类型
    /*public static void test1(K k){

    }*/

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        List<String> integers = new ArrayList<>();
        // 结果为true，Java是伪泛型，并不影响基本类型
        System.out.println(list.getClass() == integers.getClass());
    }
}
