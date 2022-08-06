package person.companion.basic;

import person.companion.basic.domain.Animal;
import person.companion.basic.domain.Dog;
import person.companion.basic.exercise.domain.PastoralDog;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/29 18:22
 */
public class LoadOrderTest {
    /**
     * 子类与父类构造方法的执行顺序
     */
    @Test
    public void test1() {
        // 默认会写调用父类的无参构造方法，如果父类的无参构造方法调用的方法被子类覆写，则使用子类的构造方法
        Animal dog = new Dog();
        // 子类拥有父类的私有字段与方法，但是不能正常访问，只能通过反射（能通过反射方法就可以说明确实是有的）
        // dog.name
        System.out.println("-------------------------------");
        PastoralDog dog1 = new PastoralDog();
        System.out.println("-------------------------------");
    }

    /**
     * 代码块加载顺序:
     * 父类静态代码块--子类静态代码块--父类代码块--父类构造方法--子类代码块--子类构造方法
     * 静态代码块只会执行一次
     */
    @Test
    public void test3() {
        Dog dog = new Dog();
        Dog dog1 = new Dog();
    }

    private void getFiled(Class cla) {
        for (Field field : cla.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Dog dog = new Dog();
                System.out.println(field.getName() + ":" + field.get(dog));
                field.set(dog, "corry");
                System.out.println(field.getName() + ":" + field.get(dog));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (cla.getSuperclass() != null) {
            getFiled(cla.getSuperclass());
        }
    }
}
