package person.companion.javacore.interfaces;

/**
 * 功能描述：用于传递set方法的接口
 *
 * @author companion
 * @date 2021/7/9 17:15
 */
@FunctionalInterface
public interface SetInterface<T> {
    void setValue(T t, String name);
}
