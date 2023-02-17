package lessons.week8.pratice.backtrack.pratice5;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 子集
 * @Description: 给你一个整数数组nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
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
 * @date: 2023/2/2
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Subsets subsets = new Subsets();
        List<List<Integer>> lists = subsets.subsets(nums);
        System.out.println(lists);
//        List<Integer> l1 = new ArrayList<>();
//        List<Integer> l2 = new ArrayList<>();
//        List<Integer> l3 = new ArrayList<>();
//        l1.add(1);
//        l1.add(2);
//        l2.add(1);
//        l2.add(2);
//        l3.add(2);
//        l3.add(1);
//        //TODO 注意list中进行equal是对比大小和里面元素是否完全一样的，但是如果顺序一旦发生变化就不一样了
//        System.out.println(l1.equals(l2));  //true
//        System.out.println(l1.equals(l3)); //false
    }

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        result.add(new ArrayList<>()); //先把空放进去
        List<Integer> path = new ArrayList<>();
        backtrack(0,path,nums);
        return result;
    }

    /**
     * 我这个算法有问题
     * @param k 阶段k
     * @param path 路径
     * @param nums
     */
    private void backtrack(int k, List<Integer> path, int[] nums) {
        if (k==nums.length) { //当到达底部返回
            return;
        }
        for (int i = k;i<nums.length;i++) { //根据当前k的阶段会有不同选择，选择次数依次下降，参考下面的决策树
            int num = nums[i];
            if (!path.contains(num)) { //如何让result去重
                path.add(num);
                if (!result.contains(path)) {
                    result.add(new ArrayList<>(path)); //这里会发生相同的子集，但是顺序不一样，例如[2, 3]和[3, 2]
                }
                backtrack(k+1,path,nums);
                path.remove(path.indexOf(num)); //撤销
            }
        }
    }


    /*另一个思路解法：放入path里的开始有4个选择，后面只有3种选择，然后是2种，最后只有唯一一种选择，但是这种有问题，除了会出现重复子集，不建议使用这种
      [1,2,3]
      可变列表:
      阶段k: 和数字个数相关
      路径:
                               []
                        |              \               \--3个选择
                        1                2              3
                      |    \            | \            |  \  --2个选择
                      12    13          21 23          31 32
                      |      \          |   |          |   | ---1个选择
                     123    123        123 123        123 123

    */
}
