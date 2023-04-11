package lessons.week9.example.dfsbfs.example1;

/**
 * @version 1.0  面试题13. 机器人的运动范围 ----重复练习
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
 * @author: bingyu
 * @date: 2023/4/11
 */
public class RpMovingCount {

    public static void main(String[] args) {
        RpMovingCount mv = new RpMovingCount();
        int count = mv.movingCount(2, 3, 1);
        System.out.println(count);
    }

    private int step = 1;

    /*

     */
    public int movingCount(int m, int n, int k) {
        int[][] arr = new int[m][n]; //创建m行n列的二维数组
        boolean[][] visited = new boolean[m][n];
        int[][] direction = {
            {-1,0},{1,0},{0,-1},{0,1} //上下左右
        };
        visited[0][0] = true;
        dfs(0,0,direction,arr,k,visited);
        return step;
    }

    //还要避免重复进入同一个坐标
    private void dfs(int row, int col, int[][] direction, int[][] arr,int k,boolean[][] visited) {
        for (int i = 0;i<direction.length;i++) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if (newRow >= 0 && newRow < arr.length && newCol >= 0 && newCol < arr[0].length) {
                boolean flag = judge(newRow,newCol,k); //判断是否比k大
                boolean visite = visited[newRow][newCol];
                if (flag && !visite) { //当前坐标和k满足关系，并且未被访问可以跳到下一格
                    step++;
                    visited[newRow][newCol] = true;
                    dfs(newRow,newCol,direction,arr,k,visited);
                }
            }
        }
    }

    private boolean judge(int x, int y, int k) {
        int count = 0;
        while (x!=0) {
            count += x % 10; //获取个位数
            x = x / 10;
        }
        while (y!=0) {
            count += y % 10;
            y = y / 10;
        }
        return count <= k;
    }


}
