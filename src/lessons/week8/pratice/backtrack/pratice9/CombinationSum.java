package lessons.week8.pratice.backtrack.pratice9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** TODO: 需要重点理解，多次练习
 * @version 1.0 组合总和
 * @Description: 给你一个 无重复元素 的整数数组candidates和一个目标整数target，找出candidates中可以使数字和为目标数target的所有不同组合 ，
 * 并以列表形式返回。你可以按任意顺序返回这些组合。candidates中的同一个数字可以无限制重复被选取。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target的不同组合数少于150个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素互不相同
 * 1 <= target <= 40
 *
 * @author: bingyu
 * @date: 2023/2/7
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {8,7,4,3};
        int target = 11;
        CombinationSum sum = new CombinationSum();
        List<List<Integer>> lists = sum.combinationSum(candidates, target);
        System.out.println(lists);
//        System.out.println(8/2); //4 最多只能出现4次
//        System.out.println(8%2); //0
//        System.out.println(8/3); //2 最多只能出现2次
//        System.out.println(8%3); //2
//        System.out.println(8%5); //3
//        System.out.println(8/5); //1 最多只能出现1次
    }


    private List<List<Integer>> result = new ArrayList<>();

    private int sum = 0;

    /*
     我的优化方法
     执行用时：2 ms, 在所有 Java 提交中击败了75.86%的用户
     内存消耗：42 MB, 在所有 Java 提交中击败了37.49%的用户

     剪枝优化后
     执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：41.9 MB, 在所有 Java 提交中击败了45.97%的用户
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        backtrack(candidates,target,path,0);
        return result;
    }

    //TODO： 推荐该方法
    private void backtrack(int[] candidates, int target, List<Integer> path, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        //if (sum > target) return;
        /*TODO 每一层的处理,每层循环遍历和candidates元素个数一样,然后还需要一个参数，为int型变量startIndex，这个参数用来记录本层递归中，
           集合从哪里开始遍历,startIndex就是防止出现重复的元素组合；由于该题允许组合中出现重复元素(但是不允许出现重复组合)，因此调用递归时不需要进行i+1，如果进行了
           i+1，就只能在下一层中下一个元素开始遍历，就不会再一个组合中出现重复的元素了
         */
        for (int i = startIndex;i<candidates.length;i++) { //使用startIndex控制横向遍历的起始位置
            int num = candidates[i];
            if (sum + num > target) continue; //进行剪枝，这样就不需要再进入下一个层数进行判断了
            path.add(num);
            sum += num;
            backtrack(candidates,target,path,i); //传i，而不是i+1是为了保证元素可以重复使用
            path.remove(path.size()-1);
            sum -= num;
        }
    }

    /*
      输入: candidates = [2,3,5], target = 8
       输出: [[2,2,2,2],[2,3,3],[3,5]]
                                      [2,3,5]
                      /                  |               \
                     2                   3                5
                 /   |  \               / \
               2     3   5             3   5 ----这里只能取3及其后面数字，如果把前面的2带上，会导致重复的组合，因此需要控制遍历的起始位置

    */


    /*完全属于我独立自主完成
      执行用时：3 ms, 在所有 Java 提交中击败了22.51%的用户
      内存消耗：41.8 MB, 在所有 Java 提交中击败了60.93%的用户
      思路，首先要算出每个元素在目标值的被除的结果和余数，这样就能得到每个元素最多能出现的次数，然后就根据这2个数组开始找可行解
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>(); //存放元素和各个元素最多能出现的次数
        for (int i = 0;i<candidates.length;i++) {
            hm.put(candidates[i],target/candidates[i]);
        }
        int size = hm.size();
        int[] nums = new int[size];
        int[] counts = new int[size];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            nums[i] = num;
            counts[i] = count;
            i++;
        }
        backtrack(0,path,nums,counts,target);
        return result;
    }

    /*以nums = [2,3,5]，counts = [4,2,1]为例每次选择数字都有3种不同的选择，每次选择放入时还要知道该数字出现的次数是否用完
     TODO： 关键是这里如何去除重复的组合?
     */
    private void backtrack(int k, List<Integer> path, int[] nums, int[] counts, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) return; //由于中途可能就会获得可行解，因此，后面还需要进行限制
        for (int i = 0;i<nums.length;i++) {
            if (i < k) continue; //TODO： 要去除重复的组合，就要保证下层组合时不能组合到比当前元素前面的数即可!只要让下层的i比上面一层i更大作为起始遍历就行
            int num = nums[i];
            if (counts[i] > 0) { //如果当前数字出现的次数还有，就放入path
                path.add(num);
                sum += num; //求和
                counts[i]--;  //减少对应次数
                backtrack(i,path,nums,counts,target);
                path.remove(path.size()-1); //撤销之前的选择
                sum -= num;
                counts[i]++;
            }
        }

    }

    /*  [2,3,5]
        [4,2,1] 最多只能出现那么多次数，接着就只能用其它数字
      输入: candidates = [2,3,5], target = 8  数字组合的和等于target
      输出: [[2,2,2,2],[2,3,3],[3,5]]
      注意--每个数字在组合里都可以重复出现!
      可变参数: candidates
      阶段k: 我认为和candidates的元素个数一样，每个数字都需要和自己，和其它元素组合，看哪一个是可行解
      path:
             1,3,2  4
             1,1,2                 [           ]
                             /                 |      \
                            1                  3                 2
                          /  \                / \      /         |          \
                        1,3  1,2            3,1 3,2   2,1       2,2          2,3
                            /   \           /        /  \
                         1,2,3  1,2,2   3,1,2      2,1,3 2,1,2

            每次都有3种可选列表
    */
}
