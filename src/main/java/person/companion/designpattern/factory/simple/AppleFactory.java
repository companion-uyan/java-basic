package person.companion.designpattern.factory.simple;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/8 17:12
 */
public class AppleFactory implements Factory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
