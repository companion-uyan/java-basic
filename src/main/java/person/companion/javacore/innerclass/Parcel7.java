package person.companion.javacore.innerclass;

import lombok.Data;

/**
 * 功能描述：匿名内部类基本使用
 *
 * @author companion
 * @date 2021/7/8 12:28
 */
public class Parcel7 {
    /*private Destination getDestination(String s) {
        // 这里返回的是Destination的一个实现类
        return new Destination() {
            @Getter
            @Setter
            private String label;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }*/

    // 上面代码是这里代码的简写
    @Data
    class MyDestination implements Destination {
        private String label;

        @Override
        public String readLabel() {
            return label;
        }
    }

    private Destination getDestination(String s) {
        return new MyDestination();
    }

    // 使用父类有参构造器的匿名内部类
    private Wrapping getWrapping(String s){
        return new Wrapping(s){};
    }

    public static void main(String[] args) {
        Parcel7 parcel7 = new Parcel7();
        Destination destination = parcel7.getDestination("匿名内部类基本使用");
        // 会报错,因为数据类型已经向上转型为Destination,而Destination接口中没有setLabel方法
        // !destination.setLabel();
        // 这里打印为null,因为接口没有办法通过构造函数传递值
        System.out.println(destination.readLabel());

        // 有参构造的匿名内部类,这里只能使用父类的构造器而且不能改动方法实现
        // 和我想得不怎么一样,感觉这样就没有什么用处了
        System.out.println(parcel7.getWrapping("测试").getValue());
    }
}
