package person.companion.javacore.innerclass;

import lombok.Data;

/**
 * 功能描述：定义在作用域中的内部类
 *
 * @author companion
 * @date 2021/7/8 12:28
 */
public class Parcel6 {
    private Destination getDestination(boolean flag) {
        Destination destination = null;
        // 如果flag为true,返回一个对象,否则返回null
        if (flag) {
            @Data
            class DestinationImpl implements Destination {
                private String label;

                public DestinationImpl(String label) {
                    this.label = label;
                }

                @Override
                public String readLabel() {
                    return label;
                }
            }

            destination = new DestinationImpl("定义在作用域中的内部类");
        }

        return destination;
    }

    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        System.out.println(parcel6.getDestination(true).readLabel());
        System.out.println(parcel6.getDestination(false));
    }
}
