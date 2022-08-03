package lessons.week5.pratice.binarysearch.pratice7;

/**
 * @version 1.0 搜索旋转排序数组
 * @Description: 整数数组 nums 按升序排列，数组中的值互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k(0 <= k < nums.length)上进行了旋转，使数组变为
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如: [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2]。给你旋转后的数组nums 和一个整数target，
 * 如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums中的每个值都 独一无二题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 * @author: bingyu
 * @date: 2022/7/26
 */
public class Search {

    public static void main(String[] args) {
        //int[] nums = {4,5,6,7,0,1,2};
        int[] nums = {0,1,2,3,4,5};
        int target = 5;
        int search = search(nums, target);
        System.out.println(search);
    }


    /**
     * 我的思路: 根据nums[mid]的大小来划分"有序区间"和"循环有序区间"，然后根据target所在"有序区间"还是"循环有序区间"来进行范围二分
     * @param nums
     * @param target
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了21.57%的用户
     */
    public static int search(int[] nums, int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) {
                return mid;
            }else {
                //TODO: 根据nums[mid]的大小来划分"有序区间"和"循环有序区间"；如果
                if (nums[low] <= nums[mid]) {  //[low,mid]有序，[mid+1,high]循环有序
                    if (target >= nums[low] && target < nums[mid]) {
                        high = mid - 1;
                    }else {
                        low = mid + 1;
                    }
                }else { //nums[low] > nums[mid] [low,mid]循环有序，[mid+1,high]有序
                    if (target > nums[mid] && target <= nums[high]) {
                        low = mid + 1;
                    }else {
                        high = mid - 1;
                    }
                }
            }
        }
        return -1;
    }


}
