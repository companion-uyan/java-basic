package person.companion.designpattern.single;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：单例模式-懒汉模式-加双检锁
 * 因为懒汉模式线程不安全，因此可以通过加锁的方式来确保线程安全
 * 但是加锁之后效率不高，因此可以使用双检锁
 *
 * @author companion
 * @date 2021/7/31 11:24
 */
public class Singleton4 {
    // 1.创建一个静态域
    public static Singleton4 instance;

    // 2.私有化构造方法
    private Singleton4() {
    }

    // 3.获取实例的方法
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }

        return instance;
    }
}
