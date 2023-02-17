package lessons.week8.pratice.backtrack.pratice10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0  组合总和 II --- 争哥解法
 * @Description: 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 *
 * 示例1:
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例2:
 * 输入: candidates=[2,5,2,1,2], target=5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * 提示:
 * 1 <=candidates.length <= 100
 * 1 <=candidates[i] <= 50
 * 1 <= target <= 30
 *
 * @author: bingyu
 * @date: 2023/2/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        ZgSolved zgSolved = new ZgSolved();
        List<List<Integer>> lists = zgSolved.combinationSum2(candidates, target);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();

    /*
     争个解法，不太推荐该方法
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashMap<Integer, Integer> hashTable = new HashMap<>();
        for (int i = 0; i < candidates.length; ++i) {
            if (!hashTable.containsKey(candidates[i])) {
                hashTable.put(candidates[i], 1);
            } else {
                hashTable.put(candidates[i], hashTable.get(candidates[i])+1);
            }
        }
        List<Integer> nums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < candidates.length; ++i) {
            if (hashTable.containsKey(candidates[i])) {
                nums.add(candidates[i]);
                counts.add(hashTable.get(candidates[i]));
                hashTable.remove(candidates[i]);
            }
        }
        backtrack(nums, counts, 0, target, new ArrayList<>());
        return result;
    }

    private void backtrack(List<Integer> nums, List<Integer> counts, int k, int left, List<Integer> path) {
        if (left == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (left < 0 || k == nums.size()) {
            return;
        }
        for (int count = 0; count <= counts.get(k); ++count) {
            for (int i = 0; i < count; ++i) {
                path.add(nums.get(k));
            }
            backtrack(nums, counts, k+1, left-count*nums.get(k), path);
            for (int i = 0; i < count; ++i) {
                path.remove(path.size()-1);
            }
        }
    }



}
