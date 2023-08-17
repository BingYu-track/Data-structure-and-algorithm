package lessons.week12.bitwise.pratice.pratice10;

/**
 * @version 1.0 231. 2 的幂
 * @Description: 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：true
 * 解释：2^0 = 1
 *
 * 示例 2：
 * 输入：n = 16
 * 输出：true
 * 解释：2^4 = 16
 *
 * 示例 3：
 * 输入：n = 3
 * 输出：false
 *
 * 示例 4：
 * 输入：n = 4
 * 输出：true
 *
 * 示例 5：
 * 输入：n = 5
 * 输出：false
 *
 *
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * 进阶：你能够不使用循环/递归解决此问题吗？
 * @author: bingyu
 * @date: 2023/8/16
 */
public class IsPowerOfTwo {

    public static void main(String[] args) {
        IsPowerOfTwo p = new IsPowerOfTwo();
        boolean res = p.isPowerOfTwo(-2147483648);
        System.out.println(res);
    }


    /*
     2^1 = 10
     2^2 = 100
     2^3 = 1000
     .....
     2^n = 1000....0 (n个0)
     以上是正数的情况,可以看到2的次幂，二进制数都只有一个1

     -8是不是2的次幂，而-4也不是2的次幂；但是题目要求判断的是n是否是2的次幂，因此负数的话都不是
     2的次幂，因此我的思路是，将十进制转为二进制数组，然后看是否只有一个1，如果只有一个1，说明该数字就是2的次幂

     -16的二进制位 ....111111110000
     注意特殊情况: -2147483648的二进制是10000000000000000000000000000000，也只有一个1。因此还要判断，如果是负数的话，
     肯定不是2的次幂

     时间1ms，击败 24.49%使用 Java 的用户
     内存37.92mb击败 6.75%使用 Java 的用户
    */
    public boolean isPowerOfTwo(int n) {
        if (n<0) return false;
        int cons = 1;
        int count = 0; //用来记录出现1的个数
        for (int i = 31;i>=0;i--) {
            if ((n & cons)!=0) {
                count++;
            }
            cons <<= 1;
        }
        return count == 1;
    }



}
