package lessons.week5.pratice.hashtable.pratice11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0 只出现一次的数字
 * @Description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author: bingyu
 * @date: 2022/8/15
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {2,2,1};
        int i = singleNumberSort(nums);
        System.out.println(i);
    }


    /*
     排序
     执行用时：7 ms, 在所有 Java 提交中击败了22.58%的用户
     内存消耗：41.8 MB, 在所有 Java 提交中击败了13.29%的用户
     */
    public static int singleNumberSort(int[] nums) {
        int n = nums.length;
        if (n==1) return nums[0];
        Arrays.sort(nums); //排序
        for (int i = 0;i<n;i++) {
            if (i==0) { //开头
                if (nums[i]!=nums[i+1]) return nums[i];
            }else if (i>0 && i<n-1) { //in middle
                if (nums[i]!=nums[i+1] && nums[i]!=nums[i-1]) return nums[i];
            }else { //in tail
                if (nums[i]!=nums[i-1]) return nums[i];
            }
        }
        return -1;
    }


    /*
      哈希
      执行用时: 12 ms, 在所有 Java 提交中击败了10.69%的用户
      内存消耗: 42.7 MB, 在所有 Java 提交中击败了5.04%的用户
    */
    public static int singleNumberHash(int[] nums) {
        HashMap<Integer,Integer> hashTable = new HashMap<>();
        int n = nums.length;
        for (int i = 0;i<n;i++) {
            if (!hashTable.containsKey(nums[i])) {
                hashTable.put(nums[i],1);
            }else {
                Integer k = hashTable.get(nums[i]);
                hashTable.put(nums[i],++k);
            }
        }
        for (Map.Entry<Integer, Integer> entry : hashTable.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }


    /*
     位运算
    */


}
