package lessons.week9.example.dfsbfs.example1;

/** TODO 题型1--二维矩阵搜索或遍历
 * @version 1.0  面试题13. 机器人的运动范围
 * @Description: 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下
 * 移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2： 就是[0,0]到[2,0]，确实只能走下面的一个格子，后面就走不了了 [0,0]、[1,0]
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 *
 * 示例 3：说明第一个顶点也要作为一个格子
 * 输入: m = 1, n = 1, k = 1
 * 输出1
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k<= 20
 *
 * @author: bingyu
 * @date: 2023/2/24
 */
public class MovingCount {

    public static void main(String[] args) {
        MovingCount v = new MovingCount();
        int count = v.movingCount(11, 8, 16);
        System.out.println(count);
    }



    private int count = 0; //用来记录到达的格子数

    /*
     TODO 这类题目的解题思路就是把问题转换为图模型来解决，这里可以把每个方格抽象成图里的每个顶点，方格四周的各自就是图里连接的顶点
      执行用时：1 ms, 在所有 Java 提交中击败了64.18%的用户
      内存消耗：38.4 MB, 在所有 Java 提交中击败了62.22%的用户
    */
    public int movingCount(int m, int n, int k) {
        if (k == 0) return 1; //
        boolean[][] visited = new boolean[m][n]; //用来记录访问过的格子坐标
        int[][] directions = {
                              {-1,0}, //表示上面的顶点
                              {1,0}, //表示下面的顶点
                              {0,-1}, //表示左边的顶点
                              {0,1} //表示右边的顶点
                            };
        dfs(0,0,m,n,k,directions,visited);
        return count;
    }

    /**
     *
     * @param i 当前行
     * @param j 当前列
     * @param m
     * @param n
     * @param sum 要求不大于的数位之和
     * @param directions 移动的四个方向
     * @param visited
     */
    private void dfs(int i, int j, int m, int n, int sum, int[][] directions,boolean[][] visited) {
//        if (i == m-1 && j==n-1) {
//            visited[i][j] = true;
//            count++;
//            return;
//        }
        visited[i][j] = true;
        count++;
        for (int k = 0;k< directions.length;k++) {
            int[] direction = directions[k]; //获取方向
            //越界的坐标点不去执行
            if (i + direction[0] < 0 || j + direction[1] < 0 || i + direction[0] >= m || j+direction[1] >= n) continue;
            //数位之和小于sum，并且之前没有被访问，开始记录
            if(check(i + direction[0],j + direction[1],sum) && !visited[i + direction[0]][j + direction[1]]) {
                dfs(i + direction[0],j + direction[1],m,n,sum,directions,visited);
                //这里不能撤销，因为即使没有走到终点，途中走的格子也需要被记录下来
            }

        }
    }

    /**
     * 用来校验当前坐标数位和是否超过目标数位和,由于题目中规定了m,n的范围在0,100之间，因此,i,j最大也只能是99，因此最多是两位数
     * @param i 当前行
     * @param j 当前列
     * @param target 目标数位和
     * @return
     */
    private boolean check(int i, int j, int target) {
        int sum = 0;
        while (i != 0) { //
            sum += (i%10); //这里是不断取个位数字
            i /= 10; //去除个位数字
        }
        while (j != 0) {
            sum += (j%10);
            j /= 10;
        }
        return sum <= target;
    }

    /* 终止条件:
       m=3 n=3
      [0,0]到[2,2]，即从左上角到右下角
       |---|---|---|
       |   |   |   |
       |---|---|---|
       |   |   |   |
       |---|---|---|
       |   |   |   |
       |---|---|---|
     */
}
