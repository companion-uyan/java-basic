package person.companion.javacore.generic.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述：常用泛型通配符
 *
 * @author companion
 * @date 2021/7/28 20:25
 */
public class BorderDemo {
    /**
     * 无界通配符
     *
     * @param list 无界通配符数组
     */
    private static void noBorder(List<?> list) {
        System.out.println(list);
    }

    /**
     * 上界通配符
     *
     * @param list 上界通配符数组
     */
    private static void extendsBorder(List<? extends Number> list) {
        System.out.println(list);
    }

    /**
     * 下界通配符
     *
     * @param list 下界通配符数组
     */
    private static void superBorder(List<? super Number> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        // 定义无界通配符
        List<Integer> noBorderInt = Arrays.asList(1, 3);
        List<String> noBorderStr = Arrays.asList("测试", "以下");
        noBorder(noBorderInt);
        noBorder(noBorderStr);

        // 上界通配符
        extendsBorder(noBorderInt);
        // 会报错，因为string不是Number子类
        // extendsBorder(noBorderStr);

        // 下界通配符
        // 报错，因为Integer不是Number父类
        // superBorder(noBorderInt);
        superBorder(Collections.singletonList(new Object()));
    }
}
