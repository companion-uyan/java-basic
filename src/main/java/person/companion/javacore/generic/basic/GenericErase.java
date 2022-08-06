package person.companion.javacore.generic.basic;

/**
 * 功能描述：泛型擦除
 *
 * @author companion
 * @date 2021/7/29 18:19
 */
public class GenericErase<T extends Number> {
    void print(T t){
        System.out.println(t);
    }

    void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {

    }
}
