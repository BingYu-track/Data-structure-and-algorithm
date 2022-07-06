package lessons.week4.pratice.sorted.pratice.pratice6;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * @author: bingyu
 * @date: 2022/6/21
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {2,3,1,6,8,7,5};
        exchange(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
    TODO 推荐该方法
     争哥解法: 用双指针解法，但是我有个疑问，
    */
    public static int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i<=j) {
            if (nums[i] % 2 == 1) { //如果i指针指向的是奇数，i指针直接向后移动一位
                i++;
            }else { //如果i指针指向的是偶数，再判断j指针指向的数
                if (nums[j] % 2 == 1) { //如果j指针指向的是奇数，就交换位置,并且j指针向前移动一位;i指针向后移动一位
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }else { //如果j指针指向的是偶数，j指针向前移动一位
                    j--;
                }

            }
        }
        return nums;
    }


}
