package lessons.week12.doublepointer.pratice.pratice5;

import java.util.Arrays;

/**
 * @version 1.0  剑指 Offer 21. 调整数组顺序使奇数位于偶数前面(双指针)
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
 * @date: 2023/7/18
 */
public class Exchange {

    public static void main(String[] args) {
        Exchange ex = new Exchange();
        int[] nums = {11,9,3,7,16,4,2,0};
        int[] res = ex.exchange(nums);
        System.out.println(Arrays.toString(res));
    }

    /*
     奇数放在偶数前，就行，和数字大小顺序无关
     思路: 使用双指针
       1.如果nums[low]是奇数，nums[high]也是奇数，low++，遇到nums[low]为偶数时互换
       2.如果nums[low]是奇数，nums[high]是偶数，这个不变,low++,high--
       3.如果nums[low]是偶数，nums[high]是奇数，这个简单，直接互换
       4.如果nums[low]是偶数，nums[high]也是偶数，high--，遇到nums[high]为奇数时互换

       执行用时：2 ms, 在所有 Java 提交中击败了39.01%的用户
       内存消耗：50.1 MB, 在所有 Java 提交中击败了54.37%的用户
    */
    public int[] exchange(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if (nums[low]%2!=0 && nums[high]%2!=0) { //都是奇数
                low++;
            } else if (nums[low]%2!=0 && nums[high]%2==0) { //low指向的是奇数，high指向的是偶数
                low++;
                high--;
            } else if (nums[low]%2==0 && nums[high]%2!=0) { //high指向的是奇数，low指向的是偶数
                swap(nums,low,high);
                low++;
                high--;
            } else if (nums[low]%2==0 && nums[high]%2==0) {
                high--;
            }
        }
        return nums;
    }

    public void swap(int[] nums,int i,int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
