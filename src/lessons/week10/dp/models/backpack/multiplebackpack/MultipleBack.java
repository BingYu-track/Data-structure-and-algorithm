package lessons.week10.dp.models.backpack.multiplebackpack;

/**
 * @version 1.0 多重背包问题
 * @Description: 有n个物品，重量分别为weight[i](i=0~n-1)，每个物品有有限多个，个数分别为count[i](i=0~n-1),选择
 *      一些物品装入背包，在补偿过背包重量w的前提下，装满背包有多少种装法?
 * @author: bingyu
 * @date: 2023/5/15
 */
public class MultipleBack {

    public static void main(String[] args) {
        int[] weight = {2,3,2,4,6,3};
        int[] count = {2,3,2,4,6,3};
        MultipleBack m = new MultipleBack();
        int num = m.bage2(weight, count, weight.length, 9);
        System.out.println(num);
    }

    /*

    */
    private int bage(int[] weight, int[] count, int n, int w) {
        int[][] dp = new int[n][w+1];

        //处理第0行
        for (int k = 0;k<=w/weight[0] && k<=count[0];k++) {
            dp[0][k * weight[0]] = 1;
        }
        //开始正式进行状态转移
        for (int i = 1;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                for (int k = 0;k<=j/weight[i] && k<=count[i];k++) {
                    //如果dp[i-1][j-k*weight[i]]是不可达的，dp[i-1][j-k*weight[i]]为0，加起来也不影响
                    dp[i][j] += dp[i-1][j-k*weight[i]];
                }
            }
        }
        return dp[n-1][w];
    }

    //争哥解法:
    private int bage2(int[] weight, int[] count, int n, int w) {
        int[][] dp = new int[n][w+1];

        //处理第0行
        for (int k = 0;k<= Math.min(w/weight[0],count[0]);k++) {
            dp[0][k * weight[0]] = 1;
        }

        for (int i = 1;i < n;i++) {
            for (int j = 0;j<=w;j++) {
                int num = Math.min(j/weight[i],count[i]);
                for (int k = 0;k<=num;k++) {
                    dp[i][j] += dp[i-1][j-k*weight[i]];
                }
            }
        }
        return dp[n-1][w];
    }

}
