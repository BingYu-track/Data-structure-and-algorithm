package lessons.week8.pratice.backtrackin.pratice7;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  全排列
 * @Description: 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * @author: bingyu
 * @date: 2023/2/6
 */
public class Permute {

    public static void main(String[] args) {
        Permute p = new Permute();
        int[] nums = {1,2,3};
        List<List<Integer>> list = p.permute(nums);
        System.out.println(list);
    }

    private List<List<Integer>> result = new ArrayList<>();

    /*
     TODO 该题需要多次重复练习

     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backtrack(0,path,nums);
        return result;
    }


    private void backtrack(int k, List<Integer> path, int[] nums) {
        if (k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        //从下面决策树可以看出，k阶段数由nums的元素个数决定，下面的分支个数随着树深度的增加而减少
        for (int i = 0;i < nums.length;i++) {
            if (!path.contains(nums[i])) { //注意在一步步return返回到第0层时，即k=0时，要避免重复处理
                path.add(nums[i]);
                backtrack(k+1,path,nums);
                path.remove(path.size()-1);
            }
        }
    }

    /*
      决策树
                   {}
               /    |    \-------3种选择
              1     2     3
             / \         -------2种选择
            12  13
           /     \   -------1种选择
          123   132
     */

}
