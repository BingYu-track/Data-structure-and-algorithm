package lessons.week1.pratice.part1.pratice2;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。不要给另外的数组分配额外的空间，
 * 你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 示例 1：
 *
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 示例 2：
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 *
 * @author: bingyu
 * @date: 2021/12/20
 */
public class ReverseString {

    public static void main(String[] args) {
        char [] c = {'h','e','g','l','o'}; //olgeh
        reverseString(c);
        System.out.println(Arrays.toString(c));
    }

    //我的解法:
    public static void reverseString(char[] s) {
        char temp = '0';
        for (int i = 0; i<(s.length/2);i++) { //执行到1半即停止
            swap(i,s.length - 1 - i,s,temp); //首位互换
        }
    }

    public static void swap(int f,int l,char[] s,char temp) {
        temp = s[f];
        s[f] = s[l];
        s[l] = temp;
    }

    //双指针解法：
    public static void reverseString2(char[] s) {
        int length = s.length;
        int i = 0; //i指向第一个位置的元素
        int j = length - 1; //j指向最后一个位置的元素
        while (i <=j ) { //i不断自增，j不断相减，期间2变量数值交换，直到都到同一个位置为止
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
