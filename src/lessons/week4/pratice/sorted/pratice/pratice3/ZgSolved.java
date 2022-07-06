package lessons.week4.pratice.sorted.pratice.pratice3;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 判断能否形成等差数列
 * @author: bingyu
 * @date: 2022/6/17
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] arr = {3,5,4,2,1,6,8};
        boolean flag = canMakeArithmeticProgression(arr);
        System.out.println(flag);
    }

    /* TODO: 争哥解法，和我一样

    */
    public static boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; ++i) {
            if (arr[i]-arr[i-1] != diff) return false;
        }
        return true;
    }

}
