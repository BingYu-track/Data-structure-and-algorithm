//给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。 
//
// 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello World"
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：s = "   fly me   to   the moon  "
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：s = "luffy is still joyboy"
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅有英文字母和空格 ' ' 组成 
// s 中至少存在一个单词 
// 
// Related Topics 字符串 👍 407 👎 0

package leetcode.editor.cn;
//Java：最后一个单词的长度
public class LengthOfLastWord{
    public static void main(String[] args) {
        Solution solution = new LengthOfLastWord().new Solution();
        // TO TEST
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        //处理空格，并返回处理空格后新字符串的长度
        int n = trim(chars);
        if (n == 0) return 0;

        //遍历每个单词
        int i = n - 1;
        while (i>=0 && chars[i] != ' ') {
            i--;
        }
        //执行到这里说明遇到了最后单词的首空格
        int lastWordLength = n - 1 - i;
        return lastWordLength;
    }

    public int trim(char[] chars) {
        int length = chars.length;
        int i = 0,j=length - 1;
        //1.去除首尾空格
        while (i < length && chars[i] ==' ') {
            i++;
        }
        while (j >= 0 && chars[j] ==' ') {
            j--;
        }

        //2.处理中间空格
        int k = 0;
        while (i<=j) { //"1 2 34"  1
            if (chars[i] ==' ') { //如果遇到空格
                while (i+1<=j && chars[i+1] == ' ') { //判断后面还有没有空格
                    i++;
                }
                //执行到这说明i+1不再是空格，因此i是最后一个空格
                chars[k] = chars[i];
            }else {
                //非空格
                chars[k] = chars[i];
            }
            k++;
            i++;
        }
        return k;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}