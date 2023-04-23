package lessons.week9.pratice.dfsbfs.pratice5;

/**
 * @version 1.0  单词搜索---重复练习
 * @Description: 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true；否则，返回 false 。
 * 单词必须按照单词的字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 开头也可以从内部字符开始
 * 示例 1：
 *
 * 输入：board = [
 *              ['A','B','C','E'],
 *              ['S','F','C','S'],
 *              ['A','D','E','E']
 *             ],
 *      word = 'ABCCED'
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：board = [
 *                  ['A','B','C','E'],
 *                  ['S','F','C','S'],
 *                  ['A','D','E','E']
 *             ],
 *      word = 'SEE'
 * 输出：true
 *
 * 示例 3：
 *
 * 输入：board = [
 *                  ['A','B','C','E'],
 *                  ['S','F','C','S'],
 *                  ['A','D','E','E']
 *              ],
 *      word = 'ABCB'
 * 输出：false
 *
 *
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * @author: bingyu
 * @date: 2023/4/20
 */
public class RpExist {

    public static void main(String[] args) {
        RpExist rp = new RpExist();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "SEE";
        boolean exist = rp.exist(board, word);
        System.out.println(exist);
    }

    /*
     执行用时：164 ms, 在所有 Java 提交中击败了51.31%的用户
     内存消耗：39.7 MB, 在所有 Java 提交中击败了53.23%的用户
    */

    private boolean hasWord = false;

    /*
      题目中是相邻可以连起来，即水平，垂直；首先从board确定找到开头字母，然后dfs看有没有后面的字母，没有
    */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; //上、下、左、右
        for (int i = 0;i<board.length;i++) {
            for (int j = 0;j<board[i].length;j++) {
                char c = board[i][j];
                if(c == words[0]) { //首字母一样，并且未访问就开始dfs
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    dfs(0,i,j,board,words,visited,direction); //以(i,j)这个顶点来匹配word
                    if (hasWord) return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param k words的字母下标
     * @param row
     * @param col
     * @param board
     * @param words
     * @param visited
     */
    private void dfs(int k,int row, int col, char[][] board, char[] words, boolean[][] visited,int[][] direction) {
        if (hasWord) return;
        visited[row][col] = true;
        if (k == words.length - 1) {
            hasWord = true;
            return;
        }
        for (int i = 0;i<direction.length;i++) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if (newRow>=0 && newCol>=0 && newRow<board.length && newCol<board[0].length) { //防止越界
                if (k+1 < words.length && words[k+1] == board[newRow][newCol] && !visited[newRow][newCol]) {
                    //System.out.print(board[newRow][newCol] + "("+newRow + "," + newCol +") "); 用来记录经过的每个节点
                    dfs(k+1,newRow,newCol,board,words,visited,direction);
                    if (hasWord) return;
                    visited[newRow][newCol] = false; //执行到这里说明没有找到，将之前访问的节点回退并设置未访问，这样后面再次访问就不会跳过了
                }
            }
        }

    }

}
