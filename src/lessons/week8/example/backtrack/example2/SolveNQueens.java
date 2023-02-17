package lessons.week8.example.backtrack.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 面试题 08.12. 八皇后
 * @Description: 设计一种算法，打印N皇后在N × N棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
 * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * TODO： 简单来说就是给你一个N×N的棋盘，让你放置N个皇后，使得它们不能互相攻击。
 *  PS：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位
 * 。
 *
 * 注意：本题相对原题做了扩展
 * 示例:
 *
 *  输入：4
 *  输出：[   ----List
 *        [ ----List
 *          ".Q..",
 *          "...Q",
 *          "Q...",
 *          "..Q."
 *        ],
 *
 *        [
 *          "..Q.",
 *          "Q...",
 *          "...Q",
 *          ".Q.."
 *        ]
 *       ]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..", // 解法 1
 *  "...Q",
 *  "Q...",
 *  "..Q."],
 *
 *  ["..Q.", // 解法 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."]
 * ]
 *
 * @author: bingyu
 * @date: 2023/1/16
 */
public class SolveNQueens {

    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> lists = solveNQueens.solveNQueens(4);
        System.out.println(lists);
    }

    private List<List<String>> result = new ArrayList<>(); //这个用来记录每个阶段的决策

    /*
     这个问题本质上跟全排列问题差不多，决策树的每一层表示棋盘上的每一行；每个节点可以做出的选择是，在该行选择任意一列放置一个皇后。
     1.可选列表---棋盘上的列数
     2.当前决策树层数--k,即棋盘上的行数(棋子也只有N个)
     3，路径
     执行用时：2 ms, 在所有 Java 提交中击败了82.56%的用户
     内存消耗：41.5 MB, 在所有 Java 提交中击败了77.15%的用户
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] chars = new char[n][n];
        //这个代码是将棋盘先全部存入"."
        for (int i = 0;i<n;i++) {
            for (int j=0;j<n;j++) {
                chars[i][j] = '.';
            }
        }
        backtrack(n,0,chars);
        return result;
    }

    /** TODO: 这道题目太神奇，经典了，需要重点理解消化！
     * N个棋子选择在当前行中选择任意一列
     * @param col 可选列表，即棋盘列数
     * @param row 决策树层数，即棋盘行数,从0开始
     * @param path 已选择的路径，记录当前每行所选择列的一组解，此时path里全是点，一个二维数组
     */
    private void backtrack(int col, int row, char[][] path) {
        if (row == col) { //row == col说明每行都遍历完成了
            List<String> list = new ArrayList<>(); //这里new应该是为了copy一个副本，不要影响原数据
            for (char[] chars : path) {
                list.add(String.valueOf(chars));
            }
            result.add(list);
            return;
        }
        //在第k行任选一列
        for (int i = 0;i < col;i++) {//这里i就是棋盘的列
            if (isOk(path,row,i)) { //判断第row行,第i列能不能存放皇后
                path[row][i] = 'Q'; //满足，将Q放在第row行第i列，放入后，当前行就不能再放入皇后了，需要处理下一行
                backtrack(col, row+1, path); //继续处理下一行，当每一层遍历完成后，就会返回递归
                path[row][i] = '.'; //恢复原来的选择
                //TODO: 我的疑问主要在递归和恢复这个操作的理解
            }
        }
    }

    /**
     * 判断row行，col列放置皇后是否合适，解决思路是看当前位置所在列，以及左上，右上的位置
     * @param path
     * @param row 存放Q所在行
     * @param col 存放Q所在列
     * @return
     */
    private boolean isOk(char[][] path, int row, int col) {
        int n = path.length;
        //1.检查所在列是否冲突
        for (int i = 0;i < row;i++) { //在指定列col找row前几行元素是否存入了Q
            char c = path[i][col]; //
            if (c == 'Q') { //有返回false
                return false;
            }
        }
        //2.判断当前位置的左对角线是否已存入了Q，从当前位置左斜上遍历
        int k = row - 1; //行
        int line = col - 1; //列
        while (k>=0 && line>=0) { //这里注意要大于等于
            if (path[k][line] == 'Q') return false;
            k--;
            line--;
        }
        //3.检查右对角线是否已存入Q，从当前位置右斜上遍历
        k = row - 1;
        line = col + 1;
        while (k>=0 && line<n) {
            if (path[k][line] == 'Q') return false;
            k--;
            line++;
        }
        return true;
    }

}
