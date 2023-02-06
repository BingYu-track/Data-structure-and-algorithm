package lessons.week8.pratice.backtrackin.pratice6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0  子集 II
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
 * @date: 2023/2/2
 */
public class SubsetsWithDup {

    public static void main(String[] args) {
        int[] nums = {1,2,1,2};
        SubsetsWithDup dup = new SubsetsWithDup();
        List<List<Integer>> lists = dup.subsetsWithDup(nums);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();

    /*完全是自己解题
    执行用时：1 ms, 在所有 Java 提交中击败了99.23%的用户
    内存消耗：41.5 MB, 在所有 Java 提交中击败了78.36%的用户

     解题思路: 和之前的题目一样，分为放和不放；只有2种选择，树的深度为nums.length；并且重复的元素会导致组合重复，我们用另一个数组用来记录数字的出现次数
      参考争哥的方法用hashMap来记录各数字出现的对应次数
    */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //将nums的元素按照从小到大进行排序,让重复的数字聚集在一起，问题在于后面如何快速去重
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        backtrack(0,nums,path);
        return result;
    }


    private void backtrack(int k, int[] nums, List<Integer> path) {
        if (k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        int num = nums[k];
        if (path.size()>0 && num == path.get(path.size()-1)) { //遇到前面一个路径是相同的元素，只选择放入，另外一个的选择不执行即可
            path.add(num);
            backtrack(k+1,nums,path);
            path.remove(path.size()-1);
            return;
        }
        backtrack(k+1,nums,path);//不放入
        //放入
        path.add(num);
        backtrack(k+1,nums,path);
        path.remove(path.size()-1); //撤销之前的选择
    }

    /* 1,2,1,2   []、[1]、[2]、[1,1]、[2,2]、[1,2]、[1,2,2]、[1,1,2]、[1,1,2,2]

     可选列表: 1 2 1
     阶段k:  nums.length
     路径:
     决策树
     k=0时1次、k=1时1次 k=2时1次

                                1 1 2
                                 {}
                  /                         \  ------选择是否放入1
                 {}                            1
                /   \               |-------/     \   ------选择是否放入1
               {}    1              |      1       1,1
             /   \  / \             |    /   \     /   \ ------选择是否放入2
            {}   2 1  1,2           |   1  {1,2} {1,1} {1,1,2}
                                    |
                                    V
                                   TODO 当现在要放入的数据遇到重复数据，并且路径前一个元素就是和当前元素一样是重复的，
                                         并且再选择不放入时，下面所有的集合都是重复的，因此将其进行剪枝只处理放入的分支

    */

}
