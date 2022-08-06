package person.companion.basic;

import org.junit.Test;

/**
 * 功能描述：测试finally与return语句的执行效果
 *
 * @author companion
 * @date 2020/5/21 19:33
 */
public class FinallyTest {
    @Test
    public void test1() {
        System.out.println(returnInt());
    }

    @Test
    public void test2(){
        // 因为String使用的是final修饰，所以finally执行之后的a与return的不是同一个对象
        System.out.println(returnString());
    }

    @Test
    public void test3(){
        /*
         * 因为a是同一个对象，所以值进行了拼接
         * 执行顺序其实是return语句--finally语句--再将return语句的对象返回
         * 因此如果return中的对象与finally之后不是同一个对象的话值不会变
         */
        System.out.println(returnStringBuilder());
    }

    public String returnString(){
        String a = "string";
        try {
            return a;
        } catch (Exception e) {
            return a;
        } finally {
            a = a + " finally";
        }
    }

    public StringBuilder returnStringBuilder(){
        StringBuilder a = new StringBuilder("string");
        try {
            return a;
        } catch (Exception e) {
            return a;
        } finally {
            a.append(" finally");
        }
    }

    public int returnInt() {
        int a = 10;
        try {
            return a;
        } catch (Exception e) {
            return a;
        } finally {
            a = 5;
        }
    }
}
