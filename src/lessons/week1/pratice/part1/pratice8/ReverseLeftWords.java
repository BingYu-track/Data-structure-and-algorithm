package lessons.week1.pratice.part1.pratice8;

import lessons.util.Util;

/**
 * @version 1.0 左旋转字符串
 * @Description: 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 *              比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出:"cdefgab"
 *
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出:"umghlrlose"
 *
 * 限制：
 * 1 <= k < s.length <= 10000
 *
 * @author: bingyu
 * @date: 2021/12/24
 */
public class ReverseLeftWords {

    public static void main(String[] args) {
        String str = "abcdefg"; //
        String s = reverseLeftWords(str, 2);
        //String s = reverseLeftWords2(str, 6);
        System.out.println(s);
    }


    //我的解法1: 思路，先整个进行翻转，然后将前后2部分字符串每段进行翻转即可
    public static String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (n >= length) return s;
        //如果要处理的字符长度小于字符串长度的一半，将其一半进行首尾互换，否则可能会导致只有一小段
        //例如: abcdefg，只处理2个的话互换会变成gfcdeba，其中"cde"没有翻转导致后面无法进行整段翻转，很难处理

        //1.将整个字符串进行一次翻转
        for (int i = 0;i<length/2;i++) {
            char temp;
            temp = chars[i];
            chars[i] = chars[length-1-i];  //将当前数字和倒数的数字进行交换
            chars[length-1-i] = temp;
        }

        //2.然后将两端0~(length-1-n)和(length-n)~(length-1)进行翻转即可
        Util.reversePart(chars,0,length- n -1); //因为交换了n次
        Util.reversePart(chars,length-n,length - 1);
        return String.valueOf(chars);
    }



    //我的解法2: 效率很低，需要一个一个进行移动
    public static String reverseLeftWords2(String s, int n) {
        char[] chars = s.toCharArray();
        for (int i = 0;i<n;i++) {
            move(chars,0,chars.length - 1);
        }
        return String.valueOf(chars);
    }

    /**
     * 将字符数组里的指定下标的字符移动到目标位置
     * @param chars
     * @param self 当前要移动的位置
     * @param target 目标位置
     * @return
     */
    public static void move(char[] chars,int self,int target) {
        for (int i = self; i<target;i++) {
            char temp;
            temp = chars[i];
            chars[i] = chars[i+1];
            chars[i+1] = temp;
        }
    }


}
