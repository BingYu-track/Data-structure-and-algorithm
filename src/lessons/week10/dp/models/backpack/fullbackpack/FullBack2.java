package lessons.week10.dp.models.backpack.fullbackpack;

/**
 * @version 1.0  完全背包问题2
 * @Description: n个物品，重量分别为weight[i](i=0~n-1)，每个物品有无限多个，选择一些物品装入背包，在不超过背包总重量w的前提下
 *        是否能装满整个背包?
 * @author: bingyu
 * @date: 2023/5/12
 */
public class FullBack2 {

    public static void main(String[] args) {
        FullBack2 f = new FullBack2();
        int[] weight = {2,3,2,4,6,3};
        boolean res = f.bage(weight, weight.length, 9);
        System.out.println(res);
    }

    /*
     我的解法
    */
    private boolean bage(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        //初始化第0行
        for (int i = 0;i<=w/weight[0];i++) {
            dp[0][i * weight[0]] = true;
        }

        //从第1行开始处理，进行状态转移
        for (int i = 1;i < n;i++) {
            for (int j = 0;j<=w;j++) {
                int num = j / weight[i]; //第i行物品能放入背包的最大个数
                for (int k = 0;k<=num;k++) {
                    if (j-k * weight[i]>=0 && dp[i-1][j-k * weight[i]]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        return dp[n-1][w];
    }


}
