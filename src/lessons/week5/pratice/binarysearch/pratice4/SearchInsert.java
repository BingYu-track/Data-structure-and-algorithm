package lessons.week5.pratice.binarysearch.pratice4;

/**
 * @version 1.0 搜索插入位置
 * @Description: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 提示:
 *
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 为无重复元素的升序排列数组
 * -10^4 <= target <= 10^4
 *
 * @author: bingyu
 * @date: 2022/7/22
 */
public class SearchInsert {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 2;
        int i = searchInsert(nums, target);
        System.out.println(i);
    }

    /**
     * 我的思路: 首先二分查找其目标值，没有的话返回按顺序插入的位置(由于要求时间复杂度是logn，因此必须是在二分查找中进行处理)
     * 有个细节，就是注意处理头和末尾两边的边界条件!
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了21.12%的用户
     */
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        //执行到这里说明没有目标值
        if (nums[low] < target) {
            return low + 1; //无论是low在末尾还是非末尾，都是取后面的
        }else {
            //执行到这里说明nums[low]大于目标值，则看low前面的值是否小于目标值是的话，当前位置就是我们要找的;否则就是当前值太大，需要减1
            if (low - 1 <= 0) { //限制开头的边界条件，比如target比最小的值都小
                return low;
            }else {
                if (nums[low - 1] < target) {
                    return low;
                }else {
                    return low - 1;
                }
            }
        }
    }


}
