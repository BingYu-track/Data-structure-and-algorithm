package lessons.week5.pratice.hashtable.pratice11;

import java.util.HashMap;

/**
 * @version 1.0
 * @Description: 只出现一次的数字--争哥解法
 * @author: bingyu
 * @date: 2022/8/15
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {2,2,1};
        int i = singleNumber(nums);
        System.out.println(i);
    }

    /*
   争哥解法：和我思路基本一致
    */
    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hashTable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int count = 1;
            if (hashTable.containsKey(nums[i])) {
                count += hashTable.get(nums[i]);
            }
            hashTable.put(nums[i], count);
        }
        for (int i = 0; i < nums.length; ++i) {
            int count = hashTable.get(nums[i]);
            if (count == 1) return nums[i];
        }
        return -1;
    }

}
