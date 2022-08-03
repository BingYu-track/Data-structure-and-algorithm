package lessons.week5.pratice.binarysearch.pratice10;

/**
 * @version 1.0
 * @Description: 寻找峰值--争哥解法
 * @author: bingyu
 * @date: 2022/7/29
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,2,5};
        int peakElement = findPeakElement(nums);
        System.out.println(peakElement);
    }

    /**
     * 争哥思路: 如果mid在上坡，就往右走;在下坡就往左走查询峰值，和我的思路基本一致
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (low == high) { //不添加这⼀⾏，测试⽤例[1]报错，这个是当数组只有一个元素时返回
                return mid;
            }
            if (mid == 0) { //mid在数组开头
                if (nums[mid]>nums[mid+1]) {
                    return mid;
                } else {
                    low = mid+1;
                }
            } else if (mid == n-1) { //mid在数组结尾
                if (nums[mid]>nums[mid-1]) {
                    return mid;
                } else {
                    high = mid-1;
                }
            } else if (nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1]) { //mid刚好处于峰值
                return mid;
            } else if (nums[mid]<nums[mid+1]) { //mid处于上坡
                low = mid+1;
            } else {
                high = mid-1; //mid处于下坡
            }
        }
        return -1;
    }


}
