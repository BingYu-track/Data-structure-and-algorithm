package lessons.week8.pratice.backtrack.pratice9;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 组合总和---争哥解法
 * @Description: 给你一个 无重复元素 的整数数组candidates和一个目标整数target，找出candidates中可以使数字和为目标数target的所有不同组合 ，
 * 并以列表形式返回。你可以按任意顺序返回这些组合。candidates中的同一个数字可以无限制重复被选取。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target的不同组合数少于150个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素互不相同
 * 1 <= target <= 40
 *
 * @author: bingyu
 * @date: 2023/2/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        int target = 8;
        ZgSolved solved = new ZgSolved();
        List<List<Integer>> lists = solved.combinationSum(candidates, target);
        System.out.println(lists);
    }

    /*
     争哥解法: 思路
     执行用时：2 ms, 在所有 Java 提交中击败了75.86%的用户
     内存消耗：41.4 MB, 在所有 Java 提交中击败了96.76%的用户
    */
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, 0, target, new ArrayList<>());
        return result;
    }

    /** 这个方法不太通用，也不好理解建议使用我上面树型模型那样通用的解法
     * 争哥思路: 每次选择一个数字后，目标值减去该数字的总和，剩下的作为新的目标值继续寻找可行解，直到目标值为0
     * @param candidates
     * @param k 阶段
     * @param left 能构成目标元素所需要的数字=目标元素-之前数字的和
     * @param path 路径
     */
    private void backtrack(int[] candidates, int k, int left, List<Integer> path) {
        if (left == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (k == candidates.length) {
            return;
        }
        for (int i = 0; i <= left/candidates[k]; ++i) { //最多只能出现0~left/candidates[k]个元素个数
            for (int j = 0; j < i; ++j) { //放0~i个元素到集合里面
                path.add(candidates[k]);
            }
            backtrack(candidates, k+1, left-i*candidates[k], path);
            for (int j = 0; j < i; ++j) { //撤销
                path.remove(path.size()-1);
            }
        }
    }



}
