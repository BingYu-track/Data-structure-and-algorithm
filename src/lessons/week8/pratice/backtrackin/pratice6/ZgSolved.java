package lessons.week8.pratice.backtrackin.pratice6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0  子集 II ----争哥解法
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
 *
 * @author: bingyu
 * @date: 2023/2/3
 */
public class ZgSolved {


    public static void main(String[] args) {
        int[] nums = {1,2,1};
        ZgSolved dup = new ZgSolved();
        List<List<Integer>> lists = dup.subsetsWithDup(nums);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();

    /*
     争哥解题思路: 用2个数组分别存储nums中的不同的元素，以及其元素出现的次数
     执行用时：1 ms, 在所有 Java 提交中击败了99.21%的用户
     内存消耗：41.7 MB, 在所有 Java 提交中击败了57.21%的用户
    */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int count = 1;
            if (hm.containsKey(nums[i])) {
                count += hm.get(nums[i]);
            }
            hm.put(nums[i], count);
        }
        int n = hm.size();
        int[] uniqueNums = new int[n];
        int[] counts = new int[n];
        int k = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (hm.containsKey(nums[i])) {
                uniqueNums[k] = nums[i];
                counts[k] = hm.get(nums[i]);
                k++;
                hm.remove(nums[i]);
            }
        }
        backtrack(uniqueNums, counts, 0, new ArrayList<Integer>());
        return result;
    }

    //1、2、1,2 => 1、1,1、2、1,1,2
    private void backtrack(int[] uniqueNums, int[] counts, int k, List<Integer> path) {
        if (k == uniqueNums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int count = 0; count <= counts[k]; ++count) {
            //TODO: counts[k]表示当前层元素出现的次数，从0开始到counts[k]结束，如果我们去掉最外层的循环，那么加入path的永远是最多次数的那个可行解
            for (int i = 0; i < count; ++i) {  //这个是放几个当前层的元素到路径里
                path.add(uniqueNums[k]); //uniqueNums[k]表示当前层需要放的元素，这里循环就是次数，表示放入元素需要放几次
            }
            backtrack(uniqueNums, counts, k+1, path);
            for (int i = 0; i < count; ++i) { //这里是撤销，之前放了几个，这里就要删除几个
                path.remove(path.size()-1);
            }
        }

    }
    /*
        1,2      []
              /     \------放入1或者不放
            {}       1
          /   \     / \ ----------放入2或者不放
         {}    2   1   1,2
   {}、2 、1、[1,1]、[1,2]、[1,1,2]
    */

}
