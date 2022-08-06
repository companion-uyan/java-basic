package person.companion.designpattern.iterator;

/**
 * 功能描述：Container主要用于需要遍历的数据进行实现，
 * 从而获得一个内部的Iterator实现
 *
 * @author companion
 * @date 2021/7/8 10:14
 */
public interface Container {
    Iterator iterator();
}
