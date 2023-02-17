package lessons.week8.pratice.backtrack.pratice1;

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

    private List<List<String>> result = new ArrayList<>();

    /*
     解题思路: 每个皇后都不同行、不同列，也不在对角线上
     执行用时：2 ms, 在所有 Java 提交中击败了80.06%的用户
     内存消耗：41.8 MB, 在所有 Java 提交中击败了51.16%的用户
    */
    public List<List<String>> solveNQueens(int n) {
        char[][] path = new char[n][n];
        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                path[i][j] = '.';
            }
        }
        backtrack(0,n,path);
        return result;
    }

    /**
     *
     * @param k 阶段    --行
     * @param n 可选列表 --列
     * @param path 路径
     */
    private void backtrack(int k, int n, char[][] path) {
         if (k == n) {
             //执行到这说明全部处理完毕
             List<String> list = new ArrayList<>();
             for (char[] chars : path) {
                 String s = String.valueOf(chars);
                 list.add(s);
             }
             result.add(list);
             return;
         }
         //开始进行选择
         for (int i = 0;i<n;i++) {
             if (isOk(k,i,path)) {
                 path[k][i] = 'Q';
                 backtrack(k+1,n,path);
                 path[k][i] = '.';//TODO 撤销---有个疑问，就是如果最终正常return后，这里是不是会撤销覆盖正确的值?
                                        //会，但是再覆盖前，就已经用result保存了结果，然后撤销后还会不断取寻找其它可行解
             }
         }
    }

    private boolean isOk(int row, int col, char[][] path) {
        //1.判断当前行有没有皇后
        for (int i = 0;i<path.length;i++) {
            if (path[row][i] == 'Q') return false;
        }

        //2.判断当前列有没有皇后
        for (int i = 0;i<path.length;i++) {
            if (path[i][col] == 'Q') return false;
        }

        //3.判断左上对角线是否有皇后
        int i = row - 1;
        int j = col - 1;
        while (i>=0 && j>=0) {
            if (path[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        //4.判断右上对角线是否有皇后
        i = row - 1;
        j = col + 1;
        while (i>=0 && j<path.length) {
            if (path[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }

    /*
       可选列表: 每行的列(注意在放入一个位置时需要检查是否符合)
       阶段k: 行
       路径: 用char数组来记录走的路径
       决策树

    */

}
