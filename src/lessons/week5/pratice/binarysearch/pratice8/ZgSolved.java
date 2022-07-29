package lessons.week5.pratice.binarysearch.pratice8;

/**
 * @version 1.0
 * @Description: 寻找旋转排序数组中的最小值--争哥解法
 * @author: bingyu
 * @date: 2022/7/28
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int min = findMin(nums);
        System.out.println(min);
    }

    /*
     争哥思路
    */
    public static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            //特殊处理low==high的情况
            if (low == high) {
                return mid;
            }
            //先处理命中情况，后处理不命中
            if ((mid != 0 && nums[mid] < nums[mid-1]) //刚好在分界点
                || (mid == 0 && nums[mid] < nums[high])) { //分界点在第一个元素，完全有序
                return mid;
            } else if (nums[mid] > nums[high]) { //右半部分循环有序，因此到(low,high]去查询分界点
                low = mid + 1;
            }else { //右侧非循环有序: 左循环有序、左有序，也是在左侧查找
                high = mid - 1;
            }
        }
        return -1;
    }
}
