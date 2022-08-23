package lessons.week5.pratice.hashtable.pratice16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Description: 交换和--争哥解法
 * @author: bingyu
 * @date: 2022/8/23
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5,6};
        int[] swapValues = findSwapValues(arr1, arr2);
        System.out.println(Arrays.toString(swapValues));
    }

    /*
     争哥思路: 这里有个隐藏条件，就是要求两数组和是一样的，也就是两数组分别能交换成为(sum1+sum2)/2；如果两数组和除以2是奇数，就可以判定不符合条件了
    */
    public static int[] findSwapValues(int[] array1, int[] array2) {
        int n = array1.length;
        int m = array2.length;
        // 计算数组1的和
        int sum1 = 0;
        for (int i = 0; i < n; ++i) {
            sum1 += array1[i];
        }
        // 计算数组2的和，并且将元素放到哈希表中
        int sum2 = 0;
        HashSet<Integer> hashTable = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            sum2 += array2[i];
            hashTable.add(array2[i]);
        }
        // sum1+sum2是奇数，那⽆解
        int sum = sum1+sum2;
        if (sum % 2 == 1) return new int[0];
        // 遍历数组1中的每个元素，在哈希表中查找
        int diff = sum/2 - sum1;
        for (int i = 0; i < n; ++i) {
            int target = array1[i] + diff;
            if (hashTable.contains(target)) {
                return new int[] {array1[i], target};
            }
        }
        return new int[0];
    }

}
