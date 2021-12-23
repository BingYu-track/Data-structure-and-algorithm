package lessons.week1.pratice.pratice5;

/**
 * @version 1.0 回文数
 * @Description: 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是
 *
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 *
 * 示例2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数
 *
 * 示例 4：
 * 输入：x = -101
 * 输出：false
 *
 * 提示：
 * -2^31<= x <= 2^31 - 1
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 * @author: bingyu
 * @date: 2021/12/21
 */
public class PalindromeNumber {


    public static void main(String[] args) {
        boolean palindrome = isPalindrome(10);
        System.out.println(palindrome);
    }

    //非0的负数都不是回文数
    //我的解法:
    public static boolean isPalindrome(int oldNum) {
        if (oldNum < 0) return false;
        int y = oldNum; //oldNum的副本
        int p = 1; //用来记录数字oldNum的位数
        while (y >= 10) {
            y /= 10;
            p++;
        }
        int topNum = y; //执行到这里，topNum就是数字oldNum最高位的数字，p是数字oldNum的位数
        y = oldNum;
        int newNum = 0; //用来记录翻转后的数字
        if (p == 1) return true; //如果是个位数，肯定是回文数
        //1.不断的去减高位，直到最低位
        for(;y>=10 ; y/=10) {
            int mul = 1; //乘数
            int lowNum = y % 10; //不断的获取旧数字的最低位的数字
            for (int j = p-1; j>0;j--) {
                mul *=10;  //从位数p，从高到低
            }
            newNum = newNum + lowNum * mul; //用旧数字的最低位的数字乘以高位乘数
            p--;
        }
        newNum = newNum + topNum; //最后加上最高位
        if (newNum == oldNum) {
            return true;
        }
        return false;
    }
    //我解法的思路:1.先获取数字的总位数，和最高位的数字
    //           2.再从低位到高位依次根据对10取余获取最低位数，并乘以高位不断进行累加


}
