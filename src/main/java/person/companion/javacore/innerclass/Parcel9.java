package person.companion.javacore.innerclass;

/**
 * 功能描述：如果定义一个匿名内部类，并且希望它使用一个在其外部定义的对象，那么编译器会要求器参数引用时final的
 * 未验证出来-java8
 *
 * @author companion
 * @date 2021/7/8 12:28
 */
public class Parcel9 {
    private MyDestination getDestination(String s) {
        return new MyDestination(s);
    }

    private class MyDestination implements Destination {
        MyDestination(String label) {
            this.label = label;
        }

        private String label;

        public String readLabel() {
            return label;
        }
    }

    public static void main(String[] args) {
        Parcel9 parcel9 = new Parcel9();
        MyDestination destination = parcel9.getDestination("测试");
        System.out.println(destination.readLabel());
    }
}
