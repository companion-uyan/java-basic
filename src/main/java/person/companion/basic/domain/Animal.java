package person.companion.basic.domain;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/29 18:24
 */
public class Animal {
    private String name;

    static {
        System.out.println("animal--static fragment");
    }

    {
        System.out.println("animal--fragment");
    }

    public Animal(){
        System.out.println("animal class constructor");
        this.say();
    }

    void say(){
        System.out.println("override method--animal class");
    }
}
