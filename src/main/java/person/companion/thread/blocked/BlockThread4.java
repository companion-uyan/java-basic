package person.companion.thread.blocked;

import person.companion.thread.basicuse.CountDownLatchTest;
import org.junit.Test;

/**
 * 功能描述：通过join实现阻塞
 *
 * @author companion
 * @date 2021/10/7 18:20
 */
public class BlockThread4 {
    /**
     * join必须等待线程死亡且不能控制锁的粒度
     * {@link CountDownLatchTest#test3}
     */
    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程2执行");
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t1.isAlive());
        System.out.println(t2.isAlive());
        System.out.println("主线程执行");
    }
}
