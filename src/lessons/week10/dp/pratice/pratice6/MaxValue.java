package lessons.week10.dp.pratice.pratice6;

/**
 * @version 1.0 剑指 Offer 47. 礼物的最大价值
 * @Description: 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
 * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [1,3,1],
 *  [1,5,1],
 *  [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * @author: bingyu
 * @date: 2023/5/29
 */
public class MaxValue {

    public static void main(String[] args) {
//        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] grid = {{0,1},{1,0}};
        MaxValue m = new MaxValue();
        int res = m.maxValue(grid);
        System.out.println(res);
    }

    /*
     这题明显可以抽象成一个0-1背包问题
    执行用时：2 ms, 在所有 Java 提交中击败了95.91%的用户
    内存消耗：42.9 MB, 在所有 Java 提交中击败了96.31%的用户
     */
    public int maxValue(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols]; //定义dp[i][j]是到达(i,j)这个位置所拿到的最大价值总和

        for (int i = 0;i < dp.length;i++) {
            for (int j = 0;j < dp[i].length;j++) { //全部赋值为-1，表示没有获取礼物
                dp[i][j] = -1;
            }
        }

        //初始化第0阶段
        dp[0][0] = grid[0][0];

        for (int i = 0;i < dp.length;i++) {
            for (int j = 0;j < dp[i].length;j++) {
                int cur = grid[i][j];
                if (i-1>=0 && dp[i-1][j]!=-1) { //来自上面的元素
                    dp[i][j] = dp[i-1][j] + cur;
                }

                if (j-1>=0 && dp[i][j-1]!=-1) { //来自左边的元素
                    dp[i][j] = Math.max(dp[i][j-1] + cur,dp[i][j]);
                }
            }
        }
        return dp[rows-1][cols-1];
    }


}
