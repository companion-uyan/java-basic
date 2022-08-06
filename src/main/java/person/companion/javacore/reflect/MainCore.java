package person.companion.javacore.reflect;

import person.companion.javacore.reflect.pojo.Student;

import java.lang.reflect.Method;

/**
 * 功能描述：反射的基本使用
 *
 * @author companion
 * @date 2021/7/29 20:48
 */
public class MainCore {
    public static void main(String[] args) {
        /**
         * 获取Class对象的四种方式
         */
        try {
            // 方式1:通过ClassLoader
            Class<?> class1 = MainCore.class.getClassLoader().loadClass("person.companion.javacore.reflect.MainCore");
            System.out.println(class1);
            // 方式2：Class.forNaame
            Class<?> class2 = Class.forName("person.companion.javacore.reflect.MainCore");
            System.out.println(class2);
            // 方式3：通过类名
            Class<MainCore> class3 = MainCore.class;
            System.out.println(class3);
            // 方式4：通过对象名
            MainCore mainCore = class3.newInstance();
            System.out.println(mainCore);


            /**
             * 常用方法
             */
            // 获取所有的公共方法
            Class<Student> studentClass = Student.class;
            Method[] methods = studentClass.getMethods();
            // System.out.println("学生类的所有公共方法，包括继承的" + Arrays.toString(methods));
            // System.out.println("学生类的自己的方法" + Arrays.toString(studentClass.getDeclaredFields()));
            // 修改私有方法权限，注意方法的第二个参数是参数类型的Class
            Method privateMethod = studentClass.getDeclaredMethod("testPrivate", String.class);
            privateMethod.setAccessible(true);
            System.out.println(privateMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
