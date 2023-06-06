package lessons.week10.dp.pratice.pratice13;

/**
 * @version 1.0  最佳买卖股票时机含冷冻期 TODO:这题太复杂太难了，就是一个多状态转移的文字逻辑游戏，期望后面不会再遇到
 * @Description: 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为1天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 *
 * 提示：
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 * @author: bingyu
 * @date: 2023/6/5
 */
public class MaxProfit {

    public static void main(String[] args) {
        MaxProfit profit = new MaxProfit();
        int[] prices = {6,1,3,2,4,7};
        int res = profit.maxProfit(prices);
        System.out.println(res); //6
    }

    /*
      题目要求"必须在再次购买前出售掉之前的股票"，这就说明必须记录状态"是否持有该股票"，这次卖出没有手续费，但是多了一个冷冻期;
      卖出股票后，不能立马买入，要等一天冷冻期，冷冻期过了才能继续买，这个冷冻期，如何在dp上进行体现?

      一、不持有  1.上一阶段是持有状态并卖出了，然后现在处于["冷冻期不持有状态"]；
                2.上一阶段已经是不持有状态，要么处于冷冻期，要么是非冷冻期；无轮上一阶段是冷冻还是非冷冻，当前阶段肯定属于["非冷冻期，不持有状态"]
                3.上一阶段是持有状态，然后[现在卖出，变成不持有的状态]。

        TODO: 因为题目中对持有股票是有条件，上一阶段无论是非冷冻期还是冷冻期，当前都可以买
      二、持有，  4.上一阶段处于冷冻期，现在处于非冷冻期，刚买。
                5.上一阶段处于非冷冻期一直都是持有，没有卖出过。
状态转移方程--
dp[i][0] ("冷冻期不持有状态")  =  dp[i-1][2] (上一阶段刚卖出，也就是不持有的第3种状态)
dp[i][1] ("非冷冻期，不持有状态") = Math.max(dp[i-1][0](不持有冷冻期) , dp[i-1][1](不持有非冷冻期))
dp[i][2] ("现在卖出，变成不持有的状态") = Math.max(dp[i-1][3] + prices[i](上一阶段刚买，持有状态) , dp[i-1][4]+prices[i](上一阶段一直持有，就没卖过) )
dp[i][3] (当前买入变成持有状态) = Math.max(dp[i-1][0] - prices[i]，dp[i-1][1] - prices[i])(上一阶段处于冷冻期/非冷冻期，然后当前阶段都可以买入)
dp[i][4] (一直是买入的持有状态，没有卖出过) =  Math.max(dp[i-1][3] , dp[i-1][4]) (上一阶段可能是刚买入变成持有状态，也可能是一直就没卖出，一直是持有状态)

执行用时：1 ms, 在所有 Java 提交中击败了80.03%的用户
内存消耗：39.6 MB, 在所有 Java 提交中击败了56.01%的用户
    */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5]; //dp[i][0]表示 "不持有"状态1  dp[i][1]表示"不持有"状态2  dp[i][2]表示"不持有"状态3
                                   // dp[i][3]表示 "持有"状态4  dp[i][4]表示"持有"状态5

        //初始化第0行，开始要么持有，要么不持有
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = - prices[0]; //持有，刚买
        dp[0][4] = - prices[0]; //持有，一直没卖出

        for (int i = 1;i<n;i++) {
            dp[i][0] =  dp[i-1][2]; //冷冻期不持有 = 来自"上一阶段刚卖出"
            dp[i][1] = Math.max(dp[i-1][0] , dp[i-1][1]); //非冷冻期不持有 = 来自"冷冻期不持有/来自非冷冻期不持有"
            dp[i][2] = Math.max(dp[i-1][3] + prices[i] , dp[i-1][4] + prices[i]); //现在卖出不持有 = 来自"刚买后持有/一直持有"再卖出
            dp[i][3] = Math.max(dp[i-1][0] - prices[i] , dp[i-1][1] - prices[i]); //处于"冷冻期持有/非冷冻期持有"然后买入
            dp[i][4] =  Math.max(dp[i-1][3],dp[i-1][4]);
        }
        int max = max(dp[n - 1][0], dp[n - 1][1],dp[n-1][2],dp[n-1][3],dp[n-1][4]);
        return max;
    }

    private int max(int i, int i1, int i2, int i3, int i4) {
        int max1 = Math.max(i, i1);
        int max2 = Math.max(i2, i3);
        int max = Math.max(max1, max2);
        return Math.max(max,i4);
    }

}
