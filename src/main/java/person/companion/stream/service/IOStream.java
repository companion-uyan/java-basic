package person.companion.stream.service;

import org.junit.Test;

import java.io.*;

/**
 * 功能描述：描述java I/O流的基本操作
 *
 * @author companion
 * @date 2020/5/11 18:09
 */
public class IOStream {
    String filePath = "E:\\IdeaProject\\java-basic\\file\\testOutputStream.text";

    @Test
    public void testFile() {
        File file = new File("/home");
        // 获取文件觉得路径
        System.out.println(file.getAbsolutePath());
        // 获取项目根路径
        System.out.println(new File("").getAbsolutePath());
        // 判断是否是文件夹
        System.out.println(file.isFile());
        // 判断文件路径是否存在
        System.out.println(file.exists());
    }

    /**
     * 字节输入流的使用-汉字会乱码
     */
    @Test
    public void testInputStream() {
        File file = new File(filePath);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            StringBuilder string = new StringBuilder();
            int read;
            // 一次读取一个字节的方法，因为汉字占两个字节，所以会乱码，所以输出中文使用字符流
            /*while ((read = inputStream.read()) != -1) {
                string.append((char) read);
            }*/

            // 方式2，读取多个字节
            byte[] readByte = new byte[4];
            while ((read = inputStream.read(readByte)) != -1) {
                string.append(new String(readByte));
            }

            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节输出流的使用
     */
    @Test
    public void testOutputStream() {
        File file = new File(filePath);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            String s = "字节输出流测试-test";
            // 注意汉字字符问题
            // outputStream.write(s.getBytes(),1,5);
            outputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过字节流输出图片
     */
    @Test
    public void testDownloadImg() {
        File file = new File("E:\\IdeaProject\\java-basic\\file\\touxiang.png");

        try (
                FileInputStream inputStream = new FileInputStream(file);
                FileOutputStream outputStream = new FileOutputStream("E:\\IdeaProject\\java-basic\\file\\downByOutputStream.png");
        ) {
            // 一次读取一个字节的方法，因为汉字占两个字节，所以会乱码，所以输出中文使用字符流

            /*
            int read;
            while ((read = inputStream.read()) != -1) {
                outputStream.write(read);
            }*/

            // 方式2，读取多个字节
            byte[] readByte = new byte[4];
            while (inputStream.read(readByte) != -1) {
                outputStream.write(readByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符输入流
     */
    @Test
    public void test5() {
        File file = new File(filePath);
        try (FileReader reader = new FileReader(file)) {
            char[] chars = new char[1];
            while (reader.read(chars) != -1) {
                System.out.println(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符输出流
     */
    @Test
    public void test3() {
        File file = new File(filePath);
        // append为false的时候会覆盖文件，true则会追加内容
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("今天是个好日子r测试中文-test");
            writer.append("\n\t追加内容");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInputStreamReader() {
        File file = new File(filePath);
        try (FileInputStream inputStream = new FileInputStream(file);
             InputStreamReader streamReader = new InputStreamReader(inputStream);) {
            int read;
            // 已经转换为字符流，不会中文乱码
            while ((read = streamReader.read()) != -1) {
                System.out.println((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOutputStreamWriter() {
        File file = new File(filePath);
        try (FileOutputStream outputStream = new FileOutputStream(file);
             OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);) {
            streamWriter.write("测试写出");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBufferedReader() {
        // 匿名流使用装饰模式，看BufferedReader的close方法可以知道它会去关闭这些构造函数中的匿名流资源，所以不用自己关闭
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));) {
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBufferedWriter() {
        // 匿名流使用装饰模式，看BufferedReader的close方法可以知道它会去关闭这些构造函数中的匿名流资源，所以不用自己关闭
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(new File(filePath)))) {
            reader.write("测试  大家覅\n 写入");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
