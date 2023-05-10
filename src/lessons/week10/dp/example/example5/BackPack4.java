package lessons.week10.dp.example.example5;

/**
 * @version 1.0  背包计数问题
 * @Description: 有n个物品，选择其中一些物品装入背包，装满背包有多少种不同的装法?(计数)
 * @author: bingyu
 * @date: 2023/5/9
 */
public class BackPack4 {

    public static void main(String[] args) {
        BackPack4 bp = new BackPack4();
        int[] weight = {2,3,2,4,6,3};
        int res = bp.backPack(weight, weight.length, 9);
        System.out.println(res);
    }

    /*
   TODO：
    问: 对于重量0，第1个不放，第2个也不放入背包，这算两种不同的装法吗? 如何理解?
    答: 只能算一种装法，dp[0~i][0]都等于1。因为都没有物品放入，只有不同的物品放入才能算不同的装法

    如果用int dp[i][j]来记录每个位置可达重量的装法个数，

         0 1 2 3 4 5 6 7 8 9
    0   [1,0,1,0,0,0,0,0,0,0]      2
    1   [1,0,1,1,0,1,0,0,0,0]      3
    2   [1,0,2,1,1,2,0,0,0,0]      2
    3   [1,0,0,0,0,0,0,0,0,0]      4
    4   [1,0,0,0,0,0,0,0,0,0]      6
    5   [1,0,0,0,0,0,0,0,0,0]      3

       不放入物品: dp[i][j] = dp[i-1][j]
       放入物品: dp[i-1][j-weight[i]]
       例如: dp[2][2]只可能从dp[1][0]放入当前物品，或者dp[1][2]不放入物品进来；dp[1][0]放入当前物品是一个装法，
       dp[1][2]不放入物品又是一个装法，加起来一共就是两个不同的装法了，如果进入dp[1][0]的装法又有多种，那么同样会影响
       其后面的dp[2][2],因此状态转移方程为dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]]。
    */
    public int backPack(int[] weight,int n,int w) {
        int[][] dp = new int[n][w+1];
        dp[0][0] = 1;
        if (weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        for (int i = 1;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                //不放
//                if (dp[i-1][j]!=0) {
//                    dp[i][j] = dp[i-1][j];
//                }
//
//                //放入
//                if (j-weight[i]>=0 && dp[i-1][j-weight[i]]!=0) {
//                    //如果dp[i][j]是0，那么装法就是继承自dp[i-1][j-weight[i]]，如果非0，说明dp[i-1][j-weight[i]]对于该重量
//                    // 是不同的装法，将其加上本身已有的dp[i][j]装法即可
//                    dp[i][j] = dp[i][j] + dp[i-1][j-weight[i]];
//                }
                if (j - weight[i] < 0) { //j - weight[i] < 0 说明当前dp[i][j]只可能从dp[i-1][j]这一个途径进来
                    dp[i][j] = dp[i-1][j];
                }else { //这里就说明可以经过两个途径进来
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]];
                }
            }
        }

        return dp[n-1][w];
    }


}
