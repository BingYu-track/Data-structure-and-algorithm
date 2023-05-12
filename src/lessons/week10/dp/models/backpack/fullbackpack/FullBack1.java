package lessons.week10.dp.models.backpack.fullbackpack;

/**
 * @version 1.0  完全背包问题
 * @Description: 有n个物品，重量分别为weight[i](i=0~n-1)，每个物品有无限多个，选择一些物品装入背包，在不超过背包总重量w的前提下
 *   背包可装物品总重量的最大值是多少?
 * @author: bingyu
 * @date: 2023/5/11
 */
public class FullBack1 {

    public static void main(String[] args) {
        FullBack1 f = new FullBack1();
        int[] items = {2,3,2,4,6,3};
        int weight = f.bage2(items, items.length, 9);
        System.out.println(weight);
    }

    /**
     * 我的解法
     * @param weight
     * @param n 物品数目
     * @param w 背包能承受的最大重量
     * @return
     */
    private int bage(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        //初始化第0行
        int ws = 0; //放入物品个数后的总重量
        for (int k = 0;ws <= w;k++) { //k为放入物品的个数,k++，只要不超过背包承受的最大重量就不断增加放入物品的个数
            dp[0][ws] = true;
            ws = k * weight[0];
        }

        //开始进行状态转移
        for (int i = 1;i<n;i++) { //第i个物品
            for (int j = 0;j<=w;j++) { //0 ~ w重量
                ws = 0;
                for (int k = 0;ws <= w;k++) { //在放入0~k为物品个数中，只要有一个满足下面的条件就说明dp[i][j]可达，后面就不需要再遍历了
                    ws = k * weight[i]; //放入k个物品后记录其重量，并在后面判断是否超过重量限制
                    if (j-k*weight[i] >= 0 && dp[i-1][j-k * weight[i]]) { //只要0~k个物品中有其中一个满足，则该位置可达
                        //System.out.println("第"+ i +"行 重量:" + j + "放入" + k + "个");
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        //在最后一行，从后往前遍历，遇到的第一个为true的位置就是背包可装重量的最大值
        for (int i = w;i>=0;i--) {
            if (dp[n-1][i]) return i;
        }
        return 0;
    }

    /*TODO： 推荐争哥的方法，更好理解
     完全背包问题争哥解法
    */
    public int bage2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        //初始化第0行
        for (int c = 0;c <= w/weight[0];c++) { //w/weight[0]是为了得到该行重量的物品最多能放入的个数
            dp[0][c * weight[0]] = true;
        }
        for (int i = 1;i<n;i++) {
            for (int j = 0;j<=w;j++) {
                int k = j/weight[i]; //控制当前物品重量能放入背包的最大个数
                for (int c = 0;c <= k;c++) {
                    if (dp[i-1][j-c*weight[i]]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        for (int i = w;i>=0;i--) {
            if (dp[n-1][i]) return i;
        }
        return 0;
    }


}
