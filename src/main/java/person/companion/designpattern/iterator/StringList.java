package person.companion.designpattern.iterator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：这里使用一个String的集合来完成遍历
 *
 * @author companion
 * @date 2021/7/8 10:16
 */
@Data
@NoArgsConstructor
public class StringList implements Container {
    /**
     * 定义一个index，用于获取当前迭代的索引
     */
    private int index;

    /**
     * 需要遍历的数据存放到这里
     */
    private String[] strings;

    public StringList(String[] strings) {
        this.strings = strings;
    }

    @Override
    public StringListIterator iterator() {
        return new StringListIterator();
    }

    /**
     * 这里使用内部类来实现Iterator接口，隐藏StringList的迭代实现并方便获取到外部类的数据
     */
    private class StringListIterator implements Iterator {
        @Override
        public boolean end() {
            return strings != null && index == strings.length;
        }

        @Override
        public String next() {
            return strings[index++];
        }

        @Override
        public Object current() {
            return strings[index];
        }
    }

    public static void main(String[] args) {
        String[] value = {"张三", "李四", "王五", "赵二", "洪一"};
        StringList list = new StringList(value);
        StringListIterator iterator = list.iterator();
        while (!iterator.end()){
            System.out.println(iterator.next());
        }
    }
}
