package lessons.week3.pratice.stack.pratice3;

import java.util.Stack;

/**
 * @version 1.0 删除字符串中的所有相邻重复项
 * @Description: 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，
 *      其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *
 *
 * 提示：
 * 1. 1 <= S.length <= 20000
 * 2. S仅由小写英文字母组成
 *
 * @author: bingyu
 * @date: 2022/4/20
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        String s = "abbdddca";
        String s1 = removeDuplicates(s);
        System.out.println(s1);
    }


    /* 注意: 只消除2个相邻的字符，有3个连续的话删除2个即可
     * 核心思路: [c,a]
     */
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0;i<chars.length;i++) {
            if (!stack.isEmpty() && stack.peek() == chars[i]) { //遍历字符的同时，不断让字符和栈顶字符作比较，相同就弹出即可!
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0,stack.pop());
        }
        return sb.toString();
    }
}
