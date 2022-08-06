package person.companion.designpattern.proxy.staticproxy;

/**
 * 功能描述：代理类与目标类要共同实现的方法
 *
 * @author companion
 * @date 2020/5/12 19:38
 */
public interface IUserDao {
    /**
     * 代理方法
     */
    void save();
}
