package lessons.week5.pratice.binarysearch.pratice10;

/**
 * @version 1.0 寻找峰值
 * @Description: 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3是峰值元素，你的函数应该返回其索引2。
 *
 * 示例2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引1，其峰值元素为2；或者返回索引5，其峰值元素为 6。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *
 * @author: bingyu
 * @date: 2022/7/29
 */
public class FindPeakElement {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,2,5};
        int peakElement = findPeakElement(nums);
        System.out.println(peakElement);
    }

    /* 推荐我的方法
       TODO: 注意该题的峰值和上一题的峰值要求不同，上一题是必须要大于左右两边且都有元素才称为峰值;这题峰值是可以单独构成的，左边或者右边没有元素，
             一边大于就可以称为峰值
             核心思路是: 如果mid在上坡，就往右走;在下坡就往左走查询峰值
    */
    public static int findPeakElement(int[] nums) {
        int length = nums.length;
        if (length == 1) return 0;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (mid > 0 && mid < length - 1) { //在数组中间
                if (nums[mid] > nums[mid-1] && nums[mid]>nums[mid+1]) {
                    return mid;
                }else  { //TODO 非峰值的，前面一个元素大于mid说明左边[low,mid)肯定有峰值
                    if (nums[mid] < nums[mid-1]) {
                        high = mid - 1;
                    }else if (nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1]) {
                        //TODO mid比前面元素大，比后面的元素小，峰值肯定在右边(mid,high]，如何理解？如果都是有序的，那么最后一个元素就是峰值，所以右边肯定有峰值
                        low = mid + 1;
                    }
                }
            }else {
                //在数组两头
                if (mid == 0) {
                    if (nums[mid] > nums[mid+1]) {
                        return mid;
                    }else {
                        low = mid + 1;
                    }
                }else if (mid == length - 1) {
                    if (nums[mid] > nums[mid-1]) {
                        return mid;
                    }else {
                        high = mid - 1;
                    }
                }
            }
        }
        return -1;
    }

}
