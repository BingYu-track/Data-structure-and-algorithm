package lessons.week12.bitwise.pratice.pratice9;

import java.util.Arrays;

/**
 * @version 1.0  面试题 16.01. 交换数字
 * @Description: 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 * 示例：
 * 输入: numbers = [1,2]
 * 输出: [2,1]
 * 提示：
 * numbers.length == 2
 * -2147483647 <= numbers[i] <= 2147483647
 * @author: bingyu
 * @date: 2023/8/16
 */
public class SwapNumbers {

    public static void main(String[] args) {
        SwapNumbers sn = new SwapNumbers();
        int[] numbers = {1,2};
        int[] res = sn.swapNumbers(numbers);
        System.out.println(Arrays.toString(res));
    }


    /*
     TODO: 使用异或运算的交换律，A^B=C C^B=A C^B=A
     时间0ms,击败 100.00%使用 Java 的用户
     内存38.80mb,击败 9.22%使用 Java 的用户
    */
    public int[] swapNumbers(int[] numbers) {
        if (numbers[0] == numbers[1]) return numbers; //设numbers[0]=A numbers[1]=B
        numbers[0] ^= numbers[1]; //numbers[0] = A^B=C
        numbers[1] ^= numbers[0]; //numbers[1] = C^B=A
        numbers[0] ^= numbers[1]; //numbers[0] = C^A=B
        return numbers;
    }

}
