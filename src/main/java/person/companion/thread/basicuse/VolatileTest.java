package person.companion.thread.basicuse;

import person.companion.util.threadutil.UnsafeUtil;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * 功能描述：测试volatile关键字
 * volatile保证了可见性与有序性，但是不能保证原子性
 *
 * @author companion
 * @date 2021/9/6 10:39
 */
public class VolatileTest {
    private volatile int num = 0;

    /**
     * 测试volatile不能保证原子性
     * 这里正是因为赋值与自加等操作都不是原子性的导致执行结果与预期结果不符合
     */
    @Test
    public void test1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        // 这里使用10个线程，每个线程完成1000次自加
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    num++;
                }

                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println(num);
    }

    /**
     * 通过Unsafe保证原子性
     * 这里的Unsafe可以从硬件层面直接通过内存地址修改元素的值，因此保证了原子性
     */
    @Test
    public void test2() throws Exception {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        assert unsafe != null;
        Field numField = this.getClass().getDeclaredField("num");
        CountDownLatch latch = new CountDownLatch(10);
        // 这里使用10个线程，每个线程完成1000次自加
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    boolean b = unsafe.compareAndSwapInt(this, unsafe.objectFieldOffset(numField), num, (num + 1));
                    // 知道改变成功为止
                    while (!b) {
                        b = unsafe.compareAndSwapInt(this, unsafe.objectFieldOffset(numField), num, (num + 1));
                    }
                }

                latch.countDown();
            }).start();
        }

        latch.await();
        System.out.println(num);
    }
}
