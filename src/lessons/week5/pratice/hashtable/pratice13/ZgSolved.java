package lessons.week5.pratice.hashtable.pratice13;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @version 1.0
 * @Description: 数组的相对排序--争哥解法
 * @author: bingyu
 * @date: 2022/8/17
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] result = relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(result));
    }

    /*
      争哥解法
    */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // arr2中每个数字在arr1中出现的次数
        HashMap<Integer, Integer> counts = new HashMap<>();
        // 先⽤arr2构建hash表
        for (int i = 0; i < arr2.length; ++i) {
            counts.put(arr2[i], 0);
        }
        // 扫描arr1统计arr2中每个数字在arr1中出现的次数
        for (int i = 0; i < arr1.length; ++i) {
            if (counts.containsKey(arr1[i])) {
                int oldCount = counts.get(arr1[i]);
                counts.put(arr1[i], oldCount+1);
            }
        }
        int[] result = new int[arr1.length];
        int k = 0;
        // 将counts的数据按照arr2的顺序输出
        for (int i = 0; i < arr2.length; ++i) {
            int count = counts.get(arr2[i]);
            for (int j = 0; j < count; ++j) {
                result[k+j] = arr2[i];
            }
            k += count;
        }
        // 将arr1中未出现在arr2中的数据有序输出到result
        Arrays.sort(arr1);
        for (int i = 0; i < arr1.length; ++i) {
            if (!counts.containsKey(arr1[i])) { //在遍历arr1时，hashMap不包含的元素放到结果数组中
                result[k++] = arr1[i];
            }
        }
        return result;
    }

}
