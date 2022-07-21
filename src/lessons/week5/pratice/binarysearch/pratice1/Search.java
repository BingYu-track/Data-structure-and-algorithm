package lessons.week5.pratice.binarysearch.pratice1;

/**
 * @version 1.0 二分查找
 * @Description: 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 * 提示：
 * 你可以假设 nums中的所有元素是不重复的。
 * n将在[1, 10000]之间。
 * nums的每个元素都将在[-9999, 9999]之间。
 *
 * @author: bingyu
 * @date: 2022/7/6
 */
public class Search {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int index = search(nums, 5);
        System.out.println(index);
    }

    /*
    我的思路: 取数组的中间元素，比较中间元素和目标值的大小，如果小于目标值，说明目标值在中间元素的后面，否则就是在中间元素的前面，再缩小范围去寻找目标值
    二分查找的终止条件: 左右指针重合，表示找到了目标值

    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：42.4 MB, 在所有 Java 提交中击败了6.43%的用户
    */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while (low<=high) {
            int mid = low + (high-low)/2; //获取中间位置
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] < target) { //如果中间位置数值小于目标值则后面去[mid+1,high]区间查询
                low = mid + 1;
            }else { //执行到这说明中间位置大于目标值，则后面去[low,mid-1]区间查询
                high = mid - 1;
            }
        }
        return -1; //执行到这里说明没找到目标值，返回-1
    }




}
