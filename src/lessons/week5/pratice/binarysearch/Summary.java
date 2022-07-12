package lessons.week5.pratice.binarysearch;

/**
 * @version 1.0
 * @Description: 二分查找总结
 * @author: bingyu
 * @date: 2022/7/12
 */
public class Summary {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int i = binarySearch(nums, 5);
        System.out.println(i);
    }

    /*
     二分查找前提是元素都是有序的，另外二分查找用在链表上，是很慢的，不适用与链表查找
     二分查找的实际复杂度是O(logn)，不断除2，形成一个等比数列
     */

    /*
     二分查找递归方式实现
    */

    /*
    二分查找非递归方式实现
    */
    public static int binarySearch(int[] nums,int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low<=high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
