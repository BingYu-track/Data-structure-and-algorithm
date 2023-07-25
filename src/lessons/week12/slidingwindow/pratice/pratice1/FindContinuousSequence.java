package lessons.week12.slidingwindow.pratice.pratice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0 剑指 Offer 57 - II. 和为s的连续正数序列
 * @Description: 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 * 限制：
 * 1 <= target <= 10^5
 *
 * @author: bingyu
 * @date: 2023/7/24
 */
public class FindContinuousSequence {

    public static void main(String[] args) {
        FindContinuousSequence f = new FindContinuousSequence();
        int[][] arrs = f.findContinuousSequence(15);
        for (int[] arr : arrs) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /*
     注意。必须是连续的正整数
     以9举例，找出符合至少含有两个数等于9的，那么只能在1~8中选择
     1,2,3,4,5,6,7,8
     使用i指针和j指针初始化指向1和2，构成一个最小的滑动窗口区间，然后，根据其数的和来进行对应处理
     1.如果滑动窗口里的数之和小于target，就让j向后移动，包含更多数字
     2.如果滑动窗口里的数之和大于target，就让i向后移动，将窗口中最小的数字移除窗口，相当于减少数值
     3.如果等于target，说明找到了一个解

     执行用时：2 ms, 在所有 Java 提交中击败了92.94%的用户
     内存消耗：39.3 MB, 在所有 Java 提交中击败了86.30%的用户
    */
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        int i = 1;
        int j = 2;
        int sum = i + j;
        List<Integer> list = new ArrayList<>();
        while (i < j && i<target && j<target) {
            //和等于目标值，说明找到了一个解，将i和j之间的所有数字遍历放入result中，然后两个指针同时向后移动
            // 因为此时只移动j的话，sum肯定比目标值大，只移动i的话，sum肯定比目标值小，所以必须两个都向后移动
            if (sum == target) {
                int[] arr = new int[j-i+1];
                for (int k = i;k<=j;k++) {
                    arr[k-i] = k;
                }
                result.add(arr);
                sum -= i;
                i++;
                j++;
                sum += j;
            }else if (sum > target) { //和大于目标值，前面指针i向后移动,sum值减少
                sum -= i;
                i++;
            }else { //和小于于目标值，后面指针j向后移动,sum值增加
                j++;
                sum += j;
            }

        }
        int[][] res = new int[result.size()][];
        for (int p = 0;p<result.size();p++) {
            int[] ints = result.get(p);
            res[p] = ints;
        }
        return res;
    }
}
