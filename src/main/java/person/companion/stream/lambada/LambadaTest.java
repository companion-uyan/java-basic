package person.companion.stream.lambada;

import org.junit.Test;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/8/4 20:27
 */
public class LambadaTest {
    /**
     * 因为函数式接口只有一个抽象方法，因为使用lambada表达式的时候
     * 实现的一定就是这个抽象方法
     */
    @Test
    public void test() {
        LambadaInterface lambadaInterface1 = (a) -> {
            System.out.println(12);
            System.out.println(a);
        };

        lambadaInterface1.getString("测试");
    }

    @Test
    public void test2() throws Exception {
        getLambada("getLambada方法", System.out::println);
    }

    private void getLambada(String s, LambadaInterface l) {
        l.getString(s);
    }
}
