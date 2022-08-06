package person.companion.basic;

import org.junit.Test;

/**
 * 功能描述：测试String、StringBuilder、StringBuffer
 *
 * @author companion
 * @date 2020/5/19 17:13
 */
public class StringTest {
    @Test
    public void test1() {
        /**
         * 一个对象，因为编译器会将常量进行优化，将"a"+"b"拼接成"ab"，因此只有一个变量
         */
        String s = "a" + "b";
        /**
         * 四个对象，当有变量的时候底层是通过new StringBuilder()来实现的，注意默认s已经创建，不再这一行算作对象，要算的话五个对象
         */
        String s1 = s + "c";
        /**
         * 2个对象
         */
        String s2 = new String("a" + "b");
        /**
         * 五个对象，加上s的话六个对象
         */
        String s3 = new String(s + "c");
    }
}
