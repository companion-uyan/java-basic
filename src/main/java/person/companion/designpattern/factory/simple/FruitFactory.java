package person.companion.designpattern.factory.simple;

/**
 * Title: 工厂类
 * Author companion
 * Written by: 2022/2/27 19:42
 * Describe:
 */
public class FruitFactory {
    public Fruit getFruit(String fruit) {
        if ("Apple".equals(fruit)) {
            return new Apple();
        }

        if ("Grape".equals(fruit)) {
            return new Grape();
        }

        return null;
    }
}
