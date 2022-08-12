package lessons.week5.pratice.hashtable.pratice8;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @version 1.0 剑指 Offer 03. 数组中重复的数字
 * @Description: 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组nums里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：[2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 *
 * @author: bingyu
 * @date: 2022/8/12
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = findRepeatNumber(nums);
        System.out.println(repeatNumber);
    }


    /*
    我的思路: 用哈希、排序都可以
    */
    public static int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0;i<nums.length;i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }else {
                set.add(nums[i]);
            }
        }
        return -1;
    }

}
