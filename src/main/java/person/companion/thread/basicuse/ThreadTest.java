package person.companion.thread.basicuse;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 功能描述：线程的基本实现方式
 *
 * @author companion
 * @date 2020/5/12 16:15
 */
public class ThreadTest {
    @Test
    public void test1() {
        // 继承Thread类，无返回值
        new Thread(() -> System.out.println("通过继承Thread的方式实现线程")).start();
    }

    @Test
    public void test2() {
        // 实现Runnable接口，无返回值
        new Thread(() -> System.out.println("如果类已经继承了其他类,则可以通过实现Runnable的方式")).start();
    }

    @Test
    public void test3() {
        try {
            // 通过实现Callable接口，有返回值
            FutureTask<String> task = new FutureTask<>(() -> "返回值");
            // 注意有这一句才能执行，否则会阻塞
            new Thread(task).start();
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用线程池的方式
     */
    @Test
    public void test4() throws ExecutionException, InterruptedException {
        // 注意方法内部就是通过ThreadPoolExecutor进行创建的问题也就出在这里了
        ExecutorService service = Executors.newFixedThreadPool(1);
        // submit方法有返回值
        Future<String> future = service.submit(() -> {
            System.out.println("submit方法有返回值");
            return "submit方法返回值";
        });

        // 注意有异常，这里就没有捕获了，难看
        System.out.println(future.get());
        service.execute(() -> System.out.println("execute方法无返回值"));
        service.shutdown();
    }
}
