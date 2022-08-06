package person.companion.designpattern.proxy.dynamicproxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/12 20:32
 */
public class DynamicProxyTest {
    /**
     * 动态代理主要使用Proxy类中的方法，必须传一个接口过来作为参数
     */
    @Test
    public void test() {
        UserDao userDao = new UserDao();
        IUserDao o = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("开始动态代理");
            method.invoke(userDao, args);
            System.out.println("结束动态代理");
            return null;
        });
        // 这里是要代理的方法
        o.save();
    }
}
