package lessons.week5.pratice.binarysearch.pratice5;

import java.util.Arrays;

/**
 * @version 1.0 在排序数组中查找元素的第一个和最后一个位置
 * @Description: 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -10^9<= nums[i]<= 10^9
 * nums是一个非递减数组
 * -10^9<= target<= 10^9
 *
 * @author: bingyu
 * @date: 2022/7/25
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,8};
        int target = 8;
        int[] result = searchRange2(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] searchRange2(int[] nums, int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        int left = -1;
        //找第一个出现的目标值
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                if(mid == 0 || nums[mid-1]!=target) {
                    left = mid;
                    break;
                }else {
                    high = mid - 1;
                }
            }else if (nums[mid] > target){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        low = 0;
        high = length - 1;
        int right = -1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                if (mid == length - 1 || nums[mid+1]!=target) {
                    right = mid;
                    break;
                }else {
                    low = mid + 1;
                }
            }else if (nums[mid] > target) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return new int[]{left,right};
    }


    /*TODO: 推荐使用争哥的方法，争哥的方法更简洁，也更容易理解！--建议多次练习
     我的思路: 5,7,7,8,8,8
     target - 1是最后一个 和 target+1的第一个
    */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {Integer.MIN_VALUE,Integer.MIN_VALUE};
        int count = 0;//用来记录result里包含的元素个数
        int length = nums.length;
        if (length == 0) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        int low = 0;
        int high = length - 1;
        boolean flag = false; //判断是否有目标值
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) { //TODO: 如果当前遇到了目标值，该位置前后都有可能是目标值，该怎么处理？
                if (count == 0) { //为空时，直接将下标赋值到结果数组中
                    result[0] = mid;
                    count++;
                }else if (count == 1) { //有一个元素时，
                    if (mid < result[0]) { //如果目标值下标比第一个元素小
                        result[1] = result[0];
                        result[0] = mid;
                    }else {  //如果目标值下标比第一个元素大
                        result[1] = mid;
                    }
                    count++;
                }else { //count==2时 已经有2个元素是
                    if (mid < result[0]) { //目标值下标比第一个元素小才赋值
                        result[0] = mid;
                    }else if (mid > result[1]) { //目标值下标比第2个元素大才赋值
                        result[1] = mid;
                    }
                }
                low = 0;
                high = mid - 1; //二分查询前半段
                flag = true;
            }else if (nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        //第二次查后半段
        low = 0;
        high = length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) { //TODO: 如果当前遇到了目标值，该位置前后都有可能是目标值，该怎么处理？
                if (count == 0) { //为空时，直接赋值
                    result[0] = mid;
                    count++;
                }else if (count == 1) { //有一个元素时，
                    if (mid < result[0]) { //如果目标值下标比第一个元素小
                        result[1] = result[0];
                        result[0] = mid;
                    }else {  //如果目标值下标比第一个元素大
                        result[1] = mid;
                    }
                    count++;
                }else { //count==2时 已经有2个元素是
                    if (mid < result[0]) { //目标值下标比第一个元素小才赋值
                        result[0] = mid;
                    }else if (mid > result[1]) { //目标值下标比第2个元素大才赋值
                        result[1] = mid;
                    }
                }
                low = mid + 1;
                high = length - 1; //二分查询后半段
                flag = true;
            }else if (nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        if (!flag) {
            result[0] = -1;
            result[1] = -1;
        }
        return result;
    }
}
