package lessons.week8.pratice.backtrack.pratice6;

import java.util.List;

/**
 * @version 1.0  子集 II--重复练习
 * @Description: 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集不能包含重复的子集。返回的解集中，子集可以按 任意顺序排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * @author: bingyu
 * @date: 2023/2/7
 */
public class RepeatPratice {

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        RepeatPratice dup = new RepeatPratice();
        List<List<Integer>> lists = dup.subsetsWithDup(nums);
        System.out.println(lists);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        return null;
    }
}
