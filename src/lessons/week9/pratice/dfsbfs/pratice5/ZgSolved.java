package lessons.week9.pratice.dfsbfs.pratice5;

/**
 * @version 1.0
 * @Description: 单词搜索---争哥解法思路
 * @author: bingyu
 * @date: 2023/3/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        char[][] board = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };
        String word = "AAB";
        ZgSolved zgSolved = new ZgSolved();
        boolean exist = zgSolved.exist(board, word);
        System.out.println(exist);
    }

    /* 争哥解法
      执行用时：262 ms, 在所有 Java 提交中击败了22.43%的用户
      内存消耗：42 MB, 在所有 Java 提交中击败了9.03%的用户
    */
    private boolean existed = false;
    private int h;
    private int w;
    public boolean exist(char[][] board, String word) {
        h = board.length;
        w = board[0].length;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                boolean[][] visited = new boolean[h][w];
                dfs(board, word, i, j, 0, visited);
                if (existed) return true;
            }
        }
        return false;
    }

    private void dfs(char[][] board, String word, int i, int j, int k, boolean[][] visited) {
        if (existed == true) return;
        if (word.charAt(k) != board[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (k == word.length() - 1) {
            existed = true;
            return;
        }
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int di = 0; di < 4; di++) {
            int nexti = i + directions[di][0];
            int nextj = j + directions[di][1];
            if (nexti >= 0 && nexti < h && nextj >= 0 && nextj < w && !visited[nexti][nextj]) {
                dfs(board, word, nexti, nextj, k + 1, visited);
            }
        }
        visited[i][j] = false;
    }

}
