package lessons.week9.example.dfsbfs.example3;

/**
 * @version 1.0
 * @Description: 岛屿数量----争哥解法
 * @author: bingyu
 * @date: 2023/2/24
 */
public class ZgSolved {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        ZgSolved islands = new ZgSolved();
        int i = islands.numIslands(grid);
        System.out.println(i);
    }


    //争哥解法，基本和我的一样
    private boolean[][] visited;
    private int h;
    private int w;
    public int numIslands(char[][] grid) {
        h = grid.length;
        w = grid[0].length;
        visited = new boolean[h][w];
        int result = 0;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                if (visited[i][j] != true && grid[i][j] == '1') {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }
    private void dfs(char[][] grid, int i, int j) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true;
        for (int k = 0; k < 4; ++k) {
            int newi = i + directions[k][0];
            int newj = j + directions[k][1];
            if (newi >= 0 && newi < h && newj >= 0 && newj < w
                    && visited[newi][newj] == false && grid[newi][newj]=='1') {
                dfs(grid, newi, newj);
            }
        }
    }
}
