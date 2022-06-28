package lessons.week4.pratice.sorted.pratice.pratice6;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * @Description: 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 *
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * 提示：
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 *
 * @author: bingyu
 * @date: 2022/6/21
 */
public class Exchange {

    public static void main(String[] args) {
        int[] arr = {3,2,1,4};
        exchange(arr);
        System.out.println(Arrays.toString(arr));
    }

    /* 1,2,3,4,5,6 注意--不需要安装大小排序，按照原顺序即可，即要保证算法的稳定性
     我的思路: 根据插入排序的思路将其分为奇数区间和偶数区间，假设第一个是奇数区间，我们需要时刻记录奇数区间末尾的位置，在遍历后面偶数区间时如果遇到
             奇数就一步一步交换直到奇数区间的末尾
      执行用时：823 ms, 在所有 Java 提交中击败了5.15%的用户
      内存消耗：49.5 MB, 在所有 Java 提交中击败了7.35%的用户
      可见花费时间较长，有什么其它更快的方法呢？
     */
    public static int[] exchange(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums;
        for (int i = 1;i < n;i++) { //从1位置开始，如果是奇数，才开始下面的逻辑
            if (nums[i] % 2 == 1) {
                int index = i;
                int j = i - 1;  //找前一个元素位置，后面进行比较
                for (;j>=0 && nums[j] % 2 == 0;j--) { //不断比较前面的元素，遇到偶数就要进行交换，直到遇到了奇数停止
                    int temp = nums[index];
                    nums[index] = nums[j];
                    nums[j] = temp;
                    index--;
                }
                //执行到这说明遇到奇数了
            }
        } //
        return nums;
    }

    public static int[] exchange2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums;

        return null;
    }

}
