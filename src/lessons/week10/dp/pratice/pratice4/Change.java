package lessons.week10.dp.pratice.pratice4;

/**
 * @version 1.0  零钱兑换 II(背包模型)
 * @Description: 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值互不相同
 * 0 <= amount <= 5000
 *
 * @author: bingyu
 * @date: 2023/5/16
 */
public class Change {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 5;
        Change c = new Change();
        int res = c.changeSpaceOptimize(amount, coins); //4
        System.out.println(res);
    }


    /*
      先使用完全背包问题思路进行解题，现在要求的是组合数
      执行用时：32 ms, 在所有 Java 提交中击败了5.80%的用户
      内存消耗：45 MB, 在所有 Java 提交中击败了21.44%的用户
    */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        //初始化第0行
        for (int k = 0;k<=amount/coins[0];k++) { //第0行物品放入背包,k为在第0行能放入背包的个数
            dp[0][k * coins[0]] = 1;
        }

        //开始正式进行状态转移(先物品，再背包)
        for (int i = 1;i < n;i++) {
            for (int j = 0;j <= amount;j++) {
                int num = j / coins[i];  //意思是到达重量j，只用物品i的话，需要k个物品i；因此到达重量j实际用到的物品i数目是0~k个，因为上一层也可能会有物品放入
                for (int k = 0;k<=num;k++) { //然后遍历k，从0~k中找上一层是否满足j-k*coins[i]的位置，这样dp[i][j]就是可达的
                    dp[i][j] += dp[i-1][j-k*coins[i]];
                }
            }
        }

        //(先背包，再物品，发现也是对的)
//        for (int j = 0;j <= amount;j++) {
//            for (int i = 1;i < n;i++) {
//                int num = j / coins[i];
//                for (int k = 0;k<=num;k++) {
//                    dp[i][j] += dp[i-1][j-k*coins[i]];
//                }
//            }
//        }

        return dp[n-1][amount];
        //TODO: 为啥使用二维数组，无论遍历顺序如何颠倒，都没问题？为何无论是先背包后物品，还是先物品后背包，得到的装法都是组合数，而不是排列数?

    }


    //TODO: 进行空间优化，使用一维数组来进行dp
    public int changeSpaceOptimize(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];

        for (int k = 0;k<=amount/coins[0];k++) { //第0行物品放入背包,k为在第0行能放入背包的个数
            dp[k * coins[0]] = 1;
        }

        //2.正式开始进行状态转移
        for (int i = 1;i < n;i++) { //物品的行
            for (int j = amount;j>=0;j--) { //背包容量的列
                int num = j / coins[i];
                for (int k = 0;k<=num;k++) {
                    dp[j] += dp[j-k*coins[i]];
                }
            }
        }
        return dp[amount];
    }




    /*
    执行用时：3 ms, 在所有 Java 提交中击败了44.61%的用户
    内存消耗：39 MB, 在所有 Java 提交中击败了66.36%的用户
     不使用背包问题的思路解题:
     这里f(amount)表示到达amount总额所使用的组合总数
     f(amount) = f(amount-coins[0]) + f(amount-coins[1]) +....+ f(amount-coins[j]); j为最后一枚硬币，因为最后一枚硬币可以是coins数组中的任意1个
       TODO：注意题目中求的是组合数，不是排列数，组合数是不强调元素之间的顺序的，比如2、2、1和1、2、2都是同一个组合，但是不同排列，这点要注意
        审题!
    */
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        //初始化第0行
        dp[0] = 1;

        //TODO： 为何先背包，再物品就不行?因为放到外面会有重复的组合,这是为什么?
        //这里是先遍历背包容量，再遍历物品，是从前往后的列，从上到下进行考察,发现得到的排列数
//        for (int i = 0;i <= amount;i++) { //背包
//            for (int j = 0;j < n;j++) { //物品
//                if (i >= coins[j]) {
//                    dp[i] += dp[i - coins[j]];
//                    System.out.print("f("+ i + ")+= f(" + (i - coins[j]) + ") ");
//                }
//            }
//            System.out.println();
//        }

        //先遍历物品，再遍历背包容量
        for (int j = 0;j < n;j++) { //物品不能从1开始，因为要累加dp[i-最后一个硬币面值]的组合数
            for (int i = 0;i <= amount;i++) {
                if (i >= coins[j]) {
                    dp[i] += dp[i - coins[j]];
                    System.out.print("f("+ i + ")+= f(" + (i - coins[j]) + ") ");
                }
            }
            System.out.println();
        }

        return dp[amount];
    }


    /*
      f(1)+= f(0) f(2)+= f(1) f(3)+= f(2) f(4)+= f(3) f(5)+= f(4)
      f(2)+= f(0) f(3)+= f(1) f(4)+= f(2) f(5)+= f(3)
      f(5)+= f(0)
    */
}
