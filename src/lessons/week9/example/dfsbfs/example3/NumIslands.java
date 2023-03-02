package lessons.week9.example.dfsbfs.example3;

/** TODO: 题型3----连通分量/连通性
 * @version 1.0  岛屿数量
 * @Description: 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 * 示例 1：
 * 输入：grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * @author: bingyu
 * @date: 2023/2/24
 */
public class NumIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        NumIslands islands = new NumIslands();
        int i = islands.numIslands(grid);
        System.out.println(i);
    }


    private int count = 0; //用来记录岛屿的数量

    /*
     解题思路: 重点是把问题抽象成图来解决，每次遍历，如果是1,岛屿数量加1，然后向上下左右进行扩展进行深度遍历,
        直到无法再遍历为止，也就说明1全部访问过了，剩下四周都是水；遇到0就直接跳过

      执行用时：4 ms, 在所有 Java 提交中击败了34.33%的用户
      内存消耗：49.9 MB, 在所有 Java 提交中击败了44.43%的用户
    */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}}; //代表上、下、左、右四个方向
        for(int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (!visited[i][j] && grid[i][j]=='1') {
                    dfs(i,j,grid,visited,directions);
                    count++; //说明找到了一个岛屿
                }
            }
        }
        //执行到这说明全部点遍历完毕
        return count;
    }

    /**
     *  终止条件是，所有点都访问过，怎么表示?
     * @param i 当前点所在行
     * @param j 当前点所在列
     * @param grid
     * @param visited
     */
    private void dfs(int i,int j,char[][] grid, boolean[][] visited,int[][] directions) {
        if (i==grid.length || j==grid[0].length || i<0 || j<0) return; //超出范围
        if(grid[i][j] == '0' || visited[i][j]) return; //遇到水，或者是已经访问过的点，则原路返回
        //执行到这里说明当前的点是1，且没有被访问过; 注意这里不能增加岛屿数量，因为和其相连的'1'都被看做同一个岛屿
        visited[i][j] = true;
        for (int k=0;k<directions.length;k++) {
            int[] direction = directions[k]; //获取当前方向
            dfs(i + direction[0],j + direction[1],grid,visited,directions);
        }
    }

    /*

    */

}
