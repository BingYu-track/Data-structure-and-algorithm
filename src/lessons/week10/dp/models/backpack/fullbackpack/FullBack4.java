package lessons.week10.dp.models.backpack.fullbackpack;

/**
 * @version 1.0  完全背包问题4
 * @Description: n个物品，重量分别为weight[i](i=0~n-1)，每个物品有无限多个，选择一些物品装入背包，在不超过背包总重量w的前提下
 *   装满背包有多少种装法?
 * @author: bingyu
 * @date: 2023/5/12
 */
public class FullBack4 {

    public static void main(String[] args) {
        FullBack4 f = new FullBack4();
        int[] weight = {2,3,2,4,6,3};
        int num = f.bage(weight, weight.length, 9);
        System.out.println(num);
    }

    /*
     第1行
     dp[i][j] = dp[i-1][j-k * weight[i]]的所有装法的和
    */
    private int bage(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        //初始化第0行，第0行只可能有一种装法
        for (int k = 0;k<=w/weight[0];k++) {
            dp[0][k * weight[0]] = 1;
        }
        //开始状态转移
        for (int i = 1;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                int num = j/weight[i];
                int sum = 0;
                //将所有能到达dp[i][j]的装法都求和
                for (int k = 0;k<=num;k++) {
                    sum += dp[i-1][j-k*weight[i]];
                }
                dp[i][j] = sum;
            }
        }
        return dp[n-1][w];
    }

    //争哥解法
    private int bage2(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        //初始化第0行，第0行只可能有一种装法
        for (int k = 0;k<=w/weight[0];k++) {
            dp[0][k * weight[0]] = 1;
        }

        for (int i = 1;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                int num = j/weight[i];
                //将所有能到达dp[i][j]的装法都求和
                for (int k = 0;k<=num;k++) {
                    dp[i][j] += dp[i-1][j-k*weight[i]];
                }
            }
        }
        return dp[n-1][w];
    }

}
