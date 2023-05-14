package lessons.week10.dp.models.backpack.fullbackpack;

/**
 * @version 1.0  完全背包问题3
 * @Description: n个物品，重量分别为weight[i](i=0~n-1)，每个物品有无限多个，选择一些物品装入背包，在不超过背包总重量w的前提下
 *    正好装满背包最少需要多少物品?
 * @author: bingyu
 * @date: 2023/5/12
 */
public class FullBack3 {

    public static void main(String[] args) {
        FullBack3 f = new FullBack3();
        int[] weight = {2,3,2,4,6,3};
        int num = f.bage(weight, weight.length, 9);
        System.out.println(num);
    }

    /*
     我的解法:
     用int类型二维数组来存储状态，dp[i][j]表示在考察第i行物品时，到重量j，需要放入的最少物品个数
     0 ~ k
    dp[i][j]只可能通过 dp[i-1][j-k*weight[i]]进来，如果dp[i-1][j-k*weight[i]]不为-1，则k就是当前位置在当前行放入的最少物品个数
    dp[i][j] = dp[i-1][j-k*weight[i]] + k; 会不会有重复路径进来
    比如: dp[1][6] 可以从dp[0][0]=0放入2个物品,也可以从dp[0][6]=3不放入物品，两个路径都是到达dp[1][6],但是放入的物品个数不一样，
    因此是存在重复的路径，我们取其中的最小值，所以初始化应该用int类型的最大值
    */
    private int bage(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        //初始化值
        for (int i = 0;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //初始化第0行
        for (int k = 0;k<=w/weight[0];k++) { //k为放入物品的个数
            dp[0][k * weight[0]] = k;
        }

        //开始正式进行状态转移
        for (int i = 1;i < n;i++) {
            for (int j = 0;j <= w;j++) {
                int num = j / weight[i]; //该行物品能放入背包的最大个数
                for (int k = 0;k <= num;k++) {
                    //因为前面已经是j / weight[i]了，所以j - k*weight[i]不可能为负数了
                    if (dp[i-1][j - k*weight[i]]!=Integer.MAX_VALUE) { //如果上一阶段可达
//                        if (i==1 && j==6) {
//                            System.out.println("可以来自: dp[" + (i-1) + "]["  + (j - k*weight[i]) + "] 放入" + k + "个物品");
//                        }
                        //之所以这样处理进行比较大小，是因为dp[i][j]在之前可能就已经有值了
                        dp[i][j] = Math.min(dp[i][j],dp[i-1][j - k*weight[i]] + k);
                        //break； 这里不能用break，因为进入dp[i][j]途径可能会有多个，需要将所有可达该重量位置的k物品个数都进行比较，然后取
                        //最小值
                    }
                }
            }
        }
        return dp[n-1][w];
    }

    /*
      [0,2147483647,1,2147483647,2,2147483647,3,2147483647,4,2147483647]
      [0,2147483647,1,1,2,2,2,3,3,3]
      [0,2147483647,1,1,2,2,2,3,3,3
      [0,2147483647,1,1,1,2,2,2,2,3]
      [0,2147483647,1,1,1,2,1,2,2,2]
      [0,2147483647,1,1,1,2,1,2,2,2]
     */

    //争哥解法:一模一样
    private int bage2(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        //初始化值
        for (int i = 0;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //初始化第0行
        for (int k = 0;k<=w/weight[0];k++) { //k为放入物品的个数
            dp[0][k * weight[0]] = k;
        }

        //开始正式进行状态转移
        for (int i = 1;i < n;i++) {
            for (int j = 0;j <= w;j++) {
                int num = j / weight[i]; //该行物品能放入背包的最大个数
                for (int k = 0;k <= num;k++) {
                    if (dp[i-1][j-k*weight[i]]!=Integer.MAX_VALUE && dp[i-1][j-k*weight[i]] + k < dp[i][j]) {
                        dp[i][j] = dp[i-1][j-k*weight[i]] + k;
                    }
                }
            }
        }
        return dp[n-1][w];
    }
}
