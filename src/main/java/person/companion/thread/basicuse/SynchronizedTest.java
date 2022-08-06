package person.companion.thread.basicuse;

import person.companion.thread.lock.ReentrantLockUse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

/**
 * 功能描述：测试synchronized关键字的原理与使用
 *
 * @author companion
 * @date 2021/9/12 9:17
 */
@Slf4j
public class SynchronizedTest {
    @Override
    public int hashCode() {
        return 456;
    }

    /**
     * 测试锁的可偏向性，锁不可偏向的情况：
     * System.identityHashCode(obj)方法
     * 默认的hashCode方法
     * JVM关闭了偏向或者延迟了偏向
     */
    @Test
    public void test1() {
        // 测试偏向延迟  -XX:BiasedLockingStartupDelay=0 关闭偏向延迟再打印
        SynchronizedTest test = new SynchronizedTest();
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
        // 测试System.identityHashCode(test)方法，与是否覆写hashCode方法无关，该方法都会导致锁不可偏向
        System.out.println("对象的hashCode为：" + Integer.toHexString(System.identityHashCode(test)));
        // printParseInstance(test);
        // 测试hashCode方法
        System.out.println(test.hashCode());
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
    }

    /**
     * 测试第一个线程会让锁编程一个偏向锁，并且该锁的记录的线程id与执行的线程id一致
     * -XX:BiasedLockingStartupDelay=0
     */
    @Test
    public void test2() throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                // 这一句还是不行，现在为止还未找到获取mark word中的线程id的方法
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });

        t1.start();
        t1.join();

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                /*
                 * 因为线程id重用问题，这里打印的可能是偏向锁，也可能是轻量锁
                 * 如果是偏向锁，是因为t1已经死亡之后创建t2会重用原来t1的线程id
                 * 可以通过t1不死亡或者线程池来解决这个问题
                 */
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });

        t2.start();
        t2.join();
    }

    /**
     * 验证synchronized重量锁的等待队列采用的是头插法
     * {@link ReentrantLockUse#test3}
     */
    @Test
    public void test3() throws InterruptedException {
        // 添加10个线程
        ArrayList<Thread> list = new ArrayList<>();
        Object o = new Object();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> {
                synchronized (o) {
                    log.debug("线程{}开始执行", Thread.currentThread().getName());
                }
            }, "t" + i));
        }

        // 依次启动10个线程，这里因为加了锁，所以10个线程不会直接执行，而是等待遍历完成之后执行
        synchronized (o) {
            log.debug("添加线程顺序：");
            for (Thread thread : list) {
                log.debug(thread.getName());
                thread.start();
                // 为了让线程依次执行，这里睡眠1秒
                Thread.sleep(100);
            }
        }

        log.debug("线程执行顺序：");
        // 等待线程执行完毕
        Thread.sleep(2000);
    }
}
