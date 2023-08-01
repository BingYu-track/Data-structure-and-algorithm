package lessons.week12.psstatistics.pratice.pratice4;

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
        int num = -2;
        int res = rb.reverseBits(num);
        System.out.println(res);

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
    public int reverseBits(int num) {
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
