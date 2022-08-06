package person.companion.designpattern.proxy.dynamicproxy;

/**
 * 功能描述：代理类与目标类要共同实现的方法
 *
 * @author companion
 * @date 2020/5/12 20:31
 */
public interface IUserDao {
    void say();

    /**
     * 代理方法
     */
    void save();

}
