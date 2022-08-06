package person.companion.javacore.generic.bridge;

/**
 * 功能描述：在泛型子类中override父类的泛型方法时，编译器会自动生成一个桥接方法
 *
 * @author companion
 * @date 2021/7/29 19:25
 */
class SubClass extends Bridge<String> {
    /*
     * 将参数类型由String改为Integer不会报错
     * 因为这时候Integer的方法不是覆写父类泛型方法，不会有桥接
     *
     * @param s 参数
     */
    @Override
    public void bridge(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Bridge bridge = new SubClass();
        bridge.bridge("测试");
        // 下面的语句编译通过，但是运行不通过
        bridge.bridge(99);
    }
}