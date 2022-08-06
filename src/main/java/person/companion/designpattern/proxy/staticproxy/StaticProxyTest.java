package person.companion.designpattern.proxy.staticproxy;

import org.junit.Test;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/12 19:41
 */
public class StaticProxyTest {
    @Test
    public void test1(){
        UserProxy userProxy = new UserProxy(new UserDao());
        userProxy.save();
    }
}
