package lessons.week8.pratice.backtrack.pratice4;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  77. 组合
 * @Description: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 *
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * @author: bingyu
 * @date: 2023/2/2
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zgSolved = new ZgSolved();
        List<List<Integer>> combine = zgSolved.combine(4, 2);
        System.out.println(combine);
    }

    /*
     争哥解法 TODO 这题是中途就会得到可行解
     执行用时：21 ms, 在所有 Java 提交中击败了12.77%的用户
     内存消耗：42.8 MB, 在所有 Java 提交中击败了48.43%的用户
     */
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1, new ArrayList<Integer>()); //从
        return result;
    }

    // n,k必须的参数
    // step阶段
    // path路径
    // step选与不选-可选列表
    private void backtrack(int n, int k, int step, List<Integer> path) {
        if (path.size()==k) {
            result.add(new ArrayList(path));
            return;
        }
        if (step == n+1) { //这里是防止出现一直不放的情况下return
            return;
        }
        backtrack(n, k, step+1, path);
        path.add(step);
        backtrack(n, k, step+1, path);
        path.remove(path.size()-1);
    }

}
