package person.companion.thread.lock.custom;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：通过yield实现加锁
 *
 * @author companion
 * @date 2021/8/15 18:57
 */
@Slf4j
public class CustomLock2 {
    private static int num = 0;
    private volatile static int status = 0;

    private static boolean compareAndSwap(int except, int value) {
        return status != except || (status = value) != value;
    }

    private static void lock() {
        // 如果修改不成功，线程一直在这里等待
        while (compareAndSwap(0, 1)) {
            /*
             * 相比之前的死循环，这里如果加锁不成功就yield
             * 但是yield只是让出cpu资源，并不能保证下一次执行的不是这个yield的线程
             */
            if ("线程2".equals(Thread.currentThread().getName())) {
                num++;
            }

            Thread.yield();
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