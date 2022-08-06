package person.companion.designpattern.single;

/**
 * 功能描述：单例模式-懒汉模式
 *
 * @author companion
 * @date 2021/7/31 11:24
 */
public class Singleton2 {
    // 1.创建一个静态域
    public static Singleton2 instance;

    // 2.私有化构造方法
    private Singleton2(){
        if (instance != null){
            throw new RuntimeException("请不要使用反射创建单例对象");
        }
    }

    // 3.获取实例的方法
    public static Singleton2 getInstance(){
        if (instance == null){
            instance = new Singleton2();
        }

        return instance;
    }
}
