package lessons.week10.dp.example.example3;

/**
 * @version 1.0  背包可行性问题(重点理解)
 * @Description: 有n个物品，选择其中一些物品装入背包，能不能正好装满背包?(可行)
 * @author: bingyu
 * @date: 2023/5/8
 */
public class BackPack2 {

    public static void main(String[] args) {
        BackPack2 bp = new BackPack2();
        int[] weight = {2,3,2,4,6,3};
        boolean res = bp.backPack(weight, weight.length, 9);
        System.out.println(res);
    }

    /*   0 1 2 3 4 5 6 7 8 9
    0   [1,0,1,0,0,0,0,0,0,0]
    1   [1,0,1,1,0,1,0,0,0,0]
    2   [1,0,1,1,1,1,0,1,0,0]
    3   [1,0,1,1,1,1,1,1,1,1]
    4   [1,0,1,1,1,1,1,1,1,1]
    5   [1,0,1,1,1,1,1,1,1,1]

        TODO： 注意我们二维数组的行并不是放入背包的物品个数，而是考察物品的个数，有一个规律需要注意，就是只要
         有一个位置可达，那么与该位置同列的，并在该位置下面的所有位置都可达，因为下面只要不放入背包，重量和该位置
         就都是一样的，也就是处于同一列可达！因此我们判断是否能装满，直接看最后一列的最后一行位置是否可达即可！
     */

    /**
     * TODO： 注意是选择其中一些放入背包，而不是将所有物品都放入背包，和前面处理背包问题一样，只是在装入时
     *  判断其二维表格中最后一列的最后一行位置是否可达即可，具体原因看上面介绍
     * @param weight 装入背包的物品列表
     * @param n 物品数量
     * @param w 背包承受的最大重量
     * @return
     */
    public boolean backPack(int[] weight,int n,int w) {
        boolean[][] dp = new boolean[n][w + 1];
        //step1:初始化第0行位置
        dp[0][0] = true; //第0行不放入，可达
        dp[0][weight[0]] = true; //第0行放入，可达

        //step2:从第1行开始处理
        for (int i = 1;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                //1.不放入
                if (dp[i-1][j]) { //不放入可达
                    dp[i][j] = true;
                }
                //2.放入
                if (j-weight[i]>=0 && dp[i-1][j-weight[i]]) {
                    dp[i][j] = true;
                }
            }
        }
        //TODO: 遍历重量为9的位置是否有可达的 不能这样处理，因为我们二维数组的行并不是放入背包的物品个数，而是考察物品的个数
        // 比如在前面几行，它可能一个物品都没放入，重量还是0呢，所以不能从最后一列的第0行开始检查，应该是看最后一行看的最后一列的位置是否为true，因为
        // 只要上面可达，那么其下面的位置就一定可达!
//        Wrong!
//        for (int i = 0;i<n;i++) {
//            if (dp[i][w]) {
//                return true;
//            }
//        }

        return dp[n-1][w]; //correct!
    }

}
