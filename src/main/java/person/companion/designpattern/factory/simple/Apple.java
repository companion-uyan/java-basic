package person.companion.designpattern.factory.simple;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/8 17:11
 */
public class Apple implements Fruit {
    public static Factory factory = Apple::new;

    @Override
    public void fruit() {
        System.out.println("Apple");
    }
}
