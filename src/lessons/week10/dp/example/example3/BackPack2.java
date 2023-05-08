package lessons.week10.dp.example.example3;

/**
 * @version 1.0  背包可行性问题
 * @Description: 有n个物品，选择其中一些物品装入背包，能不能正好装满背包?(可行)
 * @author: bingyu
 * @date: 2023/5/8
 */
public class BackPack2 {

    public static void main(String[] args) {
        BackPack2 bp = new BackPack2();
        int[] weight = {2,2,4,6,3};
        boolean res = bp.backPack(weight, weight.length, 9);
        System.out.println(res);
    }

    /**
     * TODO： 注意是选择其中一些放入背包，而不是将所有物品都放入背包，和前面处理背包问题一样，只是在装入时
     *  判断其二维表格中重量为9的位置是否有为true的，如果有，说明可行
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
        //遍历重量为9的位置是否有可达的
        for (int i = 0;i<n;i++) {
            if (dp[i][w]) {
                return true;
            }
        }
        return false;
    }

}
