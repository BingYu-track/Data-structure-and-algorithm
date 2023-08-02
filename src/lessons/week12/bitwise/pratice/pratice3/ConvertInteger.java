package lessons.week12.bitwise.pratice.pratice3;

/**
 * @version 1.0  面试题 05.06. 整数转换
 * @Description: 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 * 示例1:
 * 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 * 输出：2
 *
 * 示例2:
 * 输入：A = 1，B = 2
 * 输出：2
 * 提示:
 * A，B范围在[-2147483648, 2147483647]之间
 *
 * @author: bingyu
 * @date: 2023/8/2
 */
public class ConvertInteger {

    public static void main(String[] args) {
        ConvertInteger ci = new ConvertInteger();
        int A = 15;
        int B = 1;
        int res = ci.convertInteger(A, B);
        System.out.println(res);
    }

    /*
    就是找二进制不同的数字个数
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.1 MB, 在所有 Java 提交中击败了61.81%的用户
    */
    public int convertInteger(int A, int B) {
        int num = A ^ B;
        int cons = 1;
        int count = 0;
        for (int i = 31;i>=0;i--) {
            if ((num & cons)!=0) {
                count++;
            }
            cons <<= 1;
        }
        return count;
    }
}
