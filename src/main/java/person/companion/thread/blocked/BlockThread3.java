package person.companion.thread.blocked;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 功能描述：通过park阻塞线程
 *
 * @author companion
 * @date 2021/9/4 19:15
 */
@Slf4j
public class BlockThread3 {
    private static int num = 2;
    private final Object o = new Object();

    /**
     * 验证park不会释放锁(没有锁的概念)
     */
    @Test
    public void test1() {
        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            log.debug("线程1开始执行");
            synchronized (o) {
                LockSupport.park();
                log.debug("线程1修改数量{}为{}", num, --num);
            }
        }, "线程1");

        Thread thread2 = new Thread(() -> {
            log.debug("线程2开始执行");
            synchronized (o) {
                log.debug("线程2修改数量{}为{}", num, --num);
            }
        }, "线程2");

        try {
            thread1.start();
            TimeUnit.MILLISECONDS.sleep(1000);
            thread2.start();
            TimeUnit.MILLISECONDS.sleep(1000);
            LockSupport.unpark(thread1);
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * parkNanos自动唤醒
     */
    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("线程开始执行");
            // 中断1秒自动唤醒
            LockSupport.parkNanos(1000000000);
            log.debug("线程执行完毕");
        });

        thread.start();
        thread.join();
    }

    /**
     * 验证unpark只是给线程一个许可证，与park的执行顺序无关
     * 需要注意unpark必须在线程启动之后才有效
     */
    @Test
    public void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("线程开始执行");
            // 线程开始前unpark，线程会保留一个许可证，直到下一个park消费
            // 因为许可证只能保存一次，所以多次unpark只能通过一次park
            LockSupport.unpark(Thread.currentThread());
            LockSupport.unpark(Thread.currentThread());
            LockSupport.park();
            log.debug("线程执行第一次唤醒");
            LockSupport.park();
            log.debug("线程不会第二次唤醒，因为unpark只有一次有效");
        });

        thread.start();
        thread.join();
    }

    /**
     * 验证因为interrupt之后不会自动重置中断标志位
     * 因为根据中断标识位判断park是否有效的park方法会多次通过
     */
    @Test
    public void test4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("线程开始执行");
            LockSupport.park();
            log.debug("第一次唤醒，中断标志：{}", Thread.currentThread().isInterrupted());
            LockSupport.park();
            // 与unpark示例不同，这里会执行唤醒
            log.debug("第二次唤醒，中断标志：{}", Thread.currentThread().isInterrupted());
            // 重置中断标志之后park不会执行
            Thread.interrupted();
            LockSupport.park();
            log.debug("第三次唤醒，中断标志：{}", Thread.currentThread().isInterrupted());
        });

        thread.start();
        // 如果注释了interrupt线程会一直阻塞
        thread.interrupt();
        thread.join();
    }
}
