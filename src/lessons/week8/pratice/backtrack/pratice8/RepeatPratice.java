package lessons.week8.pratice.backtrack.pratice8;

import java.util.List;

/**
 * @version 1.0  全排列 II--重复练习
 * @Description: 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * @author: bingyu
 * @date: 2023/2/7
 */
public class RepeatPratice {

    public static void main(String[] args) {
        RepeatPratice p = new RepeatPratice();
        int[] nums = {1,2,1};
        List<List<Integer>> lists = p.permuteUnique(nums);
        System.out.println(lists);
    }



    public List<List<Integer>> permuteUnique(int[] nums) {

        return null;
    }


}
