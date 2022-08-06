package person.companion.thread.basicuse;

import person.companion.thread.blocked.BlockThread4;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述：CountDownLatch的基本使用
 *
 * @author companion
 * @date 2021/10/7 17:22
 */
public class CountDownLatchTest {
    /**
     * main与test方法的不同
     */
    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print("\r" + i);
            }
        });

        t1.start();
        // main方法可以不用join就打印结果
        t1.join();
    }

    /**
     * CountDownLatch基本使用
     */
    @Test
    public void test2() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行");
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2执行");
            latch.countDown();
        }).start();

        latch.await();
        System.out.println("主线程执行");
    }

    /**
     * 与join不同
     * CountDownLatch可以不用等待线程死亡，这样可以实现线程复用
     * 同时还可以控制具体执行到哪一步就执行主线程
     * {@link BlockThread4#test1()}
     */
    @Test
    public void test3() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行");
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            // 与join不同，CountDownLatch可以控制锁的粒度
            latch.countDown();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程2执行");
        });

        t1.start();
        t2.start();
        latch.await();
        System.out.println(t1.isAlive());
        System.out.println(t2.isAlive());
        System.out.println("主线程执行");
    }
}
