package lessons.week8.pratice.backtrack.pratice10;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<List<Integer>> lists = sum2.combinationSum2Optimize(candidates, target);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();
    private int sum = 0;


    /**
     * 我的优化
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2Optimize(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[candidates.length]; //用来记录同一树枝上的元素是否使用过
        Arrays.sort(candidates); //为什么需要排序? 这里排序的目的是为了让相同的元素聚在一起，这样就能用candidates[i]==candidates[i-1]判断是否已经使用了重复的元素
        backtrack(candidates,target,path,used,0);
        return result;
    }

    /*
    执行用时：4 ms, 在所有 Java 提交中击败了42.60%的用户
    内存消耗：42 MB, 在所有 Java 提交中击败了32.13%的用户
     */
    private void backtrack(int[] candidates, int target, List<Integer> path, boolean[] used,int k) {
        if(sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (sum > target || k==candidates.length) return;
        for (int i = k;i<candidates.length;i++) {
            //TODO 同一树层有重复元素，需要跳过，因为题目要求元素只能使用一次，那后面出现重复的元素再组合毕竟会形成重复的组合(前面题目之
            // 所以可以直接用控制起始位置遍历来去除重复组合,是因为之前的题目集合里是没有重复元素的)
            if (i>0 && candidates[i]==candidates[i-1] && !used[i-1]) continue;
            int num = candidates[i];
            path.add(num);
            used[i] = true;
            sum += num;
            backtrack(candidates,target,path,used,i+1);
            path.remove(path.size()-1);
            used[i] = false;
            sum -= num;
        }
    } //查看输出结果发现组合中数字重复使用了

    /* 题目要求是组合不能重复，且组合里数字不能重复使用
    从下面的树可以看出，可选列表每层是不断变少的,每到下一层，开始遍历的位置都会向后移动一位;然后树的高度也是有限制的,和数组元素个数一样
        输入: candidates=[1,1,2], target=2,
        输出: [[1,1],[2]]
                               [1,1,2]
                          /       |      \
                         1        1       2
                      /    \    /   \    /  \
                     1,1  1,2  1,1  1,2 2,1 2,1
     */

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
            if (i < k) continue; //我这里也是起到控制开始遍历的起点来达到去除重复组合的目的
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
