package person.companion.designpattern.single;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述：单例模式测试
 *
 * @author companion
 * @date 2020/5/25 12:43
 */
public class SingleTest {
    public static int THREAD_NUM = 1000;

    /**
     * 单例模式-饿汉模式
     */
    @Test
    public void test1() {
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();
        System.out.println(instance == instance1);
        // 测试反射
        /*try {
            Class<?> name = Class.forName("person.companion.designpattern.single.Singleton1");
            Constructor<?> declaredConstructor = name.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            Object instance2 = declaredConstructor.newInstance();
            System.out.println(instance == instance2);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 懒汉模式
     */
    @Test
    public void test2() {
        // System.out.println(Singleton2.getInstance() == Singleton2.getInstance());

        // 测试多线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Singleton2.getInstance())).start();
        }
    }

    /**
     * 懒汉模式--加锁测试
     */
    @Test
    public void test3() {
        CountDownLatch latch = new CountDownLatch(THREAD_NUM);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                Singleton3.getInstance();
                latch.countDown();
            }).start();
        }

        try {
            // 等待线程结束
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 懒汉模式--加双检锁测试
     */
    @Test
    public void test4() {
        // 测试多线程
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            // new Thread(() -> System.out.println(Singleton3.getInstance())).start();
            new Thread(() -> {
                Singleton4.getInstance();
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 静态内部类方式
     */
    @Test
    public void test5() {
        System.out.println("开始测试");
        Singleton5 instance = Singleton5.getInstance();
        Singleton5 instance1 = Singleton5.getInstance();
        System.out.println(instance == instance1);
    }

    /**
     * 单例模式-枚举
     */
    @Test
    public void test6() {
        Singleton6 instance = Singleton6.INSTANCE;
        System.out.println(instance);
    }
}
