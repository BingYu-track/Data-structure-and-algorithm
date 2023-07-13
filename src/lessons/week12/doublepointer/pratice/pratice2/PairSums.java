package lessons.week12.doublepointer.pratice.pratice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0 数对和
 * @Description: 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 *
 * 示例 1:
 * 输入: nums = [5,6,5], target = 11
 * 输出: [[5,6]]
 *
 * 示例 2:
 * 输入: nums = [5,6,5,6], target = 11
 * 输出: [[5,6],[5,6]]
 * 提示：
 * nums.length <= 100000
 * -10^5 <= nums[i], target <= 10^5
 *
 * @author: bingyu
 * @date: 2023/7/12
 */
public class PairSums {

    public static void main(String[] args) {
        PairSums ps = new PairSums();
        int[] nums = {-2,-1,0,3,5,6,7,9,13,14};
        //[2, 2, 3, 3, 4, 4, 6, 7, 12]
        int target = 12;
        List<List<Integer>> res = ps.pairSums(nums, target);
        System.out.println(res);
    }

    /*
     注意题目中是找两数之和，且一个数字只能被用一次，双指针从两头到中间，可以组合的就放入集合，
     思路: 先进行排序，然后开始双指针比较，然后再用双指针来进行处理，因为已经排序过了，双指针处理就会很方便！

     执行用时：22 ms, 在所有 Java 提交中击败了96.40%的用户
     内存消耗：54.6 MB, 在所有 Java 提交中击败了63.60%的用户
    */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); //先进行排序
        int i = 0;
        int j = nums.length-1;
        while (i<j) {
            int start = nums[i];
            int end = nums[j];
            if (start >= target) { //1.最小值都比target大，说明后面不可能有两数之和等于target的
                return result;
            }
//            if (end > target) { //TODO 末尾数字比target大，j往前移动并跳过,不能这样，因为前面数字可能为负数
//                j--;
//                continue;
//            }
            //执行到这里说明nums[i]<target,nums[j]<=target
            if (start + end < target) { //2.两数之和小于目标值，说明start过小，不可能和后面数字再组成target，i往后移动一位
                i++;
            }else if (start + end > target) { //3.两数之和大于目标值，说明end过大，不可能和前面数字再组成target了，j往前移动一位
                j--;
            }else {
                List<Integer> list = new ArrayList<>(); //说明两数之和刚好等于目标值
                list.add(nums[i]);
                list.add(nums[j]);
                result.add(list);
                i++;
                j--;
            }
        }
        return result;
    }

}
