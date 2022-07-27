package lessons.week5.example.binarysearch.example1;

/**
 * @version 1.0 例题1 --查找第一个等于x，最后一个等于x的元素
 * @Description: 先写一个基础二分，然后不同的情况，进行二分的变种：a.找重复元素中的第一个 b.第一个大于给定的元素值
 * @author: bingyu
 * @date: 2022/7/27
 */
public class Solution1 {

    public static void main(String[] args) {
        int[] nums = {1,2,4,4,4,8,8,10,12};
        int target = 1;
        int first = bSearchFirst(nums, target);
        int last = bSearchLast(nums, target);
        System.out.println("第一个等于目标值的位置: "+first);
        System.out.println("最后一个等于目标值位置: " + last);
    }

    /*
     查找第一个等于目标值
    */
    public static int bSearchFirst(int[] nums,int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) { //如果mid已经是第一个元素；或者不是第一个元素，但是前面一个元素不是目标值，
                                                            // 则当前mid就是第一个等于目标值的元素
                    return mid;
                }else {
                    //TODO；执行到这里，说明前面的一个元素等于目标值，需要使后面计算的mid往前移动一位(怎么做呢？我们只能选择移动low或者high，
                    // low++相当于mid往后移动，high--相当于mid往前移动，因此这里我们应该用high--)
                    high = high - 1;
                }
            }else if (nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /*
     查找最后一个等于目标值---相当于是查找第一个比目标值大的元素
    */
    public static int bSearchLast(int[] nums,int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) {
                if (mid == length - 1 || nums[mid+1]!=target) { //如果mid是最后一个元素，或者不是最后一个元素，但是后面的元素不等于目标值，则该mid就是我们要找的
                    return mid;
                }else {
                    low = low + 1;
                }
            }else if (nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }


}
