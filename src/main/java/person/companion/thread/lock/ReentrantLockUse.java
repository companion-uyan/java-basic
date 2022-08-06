package person.companion.thread.lock;

import person.companion.thread.basicuse.SynchronizedTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：ReentrantLock的基本使用
 *
 * @author companion
 * @date 2021/9/19 15:04
 */
@Slf4j
public class ReentrantLockUse {
    /**
     * 测试ReentrantLock基本使用
     */
    @Test
    public void test1() throws InterruptedException {
        // 无参构造，默认非公平锁
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.debug("线程1开始执行");
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
            log.debug("线程1执行完毕");
        });

        Thread t2 = new Thread(() -> {
            log.debug("线程2开始执行");
            lock.lock();
            log.debug("线程2等待");
            lock.unlock();
            log.debug("线程2执行完毕");
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * 测试ReentrantLock的condition
     */
    @Test
    public void test2() throws InterruptedException {
        // 无参构造，默认非公平锁
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread t1 = new Thread(() -> {
            log.debug("线程1开始执行");
            lock.lock();
            try {
                log.debug("线程1阻塞");
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            log.debug("线程1执行完毕");
        });

        Thread t2 = new Thread(() -> {
            log.debug("线程2开始执行");
            lock.lock();
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            log.debug("线程2执行完毕");
        });

        t1.start();
        t2.start();

        // 等待线程1进入阻塞
        TimeUnit.SECONDS.sleep(1);
        // 只唤醒condition1上面的线程
        lock.lock();
        // 注意signal与await的执行顺序，如果线程在signal之后阻塞，signal方法是无效哦的
        log.debug("condition1唤醒");
        condition1.signal();
        lock.unlock();
        t1.join();
        t2.join();
    }

    /**
     * 验证ReentrantLock重量锁的等待队列采用的是尾插法
     * {@link SynchronizedTest#test3}
     */
    @Test
    public void test3() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        // 添加10个线程
        ArrayList<Thread> list = new ArrayList<>();
        Object o = new Object();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> {
                lock.lock();
                log.debug("线程{}开始执行", Thread.currentThread().getName());
                lock.unlock();
            }, "t" + i));
        }

        // 依次启动10个线程，这里因为加了锁，所以10个线程不会直接执行，而是等待遍历完成之后执行
        lock.lock();
        log.debug("添加线程顺序：");
        for (Thread thread : list) {
            log.debug(thread.getName());
            thread.start();
            // 为了让线程依次执行，这里睡眠1秒
            Thread.sleep(100);
        }
        lock.unlock();

        log.debug("线程执行顺序：");
        // 等待线程执行完毕
        Thread.sleep(2000);
    }


    /**
     * 调用tryLock尝试加锁
     */
    @Test
    public void test4() {
        final ReentrantLock lock = new ReentrantLock();
        log.debug("主线程开始加锁");
        lock.lock();
        new Thread(() -> {
            log.debug("线程1开始尝试加锁");
            // lock.tryLock(2, TimeUnit.SECONDS);
            if (lock.tryLock()) {
                log.debug("加锁成功");
            } else {
                log.debug("加锁失败");
            }
        }).start();
    }

    /**
     * lockInterruptibly响应中断
     */
    @Test
    public void test5() {
        final ReentrantLock lock = new ReentrantLock();
        log.debug("主线程开始执行");
        lock.lock();
        final Thread thread = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("捕获中断异常");
            }
        });

        thread.start();
        thread.interrupt();
    }
}
