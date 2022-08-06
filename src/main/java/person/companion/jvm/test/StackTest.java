package person.companion.jvm.test;

/**
 * 功能描述：栈的基础测试
 * 运行代码之后找到target中的字节码文件
 * 右键open in terminal之后运行javap -v StackTest.class
 *
 * @author companion
 * @date 2021/7/29 11:17
 */
public class StackTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = a + b;
    }

    int add(int a,int b){
        boolean test = false;
        return a + b;
    }
}
