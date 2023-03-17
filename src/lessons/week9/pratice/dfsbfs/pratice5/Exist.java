package lessons.week9.pratice.dfsbfs.pratice5;

import com.sun.org.apache.bcel.internal.generic.FADD;

/**
 * @version 1.0  单词搜索
 * @Description: 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true；否则，返回 false 。
 * 单词必须按照单词的字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 开头也可以从内部字符开始
 * 示例 1：
 *
 * 输入：board = [
 *              ["A","B","C","E"],
 *              ["S","F","C","S"],
 *              ["A","D","E","E"]
 *             ],
 *      word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：board = [
 *                  ["A","B","C","E"],
 *                  ["S","F","C","S"],
 *                  ["A","D","E","E"]
 *             ],
 *      word = "SEE"
 * 输出：true
 *
 * 示例 3：
 *
 * 输入：board = [
 *                  ["A","B","C","E"],
 *                  ["S","F","C","S"],
 *                  ["A","D","E","E"]
 *              ],
 *      word = "ABCB"
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
 *
 * @author: bingyu
 * @date: 2023/2/17
 */
public class Exist {

    public static void main(String[] args) {
        char[][] board = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };
        String word = "AAB";
        Exist e = new Exist();
        boolean exist = e.exist(board, word);
        System.out.println(exist);
    }

    /*
      使用DFS来解决
      解题思路: 遍历，遇到开头字符开始向上下左右遍历，注意深度是有限的，最多只能深度遍历word.length个
      执行用时：183 ms, 在所有 Java 提交中击败了35.87%的用户
      内存消耗：39.8 MB, 在所有 Java 提交中击败了39.60%的用户
    */
    private boolean flag = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[][] directions = new int[][]{
                {-1,0},{1,0},{0,-1},{0,1} //上、下、左、右
        };
        int len = word.length(); //用来限制dfs遍历的深度
        for (int i = 0;i<m;i++) { //行
            for (int j = 0;j<n;j++) { //列
                boolean[][] visited = new boolean[m][n]; //TODO 每次遍历一个节点创建一个，这是为了防止之前第一次深度遍历时标记为已访问,导致后面深度遍历失败
                dfs(i,j,0,visited,board,directions,len,word); //对当前节点上下左右进行遍历
                if (flag) return true; //说明搜索到了指定word
            }
        }
        return false;
    }

    /**
     *
     * @param i
     * @param j
     * @param k 遍历的层数
     * @param visited 用来记录已访问的节点
     * @param board
     * @param directions 四个方向
     * @param len 最大深度
     * @param word
     */
    private void dfs(int i, int j,int k, boolean[][] visited,char[][] board,int[][] directions, int len,String word) {
        if (flag) {
            return;
        }
        //TODO: 注意解决这个问题--当顺序遍历时，与word开头相同的字符会被标记为已访问，导致后面深度遍历时可能遇到相同位置的字符时无法继续深度遍历导致失败
        if (word.charAt(k) != board[i][j]) { //如果当前要查找的字符和此时位置的字符不一样，直接返回
            return;
        }
        if (k == len-1) {  //TODO: 这里是判断的重点，我就是这里没弄清楚，如果执行到这里，说明此时已经深度遍历完word的所有字符
            flag = true;
            return;
        }
        visited[i][j] = true; //注意这里标记已访问只能对当前的dfs路径进行标记，即撤销时需要重试设置为false；因为当前dfs遍历完成后，
                            // 我们还要DFS遍历后面的点，同样会遍历到同样的位置
        for (int q = 0;q<directions.length;q++) { //向四个方向探测
            int row = i + directions[q][0];
            int col = j + directions[q][1];
            //防止越界，并限制深度，这里也需要用visited进行限制，因为我们向四周探测时可能遇到之前访问过的节点，需要避免重复处理
            if (row<0 || col<0 || row==board.length || col==board[0].length || k+1==len || visited[row][col]) continue;
            dfs(row,col,k + 1,visited,board,directions,len,word);
        }
        //
        visited[i][j] = false;
    }


    /* 这是我自己的解发，用的回溯，这题要想更快的执行需要用到DFS
      我的思路是把word插入一个trie树中，然后
    */
    private boolean result = false;

    public boolean exist2(char[][] board, String word) {
        char[] path = new char[word.length()];
        boolean[][] used = new boolean[board.length][board[0].length]; //用来记录字符是否被使用过
        //1.首先找到word的开头字符
        char c = word.charAt(0);
        for(int i = 0;i<board.length;i++) {
            for (int j = 0;j<board[i].length;j++) {
                if (board[i][j] == c) { //遇到开始字符，开始进行查询
                    path[0] = c;
                    used[i][j] = true;
                    backtrack(board,path,used,1,word,i,j);
                    if (!result) { //如果没有找到，撤消
                        path[0] = '\u0000';
                        used[i][j] = false;
                    }else {
                        //执行到这说明找到了
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** 使用的回溯来解决
     * 执行用时：173 ms, 在所有 Java 提交中击败了34.62%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了8.29%的用户
     * @param board
     * @param path 路径
     * @param used 记录矩阵字符是否被使用过
     * @param k 当前所处在的阶段
     * @param word
     * @param x 当前节点所在矩阵的行
     * @param y 当前节点所在矩阵的列
     */
    private void backtrack(char[][] board, char[] path, boolean[][] used, int k, String word,int x,int y) {
        if (k == path.length) { //递归深度到达word的长度
            String s = String.valueOf(path);
            if (s.equals(word)) result = true;
            return;
        }
        if (x<0 || y<0 || x>=board.length || y>=board[0].length) return; //进入边界，直接返回

        char c = word.charAt(k); //当前层我们要找的字符
        /*
        我们现在路径里开始是有一个x,y的字符，从这里开始,从上下，左右开始寻找相邻元素，注意，在找相邻元素的同时
         还要验证相邻的元素是否已经使用过
         */
        char[] neighb = new char[4]; //用来存储相邻的元素，按照上下左右排序
        int count = 0;//当前可能相邻的元素个数
        //TODO： 如何记录元素的x,y
        if (x-1>=0 && !used[x-1][y]) { //上面相邻的元素，且没有被使用过
            neighb[0] = board[x-1][y]; //上
            count++;
        }
        if (x+1<board.length && !used[x+1][y]){ //......
            neighb[1] = board[x+1][y]; //下
            count++;
        }
        if (y-1>=0 && !used[x][y - 1]) {
            neighb[2] = board[x][y - 1]; //左
            count++;
        }
        if (y+1<board[0].length && !used[x][y + 1]) {
            neighb[3] = board[x][y + 1]; //右
            count++;
        }

        for (int i = 0;i < neighb.length;i++) {
            int line = x;
            int col = y;
            char ch = neighb[i]; //取出相邻的元素，但是我不知道这个元素是坐标是多少
            if (ch == c) { //和我们要找的字符一样
                if (i==0) { //上
                    line = x-1;
                    col = y;
                }else if (i==1) { //下
                    line = x+1;
                    col = y;
                }else if (i==2) { //左
                    line = x;
                    col = y-1;
                }else if (i==3){ //右
                    line = x;
                    col = y+1;
                }
                path[k] = c;
                used[line][col] = true;
                backtrack(board,path,used,k+1,word,line,col);
                used[line][col] = false; //撤消
                path[k] = '\u0000';
            }
        }
    }

    /*              A
          /                  \
        S(AS)                B(AB)
                           /      \
                          F(ABF)  C(ABC)
                      /   |     \
                (ABFS)S (ABFD)D  (ABFC)C
                     |
               (ABFSA)A

      1.从上面的决策树可以看到树的深度取决于字符串中字符的个数，
      2.每个节点的分支个数取决于当前字符所在矩阵的位置
      3.当使用完一次字符时，该字符位置不能再继续使用了，需要我们使用完后进行标记
      4.

    */




}
