package lessons.week1.pratice.part1.pratice5;

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
        palindrome = isPalindrome3(123);
        System.out.println(palindrome);
    }

    //非0的负数都不是回文数
    //我的解法:
    public static boolean isPalindrome(int oldNum) {
        if (oldNum < 0) return false;
        int temp = oldNum; //oldNum的副本
        int numberDigit = 1; //用来记录数字oldNum的位数
        while (temp >= 10) {
            temp /= 10;
            numberDigit++;
        }
        int topNum = temp; //执行到这里，topNum就是数字oldNum最高位的数字，p是数字oldNum的位数
        temp = oldNum;
        int newNum = 0; //用来记录翻转后的数字
        if (numberDigit == 1) return true; //如果是个位数，肯定是回文数
        //1.不断的去减高位，直到最低位
        for(;temp>=10 ; temp/=10) {
            int mul = 1; //用来表示高位的乘数
            int lowNum = temp % 10; //不断的获取旧数字的最低位的数字
            for (int j = numberDigit-1; j>0;j--) {
                mul *=10;  //而mul的乘数是从位数numberDigit，从高到低，有几个位数,mul就要乘以几次10
            }
            newNum = newNum + lowNum * mul; //用旧数字的最低位的数字乘以高位乘数
            numberDigit--;
        }
        newNum = newNum + topNum; //最后加上最高位
        if (newNum == oldNum) {
            return true;
        }
        return false;
    }
    //我解法的思路:1.先获取数字的总位数，和最高位的数字
    //           2.再从低位到高位依次根据对10取余获取最低位数，并乘以高位不断进行累加


    //争哥解法1：要求熟练掌握1.字符串转数字---具体方法：将字符串转为char数组，然后将 字符-'0'得到的就是对应的数字值
    //                  2.数字转数字数组---不能用String.valueOf()
    public static boolean isPalindrome2(int x) { //123
        // -2147483648 ~2147483647
        int[] digits = new int[10];
        if (x < 0) return false;
        int k = 0;
        //将x转化成数字数组
        while (x != 0) {
            digits[k] = x % 10; //取余得到最低位数
            x = x / 10; //自除10去除最低位
            k++;
        }
        // 判断回⽂串
        for (int i = 0; i < k/2; ++i) { // 举例验证
            if (digits[i] != digits[k-i-1]) { // 举例验证
                return false;
            }
        }
        return true;
    }

    //争哥解法2(推荐)
    public static boolean isPalindrome3(int x) { //123
        // -2147483648 ~2147483647
        if (x < 0) return false;
        int backupX = x; //给输入的数字保留副本
        int y = 0;//y为x反转之后的值
        while (x != 0) { //TODO:x不断除以10直到除完为止，除的次数就是数字x的位数，除完后，x就会为0
            y = y*10 + x % 10; //TODO:记住这个技巧---低位*10 + 当前的数字x的最低位数
            x = x / 10;
        }
        return backupX == y;
    }

}
