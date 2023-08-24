package leetcode.dp.MaxSubArray;

/**
 * @version 1.0  最大子数组和
 * @Description: 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * @author: bingyu
 * @date: 2023/8/23
 */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray ms = new MaxSubArray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int res = ms.maxSubArray(nums);
        System.out.println(res);
    }

    /*
    找出最大子数组和，且必须是连续的
    1.定义dp
     dp[i][j]表示 下标i~j范围的子数组的sum和，因此这个数组长度应该是dp[nums.length][nums.length]
     每行表示从各个元素出发是起始下标位置因此是nums.length
     每列表示当前元素到其它元素的下标位置，例如dp[0][2]表示下标0到下标2的数组范围的sum和,也可以自己和自己组合例如dp[0][0]就表示第一个元素为一个子数组
     dp[2][0]为下标2到下标0数组范围的sum和

    2.动态转移方程
      dp[i][j] = dp[i][j-1] + nums[j]  (i<=j)
      dp[i][j] = dp[i-1][j] - nums[i-1] (i>=j)
      dp[i][j] = dp[j][i]
    上面是错误解法(因为空间复杂度太高)

    TODO:
     但是发现按照上面定义，二维数组用的空间太大，修改成如下定义:
     dp[i]表示到i结尾的子数组的最大和，这里动态转移方程还是不是太理解
    dp[i] = dp[i-1] + nums[i] (当前i位置前面有元素)
    dp[i] = nums[i] (前面没有元素)

    */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int maxSum = nums[0];
        dp[0] = nums[0];
        for (int i = 1;i<nums.length;i++) {
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]); //当前i位置元素末尾最大和
            maxSum = Math.max(maxSum,dp[i]);
        }
        return maxSum;
    }

}
