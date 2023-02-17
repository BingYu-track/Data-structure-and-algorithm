package lessons.week8.pratice.backtrack.pratice5;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 子集--争哥解法
 * @author: bingyu
 * @date: 2023/2/2
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        ZgSolved zgSolved = new ZgSolved();
        List<List<Integer>> result = zgSolved.subsets(nums);
        System.out.println(result);
    }

    private List<List<Integer>> result = new ArrayList<List<Integer>>();

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



    /* 争哥的思路是阶段为数字个数，将是否选择数字作为选择列表，选择和不选择，这样就只有2种选择
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
