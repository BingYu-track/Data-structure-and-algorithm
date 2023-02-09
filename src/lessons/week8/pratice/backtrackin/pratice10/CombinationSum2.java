package lessons.week8.pratice.backtrackin.pratice10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0  组合总和 II
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
public class CombinationSum2 {

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        CombinationSum2 sum2 = new CombinationSum2();
        List<List<Integer>> lists = sum2.combinationSum2(candidates, target);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();
    private int sum = 0;

    /*
     执行用时：4 ms, 在所有 Java 提交中击败了42.42%的用户
     内存消耗：41.9 MB, 在所有 Java 提交中击败了49.88%的用户
     每次组合每个数字只能使用一次
    */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先统计集合中的元素和出现次数
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0;i<candidates.length;i++) {
            int num = candidates[i];
            if (!hm.containsKey(num)) {
                hm.put(num,1);
            }else {
                hm.put(num,hm.get(num) + 1);
            }
        }
        int size = hm.size();
        int[] nums = new int[size];
        int[] counts = new int[size];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            nums[i] = num;
            counts[i] = count;
            i++;
        }
        List<Integer> path = new ArrayList<>();
        bacltrack(0,path,nums,counts,target);
        return result;
    }

    /*
     我的思路，和上一题基本是一模一样
    */
    private void bacltrack(int k, List<Integer> path, int[] nums, int[] counts,int target) {
        if (sum == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if (sum > target) return;
        for (int i = 0;i<nums.length;i++) {
            if (i < k) continue;
            int num = nums[i];
            if (counts[i] > 0) {
                path.add(num);
                sum += num;
                counts[i]--;
                bacltrack(i,path,nums,counts,target);
                path.remove(path.size()-1);
                sum -= num;
                counts[i]++;
            }
        }
    }

    /*
     [10,1,2,7,6,1,5]
     [1,1,6],[1,2,5],[1,7],[2,6]

     */

}
