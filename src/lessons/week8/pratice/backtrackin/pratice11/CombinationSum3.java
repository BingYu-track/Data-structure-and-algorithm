package lessons.week8.pratice.backtrackin.pratice11;


import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  组合总和 III
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
public class CombinationSum3 {

    public static void main(String[] args) {
        CombinationSum3 sum3 = new CombinationSum3();
        List<List<Integer>> lists = sum3.combinationSum3(3, 9);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();
    private boolean[] nums = new boolean[10]; //用来表示1~9的数字是否被使用
    private Integer sum = 0;


    /**
     * 只能使用1~9的数字，最多只能使用一次
     * @param k 表示一个组合规定的元素个数
     * @param n 要求组合的和为n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        backtrack(1,0,k,n,path);
        return result;
    }

    /*和上一题思路一模一样
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：39.3 MB, 在所有 Java 提交中击败了25.21%的用户
     */
    private void backtrack(int c,int step, int k, int target, List<Integer> path) {
        if (step == k) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }
        for (int i = 1;i<=9;i++) {
            if (i < c) continue; //TODO: 去除重复的组合
            if (!nums[i]) { //如果当前数字还没有被使用，那么可以选择放入path
                path.add(i);
                nums[i] = true;
                sum += i;
                backtrack(i,step+1,k,target,path);
                path.remove(path.size()-1); //撤销
                nums[i] = false;
                sum -= i;
            }
        }
    }


    /*
     可选列表是不断变化的，9~1
     阶段就是k
    */

}
