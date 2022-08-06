package person.companion.designpattern.factory.simple;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/8 17:13
 */
public class GrapeFactory implements Factory {
    @Override
    public Fruit getFruit() {
        return new Grape();
    }
}
