package person.companion.javacore.generic.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 功能描述：堆污染
 * 当参数化类型的变量(引用)指向一个非参数化类型的对象时就可能会发生堆污染
 *
 * @author companion
 * @date 2021/7/29 16:31
 */
public class HeapPollution {
    public static void main(String[] args) {
        // pollutionSet();
        pollutionList();
    }

    private static void pollutionList() {
        // 定义一个参数化类型引用
        List<Integer> typeList;
        List noTypeSet = new ArrayList();
        typeList = noTypeSet;
        noTypeSet.add(5);
        // 这里添加String就是不是说明已经堆污染??
        noTypeSet.add("ooo");

        System.out.println(typeList.get(1));
        // 这里会报强制类型转换错误
        // Integer integer1 = typeList.get(1);
        System.out.println(typeList);
    }

    private static void pollutionSet() {
        /**
         * 与上面的List直接存值不同，因为Set存值之前先要进行类型转换才能比较数据
         * 因此在noTypeSet.add(5)的时候已经确定其类型为Integer,再添加String类型就会报错
         */
        Set noTypeSet = new TreeSet();
        noTypeSet.add(5);
        noTypeSet.add("ooo");
    }
}
