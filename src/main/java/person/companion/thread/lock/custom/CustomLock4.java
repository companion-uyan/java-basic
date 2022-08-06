package person.companion.thread.lock.custom;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 功能描述：通过park优化锁等待
 *
 * @author companion
 * @date 2021/8/15 18:57
 */
@Slf4j
public class CustomLock4 {
    private static int num = 0;
    private static Queue<Thread> queue = new ConcurrentLinkedDeque<>();
    private volatile static int status = 0;

    private static boolean compareAndSwap(int except, int value) {
        return status != except || (status = value) != value;
    }

    @SneakyThrows
    private static void lock() {
        // 如果修改不成功，线程一直在这里等待
        if (compareAndSwap(0, 1)) {
            if ("线程2".equals(Thread.currentThread().getName())) {
                num++;
            }

            // 如果线程加锁不成功，则直接将线程放入队列
            queue.add(Thread.currentThread());
            LockSupport.park(Thread.currentThread());
        }
    }

    private static void unlock() {
        status = 0;
        // 取出一个新的线程唤醒
        LockSupport.unpark(queue.poll());
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
