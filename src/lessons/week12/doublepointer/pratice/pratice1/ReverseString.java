package lessons.week12.doublepointer.pratice.pratice1;

import java.util.Arrays;

/**
 * @version 1.0  反转字符串
 * @Description: 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组s的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 示例 1：
 * 输入：s = ['h','e','l','l','o']
 * 输出：['o','l','l','e','h']
 *
 * 示例 2：
 * 输入：s = ['H','a','n','n','a','h']
 * 输出：['h','a','n','n','a','H']
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 都是 ASCII 码表中的可打印字符
 *
 * @author: bingyu
 * @date: 2023/7/12
 */
public class ReverseString {

    public static void main(String[] args) {
        ReverseString rs = new ReverseString();
//        char[] s = {'H','a','n','n','a','h'};
        char[] s = {'h'};
        rs.reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    /*
     使用双指针，两两交换即可
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：49.4 MB, 在所有 Java 提交中击败了45.70%的用户
    */
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;
        while (i<j) {
            swap(s,i,j); //交换元素
            i++;
            j--;
        }
        return;
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }


}
