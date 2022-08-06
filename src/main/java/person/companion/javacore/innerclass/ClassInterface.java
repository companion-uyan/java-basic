package person.companion.javacore.innerclass;

/**
 * 功能描述：定义一个接口，有一个内部类InnerClass共所有实现类使用
 *
 * @author companion
 * @date 2021/7/8 19:09
 */
public interface ClassInterface {
    void readLabel();
    class InnerClass{
        void print(){
            System.out.println("classInterface");
        }
    }
}
