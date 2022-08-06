package person.companion.basic.exercise;

import org.junit.Test;

import java.util.HashMap;

/**
 * 功能描述：给定一个整数数组nums和一个目标值target，找出数组中和为目标值的两个数(同样的元素只能用一次)
 *
 * @author companion
 * @date 2020/5/25 10:12
 */
public class SumOfArrayTest {
    final int[] nums = {2, 7, 9, 11, 15};
    final int target = 9;

    @Test
    public void test1() {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    System.out.println("i,j:" + i + "," + j);
                    return;
                }
            }
        }
    }

    @Test
    public void test2() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement)) {
                System.out.println("i,j:" + i + "," + hashMap.get(complement));
                return;
            }
        }
    }

    @Test
    public void test3(){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
            int complement = target - nums[i];
            if (hashMap.containsKey(complement)) {
                System.out.println("i,j:" + i + "," + hashMap.get(complement));
                return;
            }
        }
    }
}
