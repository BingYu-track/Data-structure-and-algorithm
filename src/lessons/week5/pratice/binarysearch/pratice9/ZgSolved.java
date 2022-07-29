package lessons.week5.pratice.binarysearch.pratice9;

/**
 * @version 1.0
 * @Description: 山脉数组的峰顶索引--争哥解法
 * @author: bingyu
 * @date: 2022/7/29
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {24,69,100,99,79,78,67,36,26,19};
        int i = peakIndexInMountainArray(nums);
        System.out.println(i);
    }


    /**
     * 争哥解法--和我的解法基本是一样的
     * @param arr
     * @return
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + (high-low)/2;
            if (mid == 0) {
                low = mid+1;
            }else if (mid == n-1){
                high = mid-1;
            }else if (arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]) {
                return mid;
            }else if (arr[mid] > arr[mid-1]) {
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        return -1;
    }

}
