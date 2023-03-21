package lessons.week9.pratice.dfsbfs.pratice9;

/**
 * @version 1.0  529. 扫雷游戏
 * @Description: 让我们一起来玩扫雷游戏！给你一个大小为 m x n 二维字符矩阵board，表示扫雷游戏的盘面，其中：
 * 'M'代表一个 未挖出的 地雷，
 * 'E'代表一个 未挖出的 空方块，
 * 'B'代表没有相邻(上，下，左，右，和所有4个对角线)地雷的 已挖出的空白方块，
 * 数字('1' 到 '8')表示有多少地雷与这块 已挖出的 方块相邻，
 * 'X'则表示一个 已挖出的 地雷。
 * 给你一个整数数组 click ，其中 click = [clickr, clickc] 表示在所有 未挖出的方块('M' 或者 'E')中的下一个点击位置(clickr是行下标，clickc是列下标)。
 * 根据以下规则，返回相应位置被点击后对应的盘面：
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为'X' 。
 * 如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回盘面。
 *
 *
 * 示例 1：
 * 输入：board = [
 *                  ['E','E','E','E','E'],
 *                  ['E','E','M','E','E'],
 *                  ['E','E','E','E','E'],
 *                  ['E','E','E','E','E']
 *              ],
 *              click = [3,0]
 *
 * 输出：[
 *          ['B','1','E','1','B'],
 *          ['B','1','M','1','B'],
 *          ['B','1','1','1','B'],
 *          ['B','B','B','B','B']
 *      ]
 *
 *
 * 示例 2：
 * 输入：board = [
 *                  ['B','1','E','1','B'],
 *                  ['B','1','M','1','B'],
 *                  ['B','1','1','1','B'],
 *                  ['B','B','B','B','B']
 *             ],
 *             click = [1,2]
 *
 * 输出：[
 *          ['B','1','E','1','B'],
 *          ['B','1','X','1','B'],
 *          ['B','1','1','1','B'],
 *          ['B','B','B','B','B']
 *      ]
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] 为 'M'、'E'、'B' 或数字 '1' 到 '8' 中的一个
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] 为 'M' 或 'E'
 *
 * @author: bingyu
 * @date: 2023/3/21
 */
public class UpdateBoard {

    public static void main(String[] args) {
        UpdateBoard b = new UpdateBoard();
        char[][] board = {{'E','E','E','E','E'},
                          {'E','E','M','E','E'},
                          {'E','E','E','E','E'},
                          {'E','E','E','E','E'}};
        int[] click = {3,0};
        char[][] result = b.updateBoard(board, click);
        System.out.println();
        //[["E","E","E","E","E"],
        // ["E","E","E","E","E"],
        // ["M","E","E","E","E"],
        // ["1","E","E","E","E"]]
    }

    /*
     题目意思是，给出你一个初始的扫雷矩阵，并给出你下一步要点击的位置;求你点击后的扫雷矩阵状态，题目中的这2条是核心逻辑: 
     1.如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
     2.如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。

    TODO： 我的思路。 注意('board' 里只允许有['M', 'E', 'B', '1', '2', '3', '4', '5', '6', '7', '8'] 这些值。只能选M或者E进行点击)
       1.点击到地雷时，地雷位置改为X直接结束
       2.点击空方块E时，递归遍历相邻的方块，如果相邻有雷，算出有多少雷，并把当前方块E改为相邻地雷的数量，并且当前方块E周围的方块不能点开。
         如果方块E相邻无雷，则依次点开这些方块，并继续检测这些方块周围的方块，不断递归执行
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
  内存消耗：41.3 MB, 在所有 Java 提交中击败了68.94%的用户
    */
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0]; //行
        int col = click[1]; //列
        if (board[row][col] == 'M') { //刚好点击到地雷
            board[row][col] = 'X';
            return board;
        }
        int[][] directions = {
                {-1,0},{1,0},{0,-1},{0,1}, //上下左右
                {-1,-1},{1,-1},{-1,1},{1,1}  //左上、左下、右上、右下
        }; //8个方向
        //点击E方块处理
        dfs(row,col,board,directions);
        return board;
    }

    private void dfs(int row, int col, char[][] board,int[][] directions) {
        int count = checkAdjoinMine(row, col, directions, board); //得到相邻方块的地雷数量
        if (count == 0) {
            //执行到这里，说明周围没有地雷，将该方块改成B
            board[row][col] = 'B';
            //遍历四周的方块递归执行
            for (int i = 0;i<directions.length;i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                //新的方块越界，或者不是未挖出的空方块E，就跳过
                if (newRow < 0 || newCol < 0 || newRow > board.length-1 || newCol > board[0].length-1 || board[newRow][newCol]!='E') continue;
                dfs(newRow,newCol,board,directions);
            }
        }else {
            //说明周围有地雷，将当前方块标注为地雷数量
            board[row][col] = (char) (count + '0'); //TODO: 之前我是这里加的字符串，然后用的chat(0)转换的字符，导致时间效率变低了
        }
    }

    //校验当前位置和多少个地雷相邻
    public int checkAdjoinMine(int row, int col, int[][] directions, char[][] board) {
        int count = 0;
        for (int i = 0;i<directions.length;i++) { //遍历四周的方块
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            //新的方块越界，或者既不是未挖出的空方块E，也不是地雷，就跳过
            if (newRow < 0 || newCol < 0 || newRow > board.length-1 || newCol > board[0].length-1 ||
                    (board[newRow][newCol]!='E' && board[newRow][newCol]!='M')) continue;
            if (board[newRow][newCol] == 'M') { //遇到地雷，地雷数量加1
                count++;
            }
        }
        return count;
    }
}
