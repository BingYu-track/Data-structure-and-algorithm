package lessons.week5.pratice.binarysearch.pratice9;

/**
 * @version 1.0 山脉数组的峰顶索引
 * @Description: 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3，存在 i（0 < i< arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 *
 * 示例 1：
 * 输入：arr = [0,1,0]
 * 输出：1
 *
 * 示例 2：
 * 输入：arr = [0,2,1,0]
 * 输出：1
 *
 * 示例 3：
 * 输入：arr = [0,10,5,2]
 * 输出：1
 *
 * 示例 4：
 * 输入：arr = [3,4,5,1]
 * 输出：2
 *
 * 示例 5：
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 *
 * 提示：
 * 3 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^6
 * 题目数据保证 arr 是一个山脉数组
 *
 * @author: bingyu
 * @date: 2022/7/28
 */
public class PeakIndexInMountainArray {

    public static void main(String[] args) {
        int[] nums = {24,69,100,99,79,78,67,36,26,19};
        //int[] nums = {3,5,3,2,0};
        int i = peakIndexInMountainArray(nums);
        System.out.println(i);
    }

    /**
     * 我的思路: 找到arr[mid-1]<arr[mid] && arr[mid]>arr[mid+1]
     * 注意：峰值在数组开头和末尾都不属于山脉数组，因此不必要考虑峰值在开头和末尾的情况
     * 获得mid后，检查mid的前后元素，
     *    1.如果arr[mid] < arr[mid-1]说明mid在[mid,high]逆序的右半部分，因此，需要到左边去找峰值，即[low,mid-1]区间去找
     *    2.如果arr[mid] > arr[mid-1]说明mid在[low,mid]顺序左半部分，因此，需要到右边去找峰值，即[mid+1,high]区间去找
     * @param arr
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：57.7 MB, 在所有 Java 提交中击败了17.49%的用户
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int low = 0;
        int length = arr.length;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if(mid>0 && mid < length - 1) { //mid不在数组的开头或者末尾
                if (arr[mid-1]<arr[mid] && arr[mid]>arr[mid+1]) {
                    return mid;
                }else if (arr[mid] < arr[mid-1]){ //mid在[mid,high]逆序的右半部分，因此，需要到左边去找峰值，即[low,mid-1]区间去找
                    high = mid - 1;
                }else if (arr[mid] > arr[mid-1]){ //mid在[low,mid]顺序左半部分，因此，需要到右边去找峰值，即[mid+1,high]区间去找
                    low = mid + 1;
                }
            } else { //mid在数组开头或者末尾(虽然无需考虑峰值在开头和末尾的情况，但是二分时，mid可能到开头或者末尾)
                if (mid==0) {
                    //mid在数组开头的话肯定要到右边区间(mid,high]查找
                    low = mid + 1;
                }else if (mid == length - 1) {
                    //mid在数组末尾的话肯定要到左边区间[low,mid)查找
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


}
