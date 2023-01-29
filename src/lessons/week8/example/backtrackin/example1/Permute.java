package lessons.week8.example.backtrackin.example1;


import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 全排列
 * @Description: 给定一个不含重复数字的数组 nums,返回其所有可能的全排列 。你可以 按任意顺序返回答案。
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
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * @author: bingyu
 * @date: 2023/1/17
 */
public class Permute {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Permute permute = new Permute();
        List<List<Integer>> lists = permute.permute(nums);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>(); //这个用来记录每个阶段的决策


    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backtrack(nums, 0 ,path);
        return result;
    }

    /** TODO：比较"排列"和"组合"的区别
     *   排列，是固定的
     *
     * @param nums 可选列表，nums中除掉寻找于path中的数据
     * @param k 表示当前所属的决策阶段，这里k就相当于决策树的层数，表示当前在第几层
     * @param path 用来记录路径到path里
     */
    private void backtrack(int[] nums, int k, List<Integer> path) {
        //结束条件
        if(k == nums.length) {
            result.add(new ArrayList<>(path));//说明得到了一个可行解，将其放入结果数组
            return;
        }
        //这里开始是决策阶段
        for (int i = 0;i < nums.length;i++) { //在可选列表中选择
            //排除不合法的选择(因为此时路径可能已经有数字了)
            if (path.contains(nums[i])) {
                continue;
            }
            //做选择，将选择的路径添加到path里
            path.add(nums[i]);
            //递归，将决策阶段+1，进入下一层决策树
            backtrack(nums,k+1,path);
            //撤销路径中最近选择的
            path.remove(path.size()-1);
        }
        //先是取了1,2,3这个解，然后回退到了第2阶段
    }

    /* 递归有递归树，同样的回溯有"决策树"
                          (1,2,3)
                选择1 /   选择2|     \ 选择3 ----->阶段0决策
                    /        |       \
                 (2,3)     (1,3)    (1,2)
                /   \     /   \      /  \
           选择2   选则3 选择1  选择3 选择1 选择2  ----->阶段1决策
              |      |   |     |    |     |
              3      2   3     1    2     1   ----->剩下就是阶段2决策，直接输出，因为只剩最后一个数字了
     */


}
