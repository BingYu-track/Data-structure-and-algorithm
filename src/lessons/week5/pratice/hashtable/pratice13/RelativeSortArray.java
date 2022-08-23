package lessons.week5.pratice.hashtable.pratice13;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @version 1.0  数组的相对排序
 * @Description: 给你两个数组，arr1和arr2，arr2中的元素各不相同，arr2 中的每个元素都出现在arr1中。
 * 对arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
 *
 * 示例 1：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 示例 2:
 * 输入：arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * 输出：[22,28,8,6,17,44]
 *
 *
 * 提示：
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2中的元素arr2[i]各不相同
 * arr2中的每个元素arr2[i]都出现在arr1中
 *
 * @author: bingyu
 * @date: 2022/8/16
 */
public class RelativeSortArray {


    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] result = relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(result));
        //排序后: [0, 2, 5, 7, 12, 12, 13, 21, 23, 24, 24, 27, 29, 30, 31, 33, 38, 42, 43, 48]
        //预期结果：
        //[2,42,38,0,43,21,5,7,12,12,13,23,24,24,27,29,30,31,33,48]

        //输出：
        //[2,42,38,0,43,21,0,0,0,0,5,7,13,23,27,29,30,31,33,48]
    }

    /*
     TODO： 我的解法:弄个map，把arr1放进去，value为0;再遍历arr2，arr2有的就+1，没有的不加，搞个新数组塞进去，然后遍历arr2，从map里拿数拼result，新数组排排序就行了
     空间复杂度: O(m+n) 即两个数组的长度和   时间复杂度: 最差时间复杂度O(N^2) 最好时间复杂度O(n)  平均时间复杂度:
     执行用时：1 ms, 在所有 Java 提交中击败了60.43%的用户
     内存消耗：41.2 MB, 在所有 Java 提交中击败了41.67%的用户
    */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int[] result = new int[n1];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i<arr1.length;i++) { //step1: 将arr1的所有元素放入map中，并记录出现的次数
            int num = arr1[i];
            if (map.containsKey(num)) {
                int k = map.get(num);
                map.put(num,++k);
            }else {
                map.put(arr1[i],1);
            }
        }
        int j = 0; //用来记录结果数组的下标
        for (int i = 0;i<arr2.length;i++) { //step2: 再遍历arr2的过程中结合之前得到的hashMap,将其放入结果数组中
            int num = arr2[i];
            Integer count = map.get(num); //获取当前数字的重复次数
            while (count!=0) { //如果有放进结果数组，直到value减为0
                result[j++] = num;
                count--;
                map.put(num,count); //更新次数
            }
        }
        int k = j;
        //再遍历arr1，遇到不包含的说明就是剩下的元素，将其放入结果数组中，最后进行排序即可
        for (int n = 0;n<arr1.length;n++) {
            int num = arr1[n];
            if (map.get(num)!=0) {
                result[j++] = num;
            }
        }
        Arrays.sort(result,k,result.length); //对数组指定区间进行排序
        return result;
    }

    /* TODO: 需要重复多次练习
     leetcode官方题解
     思路:
    */
    public static int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) { //获取arr1数组中的最大值
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1]; //创建以arr1数组中的最大值的长度的数组
        for (int x : arr1) {
            ++frequency[x]; //TODO: 遍历arr1，根据其遍历的值到frequency数组查找对应的位置并加1(为何要这样做?)
        }
        int[] ans = new int[arr1.length]; //
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }




}
