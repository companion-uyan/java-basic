package person.companion.designpattern.single;

/**
 * 功能描述：单例模式-静态内部类
 * ps:静态内部类在访问的时候才会加载
 *
 * @author companion
 * @date 2021/7/31 11:24
 */
public class Singleton5 {
    // 1.创建一个静态域
    static class SingletonClass{
        static Singleton5 instance = new Singleton5();
    }

    // 2.私有化构造方法
    private Singleton5() {
        System.out.println("静态内部类在访问时才会调用构造方法");
    }

    // 3.获取实例的方法
    public static Singleton5 getInstance() {
        return SingletonClass.instance;
    }
}
