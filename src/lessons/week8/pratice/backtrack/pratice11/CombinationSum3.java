package lessons.week8.pratice.backtrack.pratice11;


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
     * 只能使用1~9的数字，每个组合元素最多只能使用一次，因为集合不存在重复元素，因此无需进行树层去重，我们只需要进行数枝去重即可，数枝去重就用startIndex即可
     * @param k 表示一个组合规定的元素个数
     * @param n 要求组合的和为n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        //backtrack2(1,0,k,n,path);
        backtrack(1,1,k,n,path);
        return result;
    }

    /** TODO:推荐该方法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了65.15%的用户
     * @param startIndex 用来控制每层横向遍历的起始位置--保证组合不会重复
     * @param step 用来记录树的深度，相当于阶段k
     * @param k  根据题意规定树的最大深度
     * @param target 目标值
     * @param path 路径
     */
    private void backtrack(int startIndex,int step,int k, int target, List<Integer> path) {
        if (sum == target && step == k+1) { //保证和等于目标值外，还要时组合里的元素个数一样
            result.add(new ArrayList<>(path));
            return;
        }
        if (step >= k+1) return;
        for (int i = startIndex;i<=9;i++) {
            path.add(i);
            sum += i;
            backtrack(i+1,step+1,k,target,path);
            path.remove(path.size()-1);
            sum -= i;
        }
    }

    /*   因为要求组合不能重复，肯定要用startIndex控制横向遍历的起始位置，然后因为要求，集合里的元素只能使用一次
           1~9，只能用一次，而且组合不能重复，k相当于固定了树的深度
                 1                     2                 .........3        4        5      6        7        8       9
            2 3 4 5 6 7 8 9         3 4 5 6 7 8 9
        3456789
     */




    /*和上一题思路一模一样
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：39.3 MB, 在所有 Java 提交中击败了25.21%的用户
     */
    private void backtrack2(int c,int step, int k, int target, List<Integer> path) {
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
                backtrack2(i,step+1,k,target,path);
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
