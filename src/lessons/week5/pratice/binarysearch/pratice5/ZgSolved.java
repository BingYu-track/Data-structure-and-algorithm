package lessons.week5.pratice.binarysearch.pratice5;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 在排序数组中查找元素的第一个和最后一个位置--争哥解法
 * @author: bingyu
 * @date: 2022/7/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,8};
        int target = 8;
        int[] result = searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }

    /*TODO: 推荐该方法
     争哥思路: 使用left和right两个变量来记录第一个目标值元素位置和最后一个目标值的元素位置
    */
    public static int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        int left = -1; //记录第一个目标值的下标位置
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] == target) { //遇到目标值
                if (mid == 0 || nums[mid-1] != target) {
                    left = mid; //如果mid是第一个元素，或者非第一个元素，但是前面一个元素小于目标值，则当前mid就是我们要找的第一个目标元素
                    break;
                } else {
                    high = mid-1; //mid!=0 && nums[mid-1] == target非开头元素，且前一个元素等于目标值，则向前移动
                }
            } else if (nums[mid] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        low = 0;
        high = nums.length-1;
        int right = -1; //记录最后一个目标值的下标位置
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] == target) { //遇到目标值
                if (mid == nums.length-1 || nums[mid+1] != target) {
                    right = mid; //如果mid是末尾元素，或者非末尾元素，但是后面一个元素不等于目标值，则当前mid就是我们要找的最后一个目标值的位置
                    break;
                } else {
                    low = mid+1; //mid != nums.length-1 && nums[mid+1] == target 非末尾，且后一共元素等于目标值，因此向后移动
                }
            } else if (nums[mid] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return new int[]{left, right};
    }


}
