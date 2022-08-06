package person.companion.basic;

import org.junit.Test;

import java.util.*;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/16 10:42
 */
public class CollectionTest {
    @Test
    public void testArrayList(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        // 插入方法一定会引起复制，可能会扩容
        // 删除方法一定会引起复制
        // 插入方法可能会扩容
        strings.add(2, "d");
        // 可以调用ensureCapacity手动扩容，也可以构造方法设置初始容量来避免大数据重复扩容
        strings.ensureCapacity(50);
        System.out.println(strings);
    }

    @Test
    public void testHashSet(){
        HashSet<HashSetDTO> dtoList = new HashSet<>();
        System.out.println(dtoList.add(new HashSetDTO("zs", 1)));
        System.out.println(dtoList.add(new HashSetDTO("li", 10)));
        // HashSet的重复值不会覆盖
        System.out.println(dtoList.add(new HashSetDTO("li", 15)));
        dtoList.add(new HashSetDTO("ww", 10));
        System.out.println(dtoList);
    }

    @Test
    public void testHashMap(){
        HashMap<HashSetDTO, String> map = new HashMap<>();
        System.out.println(map.put(new HashSetDTO("zs", 10), "zs"));
        System.out.println(map.put(new HashSetDTO("ls", 15), "ls"));
        // 会覆盖原来的value的值，key值不会变
        System.out.println(map.put(new HashSetDTO("zs", 15), "zs1"));
        System.out.println(map);

        // 测试HashMap容量问题
        HashMap<String, String> hashMap = new HashMap<>(15);
        System.out.println(hashMap.size());
        hashMap.put("1", "2");
        System.out.println(hashMap.size());
    }

    @Test
    public void testLinkedHashMap(){
        Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
        map.get("key" + 3);
        for (String value : map.keySet()) {
            System.out.println(value);
        }
    }

    @Test
    public void testTreeMap(){
        // 如果类继承了Comparable接口，构造器使用了比较器，以构造器的方法优先
        TreeMap<HashSetDTO, String> treeMap = new TreeMap<>(new Comparator<HashSetDTO>() {
            @Override
            public int compare(HashSetDTO o1, HashSetDTO o2) {
                return -1;
            }
        });
        treeMap.put(new HashSetDTO("zs", 10), "zs");
        treeMap.put(new HashSetDTO("ls", 15), "zs");
        treeMap.put(new HashSetDTO("zs", 1), "zs1");
        System.out.println(treeMap);
    }

    static class HashSetDTO implements Comparable{
        private String name;
        private int age;

        @Override
        public int hashCode() {
            if(age == 10 || age == 15){
                return 1;
            }

            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            HashSetDTO dto = (HashSetDTO) obj;
            return dto.name.equals(name);
        }

        public HashSetDTO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "HashSetDTO{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            HashSetDTO setDTO = (HashSetDTO) o;
            return setDTO.age - this.age;
        }
    }
}
