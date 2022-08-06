package person.companion.util.threadutil;

/**
 * 功能描述：获取线程的工具类
 *
 * @author companion
 * @date 2021/9/12 18:11
 */
public class ThreadUtil {
    public static native int getThreadId();

    static {
        // System.loadLibrary("ThreadUtil");
        System.load((ThreadUtil.class.getClassLoader().getResource("dll/").getFile() + "ThreadUtil.dll").substring(1));
    }

    public static String getThreadIdHex() {
        return Integer.toHexString(getThreadId());
    }
}
