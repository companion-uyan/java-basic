package person.companion.designpattern.single;

/**
 * 功能描述：单例模式-饿汉模式
 *
 * @author companion
 * @date 2021/7/31 11:24
 */
public class Singleton1 {
    // 1.创建一个静态域，实例化对象
    public static Singleton1 instance = new Singleton1();

    // 2.私有化构造方法
    private Singleton1(){
        if (instance != null){
            throw new RuntimeException("请不要使用反射创建单例对象");
        }
    }

    // 3.获取实例的方法
    public static Singleton1 getInstance(){
        return instance;
    }
}
