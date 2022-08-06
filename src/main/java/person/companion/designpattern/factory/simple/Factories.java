package person.companion.designpattern.factory.simple;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/8 17:14
 */
public class Factories {
    public static void main(String[] args) {
        new AppleFactory().getFruit().fruit();
        new GrapeFactory().getFruit().fruit();
        // 通过内部类的方式来调用Apple内部的工厂类
        Apple.factory.getFruit().fruit();
        // 使用一个工厂生产多个类
        FruitFactory roleFactory = new FruitFactory();
        roleFactory.getFruit("Grape").fruit();
        roleFactory.getFruit("Apple").fruit();
    }
}
