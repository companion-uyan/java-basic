package person.companion.designpattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 功能描述：使用cglib可以不用实现接口类
 *
 * @author companion
 * @date 2020/5/12 19:39
 */
public class UserDao {
    public void save() {
        System.out.println("save方法");
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userDao.getClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("cglib开始操作");
            Object invoke = method.invoke(userDao, objects);
            System.out.println("cglib结束操作");
            return invoke;
        });

        // 调用save方法
        UserDao o = (UserDao) enhancer.create();
        o.save();
        System.out.println(System.currentTimeMillis());
    }
}
