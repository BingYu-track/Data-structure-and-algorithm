package lessons.week10.dp.pratice.pratice10;

/**
 * @version 1.0  打家劫舍 II
 * @Description: 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 示例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * @author: bingyu
 * @date: 2023/5/31
 */
public class Rob {

    public static void main(String[] args) {
        int[] nums = {1,2,1,1};
        Rob r = new Rob();
        int res = r.rob(nums);
        System.out.println(res);
    }


    /*
     相比之前，是首尾相连了，即第0个房间不偷，最后一个房间是可偷的；第0个房间偷了，最后一个房间是不能偷的
     dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1])
     dp[i][1] = dp[i-1][0] + nums[i]

     第一间房和最后一间房不能同时偷
     1.如果第1间房偷了，最后一间房不能偷处理范围为[0,n-1]，因为最后一间房不能偷所以最后一间房后金额就是n-1的
     2.如果最后一间房偷了，则第1间房不能偷，则处理范围为[1,n]，同理，因为第一间房不能偷，金额为0，只能从第2间房开始

     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：38.7 MB, 在所有 Java 提交中击败了84.28%的用户
    */
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        if (n==1) return nums[0];
        //初始化第0个房间和最后一个房间
        if (n == 2) { //如果只有2个房间和之前一样处理
            dp[0][0] = 0; //不偷
            dp[0][1] = nums[0]; //偷
            dp[1][0] = Math.max(dp[0][0],dp[0][1]); //当前不偷，则上一个房间可偷可不偷
            dp[1][1] = dp[0][0] + nums[1]; //当前偷了，上一个房间只可能是不偷
            return Math.max(dp[n-1][0] , dp[n-1][1]);
        } else {
            //说明不止2个房间此时要分情况进行处理
            //1.先处理第1间房偷的情况，此时处理[0,n-1]范围的房子
            dp[0][0] = 0; //不偷
            dp[0][1] = nums[0]; //偷

            for (int i = 1;i<n-1;i++) {
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
                dp[i][1] = dp[i-1][0] + nums[i];
            }
            int dp1 = Math.max(dp[n-2][0] , dp[n-2][1]); //得到偷第1个房间情况的最大金额

            //处理最后一间房偷的情况，此时处理[1,n]范围的房子，因此这里初始化从第2间房开始
            dp[1][0] = 0; //不偷
            dp[1][1] = nums[1]; //偷

            for (int i = 2;i<n;i++) {
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
                dp[i][1] = dp[i-1][0] + nums[i];
            }
            int dp2 = Math.max(dp[n-1][0] , dp[n-1][1]);
            return Math.max(dp1,dp2);
        }

    }

}
