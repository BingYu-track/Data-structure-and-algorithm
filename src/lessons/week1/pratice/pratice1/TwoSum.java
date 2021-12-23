package lessons.week1.pratice.pratice1;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
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
 * @author: bingyu
 * @date: 2021/12/20
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] ints = twoSum(nums,6);
        System.out.println(Arrays.toString(ints));
    }

    //我的解法：暴力穷举
    public static int[] twoSum(int[] nums,int target) {
        //1.用每一个数和其它数字进行相加和组合
        for (int i = 0; i<nums.length; i++) {
            for (int j = i+1; j<nums.length; j++) { //j是后面需要加的数
                if ((nums[i] + nums[j]) == target) {
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return null;
    }
}
