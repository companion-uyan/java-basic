package person.companion.basic.domain;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/29 18:27
 */
public class Dog extends Animal {
    static {
        System.out.println("dog--static fragment");
    }

    {
        System.out.println("dog--fragment");
    }

    public Dog() {
        super();
        System.out.println("dog class constructor");
        say();
    }

    @Override
    void say() {
        System.out.println("override method--dog class");
    }
}
