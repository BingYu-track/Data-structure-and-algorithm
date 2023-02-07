package lessons.week8.pratice.backtrackin.pratice8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0  全排列 II
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
 *
 * @author: bingyu
 * @date: 2023/2/6
 */
public class PermuteUnique {

    public static void main(String[] args) {
        PermuteUnique p = new PermuteUnique();
        int[] nums = {1,2,1,2};
        List<List<Integer>> lists = p.permuteUnique(nums);
        System.out.println(lists);
    }

    private List<List<Integer>> result = new ArrayList<>();

    /*
     TODO: 必须重复进行练习
     执行用时：1 ms, 在所有 Java 提交中击败了99.45%的用户
     内存消耗：42.1 MB, 在所有 Java 提交中击败了72.42%的用户
    */
    public List<List<Integer>> permuteUnique(int[] nums) {
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        for (int i = 0;i<nums.length;i++) {
            int num = nums[i];
            if (!hm.containsKey(num)) {
                hm.put(num,1);
            }else {
                hm.put(num,hm.get(num) + 1);
            }
        }
        int size = hm.size();
        int[] counts = new int[size];
        int[] uniqueNums = new int[size];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            Integer uniqueNum = entry.getKey();
            Integer count = entry.getValue();
            uniqueNums[i] = uniqueNum;
            counts[i] = count;
            i++;
        }
        List<Integer> path = new ArrayList<>();
        backtrack(0,path,uniqueNums,counts,nums);
        return result;
    }

    /*
      思路: uniqueNums: [1,2]
               counts: [2,1]
               result: [null,null,null]
          从上面得到，我们结果数组有3个空需用到uniqueNums和result来填满
     */
    private void backtrack(int k, List<Integer> path, int[] uniqueNums, int[] counts,int[] nums) {
        if (k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i < uniqueNums.length;i++) { //这里k阶段都是表示上面result待填的空数组下标位置
            if (counts[i]==0) { //如果当前元素用完了，马上跳到下个元素放进路径里
                continue;
            }
            path.add(uniqueNums[i]);
            counts[i]--; //减少其元素个数
            backtrack(k+1,path,uniqueNums,counts,nums);
            path.remove(path.size()-1); //撤销
            counts[i]++; //增加元素个数，使其返回原来一样的状态
        }

    }

    /*可以思考单纯用决策树来如何解决?
    1,1,2、 1,2,1、 2,1,1  从下面决策树可以看出重复数字下的全部剪枝，如何只遍历一个重复的节点?
       决策树:         counts[2,1]
                            [1,2]
                      /        |        \   --------选择放入1，放入1,1还是放入2
                     [1]      [1,1]      [2]
                      |         |         |   \      --------放入2
                     [1,2]    [1,1,2]    [2,1] [2,1,1]
     */
}
