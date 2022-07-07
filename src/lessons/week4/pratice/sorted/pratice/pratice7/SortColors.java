package lessons.week4.pratice.sorted.pratice.pratice7;

import java.util.Arrays;

/**
 * @version 1.0 颜色分类
 * @Description: 给定一个包含红色、白色和蓝色、共n个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1和2分别表示红色、白色和蓝色。必须在不使用库的sort函数的情况下解决这个问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * @author: bingyu
 * @date: 2022/6/23
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {0,2,1,0,0,2,1,1,1,2,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
    TODO: 推荐使用
     我的思路: 现遍历一遍，将0,1,2数量都记录下来，然后根据各自的数量再复制到数组里即可
     执行用时: 0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗: 39.9 MB, 在所有 Java 提交中击败了54.67%的用户
    */
    public static void sortColors(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0;i<nums.length;i++) { //记录0,1,2数字的数量
            if (nums[i] == 0) {
                count0++;
            }else if (nums[i] == 1) {
                count1++;
            }else if (nums[i] == 2) {
                count2++;
            }
        }
        for (int i = 0;i<nums.length;i++) { //给数组重新赋值
            if (i<count0) {
                nums[i] = 0;
            }else if (i>=count0 && i<(count0 + count1)) {
                nums[i] = 1;
            }else {
                nums[i] = 2;
            }
        }
    }


}
