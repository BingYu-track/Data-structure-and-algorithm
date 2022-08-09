package lessons.week5.example.hashtable.example2;

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
        int[] nums = {-1,0,1,2,-1,-4}; //[-4,-1,-1,0,1,2]
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }


    /* TODO: 争哥例题，这里重点思路是如何去除重复的三元组?
            如果有2个元素重复，则说明这是一个
    */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0;i<nums.length;i++) {
            hashMap.put(nums[i],i);
        }
        for (int i = 0;i<nums.length;i++) {  //第一个数
            if (i != 0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1;j<nums.length;j++) { //第2个数
                //TODO: 注意点1--排除和前面重复的元素，因为数字i是固定的，j一样，那么0-i-j就注定是重复的，需要跳过
                if (j != i+1 && nums[j] == nums[j-1]) continue;
                int t = -nums[i] - nums[j];
                if (hashMap.containsKey(t)) { //检测是否包含目标值减去2个数后的值
                    int index = hashMap.get(t); //如何去掉重复的三元组?
                    List<Integer> list = new ArrayList<>();
                    //TODO:注意点2--这里也是为了避免重复，为啥要改成 index > j？
                    //      是因为遍历时，是从左到右一个一个遍历的，如果得到的index小于j，就说明index这个数是之前已经遍历过的，
                    //      要么是不符合条件，如果符合，那在之前遍历就已经放进list了，需要避免，因此需要保证index>j
                    //if (i!=j && i!=index && j!=index) {
                    if (index > j) {
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

    /**
     * 争哥解法:
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums); //TODO 避免重复，因为这里争哥先排序了，所以就使重复的元素放到一起了，因此后面方便跳过重复的元素
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            hashMap.put(nums[i], i);
        }
        for (int i = 0; i < n; ++i) {
            System.out.println();
            if (i != 0 && nums[i] == nums[i-1]) continue; // 避免a重复, 1,1,3…

            for (int j = i+1; j < n; ++j) {
                if (j != i+1 && nums[j] == nums[j-1]) continue; // 避免b重复 1,2,2,…

                int target = -1*(nums[i]+nums[j]);
                if (!hashMap.containsKey(target)) {
                    continue;
                }
                int k = hashMap.get(target);
                if (k > j) { // 避免重复,因为排序了，这里怎么保证k一定是大于j，就是不重复的?
                    List<Integer> resultItem = new ArrayList<>();
                    resultItem.add(nums[i]);
                    resultItem.add(nums[j]);
                    resultItem.add(nums[k]);
                    result.add(resultItem);
                }
            }
        }
        return result;
    }

}
