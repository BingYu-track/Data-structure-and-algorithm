package lessons.week12.bitwise.pratice.pratice8;

/**
 * @version 1.0 剑指 Offer 56 - II.数组中数字出现的次数 II
 * @Description: 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例 1：
 * 输入：nums = [3,4,3,3]
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 * 限制：
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 * @author: bingyu
 * @date: 2023/8/15
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {3,4,3,3}; //
        SingleNumber s = new SingleNumber();
        int res = s.singleNumber(nums);
        System.out.println(res);
    }

    /*

     我的思路: 这题不能再沿用异或运算的思路了，因为3个数异或还是他自己;
     因为数组里的数都是3个配对，因此3个配对的数的每个二进制位如果是1，应该都是3的倍数，那么，我们只需要将这些数每位的1数量统计出来放入数组中，
     然后每位对3取余，得到的就是只出现一次的数在当前二进制位的数值，配合png图片更好理解
    时间14ms 击败 26.35%使用 Java 的用户
    内存42.56mb,击败 60.74%使用 Java 的用户
    */
    public int singleNumber(int[] nums) {
        int[] bits = new int[32]; //数组用来统计nums数组中所有元素里32位，每位出现1的总次数
        for (int i = 0;i<nums.length;i++) { //遍历每个数
            int num = nums[i];
            int cons = 1;
            for (int j = 31;j>=0;j--) { //对每个数进行二进制1的统计
                if ((num & cons)!=0) {
                    bits[j]++;
                } //20k
                cons <<= 1;
            }
        }
        //执行到这里说明sum数组已经收录了每位所有1的数量，开始对每位进行取余操作
        for (int i = 0;i<bits.length;i++) {
            if (bits[i] != 0) {
                bits[i] %= 3;
            }
        }
        int cons = 1;
        //此时sum数组里存储的就是只有一个数的二进制形式，我们再将其转换为十进制，使用公式法
        int res = bits[31];  //最后一位是自己，即bits[i] * 2^0
        for (int i = 30;i>=0;i--) { //从后面第2位开始即 bits[i] * 2^1 开始
            res += bits[i] * (cons <<= 1);
        }
        return res;
    }



}
