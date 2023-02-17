package lessons.week8.pratice.backtrack.pratice11;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  组合总和 III ----争哥解法
 * @Description: 找出所有相加之和为n的k个数的组合，且满足下列条件：
 *
 * 只使用数字1到9
 * 每个数字最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 *
 * 示例 3:
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 *
 * 提示:
 * 2 <= k <= 9
 * 1 <= n <= 60
 *
 * @author: bingyu
 * @date: 2023/2/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zgSolved = new ZgSolved();
        List<List<Integer>> lists = zgSolved.combinationSum3(3, 9);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();


    // 1~9，选k个数，和为n
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k, n, 1, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(int k, int n, int step, int sum, List<Integer> path) {
        if (sum == n && path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (sum > n || path.size() > k || step > 9) {
            return;
        }
        backtrack(k, n, step+1, sum, path);
        path.add(step);
        backtrack(k, n, step+1, sum+step, path);
        path.remove(path.size()-1);
    }
}
