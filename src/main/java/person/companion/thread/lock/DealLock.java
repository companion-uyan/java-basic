package person.companion.thread.lock;

/**
 * 功能描述：两个监控对象o1o2
 * 第一个线程获取o1锁之后sleep,等待第二个线程获取o2
 * 之后第一个线程获取o2，第二个线程获取o1
 *
 * @author companion
 * @date 2021/8/2 10:27
 */
public class DealLock {
    // 创建两个锁资源
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    public static void main(String[] args) {
        // 创建第一个线程，先获取o1之后sleep再获取o2
        new Thread(() -> {
            synchronized (o1) {
                try {
                    System.out.println("第一个线程获取到o1锁");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 获取o2锁
                synchronized (o2) {
                    System.out.println("第一个线程获取到o2锁");
                }
            }
        }).start();

        // 创建第二个线程，先获取o2再获取o1
        new Thread(() -> {
            synchronized (o2) {
                System.out.println("第二个线程获取到o2锁");
                synchronized (o1) {
                    System.out.println("第二个线程获取到o1锁");
                }
            }
        }).start();
    }
}
