package lessons.week12.psstatistics.pratice.pratice3;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  面试题 05.03. 翻转数位
 * @Description: 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 * 示例 1：
 * 输入: num = 1775(11011101111) 二进制
 * 输出: 8
 *
 * 示例 2：
 * 输入: num = 7(0111) 二进制  TODO: 注意这里,7的二进制就是111，但这里前面加了个0
 * 输出: 4
 *
 * @author: bingyu
 * @date: 2023/7/28
 */
public class ReverseBits {

    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
//        int num = 1775;
        int num = -1;
        int res = rb.reverseBits(num);
        System.out.println(res);

    }

    /* TODO: 推荐该方法
     争哥思路:
     1.将十进制数先转为二进制并用数组存储
     2.再用2个数组记录nums[i]时前面连续1的个数，以及后面连续的1的个数
     3.最后进行统计即可,注意题目要求获得最长的一串1必须是连续的

     执行用时：0ms击败100%用户
     内存消耗：37.9MB，击败97.29%用户
     */
    public int reverseBits(int num) {
        int[] nums = toBinary(num);
        int[] leftCount = new int[32];
        int[] rightCount = new int[32];
        int count = 0;
        //1.统计0左边多少个1，并存入leftCount数组
        for (int i = 0;i<leftCount.length;i++) {
            leftCount[i] = count;
            if (nums[i]==1) { //从左到右遇到1，不断累积
                count++;
            }else {
                count = 0; //TODO: 注意1--这里遇到0就直接重新开始将count赋值0，因为题目要求的是连续1的长度!
            }
        }
        count = 0;
        //2.统计0右边多少个1，并存入rightCount数组
        for (int i = 31;i>=0;i--) {
            rightCount[i] = count;
            if (nums[i] == 1) { //从右到左，遇到1就累加
                count++;
            }else {
                count = 0;
            }
        }
        int maxCount = 0;
        for (int i = 0;i<nums.length;i++) {
            //TODO: 注意2--这里不能这样判断，因为可能出现所有二进制位都是1，就没有0存在，因此无论nums[i]是0还是1，都需要进行判断
            // 因为1左右两边形成的连续1的长度可能比0形成的还要长
//            if (nums[i] == 0) {
//                maxCount = Math.max(maxCount,leftCount[i] + rightCount[i] + 1);
//            }
            maxCount = Math.max(maxCount,leftCount[i] + rightCount[i] + 1);
        }
        return maxCount;
    }

    /*
     十进制树转为二进制数组
    */
    private int[] toBinary(int num) {
        int[] nums = new int[32];
        int cons = 1;
        for (int i = 31;i>=0;i--) {
            if ((num & cons)!=0) {
                nums[i] = 1; //因为二进制要么是0，要么是1，如果与运算后不为0，那么当前二进制位肯定就是1
            }
            cons <<= 1;
        }
        return nums;
    }


    /*
     题目意思是: 转换成2进制后，只能选1个数将0变成1，然后找数字连续是1的最长长度
     例如: 7(0111) 将第一个0变为1，就成了4个1
        1775(11011101111) 将第二个0变为1，这样最长且连续的1子串长度就是8

        我的大致思路是
        1.将十进制数先转为二进制并用数组存储，
        2.然后再用2个数组记录nums[i] = 0时的前缀和以及后缀和，
        并用max记录其1的连续值，然后不断这样循环执行，直到最后一个0处理完毕

        TODO: 还要注意num为负数的情况
        执行用时：1 ms, 在所有 Java 提交中击败了19.78%的用户
        内存消耗：38.1 MB, 在所有 Java 提交中击败了82.38%的用户
    */
    public int reverseBits2(int num) {
        int[] binary = transformBinary(num); //TODO： 这个方法是重点!必须掌握和理解
        List<Integer> list = new ArrayList<>(); //用来记录0在nums的下标位置
        int[] preSum = new int[binary.length]; //用来存储nums[i]的前缀和
        int[] suffSum = new int[binary.length]; //用来存储所有0的后缀和
        //1.计算前缀和(不包含自身)
        for (int i = 0;i<binary.length;i++) {
            if (i == 0) {
                preSum[i] = 0;
            }else {
                preSum[i] = preSum[i-1] + binary[i-1];
            }
            if (binary[i] == 0) {
                list.add(i);
            }
        }
        if (list.size() == 0) return binary.length; //没有0
        //2.计算后缀和
        for (int i = binary.length - 1;i>=0;i--) {
            if (i == binary.length - 1) {
                suffSum[i] = 0;
            }else {
                suffSum[i] = suffSum[i+1] + binary[i+1];
            }
        }
        int maxLength = 1; //如果全部都是0，至少长度为1，用来记录所有0变为1连续的最大长度
        for (int i = 0;i<list.size();i++) {
            int index = list.get(i);
            int preIndex = 0; //前一个0元素所在的位置
            int nextIndex = binary.length - 1; //后一个0元素所在的位置
            if (i > 0) { //不是第一个0元素，就计算上一个0元素的位置
                preIndex = list.get(i-1);
            }
            if (i < list.size() - 1) { //不是最后一个0元素，就计算下一个0元素的位置
                nextIndex = list.get(i+1);
            }
            int preLen = preSum[index] - preSum[preIndex]; //0元素的前缀和
            int suffLen = suffSum[index] - suffSum[nextIndex]; //0元素所在的后缀和
            int zeroLenth = preLen + suffLen + 1;
            maxLength = Math.max(maxLength,zeroLenth);
        }
        return maxLength;
    }


    //将十进制数转换为二进制数组
    //方法一: 除基倒取余法  就是不断对除以2，并取出其余数(但是要注意，int值范围有限，可以用字符串来处理，另外要注意n可能还为负数)
    //TODO： 由于这个方法没有考虑到负数，因此pass
    public int[] transformBinary(int num){
        int[] bin = new int[32];
        int cons = 1;
        //每位数不断和1进行"与"运算
        for (int i = 31;i>=0;i--) {
            if ((num & cons)!=0) {
                bin[i] = 1;
            }
            cons <<= 1;
        }
        return bin;
    }

    /*
      方法二: 利用“移位”操作实现，推荐使用该方法
        1.由于计算机中存储的都是数的补码，正数的原码、反码、补码都是相同的；而负数的原码、反码、补码是不一样的，
        2.负数的补码 = 原码取反 + 1（符号位不变）。所以，负数是按照它的补码输出的
        3.注意: << 左移，低位补0相当于数字乘以2，右移高位补0，相当于除以2
        我们可以直接利用移位操作对一个十进制数进行移位，即：将最高位的数移至最低位（移31位），
    */
    public String transformBinary2(int n){
        String str = "";
        for(int i = 31;i >= 0; i--) {
            /*
             这里为啥要右移？
             */
            int num = n >>> i & 1;// >>>为逻辑移位符，向右移n位，高位补0；然后再与0000001进行与操作
            str = num + str;
            System.out.println(str.length());
            System.out.println();
            String s = new String();
        }
        return str;
    }



}
