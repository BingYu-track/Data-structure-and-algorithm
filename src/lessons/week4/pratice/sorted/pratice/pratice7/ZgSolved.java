package lessons.week4.pratice.sorted.pratice.pratice7;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 颜色分类
 * @author: bingyu
 * @date: 2022/6/23
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,1,0,0,2,1,1,1,2,0};
        nums = new int[]{1,2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
      TODO: 推荐使用
     争哥解法:使用的双指针解法,
     核心思路: 先划分2段区域，0,1看成1段，2看成第2段;先将所有2放到后面区域，然后再将剩余的0和1的区域划分为0,1两端区域，继续处理即可
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：39.7 MB, 在所有 Java 提交中击败了73.18%的用户
    */
    public static void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i<j) {
            if (nums[i]!=2) {
                i++;
                continue;
            }
            //执行到这说明，遇到了i指针指向了2，需要和j指针进行交换
            if (nums[j]==2) { //如果j指针也是指向2，j指针需要向前移动，直到指向非2数字开始交换
                j--;
            }else {
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //执行到这里说明2全部处于正确位置了，开始对0和1进行处理
        i = 0;
        if (nums[j]==2) j--; //防止j指针指向了2
        while (i<j) {
            if (nums[i]!=1) {
                i++;
                continue;
            }
            //执行到这说明i指针遇到了1
            if (nums[j]==1) {
                j--;
            }else {
                swap(nums,i,j);
                i++;
                j--;
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
