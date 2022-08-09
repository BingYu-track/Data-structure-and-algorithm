package lessons.week5.pratice.hashtable.pratice1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @version 1.0  两数之和(哈希表解法)
 * @Description: 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * @author: bingyu
 * @date: 2022/8/8
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] arr = twoSum(nums, target);
        System.out.println(Arrays.toString(arr));
    }

    //TODO:争哥例题
    //时间复杂度O(n)、空间复杂度O(1)
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0;i < nums.length;i++) {
            hashMap.put(nums[i],i);
        }
        for (int i = 0;i < nums.length;i++) {
            if (hashMap.containsKey(target - nums[i])) {
                int k = hashMap.get(target - nums[i]); //获取另一个数的下标
                if (k!=i) {
                    result[0] = i;
                    result[1] = k;
                }
            }
        }
        return result;
    }

}
