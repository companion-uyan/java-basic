package person.companion.javacore.anno.handle.reflect;

import person.companion.javacore.anno.ExecuteTime;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Title: 通过反射解析注解
 * Author companion
 * Written by: 2022/2/8 20:29
 * Describe:
 */
public class HandleAnnoByReflectTest {
    String path = this.getClass().getClassLoader().getResource("").getPath().substring(1);

    @SneakyThrows
    @ExecuteTime(TimeUnit.MILLISECONDS)
    public static void sleep() {
        Thread.sleep(100);
    }

    /**
     * 已经知道注释所在类的类名，直接使用
     */
    @Test
    public void testByClassName() throws Exception {
        handleAnno(HandleAnnoByReflectTest.class);
    }

    /**
     * 不知道有哪些地方使用，通过报名遍历
     */
    @Test
    public void testByPackageName() throws Exception {
        String packageName = "com/companion/javacore/anno/handle/reflect";
        // 获取绝对路径名
        File file = new File(path, packageName);
        // 递归获取.class结尾的文件
        List<Class> classList = recursiveFile(file, new ArrayList<>());
        for (Class aClass : classList) {
            handleAnno(aClass);
        }
    }

    private void handleAnno(Class<HandleAnnoByReflectTest> className) throws IllegalAccessException, InvocationTargetException {
        for (Method method : className.getDeclaredMethods()) {
            ExecuteTime executeTime = method.getAnnotation(ExecuteTime.class);
            if (executeTime != null) {
                System.out.println("单位：" + executeTime.value().name());
                long start = System.currentTimeMillis();
                method.invoke(this);
                long end = System.currentTimeMillis();
                System.out.println("执行时间：" + (end - start));
            }
        }
    }

    /**
     * 递归获取.class结尾的文件
     *
     * @param file    需要遍历的文件夹
     * @param classes 集合
     * @return 类
     */
    private List<Class> recursiveFile(File file, ArrayList<Class> classes) throws ClassNotFoundException {
        // 遍历子目录
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                recursiveFile(file, new ArrayList<>());
            }

            if (listFile.isFile() && listFile.getName().endsWith(".class")) {
                String className = listFile.getAbsolutePath().substring(path.length()).split("\\.")[0].replace("\\", ".");
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}
