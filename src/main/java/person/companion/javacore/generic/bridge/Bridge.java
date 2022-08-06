package person.companion.javacore.generic.bridge;

/**
 * 功能描述：在泛型子类中override父类的泛型方法时，编译器会自动生成一个桥接方法
 *
 * @author companion
 * @date 2021/7/29 19:25
 */
public class Bridge<T> {
    // 定义一个泛型方法，用于覆写并验证桥接
    public void bridge(T t) {
        System.out.println(t);
    }
}