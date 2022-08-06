package person.companion.thread.blocked;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：通过wait阻塞线程
 * 必须在代码块中使用
 * 会释放锁资源
 * wait的常用方法有两个，一个有参一个无参，无参需要使用notify或者notifyAll唤醒
 * Object.wait()方法声明抛出了中断异常，调用者需要捕获或者再抛出
 * 如果在wait()之前执行了notify()会抛出IllegalMonitorStateException异常；
 *
 * @author companion
 * @date 2021/9/4 19:15
 */
@Slf4j
public class BlockThread1 {
    private static int num = 2;
    private final Object o = new Object();

    /**
     * 验证wait有参超时自动唤醒
     */
    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("线程开始执行");
            synchronized (o) {
                try {
                    // o.wait(2000);
                    // 换成无参方法，则线程会一直阻塞直到notifyAll或者notify恰好唤醒这个线程
                    o.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            log.debug("线程自动唤醒，执行后面的程序性");
        });

        thread.start();
        thread.join();
    }

    /**
     * 通过wait与notify验证会释放锁资源
     */
    @Test
    public void test2() {
        Thread thread1 = new Thread(() -> {
            log.debug("线程1开始执行");
            synchronized (o) {
                try {
                    // 如果没有wait方法，会是线程1先修改num，然后才是num2修改num
                    // 但是wait方法释放锁之后就是线程2先修改值
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            TimeUnit.MILLISECONDS.sleep(200);
            thread2.start();
            TimeUnit.MILLISECONDS.sleep(200);
            synchronized (o) {
                o.notify();
            }

            // 等待线程执行完毕
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证wait方法之前执行notify会抛出异常
     */
    @Test
    public void test3() {
        Thread thread = new Thread(() -> {
            log.debug("线程开始执行");
            log.debug("线程执行完毕");
        });

        thread.start();

        // 没有synchronized语句直接调用会报错
        // notify();
        // 监视对象与调用对象不同，会报错
        /*synchronized (o){
            this.notify();
        }*/
        // 监视对象与调用对象相同，不会报错
        synchronized (o) {
            o.notify();
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            thread.interrupt();
        }
    }
}
