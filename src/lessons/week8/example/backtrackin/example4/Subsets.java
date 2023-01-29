package lessons.week8.example.backtrackin.example4;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 子集(所有的组合)
 * @Description: 给你一个整数数组nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * TODO:注意组合的子集有2^n，n表示元素的个数!例如例1中，就是2^3=8种组合
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * @author: bingyu
 * @date: 2023/1/16
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        Subsets subsets = new Subsets();
        List<List<Integer>> result = subsets.subsets(nums);
        System.out.println(result);
    }

    private static List<List<Integer>> result = new ArrayList<List<Integer>>();


    /*
      可选列表: 就是nums
      路径: 就是当前已经选择的子集 用List<List<Integer>>存储
      决策阶段: 在k中从0~k
      解题思路: 列出所有的子集，在列出期间，把重复的去掉
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        backtrack(nums,list,0);
        return result;
    }

    /**TODO: 重点思路，还是要先画出决策树才能更好的写出代码
     * 每个数字有2个选择，放入或者不放入
     * @param nums 可选列表
     * @param path 路径
     * @param k 阶段
     */
    private void backtrack(int[] nums, List<Integer> path, int k) {
        if (k == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        //TODO 不选择，直接跳到下一阶段，从下面的决策树可以看到，不选择返回不需要撤销，选择返回是需要撤销的
        backtrack(nums,path,k+1);
        //TODO 选择后放入路径中，再跳到下一阶段
        path.add(nums[k]);
        backtrack(nums,path,k+1);
        path.remove(path.size()-1); //撤销之前的选择
    }



    /*
     画出决策树 1,2,3
                        {}
            /                       \   -----k=0，选择放入1还是不放入
          {}                        {1}
         /  \                    /       \ -----k=1 选择放入2还是不放入
       {}   {2}                 {1}     {1,2}
      / \   /  \               /   \    /    \  ----k=2 选择放入3还是不放入
     {} {3}{2} {2,3}         {1} {1,3} {1,2} {1,2,3} ---到递归到底部时进行返回，并放入结果数组里
     */

}
