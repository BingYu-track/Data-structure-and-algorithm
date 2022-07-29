package lessons.week5.pratice.binarysearch.pratice8;

/**
 * @version 1.0 寻找旋转排序数组中的最小值
 * @Description: 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7]
 * 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @author: bingyu
 * @date: 2022/7/28
 */
public class FindMin {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int min = findMin(nums);
        System.out.println(min);
    }

    /*
      TODO: 推荐
      我的思路: 最小值就是分界点，要到跨分界点的区间去找，找nums[i] > nums[i+1]，此时nums[i+1]这个就是我们要找的元素
              nums[low] <= nums[mid]说明在[low,mid]是有序的，(mid,high]循环有序的
       执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
       内存消耗：40.9 MB, 在所有 Java 提交中击败了75.68%的用户
    */
    public static int findMin(int[] nums) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] >= nums[low] && nums[mid] <= nums[high]) { //说明low到high是完全有序的，最小值就是nums[low]
                return nums[low];
            }else {
                //执行到这里说明low到high是循环有序的，开始查找最小值
                if (mid>0 && nums[mid - 1] > nums[mid]) { //如果mid前面的元素大于mid，则说明mid就是分界点，也就是最小值
                    return nums[mid];
                }else {
                    //mid不是分界点，就去循环有序的区间里二分查找
                    if (nums[low] <= nums[mid]) {  //[low,mid]有序，[mid+1,high]循环有序
                        low = mid + 1;
                    }else { //nums[low] > nums[mid] [low,mid]循环有序，[mid+1,high]有序
                        high = mid - 1;
                    }
                }
            }

        }
        return -1;
    }



}
