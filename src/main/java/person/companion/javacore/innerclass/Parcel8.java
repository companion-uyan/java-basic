package person.companion.javacore.innerclass;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述：通过实例初始化达到匿名内部类的有参构造的效果
 *
 * @author companion
 * @date 2021/7/8 12:28
 */
public class Parcel8 {
    private Destination getDestination(String s) {
        // 这里返回的是Destination的一个实现类
        return new Destination() {
            {
                label = s;
            }

            @Getter
            @Setter
            private String label;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 parcel8 = new Parcel8();
        System.out.println(parcel8.getDestination("通过实例初始化实现初始化字段").readLabel());
    }
}
