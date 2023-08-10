package lessons.week12.psstatistics.pratice.pratice2;

import java.util.Arrays;

/**
 * @version 1.0  除自身以外数组的乘积
 * @Description: 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 题目数据 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 *
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在32 位 整数范围内
 * 进阶：你可以在 O(1)的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * @author: bingyu
 * @date: 2023/7/27
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        ProductExceptSelf pes = new ProductExceptSelf();
        int[] nums = {-1,1,0,-3,3};
        int[] result = pes.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }

    /*
      题目要求取得一个数组并是result[i]等于nums中除nums[i]之外其余各元素的乘积.元素可以为负数和0
      如果单纯使用暴力解法时间复杂度为O(n^2)
      我的思路: 1.一个数组记录nums[i]前缀积(不包含自己)
              2.另一个数组记录nums[i]后缀积(不包含自己)
              3.最后对应下标两两相乘即是我们要求的

      执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：53 MB, 在所有 Java 提交中击败了5.02%的用户
      争哥的思路和我一样
    */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        //1.求nums[i]的前缀积
        for (int i = 0;i < n;i++) {
            if (i == 0) {
                prefix[i] = 1; //注意前缀积和后缀积都要从1开始，否则从0开始的话，无轮怎么乘都是0了
            }else {
                prefix[i] = prefix[i-1] * nums[i-1];
            }
        }
        //2.求nums[i]的后缀积
        for (int i = n-1;i>=0;i--) {
            if (i == n-1) {
                suffix[i] = 1;
            }else {
                suffix[i] = suffix[i+1] * nums[i+1];
            }
        }
        //然后把两者数组元素直接相乘即可!
        for (int i = 0;i<suffix.length;i++) {
            suffix[i] = suffix[i] * prefix[i];
        }
        return suffix;
    }

}
