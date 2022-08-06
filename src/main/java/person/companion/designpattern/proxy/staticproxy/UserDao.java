package person.companion.designpattern.proxy.staticproxy;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/12 19:39
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("save方法");
    }
}
