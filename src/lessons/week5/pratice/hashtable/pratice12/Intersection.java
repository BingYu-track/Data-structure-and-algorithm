package lessons.week5.pratice.hashtable.pratice12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0 两个数组的交集 TODO 该题需要重视起来
 * @Description: 给定两个数组nums1和nums2 ，返回 它们的交集。输出结果中的每个元素一定是唯一的。我们可以不考虑输出结果的顺序 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 *
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 * @author: bingyu
 * @date: 2022/8/15
 */
public class Intersection {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    /*
    我的思路: 哈希 时间复杂度: O(m+n)  空间复杂度: O(m+n)
     执行用时：2 ms, 在所有 Java 提交中击败了95.65%的用户
     内存消耗：41.6 MB, 在所有 Java 提交中击败了47.25%的用户
    */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> hashTable = new HashMap<>();
        int n1 = nums1.length;
        int n2 = nums2.length;
        for (int i = 0;i<n1;i++) {
            hashTable.put(nums1[i],0);
        }
        int k = 0;
        for (int j = 0;j<n2;j++) {
            if (hashTable.containsKey(nums2[j]) && hashTable.get(nums2[j])==0) { //包含key，且是第一次遇到的就放进去，并且用k记录重复的个数
                hashTable.put(nums2[j],1);
                k++;
            }
        }
        int[] result = new int[k]; //创建结果数组
        k = 0;
        for (Map.Entry<Integer, Integer> entry : hashTable.entrySet()) {
            if (entry.getValue() > 0) {
                result[k] = entry.getKey();
                k++;
            }
        }
        return result;
    }

    /* TODO: 需要重复多次练习
     leetcode官方题解: 排序,然后两数组的指针比较大小，小的先移动直到两指针相等时，将其元素放到结果数组，并且在放入的过程中比较结果数组里是否有与
                     其相同的，不相同就直接放入结果数组即可。
     4,5,9
     4,4,8,9,9
     执行用时：2 ms, 在所有 Java 提交中击败了95.66%的用户
     内存消耗：41.7 MB, 在所有 Java 提交中击败了22.80%的用户

    */
    public static int[] intersectionSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] result = new int[n1 + n2];
        int k = 0;
        int low1 = 0;
        int high1 = n1 - 1;
        int low2 = 0;
        int high2 = n2 - 1;
        while (low1<=high1 && low2<=high2) {
            int num1 = nums1[low1];
            int num2 = nums2[low2];
            if (num1 == num2) { //遇到2数组相同的元素，才放进结果数组
                //在nums1数组遇到相同的数字，比较当前数字后面的数字，如果不一样，就说明该数字可能是重复数字的最后一个，将其放进结果数组
//                if (k == n1 + n2 - 1 || result[k]!=result[k+1]) {
//                    result[k++] = num1;
//                } //TODO 但是发现这样不行，因为刚初始化的结果数组都是0，上面if是进不去的，永远是false，所以只能比较前面一个元素再存入结果数组
                if (k==0 || result[k-1] != num1) { //如果当前k是在结果数组的开头，或者结果数组前面k-1位置的元素和当前nums1数组元素不一样，则存入结果数组(用nums1和nums2数组都可以，因为它们相交元素都有)
                    result[k++] = num1;
                }
                //一同向后移动，
                low1++;  //5,9
                low2++;  //4,8,9,9
            }else if (num1 < num2) { //不相等，让小的数组指针向后移动
                low1++;
            }else {
                low2++;
            }
        }
        return Arrays.copyOfRange(result, 0, k); //注意由于结果数组后面有0，因此需要截取数组
    }

    //二分查找优化
    public static int[] intersectionBs(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

}
