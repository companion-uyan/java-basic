package person.companion.designpattern.single;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：单例模式-懒汉模式-加锁
 * 因为懒汉模式线程不安全，因此可以通过加锁的方式来确保线程安全
 *
 * @author companion
 * @date 2021/7/31 11:24
 */
public class Singleton3 {
    // 1.创建一个静态域
    public static Singleton3 instance;

    // 2.私有化构造方法
    private Singleton3() {
        if (instance != null) {
            throw new RuntimeException("单例失败");
        }
    }

    // 3.获取实例的方法
    public static synchronized Singleton3 getInstance() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (instance == null) {
            instance = new Singleton3();
        }

        return instance;
    }
}
