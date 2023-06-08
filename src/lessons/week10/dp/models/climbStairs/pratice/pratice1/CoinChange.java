package lessons.week10.dp.models.climbStairs.pratice.pratice1;

import javax.security.auth.login.CredentialException;

/**
 * @version 1.0  使用爬楼梯的DP模型来解决"零钱兑换问题" TODO:需要重点理解！
 * @Description: 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 * 示例1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 * @author: bingyu
 * @date: 2023/6/7
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int[] coins = {2, 5, 10,1};
        int res = c.coinChange(coins, 27);
        System.out.println(res); //4
    }

    /*
     TODO: 使用爬楼梯DP模型，难点还是在于开始的初始化
     amount总金额相当于总台阶数，coins相当于一步能走的台阶个数，求到达总台阶数时，最少走了几步?
     因为coins中的每个硬币数量无限，就类似与我在一步走coins[i]时，我可以走无限步，只要不超过总台阶数amount即可

     执行用时：15 ms, 在所有 Java 提交中击败了37.37%的用户
     内存消耗：41.8 MB, 在所有 Java 提交中击败了30.33%的用户
    */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Integer max = amount + 1; //因为总共台阶数只有amount个，因此最多的步数也就是amount步，不可能超过amount+1步
        for (int i = 0;i<dp.length;i++) {
            dp[i] = max; //初始还未最大值
        }

        //初始化第1个台阶: 第一步 1/2/5个台阶
        dp[0] = 0;

        /*
        TODO: 从0和1取最小的就是0 + 1=1也符合条件，因为走到2个台阶可以一步走2个，或者走2步，每步1个台阶，因此满足上面状态转移方程的等式，
         因此dp[0]=0初始化没问题

          dp[2] = Math.min(dp[2-2],dp[2-1]) + 1;
          dp[3] = Math.min(dp[3-2],dp[3-1]) + 1;
         */

        for (int i = 1;i<=amount;i++) { //i表示台阶数，从第1个台阶开始
            for (int j = 0;j<coins.length;j++) { //每步可能走的台阶数
                //TODO： i - coins[j]>=0表示每步走的台阶数小于等于当前台阶数才是有意义的，否则当前台阶数走不了
                // dp[i- coins[j]]!=max表示上一阶段的位置是可达的，因此可以推导到dp[i]当前阶段
                if (i - coins[j]>=0 && dp[i- coins[j]]!=max) {
                    //另外由于是求最小值，因此那些无法到达的台阶都应该赋值为最大值，因此在程序的开始我们就将所有默认值初始化为最大值
                    dp[i] = Math.min(dp[i],dp[i- coins[j]]);
                }
            }
            //执行到这里，说明dp[i]已经取到了上一阶段所有步中最少的，因此当前只要再加1步即可(必须是可达的)
            if (dp[i]!=max) dp[i] += 1;
        }
        if (dp[amount]==max) return -1;
        return dp[amount];
    }

    /*
      方法一: 按照完全背包的dp模型，就是硬币放还是不放入背包，然后到达指定数值位置计算dp(每种硬币都是无限的)
      状态转移方程: dp[i][j] = Math.min(  dp[i-1][j-k*coins[i-1]]  )
      TODO:为什么是dp[i-1][j-k*coins[i]]不是dp[i-1][j-k*coins[i-1]?
         因为当前阶段的硬币最少个数 =  上一阶段硬币最少个数 + 当前阶段硬币可放的个数
    */
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<=amount;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //初始化第0个硬币,由于可以放无限个，因此最多是11/coins[0]个硬币
        for (int i = 0;i < amount/coins[0];i++) { //这里i就是可以放入硬币的个数
            dp[0][coins[0] * i] = i;
        }

        for (int i = 1;i<n;i++) { //从第1个硬币开始处理，为横坐标
            for(int j = 0;j<=amount;j++) { //所有可能的金额值，为纵坐标
                int num = j / coins[i]; //在指定金额为j时，放入第i个硬币的最大个数
                //因此dp[i][j]可能通过dp[i-1][j-num*coins[i-1]](num为变量)中所有的位置进来，因此我们要求其等式中最小的那一个
                //求出最小的那个后，就是dp[i][j]上一阶段的最小硬币个数，因此dp[i][j]的硬币个数就是上一阶段加上此时num的硬币个数即可
                for (int k = 0;k<=num;k++) {
                    // if (j-k*coins[i]>=0) { //不需要这个，因为前面已经k就是来自j / coins[i]得到的，所以不会出现j-k*coins[i]为负数的情况
                    if(dp[i-1][j-k*coins[i]]!=Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k*coins[i]]) + k;
                    }
                }
            }
        }

        //如果
        if(dp[n-1][amount] == Integer.MAX_VALUE) return -1;
        return dp[n-1][amount];
    }

}
