package lessons.week1.pratice.part1.pratice10;

/**
 * @version 把字符串转换成整数
 * @Description: 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用atoi或者其他类似的库函数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。当我们寻找到的第一个非空字符
 * 为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，
 * 则直接将其与之后连续的数字字符组合起来，形成整数。该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回0。
 *
 *
 * 说明：
 * 假设我们的环境只能存储32位大小的有符号整数，那么其数值范围为[−2^31, 2^31−1]。如果数值超过这个范围，
 * 请返回INT_MAX(2^31−1)或INT_MIN(−2^31)
 *
 * 示例1:
 * 输入: "42"
 * 输出: 42
 *
 * 示例2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42。
 *
 * 示例3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3'，因为它的下一个字符不为数字。
 *
 * 示例4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。因此无法执行有效的转换。
 *
 * 示例5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字"-91283472332"超过32位有符号整数范围。因此返回INT_MIN (−2^31) 。
 *
 *
 * @author: bingyu
 * @date: 2021/12/29
 */
public class StrToInt {

    public static void main(String[] args) {
        String str = "4193 with words";
        int num = strToInt(str);
        System.out.println(num);
    }

    //我的解法:
    //特殊情况：1.空字符串 2.全是空格 3.开头非数字字符  4.是数字，但是超出32位有符号整数范围了
    public static int strToInt(String str) {
        if (str.length() == 0) return 0;
        char[] chars = str.toCharArray();
        //1.去除首空格
        int low = 0;
        int high = chars.length - 1;
        while (low < high && chars[low]==' ') {
            low++;
        }

        //2.判断开头字符是否有效
        if (!isValid(chars[low])) return 0; //开头是无效字符

        //3.执行到这里说明开头字符是有效的开始将字符串转化为数字
        int num = 0; //用来记录转换的数字
        int numLength = 0; //用来记录转换数字的长度
        boolean flag = false; //判断首字符是否是'负号'的标志

        //执行到这里，low指向的数值要么是数字，要么是正负号，开始获取数字的真实长度
        //a.去除正负号，数字长度减1,low到high
        if (chars[low]=='-') {
            flag = true;
            low++;
        } else if (chars[low]=='+') {
            low++;
        }
        //b.执行到这里，遍历low和high之间的字符串，直到遇到非数字停止
        int k = low;
        int max = 2147483647;
        int min = -2147483648;
        int minp = min / 10; //获取int最小值的前9位数
        int maxp = max / 10; //获取int最大值的前9位数
        while (k <= high && isNumber(chars[k])) { //每次都会判断字符是否是数字
            if (flag) {
                //负数
                if (num < minp) return min; //判断当前num是否小于int最小值的前9位数，小于，下面乘以10后必定溢出
                num = num * 10;
                int reduce = min - num;
                if (reduce > -(chars[k]-48)) { //执行到这里说明num没溢出，再比较num和min的差是否大于当前要减的数字，如果大于说明也会溢出
                    num = min;
                }else {
                    num = num - (chars[k]-48);
                }
            }else {
                //正数
                if (num > maxp) return max; //判断当前num是否大于int最大值的前9位数，大于，下面乘以10后必定溢出
                num = num * 10;
                int reduce = max - num;
                if (reduce < (chars[k]-48)) { //执行到这里说明num没溢出，再比较num和max的差是否大于将要加的数字，如果大于说明也会溢出
                    num = max;
                }else {
                    num = num + (chars[k]-48);
                }

            }
            numLength++;
            k++;
        }
        return num;
    }


    //判断字符是否有效：开头是'负号'或者'正号'，或者是数字才有效,否则均是无效字符
    public static boolean isValid(char c) {
        if ((c>='0' && c<='9') || (c=='-' || c=='+')) {
            return true;
        }
        return false;
    }

    //判断字符是否是数字
    public static boolean isNumber(char c) {
        if (c>='0' && c<='9') {
            return true;
        }
        return false;
    }


}
