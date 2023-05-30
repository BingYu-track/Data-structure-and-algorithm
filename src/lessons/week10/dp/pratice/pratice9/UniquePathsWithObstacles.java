package lessons.week10.dp.pratice.pratice9;

/**
 * @version 1.0  不同路径 II
 * @Description: 一个机器人位于一个m x n网格的左上角 (起始点在下图中标记为 “Start”)。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角(在下图中标记为 “Finish”)。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],
 *                      [0,1,0],
 *                      [0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],
 *                      [0,0]]
 * 输出：1
 *
 * 提示：
 * m ==obstacleGrid.length
 * n ==obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 *
 * @author: bingyu
 * @date: 2023/5/30
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        UniquePathsWithObstacles u = new UniquePathsWithObstacles();
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        int res = u.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }

   /*
      dp[i][j]来自dp[i-1][j]和dp[i][j-1]
      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：39.7 MB, 在所有 Java 提交中击败了34.94%的用户
    */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0; //起始位置或者目标位置就有障碍，说明不可能有路径到达
        int[][] dp = new int[m][n];

        //初始化第0行
        dp[0][0] = 1;

        for (int i = 0;i<dp.length;i++) {
            for (int j = 0;j<dp[i].length;j++) {
                int cur = obstacleGrid[i][j];
                if (cur == 1) continue; //如果遇到障碍直接跳过
                if (i-1>=0 && dp[i-1][j]!=0) {
                    dp[i][j] += dp[i-1][j];
                }
                if (j-1>=0 && dp[i][j-1]!=0) {
                    dp[i][j] += dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

}
