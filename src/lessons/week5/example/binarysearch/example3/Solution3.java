package lessons.week5.example.binarysearch.example3;

/**
 * @version 1.0
 * @Description: 循环有序数组中查找元素x（有重复数据、没有重复数据）
 * TODO: 循环有序数组指的是将一个有序数组循环左移/右移若干距离之后变成的数组，例如：将严格递增数组[1,2,3,4,5,6]，
 *       向右移动3个单位，那么新的数组[4,5,6,1,2,3]就是一个循环有序数组
 *
 * @author: bingyu
 * @date: 2022/7/27
 */
public class Solution3 {

    public static void main(String[] args) {
        int[] nums = {8,9,10,11,15,1,2,3,5,6,7}; //循环有序数组
        int[] arrs = {9,10,11,15,1,2,3,5,6,7,8};
        int target = 9;
        int i = noReaptBSearch(arrs, target);
        System.out.println(i);
    }


    /*
     无重复数据
     TODO 这题需要多练习练习
     解题的核心思想是: 在有序区间里查找，没在有序区间，不断二分最终缩小范围的区间也会变成有序区间进行处理
     思路: 1.由于没有重复元素 nums[mid]==target直接返回mid
          2.其它情况下
            2.1 nums[low] <= nums[mid]说明[low,mid]有序，[mid+1,high]是循环有序
                如果target落在有序的这边，就在有序的区间查找；否则就是在另一边
            2.2 nums[low] > nums[mid]说明跨过了分界点，区间[low,mid]是循环有序的，[mid+1,high]是有序的，
                如果target落在有序的这边，就在有序的区间查找；否则就是在另一边
     TODO: 为什么nums[low]<=nums[mid]说明[low,mid]是有序的？ 因为这样说明[low,mid]跨过分界点，如果[low,mid]跨过分界点，
            那么一定是nums[low]>nums[mid]
    */
    public static int noReaptBSearch(int[] nums,int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                return mid;
            }else {
                //无论nums[mid] > target 或者 nums[mid] < target，都需要知道target是在有序区间还是循环有序区间，后面才能继续进行处理，不能直接按照原来的二分
                if (nums[low] <= nums[mid]) { //
                    if (target >= nums[low] && target < nums[mid]) { //这里只可能是target < nums[mid]，因为<=中=直接到前面就返回了
                        //target落在有序区间，到[low,mid)区间查找
                        high = mid - 1;
                    }else {
                        //target落在循环有序的区间，到(mid,high]区间查找
                        low = mid + 1;
                    }
                }else { //nums[low] > nums[mid]
                    if (target > nums[mid] && target <= nums[high]) {
                        //target落在有序区间，到(mid,high]区间查找
                        low = mid + 1;
                    }else {
                        //target落在循环有序的区间，到[low,mid)区间查找
                        high = mid - 1;
                    }
                }
            }
        }
        return -1;
    }


    /*
     有重复数据(该题比较困难，后面有时间精力再做)
    */
    public static int haveReaptBSearch(int[] nums,int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low<=high) {
            int mid = low + (high - low)/2;

        }
        return -1;
    }




}
