package lessons.week10.dp.pratice.pratice3;

import java.util.Arrays;

/**
 * @version 1.0  零钱兑换(背包模型)
 * @Description: 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
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
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * @author: bingyu
 * @date: 2023/5/16
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 6;
        int res = c.coinChange3(coins, amount);
        System.out.println(res);
    }


    /*使用完全背包思路处理
    执行用时：449 ms, 在所有 Java 提交中击败了5.01%的用户
    内存消耗：42.8 MB, 在所有 Java 提交中击败了5.07%的用户
     coins每个硬币数量是无限的，求能凑出amount的最少硬币个数，这明显是完全背包问题
     dp[i][j] = Math.min(dp[i-1][j-k*coins[i]] + k........)
    */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<=amount;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //初始化第0行，k为能放入物品的个数，放入后物品的总重量不能超过最大值amount
        for (int k = 0;k<=amount/coins[0];k++) {
            dp[0][k * coins[0]] = k;
        }

        for (int i = 1;i < n;i++) {
            for (int j = 0;j<=amount;j++) {
                int num = j / coins[i];
                for (int k = 0;k<=num;k++) {
                    if (dp[i-1][j-k*coins[i]]!=Integer.MAX_VALUE) { //dp[i-1][j-k*coins[i]] + k这里可能会越界的
                        dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k*coins[i]] + k);
                    }
                }
            }
        }
        if (dp[n-1][amount] == Integer.MAX_VALUE) return -1;
        return dp[n-1][amount];
    }

    /*
     使用完全背包的思路处理，并进行了空间优化
     执行用时：446 ms, 在所有 Java 提交中击败了5.01%的用户
     内存消耗：41.9 MB, 在所有 Java 提交中击败了12.86%的用户
    */
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        for (int j = 0;j<=amount;j++) {
            dp[j] = Integer.MAX_VALUE;
        }
        //初始化第0行，k为能放入物品的个数，放入后物品的总重量不能超过最大值amount
        for (int k = 0;k<=amount/coins[0];k++) {
            dp[k * coins[0]] = k;
        }

        for (int i = 1;i < n;i++) {
            for (int j = amount;j>=0;j--) {
                int num = j / coins[i];
                for (int k = 0;k<=num;k++) {
                    if (dp[j-k*coins[i]]!=Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j],dp[j-k*coins[i]] + k);
                    }
                }
            }
        }
        if(dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }

    /*
    TODO： 该方法比较难想
     leetcode官方题解,不太理解
     官方题解的表格是以背包重量为行，物品种类为列，以[1,2,5],amount=6为例
     f(amount)表示到达重量amount最少需要的物品个数，假设我们知道了f(0) ~ f(amount-1)的所有值
     则f(amount) = Math.min( f(amount - coins[0]),f(amount - coins[1])......,f(amount-coins[j]) ) + 1;
     如何理解上面的等式?
     就是到达amount这个总和，必定有最后一个硬币的面额加入其中，这个最后一个面额的硬币可能是coins中任何一个数字，因此
     Math.min(f(amount - coins[0]),f(amount - coins[1])......,f(amount-coins[j]))就是取所有达到amount的可能中的
     最小值。
     j表示最后一枚硬币的面额

     TODO: 重点理解掌握该解题方法
    */
    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0; //初始化第0行
        for (int i = 1; i <= amount; i++) { //从1到amount
            for (int j = 0; j < coins.length; j++) { //遍历coins中的每一个数
                if (coins[j] <= i) { //因为后面我们需要用i - coins[j]，因此coins[j] <= i才能继续向下执行
                    //因为每个f(w)的最小值等于取f(w-j)的所有值然后取其最小值
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
            //TODO: 执行到这里就得到dp[i]的最小值，即到达面值总额为i时，所需要最少的硬币个数！
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
