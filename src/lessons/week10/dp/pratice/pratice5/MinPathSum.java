package lessons.week10.dp.pratice.pratice5;

/**
 * @version 1.0 最小路径和
 * @Description: 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 *
 * @author: bingyu
 * @date: 2023/5/29
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        MinPathSum sum = new MinPathSum();
        int res = sum.minPathSum(grid);
        System.out.println(res);
    }


    /*
     将其抽象成二维背包模型，只有向右走或者向下走两种决策选择，相当于背包的放入和不放入。
     从(0,0)到(2,2)

     执行用时：2 ms, 在所有 Java 提交中击败了92.83%的用户
     内存消耗：43.1 MB, 在所有 Java 提交中击败了80.70%的用户
    */
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        //定义dp[i][j]是到达(i,j)这个位置时的最短路径和，dp[i][j]只可能来自dp[i-1][j]和dp[i][j-1]
        for (int i = 0;i < dp.length;i++) {
            for (int j =0;j < dp[i].length;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        //初始化第0阶段
        dp[0][0] = grid[0][0];

        //因为是要么向下，要么向左，因此从第1阶段开始，行和列可能都为0
        for (int i = 0;i < dp.length;i++) {
            for (int j = 0;j < dp[i].length;j++) {
                int cur = grid[i][j]; //当前位置的数值
                if (i-1>=0 && dp[i-1][j]!=Integer.MAX_VALUE) {
                    dp[i][j] = dp[i-1][j] + cur; //上面的加上当前值
                }
                if (j-1>=0 && dp[i][j-1]!=Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j-1] + cur,dp[i][j]); //左边的加上当前值
                }

            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
