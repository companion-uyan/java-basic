package person.companion.javacore.interfaces;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 功能描述：常用函数式接口
 *
 * @author companion
 * @date 2021/7/9 16:34
 */
public class BasicInterface {
    @Test
    public void test() {
        Supplier<Student> supplier = Student::new;
        Student student = supplier.get();

        Consumer<Student> consumer = s -> {
            s.setName("name");
        };
        consumer.accept(student);

        Function<Student, String> function = Student::getName;
        System.out.println(function.apply(student));

        Predicate<Student> predicate = s -> "name".equals(s.getName());
        System.out.println(predicate.test(student));

        Comparator<Integer> comparator = Integer::compareTo;
        System.out.println(comparator.compare(7, 8));
    }

    /**
     * 测试set方法的接口
     */
    @Test
    public void test2() {
        Student student = new Student();
        SetInterface<Student> setInterface = Student::setName;
        setInterface.setValue(student, "测试");
        System.out.println(student.getName());
    }
}
