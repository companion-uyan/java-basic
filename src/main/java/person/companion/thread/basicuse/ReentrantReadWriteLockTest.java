package person.companion.thread.basicuse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能描述：ReentrantReadWriteLock使用
 *
 * @author companion
 * @date 2021/10/7 20:19
 */
@Slf4j
public class ReentrantReadWriteLockTest {
    /**
     * ReentrantReadWriteLock基本使用
     * 读写互斥
     * 在等待队列中，所有的写锁都是互斥执行的，连续的读锁并发执行(直到遇到写锁)
     */
    @Test
    public void test1() throws InterruptedException {
        final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        // 获取读锁
        final ReentrantReadWriteLock.ReadLock r = rw.readLock();
        // 获取写锁
        final ReentrantReadWriteLock.WriteLock w = rw.writeLock();
        // t1写锁互斥执行
        Thread t1 = new Thread(() -> {
            w.lock();
            try {
                Thread.sleep(100);
                log.debug("执行读锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            w.unlock();
        });

        // t2与t3读锁并发执行
        Thread t2 = new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(100);
                log.debug("并发执行写锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.unlock();
        });

        Thread t3 = new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(100);
                log.debug("并发执行写锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.unlock();
        });

        // t4写锁互斥执行
        Thread t4 = new Thread(() -> {
            w.lock();
            try {
                Thread.sleep(100);
                log.debug("并发执行写锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            w.unlock();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    /**
     * 官方例子
     */
    class CachedData {
        Object data;
        volatile boolean cacheValid;
        final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        void processCachedData() {
            rwl.readLock().lock();
            // 判断数据是否过期
            if (!cacheValid) {
                // Must release read lock before acquiring write lock
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    // Recheck state because another thread might have
                    // acquired write lock and changed state before we did.
                    /*
                     * 再次判断，与synchonrized相同
                     * 防止有线程2在线程1执行rwl.readLock().unlock()之后切换执行
                     * 这时候t2加锁成功，如果没有二次判断，t1也会加锁成功
                     */
                    if (!cacheValid) {
                        // 查询并更新数据
                        // data = ...
                        cacheValid = true;
                    }
                    // Downgrade by acquiring read lock before releasing write lock
                    rwl.readLock().lock();
                } finally {
                    rwl.writeLock().unlock(); // Unlock write, still hold read
                }
            }

            try {
                // 使用数据
                // use(data);
            } finally {
                rwl.readLock().unlock();
            }
        }
    }

    /**
     * 锁可以降级，不可以升级
     * w->r 降级
     * r->w 升级
     */
    @Test
    public void test2() {
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.ReadLock r = lock.readLock();
        final ReentrantReadWriteLock.WriteLock w = lock.writeLock();
        /*
         * 锁降级可以正常执行，因为ReentrantReadWriteLock支持可重入
         * 只需要按顺序解锁即可
         */
        w.lock();
        log.debug("加写锁");
        r.lock();
        log.debug("加读锁");
        r.unlock();
        w.unlock();
        log.debug("执行完毕");

        // 锁升级会造成死锁，在加写锁时阻塞
        /*r.lock();
        log.debug("加读锁");
        w.lock();
        log.debug("加写锁");
        w.unlock();
        r.unlock();
        log.debug("执行完毕");*/
    }
}
