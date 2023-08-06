package lessons.week12.doublepointer.pratice.pratice3;

import java.util.Arrays;

/**
 * @version 1.0  两数之和
 * @Description: 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
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
 * 提示：
 *
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * @author: bingyu
 * @date: 2023/7/13
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {-1,-2,-3,-4,-5};
        int target = -8;
        TwoSum t = new TwoSum();
        int[] res = t.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

    /*
    注意：这里是要求原数组元素的下标位置，如果排序了，就会改变原数组，因此我们只能复制一遍数组,另外还需要一个数组来
    记录原数组位置是否被使用过!因此该题使用双指针的话，空间复杂度会很高
    执行用时：3 ms, 在所有 Java 提交中击败了53.70%的用户
    内存消耗：42.7 MB, 在所有 Java 提交中击败了16.72%的用户
    */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int[] arr = Arrays.copyOf(nums, nums.length); //复制一个数组用来排序，为后面的双指针操作做数据准备
        boolean[] used = new boolean[nums.length]; //
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        while (i<j) {
            int start = arr[i];
            int end = arr[j];
            //1.if (start>target) break; 本意是，如果start最小值都比目标值大，则和后面数字相加，肯定大于目标值，但是如果start后面全是负数，就不一定了
            if (start + end < target) {
                i++;
            }else if (start + end > target) {
                j--;
            }else {
                //在原数组找出start和end所在下标
                boolean [] tmp = new boolean[2]; //3.使用该数组来区分result是否已经用过了，防止重复赋值
                for (int k = 0;k < nums.length;k++) { //遍历原数组，从中寻找和start,end数字一样的元素
                    if (nums[k] == start && !used[k] && !tmp[0]) { //找到start在原数组的下标
                        used[k] = true;
                        tmp[0] = true;
                        result[0] = k;
                    }
                    if (nums[k] == end && !used[k] && !tmp[1]) { //找到end在原数组的下标
                        used[k] = true;
                        tmp[1] = true;
                        result[1] = k;
                    }
                }
                i++;
                j--;
            }
        }
        return result;
    }

}
