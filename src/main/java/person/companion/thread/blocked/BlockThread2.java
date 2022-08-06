package person.companion.thread.blocked;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：通过sleep阻塞线程
 *
 * @author companion
 * @date 2021/9/4 19:15
 */
@Slf4j
public class BlockThread2 {
    private static int num = 2;
    private final Object o = new Object();

    @Test
    public void test2() {
        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            log.debug("线程1开始执行");
            synchronized (o) {
                try {
                    // o.wait(1000);
                    // sleep不会释放锁，因此要等线程1修改值之后线程2才能执行同步代码
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 重置中断标志
                    Thread.currentThread().interrupt();
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
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
