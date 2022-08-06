package person.companion.stream.service;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * 功能描述：获取项目路径的几种方式
 *
 * @author companion
 * @date 2020/5/11 19:40
 */
public class FilePath {
    /**
     * 通过类加载器的方式获取类加载器所在路径
     */
    @Test
    public void test1() {
        // 如果获取的是类加载器，则默认为项目路径
        String resource = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        System.out.println(resource);
        // 会报空指针异常，因为根目录下面没有/这个文件
        /*String resource2 = Objects.requireNonNull(this.getClass().getClassLoader().getResource("/")).getPath();
        System.out.println(resource2);*/
        // TODO 会报空指针异常，以后找原因
        String resource3 = Objects.requireNonNull(this.getClass().getClassLoader().getResource("testOutputStream.text")).getPath();
        System.out.println(resource3);
    }

    /**
     * 使用字节码文件所在位置,一般是target目录，此项目是out
     */
    @Test
    public void test2() {
        URL resource = this.getClass().getResource("");
        System.out.println(resource);
        URL resource2 = this.getClass().getResource("/");
        System.out.println(resource);
        // 文件名没有作用
        URL resource3 = this.getClass().getResource("test112.text1231321");
        System.out.println(resource);

    }

    /**
     * 使用file的方式
     */
    @Test
    public void test3() {
        // 获取的文件名是项目的根路径
        try {
            File file = new File("testOutputStream.text");
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取的文件名是项目的根路径
        try {
            File file = new File("");
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 项目路径
        System.out.println(new File("").getAbsolutePath());
        // 符盘路径
        System.out.println(new File("/").getAbsolutePath());
    }

    /**
     * 通过系统方法
     */
    @Test
    public void test4(){
        // 注意此方法返回的不一定是项目路径， 而是工作路径，也就是在哪里启动的java的start.bat 目录就是哪个
        System.out.println(System.getProperty("user.dir"));
    }
}
