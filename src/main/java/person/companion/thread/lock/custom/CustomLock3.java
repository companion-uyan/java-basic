package person.companion.thread.lock.custom;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：通过sleep优化锁等待
 *
 * @author companion
 * @date 2021/8/15 18:57
 */
@Slf4j
public class CustomLock3 {
    private static int num = 0;
    private volatile static int status = 0;

    private static boolean compareAndSwap(int except, int value) {
        return status != except || (status = value) != value;
    }

    @SneakyThrows
    private static void lock() {
        // 如果修改不成功，线程一直在这里等待
        while (compareAndSwap(0, 1)) {
            if ("线程2".equals(Thread.currentThread().getName())) {
                num++;
            }

            // 通过sleep不能在前一个线程执行完之后马上唤醒下一个线程，还可能唤醒的时候前一个线程没有执行完，需要继续等待
            TimeUnit.SECONDS.sleep(5);
        }
    }

    private static void unlock() {
        status = 0;
    }

    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            log.debug("线程1开始执行");
            lock();
            log.debug("线程1加锁成功");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            unlock();
            log.debug("线程1执行完毕，解锁");
            latch.countDown();
        }, "线程1").start();

        // 为了让线程1加锁成功，这里睡眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(() -> {
            log.debug("线程2开始执行");
            lock();
            log.debug("线程2加锁成功");
            unlock();
            log.debug("线程2执行完毕，解锁");
            latch.countDown();
        }, "线程2").start();

        latch.await();
        log.debug("线程2执行了{}次等待", num);
    }
}
