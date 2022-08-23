package lessons.week5.pratice.hashtable.pratice16;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @version 1.0 面试题 16.21. 交换和
 * @Description: 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 *
 * 示例:
 * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
 * 输出: [1, 3]
 *
 * 示例:
 * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
 * 输出: []
 *
 * 提示：
 * 1 <= array1.length, array2.length <= 100000
 *
 * @author: bingyu
 * @date: 2022/8/21
 */
public class FindSwapValues {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5,6};
        int[] swapValues = findSwapValues(arr1, arr2);
        System.out.println(Arrays.toString(swapValues));
    }

    /** [258, 282, 382, 494, 519, 662, 719, 795, 886, 4718]  sum1=9715 ->4718
     *  [20, 20, 38, 50, 52, 78, 81, 96]   sum2=435 ->78
     * 我的思路:
     * 根据题目思路，可以推导出以下公式：就是求
     *  sum1-x1+x2 = sum2-x2+x1
     *  sum1-x1+x2-sum2+x2-x1 = 0
     *  sum1-sum2+2(x2)-2(x1) = 0
     *  sum1-sum2 = 2(x1-x2)
     *  sum1-sum2-2(x1) = -2(x2)
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了87.55%的用户
     * 内存消耗：50.6 MB, 在所有 Java 提交中击败了60.13%的用户
     */
    public static int[] findSwapValues(int[] array1, int[] array2) {
        int[] result = new int[2];
        int sum1 = 0;
        int sum2 = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0;i<array1.length;i++) {
            sum1 += array1[i];
        }
        for (int j = 0;j<array2.length;j++) {
            sum2 += array2[j];
            set.add(array2[j]);
        }
        boolean flag = false;
        int diff = sum1 - sum2;
        for (int i = 0;i<array1.length;i++) {
            if ((diff - 2 * array1[i]) % 2 == 0) { //只有取模等于0时，才能说明当前数字满足条件
                int num = -1*(diff - 2 * array1[i])/2;
                if (set.contains(num)) {
                    result[0] = array1[i];
                    result[1] = num;
                    flag = true;
                    return result;
                }
            }
        }
        if (!flag) {
            result = new int[0];
        }
        return result;
    }


}
