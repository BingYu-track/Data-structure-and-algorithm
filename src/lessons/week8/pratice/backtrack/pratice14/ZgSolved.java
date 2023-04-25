package lessons.week8.pratice.backtrack.pratice14;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  括号生成----争哥解法
 * @Description: 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 *
 * @author: bingyu
 * @date: 2023/2/16
 */
public class ZgSolved {

    public static void main(String[] args) {
        int n = 3;
        ZgSolved zgSolved = new ZgSolved();
        List<String> list = zgSolved.generateParenthesis(n);
        System.out.println(list);
    }

    private List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        char[] path = new char[2*n];
        backtrack(n, 0, 0, 0, path);
        return result;
    }

    /*
    争哥解法
    */
    private void backtrack(int n, int leftUsed, int rightUsed, int k, char[] path) {
        if (k == 2*n) {
            result.add(String.valueOf(path));
            return;
        }
        if (leftUsed < n) {
            path[k] = '(';
            backtrack(n, leftUsed+1, rightUsed, k+1, path);
        }
        if (leftUsed > rightUsed) {
            path[k] = ')';
            backtrack(n, leftUsed, rightUsed+1, k+1, path);
        }
    }
}
