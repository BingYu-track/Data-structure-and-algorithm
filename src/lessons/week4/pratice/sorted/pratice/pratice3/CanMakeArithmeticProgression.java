package lessons.week4.pratice.sorted.pratice.pratice3;

import java.util.Arrays;

/**
 * @version 1.0 判断能否形成等差数列
 * @Description: 给你一个数字数组arr,如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为等差数列。
 *               如果可以重新排列数组形成等差数列，请返回true否则，返回false。
 *
 * 示例 1：
 * 输入：arr = [3,5,1]
 * 输出：true
 * 解释：对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
 *
 * 示例 2：
 * 输入：arr = [1,2,4]
 * 输出：false
 * 解释：无法通过重新排序得到等差数列。
 *
 * 提示：
 * 2 <= arr.length <= 1000
 * -10^6 <= arr[i] <= 10^6
 *
 * @author: bingyu
 * @date: 2022/6/17
 */
public class CanMakeArithmeticProgression {

    public static void main(String[] args) {
        int[] arr = {0,0,1};
        boolean flag = canMakeArithmeticProgression(arr);
        System.out.println(flag);
    }

    /*
     我的思路:
     不敢用排序算法，因为用了排序时间复杂度会比较高，有可能超时

     判断是否是等差数列，求出a1和an,以及元素的数量，再根据等差数列求和公式，求出正确的和，然后我们验证该数组
     所有元素的和是否等于我们之前等差数列求和公式的结果,这个思路不对

     2,10,7,8,3
    */
    public static boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); //使用API函数的快排算法实现的
        int d = Integer.MIN_VALUE;
        for (int i = 0;i < n-1;i++) { //这里是遍历排序后的数组，检测任意2个相邻的数字相差是否是固定的
            if (d == Integer.MIN_VALUE) {
                d = arr[i + 1] - arr[i];
            }else if (arr[i + 1] - arr[i] != d){
                return false;
            }
        }
        return true;
    }
    //a1 + a1+d + a1+2d + .... + a1+(n-1)d = n*a1 + d + 2d + ... + (n-1)d = n*a1 + n*d*(n-1)/2

}
