package lessons.week8.pratice.backtrackin.pratice2;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  解数独
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
 * @date: 2023/1/16
 */
public class SolveSudoku {

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
        SolveSudoku sudoku = new SolveSudoku();
        sudoku.solveSudoku(board2);
        System.out.println();
    }

    private boolean flag = false;

    /*TODO: 注意数独问题是必须把空格全部填满
     要求 1.行，列均不能出现相同的数字
         2.3x3宫内不能出现相同的数字
         3.输入给定的数字是不能动的
      我的思路是扫描所在行，排除已占的;再扫描所在列,排除已占的;最后
      执行用时 31 ms, 在所有 Java 提交中击败了5.14%的用户
     内存消耗：41.4 MB, 在所有 Java 提交中击败了5.02%的用户
     完全是自主解决
    */
    public void solveSudoku(char[][] board) {
        backtrack(0,0,board);
    }

    /*
     * @param row 所在行---阶段k
     * @param col 所在列
     * @param board --->看作path
     */
    private void backtrack(int row, int col,char[][] board) {
        if (row == board.length && col == 0) { //终止条件
            flag = true;
            return; //在这里得到了正确的结果，但是return后又会按原来的路径返回撤销，该如何解决? 这里是使用布尔变量来进行控制
        }
        char[] line = board[row]; //所在行
        if (line[col]!='.') {  //如果遇到非空格
            if (col == board.length-1  && row <= board.length-1) { //当前列刚好在末尾，到下一行
                backtrack(row+1,0,board);
            }
            if (col < board.length-1) {
                backtrack(row,col+1,board); //不在末尾，到下一列
            }
        }else {
            //执行到这里说明当前row,col位置的元素是空格，计算其可选列表，另外放在这里还有个好处就是不会覆盖固定值
            List<Character> list = calculate(row,col,board);//可选列表，从里面选择放入当前位置的空格
            if (list.size() == 0) return; //TODO: 如果还没到结尾，就没有可选列表，说明当前路径是错误的，直接返回
            //TODO 注意，这里也需要用flag进行控制是否需要继续遍历，否则return返回时，如果list里还有没遍历完的，会继续向下执行覆盖掉正确的结果
            for (int j=0;j<list.size() && !flag;j++) { //在可选列表中进行选择放入i下标位置
                Character c = list.get(j);
                line[col] = c;
                if (col == board.length-1) { //如果列到了末尾，换行
                    backtrack(row+1,0,board);
                }else {
                    backtrack(row,col+1,board); //执行到列的下个阶段，如果这里是最后一个位置，该如何处理?
                }
                if (!flag) {
                    line[col] = '.'; //如果没成功，就撤销
                }
            }
        }
    }

    /**
     * The ASCII value of '0' is 48. So, if you add 1 with 48, it becomes 49 which is equal to 1. The ASCII character of 49 is 1.
     * @param row 行
     * @param col 列
     * @param board
     * @return
     */
    private List<Character> calculate(int row, int col, char[][] board) {
        List<Character> list = new ArrayList<>();
        //初始化1~9字符
        for (int i = 1;i<=9;i++) {
            char c = (char) (i + '0'); //int转为char
            list.add(c);
        }
        //1.排除行元素
        char[] line = board[row];
        for (char c : line) {
            if (list.contains(c)) {
                int index = list.indexOf(c);
                list.remove(index);
            }
        }
        //2.排除列元素
        for (int i=0;i<board.length;i++) {
            char c = board[i][col];
            if (list.contains(c)) {
                int index = list.indexOf(c);
                list.remove(index);
            }
        }
        //3.排除3x3宫内元素
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = 0;
        int colEnd = 0;
        int i1 = board.length / 3 - 1;
        int i2 = (2 * board.length / 3) - 1;
        //3.1确定当前位置在行的范围
        if (row <= i1) {
            rowEnd = i1;
        }else if (row<=i2) {
            rowStart = i1 + 1;
            rowEnd = i2;
        }else {
            rowStart = i2 + 1;
            rowEnd = board.length - 1;
        }
        //3.2确定当前位置在列的范围
        if (col <= i1) {
            colEnd = i1;
        }else if (col <= i2) {
            colStart = i1 + 1;
            colEnd = i2;
        }else {
            colStart = i2 + 1;
            colEnd = board.length - 1;
        }
        for (int i = rowStart;i<=rowEnd; i++) {
            for (int j = colStart;j<=colEnd;j++) {
                char c = board[i][j];
                if (list.contains(c)) {
                    int index = list.indexOf(c);
                    list.remove(index);
                }
            }
        }
        return list;
    }


    /*终止条件--到达第9行，第9列就终止
     可选列表: 9 - 当前位置所在行的不同数字个数 - 所在列的不同数字个数 - 3x3宫内的不同数字个数; 该可变参数时时刻刻都在发生变化
     阶段: 就是board的每一行以及board每列都构成一个阶段
     路径: 就是board的每一列
       决策树
                 5
             /   |   \
            2    4    6

    */
}
