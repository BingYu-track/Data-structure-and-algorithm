package lessons.week5.pratice.binarysearch.pratice4;

/**
 * @version 1.0
 * @Description: 搜索插入位置--争哥解法
 * @author: bingyu
 * @date: 2022/7/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 2;
        int i = searchInsert(nums, target);
        System.out.println(i);
    }

    /**
     * 争哥解法: 转换成找第一个大于等于目标值的元素
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                if(mid==0 || nums[mid-1] < target) { //mid是第一个元素，mid就是第一个大于目标元素；mid不等于0，就看前面是否小于目标值
                    return mid;
                }else {
                    //执行到这里说明,mid!=0并且nums[mid-1]>target，需要我们向前移动
                    high = mid - 1;
                }
            }else { //nums[mid] < target
                low = mid + 1;
            }
        }
        //执行到这里说明没在数组中间和数组开头找到，那就只能是在数组末尾了
        return length;
    }

}
