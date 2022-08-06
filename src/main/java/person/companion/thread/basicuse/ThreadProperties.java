package person.companion.thread.basicuse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：线程的三大性质(可见、有序、原子)与两大核心测试(指令重排序、happen-before)
 *
 * @author companion
 * @date 2021/9/18 19:33
 */
@Slf4j
public class ThreadProperties {
    /**
     * 测试执行重排序对于线程的影响
     */
    boolean flag = true;

    @Test
    public void test1() throws InterruptedException {
        log.debug("开始执行");
        Thread thread = new Thread(() -> {
            while (flag) {
                // 没有这句打印程序会一直执行，因为指令重排序导致后面修改的flag对这里不起作用
                // System.out.println(123);
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("修改标志");
        flag = false;
        thread.join();
    }
}
