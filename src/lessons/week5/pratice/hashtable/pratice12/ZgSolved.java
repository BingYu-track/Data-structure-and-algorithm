package lessons.week5.pratice.hashtable.pratice12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Description: 两个数组的交集--争哥解法
 * @author: bingyu
 * @date: 2022/8/16
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    /*
    争哥解法
    */
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashTable = new HashSet<>();
        for (int i = 0; i < nums1.length; ++i) {
            hashTable.add(nums1[i]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; ++i) {
            if (hashTable.contains(nums2[i])) {
                hashTable.remove(nums2[i]);
                result.add(nums2[i]);
            }
        }
        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }
}
