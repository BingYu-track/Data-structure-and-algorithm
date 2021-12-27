//输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
//则输出"student. a am I"。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/ 
//
//
// 注意：此题对比原题有改动 
// Related Topics 双指针 字符串 👍 149 👎 0

package leetcode.editor.cn;
//Java：翻转单词顺序
public class FanZhuanDanCiShunXuLcof{
    public static void main(String[] args) {
        Solution solution = new FanZhuanDanCiShunXuLcof().new Solution();
        // TO TEST
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String reverseWords(String s) {
        // 这⾥只是因为Java中的String不可变
        char[] str = s.toCharArray();
        //1.先处理空格并返回处理后的字符串长度
        int n = trim(str); //⾃⼰实现的
        if (n == 0) return ""; //如果长度为0，返回""

        //2.翻转所有字符
        reverse(str, 0, n-1);

        //3.翻转每个单词
        int p = 0;
        while (p < n) {
            int r = p;
            while (r < n && str[r] != ' ') {
                r++;
            }
            //执行到这里说明r位置是空格，则需要翻转p到n-1位置的单词，然后p从r+1位置开始作为起始点
            reverse(str, p, r-1);
            p = r+1;
        }

        // 这⾥只是为了配合输出
        char[] newStr = new char[n];
        for (int i = 0; i < n; ++i) {
            newStr[i] = str[i];
        }
        return new String(newStr);
    }



    public int trim(char[] str) {
        int n = str.length;
        // 跳过⾸空格
        int i = 0;
        while (i < n && str[i] == ' ') {
            i++;
        }
        // 跳过尾空格
        int j = n-1;
        while (j >= 0 && str[j] == ' ') {
            j--;
        }
        //清除内部多于空格，并且把i~j之间的字符搬移到数组最前⾯
        //处理思路: 将i和j之间的元素一个个移动到右边，如果遇到字母，直接移动，如果遇到空格，如果只有一个空格就移动，如果有多个空格
        int k = 0;
        while (i <= j) { //因为要将i和j之间的元素一个个移动到右边，所以需要移动j-i次
            if (str[i] == ' ') { //因为执行到这里的i和j之间是没有首位空格的，i和j中间如果再遇到空格，就是单词中间的空格
                if (i+1<=j && str[i+1] != ' ') { //如果遇到了空格，但是后面位置不是空格就把该位置的空格移动到右变，如果后面还有空格就跳过
                    str[k++] = ' ';
                }
            } else {
                str[k++] = str[i];
            }
            i++;
        }
        return k;
    }

    public void reverse(char[] arr,int first,int last) {
        if (last < first) return;
        int length = last - first + 1; //得到指定要翻转的子段的长度
        //从first开始，互换first + length/2次，
        for (int i = first,j = 0; i<(first + length/2);i++,j++) {
            char temp = arr[i];
            arr[i] = arr[first + length - 1 - j];
            arr[first + length - 1 - j] = temp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}