package lessons.week10.dp.models.climbStairs.pratice.pratice3;

/**
 * @version 1.0  使用最小花费爬楼梯
 * @Description: 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第i个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。请你计算并返回达到楼梯顶部的最低花费。
 *
 *
 * 示例 1：
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15
 *
 * 示例 2：
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 *
 * 提示：
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 * @author: bingyu
 * @date: 2023/6/8
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        MinCostClimbingStairs m = new MinCostClimbingStairs();
        int[] cost = {10,15,20};
        int res = m.minCostClimbingStairs(cost);
        System.out.println(res);
    }

    /*
     按照题目的意思: cost的元素个数就是代表楼梯的总台阶数；每个台阶向上爬需要支付一定的费用cost[i]。例如cost = [10,15,20]中，元素个数为3
     表示该楼梯一共有3个台阶，从第0个台阶爬需要支付10元即cost[0]；同样如果从第1个台阶往上爬要支付cost[1]即15元；每次可以爬一个或者2个台阶

    注意这里爬到顶部不是爬到最后一个元素，而是要到数组外才算是到楼顶
     返回达到楼梯顶部的最低花费
    */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length; //总台阶个数
        int[] dp = new int[n+1]; //TODO：注意这里，因为要到数组外才算是到楼顶，因此需要增加一个空间作为楼顶
        for (int i = 0;i<n;i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        //1.定义dp[i]的含义 -- 这里dp[i]表示在超过第i个台阶时需要支付的最低费用

        //2.定义状态转移方程: dp[i] = Math.min(dp[i-1] + cost[i-1] , dp[i-2] + cost[i-2]);

        //3.dp数组如何初始化，因为题目要求只能从0或者1开始爬
        dp[0] = 0; //在第0个台阶，还没爬，不用支付费用
        dp[1] = Math.min(0 , dp[0] + cost[0]); //有2种可能 1.从下标为1的台阶开始，还没爬，不需要费用 2.从下标0开始爬到了dp[1]

        //4.确定遍历顺序，从上面的状态转移方程可以看到肯定是要从小到大遍历
        for (int i = 2;i<=n;i++) { //从第2个台阶开始
            dp[i] = Math.min(dp[i-1] + cost[i-1] , dp[i-2] + cost[i-2]);
        }
        //5.举例推导dp数组
        return dp[n];
    }

}
