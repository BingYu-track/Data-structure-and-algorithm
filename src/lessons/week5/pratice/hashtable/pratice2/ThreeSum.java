package lessons.week5.pratice.hashtable.pratice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0 三数之和
 * @Description: 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 * @author: bingyu
 * @date: 2022/8/8
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }


    /* TODO: 争哥例题 ，这个需要重点练习！ i=1、j=3、k=4 ==>i=2、j=3、k=4
       [-4,-1,-1,0,1,2]
    */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int n = nums.length;
        for (int i = 0;i<n;i++) {
            hashMap.put(nums[i],i);
        }
        for (int i = 0;i<n;i++) {
            if (i!=0 && nums[i] == nums[i-1]) continue; //如果当前元素和前一个元素一样，则跳过，因为就算前一个元素满足条件，再处理这个元素就已经重复了
            for (int j = i+1;j < n;j++) {
                if (j!=i+1 && nums[j] == nums[j-1]) continue;
                int diff = - nums[j] - nums[i];
                if (hashMap.containsKey(diff)) {
                    int index = hashMap.get(diff);
                    //j一定是比i大，j前面的数一定是已经处理过的，要么是满足条件已经放入List，要么是不满足跳过的，因此我们满足条件获得的index一定是在后面大于j的
                    if (index > j) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[index]);
                        lists.add(list);
                    }
                }
            }
        }
        return lists;
    }


}
