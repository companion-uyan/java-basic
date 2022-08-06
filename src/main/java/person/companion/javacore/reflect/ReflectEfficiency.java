package person.companion.javacore.reflect;

/**
 * 功能描述：通过反射创建类的性能比较
 *
 * @author companion
 * @date 2021/7/29 19:47
 */
public class ReflectEfficiency {
    public static void main(String[] args) throws Exception {
        createConstructor();
        createReflect();
    }

    /**
     * 通过反射创建对象
     */
    private static void createReflect() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Class<?> aClass = Class.forName("person.companion.javacore.reflect.ReflectEfficiency");
            aClass.newInstance();
        }

        long end = System.currentTimeMillis();
        System.out.println("构造反射创建对象时间：" + (end - start));
    }

    /**
     * 通过构造函数创建对象
     */
    private static void createConstructor() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ReflectEfficiency reflect = new ReflectEfficiency();
        }

        long end = System.currentTimeMillis();
        System.out.println("构造函数创建对象时间：" + (end - start));
    }
}
