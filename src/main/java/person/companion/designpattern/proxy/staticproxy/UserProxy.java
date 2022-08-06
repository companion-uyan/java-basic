package person.companion.designpattern.proxy.staticproxy;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/12 19:40
 */
public class UserProxy implements IUserDao {
    private IUserDao userDao;
    public UserProxy(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("开始");
        userDao.save();
        System.out.println("结束");
    }
}
