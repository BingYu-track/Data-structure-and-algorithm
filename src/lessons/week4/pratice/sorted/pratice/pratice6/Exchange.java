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
        int[] arr = {2,4,6};
        exchange(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
     TODO: 推荐该方法
     突然发现可以使用快速排序中的partition函数的双指针发解题，时间复杂度O(n)，空间复杂度O(1)
     执行用时：2 ms, 在所有 Java 提交中击败了41.10%的用户
     内存消耗：49.2 MB, 在所有 Java 提交中击败了49.44%的用户
    */
    public static int[] exchange(int[] nums) {
        int n = nums.length;
        if (n<=1) return nums;
        partition(nums,0,n-1);
        return nums;
    }

    //划分奇偶数
    private static int partition(int[] nums, int odd, int even) {
        int i = odd; //奇数指针
        int j = even - 1; //偶数指针
        while (i<j) {
            if (i<j && nums[i]%2==1) {
                i++;
            }
            if (i<j && nums[j]%2==0) {
                j--;
            }
            if (i<j && nums[i]%2==0 && nums[j]%2==1) {
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //执行到这里说明奇偶区间区分完成，开始处理分界点--寻找偶数与分界点交换即可！
        while (j<even && nums[j]%2==1) { //这里前面加上j<even是为了防止全是奇数时导致数组越界
            j++;
        }
        //执行带这里说明j指向的是偶数，此时将分界点与其交换位置即可
        swap(nums,even,j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /* 1,2,3,4,5,6 注意--不需要安装大小排序，按照原顺序即可，即要保证算法的稳定性
     我的思路: 根据插入排序的思路将其分为奇数区间和偶数区间，假设第一个是奇数区间，我们需要时刻记录奇数区间末尾的位置，在遍历后面偶数区间时如果遇到
             奇数就一步一步交换直到奇数区间的末尾
      执行用时：823 ms, 在所有 Java 提交中击败了5.15%的用户
      内存消耗：49.5 MB, 在所有 Java 提交中击败了7.35%的用户
      可见花费时间较长，有什么其它更快的方法呢？
     */
    public static int[] exchange2(int[] nums) {
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



}
