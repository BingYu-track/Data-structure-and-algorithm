package lessons.week9.example.dfsbfs.example3;

/**
 * @version 1.0  岛屿数量 ---重复练习
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
 * @author: bingyu
 * @date: 2023/4/17
 */
public class RpNumIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'1','1','0','1','1'}
        };
        RpNumIslands islands = new RpNumIslands();
        int i = islands.numIslands(grid);
        System.out.println(i);
    }

    private int islandCount = 0; //用来记录岛屿的数量

    /*
       执行用时：4 ms, 在所有 Java 提交中击败了34.87%的用户
       内存消耗：49.9 MB, 在所有 Java 提交中击败了26.76%的用户
       遇到1就count加1，并且记录已访问的元素，不断上下左右遍历，直到上下左右都为0就返回
     */
    private int numIslands(char[][] grid) {
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; //上、下、左、右
        boolean[][] visited = new boolean[grid.length][grid[0].length]; //记录已访问元素
        for(int i = 0;i < grid.length;i++) {
            for (int j = 0;j < grid[i].length;j++) {
                if (!visited[i][j] && grid[i][j] == '1') { //未访问并且为1时说明遇到了一个新的岛屿
                    islandCount++;
                    dfs(i,j,grid,directions,visited);
                }
            }
        }
         //从(0,0)坐标开始
        return islandCount;
    }

    /**
     *
     * @param row 行下标
     * @param col 列下标
     * @param grid
     * @param directions 方向
     * @param visited 已访问
     */
    private void dfs(int row, int col, char[][] grid, int[][] directions, boolean[][] visited) {
        visited[row][col] = true;
        if (grid[row][col] == '0') return;
        for (int i = 0;i<directions.length;i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow >=0 && newRow < grid.length && newCol >=0 && newCol < grid[0].length) { //防止越界
                if (!visited[newRow][newCol]) { //未访问的，就继续dfs，直到遇到0
                    dfs(newRow,newCol,grid,directions,visited);
                }
            }
        }
    }

}
