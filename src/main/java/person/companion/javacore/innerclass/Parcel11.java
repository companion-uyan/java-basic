package person.companion.javacore.innerclass;

/**
 * 功能描述：通过实现接口使用其内部类
 *
 * @author companion
 * @date 2021/7/8 19:12
 */
public class Parcel11 implements ClassInterface{
    @Override
    public void readLabel() {

    }

    public static void main(String[] args) {
        new InnerClass().print();
    }
}
