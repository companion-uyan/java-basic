package person.companion.util.fileutil;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 功能描述：类相关的方法
 *
 * @author companion
 * @date 2020/6/2 10:36
 */
public class ClassUtil {
    public static HashMap<String, Object> getFiled(Class cla) {
        HashMap<String, Object> map = new HashMap<>();
        for (Field field : cla.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(cla.newInstance()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (cla.getSuperclass() != null) {
            getFiled(cla.getSuperclass());
        }

        return map;
    }
}
