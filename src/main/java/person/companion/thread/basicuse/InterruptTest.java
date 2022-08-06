package person.companion.thread.basicuse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 功能描述：测试中断
 *
 * @author companion
 * @date 2021/10/5 20:49
 */
@Slf4j
public class InterruptTest {
    /**
     * 验证线程中断不会停止线程，只是修改中断标志位
     */
    @Test
    public void test1() throws InterruptedException {
        log.debug("主线程开始执行");
        final Thread t = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                log.debug(String.valueOf(i));
            }
        });

        t.start();
        Thread.sleep(1);
        t.interrupt();
        log.debug("执行1：{}", t.isInterrupted());
        t.join();
        // 如果代码执行完毕，isInterrupted方法返回false
        log.debug("执行3：{}", t.isInterrupted());
        log.debug("主线程执行完毕");
    }

    /**
     * 调用处抛出InterruptedException异常，并且在抛出异常后立即将线程的中断标示位清除
     */
    @Test
    public void test2() throws InterruptedException {
        log.debug("主线程开始执行");
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                // 重置中断标志位
                Thread.currentThread().interrupt();
            }
        });

        t.start();
        t.interrupt();
        // 等待值刷回主线程
        Thread.sleep(100);
        log.debug("主线程中断执行：{}", t.isInterrupted());
        log.debug("主线程中断执行：{}", t.isInterrupted());
        t.join();
        log.debug("主线程执行完毕");
    }

    /**
     * interrupted方法返回的是当前线程而不是当前对象的值
     */
    @Test
    public void test3() throws InterruptedException {
        log.debug("主线程开始执行");
        final Thread t = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                log.debug(String.valueOf(i));
            }
        });

        t.start();
        Thread.sleep(1);
        t.interrupt();
        // 返回的是主线程而不是t的状态
        log.debug("执行1：{}", t.interrupted());
        // 可以看到执行了上面的方法后线程t的状态仍然是true
        log.debug("执行2：{}", t.isInterrupted());
        t.join();
        log.debug("主线程执行完毕");
    }

    /**
     * interrupted会重置中断标志位
     */
    @Test
    public void test4() throws InterruptedException {
        log.debug("主线程开始执行");
        final Thread t = new Thread(() -> {
            // 循环等待中断
            while (!Thread.currentThread().isInterrupted()) {
            }
            // System.out.println(Thread.currentThread().isInterrupted());
            // System.out.println(Thread.currentThread().isInterrupted());
            // 与上面的代码相比，第二句代码会打印false
            System.out.println(Thread.interrupted());
            System.out.println(Thread.interrupted());
        });
        t.start();
        t.interrupt();
        t.join();
        log.debug("主线程执行完毕");
    }
}
