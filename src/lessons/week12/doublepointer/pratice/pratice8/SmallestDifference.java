package lessons.week12.doublepointer.pratice.pratice8;

import java.util.Arrays;

/**
 * @version 1.0 面试题 16.06. 最小差
 * @Description: 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 * 示例：
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出：3，即数值对(11, 8)
 *
 * 提示：
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 * @author: bingyu
 * @date: 2023/7/20
 */
public class SmallestDifference {

    public static void main(String[] args) {
        int[] a = {1, 3, 15, 11, 2};
        int[] b = {23, 127, 235, 19, 8};
        SmallestDifference sd = new SmallestDifference();
        int res = sd.smallestDifference(a, b);
        System.out.println(res);
    }

    /*
      核心思想是将差值逼近0

        执行用时：21 ms, 在所有 Java 提交中击败了69.10%的用户
        内存消耗：49.2 MB, 在所有 Java 提交中击败了62.46%的用户
    */
    public int smallestDifference(int[] a, int[] b) {
        int len1 = a.length;
        int len2 = b.length;
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        long min = Integer.MAX_VALUE; //用来记录最小差值
        while (i<len1 && j<len2) {
            if (a[i] == b[j]) return 0; //如果两个数字相等，那么其差一定是最小的
            long diff = a[i] - b[j]; //防止越界
            min = Math.min(Math.abs(diff),min); //取最小值
            // TODO：为何要这样做？
            //  可以这样理解，因为差值绝对值最小就是0，因此我们要尽量让差值往0靠拢，
            //  1.如果dff<0，说明i指针指向的数字小于j指向的数字，因此要逼近0的话，应该让i对应的数值增加，因为已经排过序了，因此i只有往后移动才能逼近0
            //  2.同理如果diff>0，说明j指针指向的数字太小，为了使差值逼近0，j必须往后移动增加数值
            //  3.在上述逼近0的过程中取其差值的最小值，当其中一个数组的元素全部遍历过一遍后，就说明此时diff就是最小差
            if (diff < 0) {
                i++;
            }else {
                j++;
            }
        }
        return (int) min;
    }

}
