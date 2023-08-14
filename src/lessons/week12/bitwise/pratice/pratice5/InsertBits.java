package lessons.week12.bitwise.pratice.pratice5;

/**
 * @version 1.0  面试题 05.01. 插入
 * @Description: 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。
 *
 * 题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
 *
 *
 * 示例1:
 * 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 * 输出：N = 1100(10001001100)
 *
 * 示例2:
 * 输入： N = 0, M = 31(11111), i = 0, j = 4
 * 输出：N = 31(11111)
 *
 * @author: bingyu
 * @date: 2023/8/3
 */
public class InsertBits {

    public static void main(String[] args) {
        InsertBits ib = new InsertBits();
        int N = 1280; //10100000000
        int M = 19;//10011
        int i = 2;
        int j = 8;
        //预期结果应该是 (1100)10001001100
        int res = ib.insertBits(N, M, i, j);
        System.out.println(res);
    }


    /*
     我的思路：1.在所有32位都为1的二进制数里，在j前面的位数和i后面的位数全部设为1，i~j范围全部设为0，因此
               这样得到一个数记为K，此时j前面的位数就都是1，i后面的位数也都是1吗，i~j为0。
             2.再让k和N进行与运算得到num，这样做的目的是保留N，i~j范围内为0，因此和N与也是0，但是i~j范围就都是N的位数不变了
             3.然后让M右移i位，使其到达指定位数，最后和num进行或运算，将M位数数字保留到num的i~j范围里得到最终结果

        执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗：38.2 MB, 击败 64.76%使用 Java 的用户
    */
    public int insertBits(int N, int M, int i, int j) {
        int k = 1;
        //TODO: 1向右移，并判断是否在i~j的范围之外，在当在i~j范围外时，在末尾加1
        for (int p = 0;p<32;p++) {
            k <<= 1;
            //1向右移动32位，并且在适当的条件下加上1使其i~j范围为0，其它都是1
            if (p<32-j-1 || p>=32-i) { //这里需要减去一个1，因为k初始时这个1就已经有了，因此需要减去1个
                k++;
            }
        }
        /*                                     j     i
          执行到这里得到 k=11111111111111111111111000000011(-509)
                                  10100000000
         */
        int num = N & k; //num=10000000000(1024)
        M <<= i; //M右移i位，使其到达指定位数，然后相加即可
        int res = num | M;
        return res;
    }

    /*
    争哥的解法思路:
     */
    public int insertBits2(int N, int M, int i, int j) {
        int[] nbits = new int[32];
        int[] mbits = new int[32];
        int nbitsNum = 0;
        int mbitsNum = 0;
        int mask = 1;
        //1.将十进制数N转换为二进制数组
        for (int k = 0; k < 32; ++k) {
            if ((N & mask)!=0) {
                nbits[k] = 1;
            }
            mask <<= 1;
        }
        mask = 1;
        //2.将十进制数M转换为二进制数组
        for (int k = 0; k < 32; ++k) {
            if ((M & mask)!=0) {
                mbits[k] = 1;
            }
            mask <<= 1;
        }
        //3.都转换成了数组，这样就很容易把M的元素插入到N的数组中了
        for (int k = i; k <= j; ++k) {
            nbits[k] = mbits[k-i];
        }
        //4.再将得到的最终二进制数组转换成十进制数即可!
        mask = 1;
        int ret = 0;
        for (int k = 0; k < 32; ++k) {
            ret += (nbits[k] * mask);
            mask <<= 1;
        }
        return ret;
    }

}
