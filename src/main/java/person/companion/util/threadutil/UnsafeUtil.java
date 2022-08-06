package person.companion.util.threadutil;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 功能描述：用于实例化Unsafe类
 *
 * @author companion
 * @date 2021/9/6 10:47
 */
@Slf4j
public class UnsafeUtil {
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            log.debug("实例化Unsafe异常:\n{}", e.toString());
        }

        return null;
    }
}
