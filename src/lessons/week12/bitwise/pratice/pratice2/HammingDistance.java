package lessons.week12.bitwise.pratice.pratice2;

/**
 * @version 1.0  汉明距离
 * @Description: 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * 示例 1：
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 示例 2：
 * 输入：x = 3, y = 1
 * 输出：1
 *
 * 提示：
 * 0 <=x, y <= 2^31 - 1
 *
 * @author: bingyu
 * @date: 2023/8/2
 */
public class HammingDistance {

    public static void main(String[] args) {
        HammingDistance hd = new HammingDistance();
        int x = 3;
        int y = 4;
        int res = hd.hammingDistance(x, y);
        System.out.println(res);
    }

    /*
     题目就是求同一个位，不同数字的个数,因此将两个数字进行异或操作，得到数字时再查看起二进制数有多少个1即可
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：38.1 MB, 在所有 Java 提交中击败了93.77%的用户
    */
    public int hammingDistance(int x, int y) {
        int num = x^y;
        int cons = 1;
        int count = 0; //用来记录不同的数目
        for (int i = 31;i>=0;i--) {
            if ((num & cons)!=0) {
                count++;
            }
            cons <<= 1;
        }
        return count;
    }
}
