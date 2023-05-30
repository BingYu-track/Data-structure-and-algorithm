package lessons.week10.dp.pratice.pratice8;

/**
 * @version 1.0 不同路径
 * @Description: 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 *
 * @author: bingyu
 * @date: 2023/5/30
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();
        int res = u.uniquePaths(7, 3);
        System.out.println(res);
    }

    /*
    这明显也是一个背包模型
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.1 MB, 在所有 Java 提交中击败了85.06%的用户
    */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //初始化第0行，第0行是应该为1还是0呢？这里先定义为1;dp[i][j]来自dp[i-1][j]和dp[i][j-1]
        dp[0][0] = 1;

        //继续从第0行开始
        for (int i = 0;i<dp.length;i++) {
            for (int j = 0;j<dp[i].length;j++) {
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
