package lessons.week10.dp.example.example4;


/**
 * @version 1.0  背包另一个最值问题 (重点理解!)
 * @Description: 有n个物品，选择其中一些物品装入背包，求正好装满背包所需物品最少个数?(如果装不满，返回-1)
 * @author: bingyu
 * @date: 2023/5/8
 */
public class BackPack3 {

    public static void main(String[] args) {
        BackPack3 bp = new BackPack3();
        int[] weight = {2,3,2,4,6,3};
        int res = bp.backPack(weight, weight.length, 9);
        System.out.println(res);
    }

    /* TODO: 这题如果像之前那样处理，用boolean[][] dp来判断装满的最小物品个数，是不行的，因为行是考察个数，不是放入背包的物品个数,就以上面为例，
         在下标为3的行中装满了，但是你无法知道装入背包的物品个数，虽然是考察到了第4个物品，但是可能放入背包的数量就一个，因此我们不能这样处理,而是要用
         二维数组来记录每个位置放入物品的个数。用int[][] dp来表示！

         0 1 2 3 4 5 6 7 8 9
    0   [1,0,1,0,0,0,0,0,0,0]
    1   [1,0,1,1,0,1,0,0,0,0]
    2   [1,0,1,1,1,1,0,1,0,0]
    3   [1,0,1,1,1,1,1,1,1,1]
    4   [1,0,1,1,1,1,1,1,1,1]
    5   [1,0,1,1,1,1,1,1,1,1]

    我们换int[][] dp来表示

          0  1  2  3  4  5  6  7  8  9
    0   [ 0,-1, 1,-1,-1,-1,-1,-1,-1,-1]
    1   [ 0,-1, 1, 1,-1, 2,-1,-1,-1,-1]
    2   [ 0,-1, 1, 1, 2, 2,-1, 3,-1,-1]
    3   [ 0,-1, 1, 1, 1,-1, 2, 2,-1,-1]
    4   [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
    5   [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
       TODO:
        根据上面分析的表格，存在一个位置有多种路径，比如在dp[3][7]中，有2个路径,路径1是 放入重量为3的物品，在放入重量为4的物品到达,物品个数为2
        路径2: 放入重量为2,3,2前3个物品，第4个物品不放，重量也为7，但是物品个数为3，我们只能取最小值，因此状态转移方程得到:
        当i=3,j=7时，可以从dp[i-1][j-weight[i]] + 1即dp[2][3]=1推导出来(放入当前物品的情况)，此时dp[3][7]=2
        另外一个从dp[i-1][j]推导出来(不放入当前物品的情况),即dp[2][7]=3,此时dp[3][7]=3

        因此，实际就是比较放入和不放入，哪个最小就取哪个，即dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-weight[i]] + 1);
    */

    /**
     *
     *  状态转移方程: dp[i][j] =
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public int backPack(int[] weight,int n,int w) {
        int[][] dp = new int[n][w+1]; //dp[i][j]表示在考察第i个物品，重量为j时放入背包的物品个数
        for (int i = 0;i < n;i++) { //全部位置初始化为-1，为了和放入物品个数0做区分
            for (int j = 0; j <= w;j++) {
                dp[i][j] = Integer.MAX_VALUE; //注意由于是是比较最小值，因此这里初始化应该初始化最大值
            }
        }

        //第0行初始化
        dp[0][0] = 0; //不放入，重量为0，放入的物品个数也为0
        if (weight[0] <= w) { //放入
            dp[0][weight[0]] = 1;
        }

        //从第1行开始考察
        for (int i = 1;i < n;i++) {
            for (int j = 0;j <= w;j++) {
                if (dp[i-1][j] != Integer.MAX_VALUE) { //不放入物品
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j]);
                }

                //放入物品
                if (j-weight[i]>=0 && dp[i-1][j-weight[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-weight[i]] + 1);
                }
            }
        }
        if (dp[n-1][w] == Integer.MAX_VALUE) return -1;
        return dp[n-1][w];
    }


}
