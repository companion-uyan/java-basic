package person.companion.javacore.reflect;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * 功能描述：
 * 破坏单例模式与解决办法
 * 因为反射可以获取私有的构造方法，因此可能调用私有的构造方法构造新的对象
 * 这时候可以在私有的构造方法中判断是否已经创建对象，创建了就不能再创建
 *
 * @author companion
 * @date 2021/7/29 21:09
 */
public class SingleDemo {
    private static SingleDemo instance;

    // 私有化构造方法
    private SingleDemo() {
        // 通过在构造方法中判断是否有值来解决反射创建其他对象问题
        // 对于懒汉模式，不止反射，多线程也会造成多次调用构造方法
        /*if (instance != null){
            throw new RuntimeException("不要使用反射创建对象");
        }*/
    }

    // 提供一个方法来创建单例
    public static SingleDemo createInstance() {
        if (instance == null) {
            instance = new SingleDemo();
        }

        return instance;
    }

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>(7);
        SingleDemo instance = SingleDemo.createInstance();
        SingleDemo instance1 = SingleDemo.createInstance();
        System.out.println(instance == instance1);

        // 使用反射
        try {
            Constructor<SingleDemo> declaredConstructor = SingleDemo.class.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            SingleDemo instance2 = declaredConstructor.newInstance();
            System.out.println(instance2 == instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
