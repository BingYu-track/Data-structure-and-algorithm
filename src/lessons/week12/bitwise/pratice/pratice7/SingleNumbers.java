package lessons.week12.bitwise.pratice.pratice7;

import java.util.Arrays;

/**
 * @version 1.0  剑指 Offer 56 - I. 数组中数字出现的次数
 * @Description: 一个整型数组nums里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 * 限制：
 * 2 <= nums.length <= 10000
 *
 * @author: bingyu
 * @date: 2023/8/3
 */
public class SingleNumbers {

    public static void main(String[] args) {
        SingleNumbers sn = new SingleNumbers();
//        int[] nums = {1,2,10,4,1,4,3,3};
//        int[] nums = {4,1,4,6};
        int[] nums = {3,2,1,3};
        int[] res = sn.singleNumbers(nums);
        System.out.println(Arrays.toString(res));
    }

    /*
    TODO 核心思路: 数组里面的数字大部分已经是两两配对了，找出其中2个数字没有配对的，直接使用异或运算的交换律，对所有数字进行异或运算
     我们得到这两个非配对数字异或的结果，然后根据异或的结果我们可以知道这两个数字哪一个二进制位是不同的，然后我们根据这个二进制位的位置是0还是1来再次
     进行分组，相同的数字二进制位肯定是分在一个组了，因此这样就形成了2个组，两个组除了1个数字没有配对，其它都是配对的，这样再分别对每个组的数字进行异或运算
     就能分别得到两个不配对的数字了!(争哥的思路，我自己的实现)

     执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：42.89 MB, 击败 17.19%使用 Java 的用户
    */
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int i = 0;i<nums.length;i++) {
            res ^= nums[i];
        }
        //执行到这里，得到两个非配对的数字异或的结果
        int cons = 1;
        for (int i = 0;i<32;i++) {
            if ((res & cons)!=0) { //不等于0，说明两个不配对的数字在当前二进制位，数字是不一样的，此时cons就是两个数字二进制位不同的位置
                break;
            }
            cons <<= 1; //右移
        }
        int a = 0;
        int b = 0;
        //在nums数组中根据元素和cons与运算的结果进行分组，并直接进行或运算
        for (int i = 0;i<nums.length;i++) {
            if ((nums[i] & cons) != 0) {
                a ^= nums[i]; //TODO：注意0和任何数字进行或运算，结果任然是这个数字本身
            }else {
                b ^= nums[i];
            }
        }
        return new int[]{a,b};
    }



    /*
     争哥解法
    */
    public int[] singleNumbersZg(int[] nums) {
        int xorResult = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            xorResult ^= nums[i];
        }
        int tag = 1;
        while ((xorResult & tag) == 0) {
            tag = tag << 1;
        }
        int a = 0;
        int b = 0;
        //争哥这里是一边判断分组，一边进行或运算
        for (int i = 0; i < n; ++i) {
            if ((nums[i] & tag) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[] {a, b};
    }

}
