package person.companion.javacore.innerclass;

import lombok.Data;

/**
 * 功能描述：定义在方法中的内部类
 *
 * @author companion
 * @date 2021/7/8 12:28
 */
public class Parcel5 {
    private Destination getDestination(String label) {
        @Data
        class DestinationImpl implements Destination {
            private String label;

            private DestinationImpl(String label) {
                this.label = label;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }

        return new DestinationImpl(label);
    }

    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        Destination destination = parcel5.getDestination("测试方法中的内部类");
        System.out.println(destination.readLabel());
    }
}
