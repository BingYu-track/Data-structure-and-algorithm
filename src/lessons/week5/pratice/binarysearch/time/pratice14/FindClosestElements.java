package lessons.week5.pratice.binarysearch.time.pratice14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0  找到K个最接近的元素
 * @Description: 给定一个排序好的数组arr,两个整数k和x,从数组中找到最靠近x（两数之差最小）的k个数。返回的结果必须要是按升序排好的。
 *
 * 整数a比整数b更接近x需要满足：
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length<= 10^4
 * arr按升序排列
 * -10^4<= arr[i] ,x <= 10^4
 *
 * @author: bingyu
 * @date: 2022/8/1
 */
public class FindClosestElements {

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 1;
        int x = 1;
        List<Integer> closestElements = findClosestElements(nums, k, x);
        System.out.println(closestElements);
    }


    /* TODO: 推荐该方法
      我的思路借鉴官方题解改进,使用[mid-k-1.mid+k-1]来缩小范围这个思想，但是前提是找到目标值待插入的位置
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int length = arr.length;
        int low = 0;
        int high = length - 1;
        int mid = 0;
        boolean flag = false;
        while (low <= high) { //这里还要得到没有目标值时插入的位置
            mid = low + (high - low)/2;
            if (arr[mid] == x) {
                //找到目标值
                flag = true;
                break;
            }else if (mid > 0 && arr[mid-1] < x && arr[mid] > x){ //x在数组中没有目标值，但是找到了插入的位置
                break;
            }else if (arr[mid] > x) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        if (x <= arr[0]) { //x比数组的第一个元素都小
            for (int i = 0;i < k;i++) {
                list.add(arr[i]);
            }
        }else if (x >= arr[length - 1]) { //x比数组的最后一个元素都大
            for (int i = length - k;i < length;i++) {
                list.add(arr[i]);
            }
        }else {
            //x的大小处于数组中部位置
            low = mid - k - 1; //从mid到左边找k个元素
            high = mid + k - 1; //从mid到右边找k个元素
            if (low < 0) low = 0; //这样做的目的是mid-k-1可能会越过边界，此时只能取第一个下标的元素作为开始区间
            if (high > length - 1) high = length - 1;
            while (high - low + 1 > k) { //当low和high之间的元素个数等于k时停止
                int absl = Math.abs(arr[low] - x);
                int absh = Math.abs(arr[high] - x);
                if (absl<=absh) {
                    high--;
                }else {
                    low++;
                }
            }
            for (;low<=high;low++){
                list.add(arr[low]);
            }
        }
        return list;
    }

    /*
     我的思路: 先二分查找，得到mid
     1.如果x在数组中，就从左右的两边的数字找起(双指针)
     2.x不在数组中，没有的话，并且小于等于第一个元素，就从左至右取；大于等于最后一个元素，就从右至左取;
       如果x大于数组的最小值，小于数组的最大值，就从mid开始两边查找，双指针
        时间复杂度: O(n);  空间复杂度: O(k)--返回结果数组的空间
    */
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int length = arr.length;
        int low = 0;
        int high = length - 1;
        int mid = 0;
        boolean flag = false;
        while (low <= high) {
            mid = low + (high - low)/2;
            if (arr[mid] == x) {
                //找到目标值
                flag = true;
                break;
            }else if (arr[mid] > x) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        if (x <= arr[0]) { //x比数组的第一个元素都小
            for (int i = 0;i < k;i++) {
                list.add(arr[i]);
            }
        }else if (x >= arr[length - 1]) { //x比数组的最后一个元素都大
            for (int i = length - k;i < length;i++) {
                list.add(arr[i]);
            }
        }else {
            //执行到这里说明在x大于数组最小值，小于数组最大值
            int i = 0,ki = 0;
            int j = 0,kj = 0;
            int count = 0; //用来记录包含的元素数量
            if (flag) {
                //说明数组包含x   k等于1时如何处理?
                i = mid - 1;
                j = mid + 1;
                count = 1;
            }else {
                //x不在数组中，从mid开始，因为mid应该是最接近x的数
                if (arr[mid] < x) { //mid小于目标值
                    i = mid;
                    j = mid + 1;
                }else { //arr[mid]大于x目标值
                    i = mid - 1;
                    j = mid;
                }
                count = 0;
            } //mid=0
            while (i >= 0 && j <= length - 1 && count < k) { //k=3
                int absi = Math.abs(arr[i] - x); //i指向的元素与x相差的绝对值
                int absj = Math.abs(arr[j] - x); //j指向的元素与x相差的绝对值
                if (absi <= absj) {
                    i--;
                    ki++;
                    count++;
                }else {
                    j++;
                    kj++;
                    count++;
                }
            }
             //说明还没有遍历完，但是左右两边，其中有一边是已经到头了，需要遍历另一边
            while (count < k && j>length - 1) {
                //说明右边的已经探测完成，开始探测左边
                i--;
                ki++;
                count++;
            }
            while (count < k && i<0) {
                //说明左边的已经探测完成，开始探测右边
                j++;
                kj++;
                count++;
            }
            i++;
            j--;
            for (int q =0;q<count;q++) {
                list.add(arr[i+q]);
            }

        }
        return list;
    }


}
