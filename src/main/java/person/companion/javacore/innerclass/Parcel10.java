package person.companion.javacore.innerclass;

import lombok.Data;

/**
 * 功能描述：静态内部类
 *
 * @author companion
 * @date 2021/7/8 19:00
 */
public class Parcel10 {
    @Data
    static class MyDestination implements Destination{
        private String label;

        @Override
        public String readLabel() {
            return label;
        }
    }

    public static void main(String[] args) {
        MyDestination destination = new MyDestination();
        MyDestination destination1 = new MyDestination();
        destination.setLabel("label");
        destination1.setLabel("destination");
        // 静态内部类与静态字段不同，可以有多个静态内部类的实例
        System.out.println(destination.readLabel());
        System.out.println(destination1.readLabel());
    }
}
