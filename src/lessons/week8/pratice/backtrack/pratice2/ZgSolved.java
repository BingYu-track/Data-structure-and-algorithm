package lessons.week8.pratice.backtrack.pratice2;

/**
 * @version 1.0  解数独 --争哥解法
 * @Description: 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 * 示例 1：
 *
 *
 * 输入：board = [
 *                  ['5','3','.','.','7','.','.','.','.'],
 *                  ['6','.','.','1','9','5','.','.','.'],
 *                  ['.','9','8','.','.','.','.','6','.'],
 *                  ['8','.','.','.','6','.','.','.','3'],
 *                  ['4','.','.','8','.','3','.','.','1'],
 *                  ['7','.','.','.','2','.','.','.','6'],
 *                  ['.','6','.','.','.','.','2','8','.'],
 *                  ['.','.','.','4','1','9','.','.','5'],
 *                  ['.','.','.','.','8','.','.','7','9']
 *             ]
 * 输出：[
 *          ['5','3','4','6','7','8','9','1','2'],
 *          ['6','7','2','1','9','5','3','4','8'],
 *          ['1','9','8','3','4','2','5','6','7'],
 *          ['8','5','9','7','6','1','4','2','3'],
 *          ['4','2','6','8','5','3','7','9','1'],
 *          ['7','1','3','9','2','4','8','5','6'],
 *          ['9','6','1','5','3','7','2','8','4'],
 *          ['2','8','7','4','1','9','6','3','5'],
 *          ['3','4','5','2','8','6','1','7','9']
 *     ]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 *
 * @author: bingyu
 * @date: 2023/2/2
 */
public class ZgSolved {

    public static void main(String[] args) {
        char[][] board1 = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        char[][] board2 = {
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'},
        };
        ZgSolved sudoku = new ZgSolved();
        sudoku.solveSudoku(board2);
        System.out.println();
    }

    /*
     争哥解法:
     */
    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][][] blocks = new boolean[3][3][10];
    private boolean solved = false;

    /* 争哥解法
      执行用时：2 ms, 在所有 Java 提交中击败了89.04%的用户
      内存消耗：41.4 MB, 在所有 Java 提交中击败了5.00%的用户
     */
    public void solveSudoku(char[][] board) {
        //这个是记录所有数字是否在行、列、3*3格子里各个数字是否存在
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    blocks[i/3][j/3][num] = true;
                }
            }
        }
        backtrack(0, 0, board);
    }

    private void backtrack(int row, int col, char[][] board) {
        if (row == 9) {
            solved = true;
            return;
        }
        if (board[row][col]!='.') {
            int nextRow = row;
            int nextCol = col+1;
            if (col == 8) {
                nextRow = row+1;
                nextCol = 0;;
            }
            backtrack(nextRow, nextCol, board);
            if (solved) return;
        } else {
            for (int num = 1; num <= 9; ++num) {
                //判断当前第row行，第col列，在1~9的数字中哪个可以使用
                if (!rows[row][num] && !cols[col][num] && !blocks[row/3][col/3][num]) {
                    board[row][col] = String.valueOf(num).charAt(0); // 数字转化成字符
                    rows[row][num] = true;
                    cols[col][num] = true;
                    blocks[row/3][col/3][num] = true;
                    int nextRow = row;
                    int nextCol = col+1;
                    if (col == 8) {
                        nextRow = row+1;
                        nextCol = 0;;
                    }
                    backtrack(nextRow, nextCol, board);
                    if (solved) return;
                    board[row][col] = '.';
                    rows[row][num] = false;
                    cols[col][num] = false;
                    blocks[row/3][col/3][num] = false;
                }
            }
        }
    }

}
