package lessons.week3.pratice.queue.pratice6;

import java.util.Stack;

/**
 * @version 1.0 有效的括号
 * @Description: 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例3：
 * 输入：s = "(]"
 * 输出：false
 *
 * 示例4：
 * 输入：s = "([)]"
 * 输出：false
 *
 * 示例5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 *
 * 提示：
 * 1 <= s.length <= 104
 * s仅由括号 '()[]{}' 组成
 *
 * @author: bingyu
 * @date: 2022/4/18
 */
public class IsValid {

    public static void main(String[] args) {
        String s = "}}";
        boolean valid = isValid(s);
        System.out.println(valid);
    }

    /** 括号连连消
     * 我的思路是，将字符遍历放入栈中，当遇到左括号记录下来，当遇到相应右括号时，将2个括号移出栈；如果遇到右括号时，左括号和右括号不匹配，说明不符合
     *  说明不对，返回false
     *  特殊情况1: 就1个字符
     *  特殊情况2: 偶数字符，但是全是左括号或者右括号
     * @param s
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        if (length % 2 != 0) return false;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') { //遇到左括号放入栈中
                stack.push(c);
            }else {
                //执行到这里说明遇到了右括号
                if (stack.isEmpty()) return false; //遇到右括号时，栈是空的说明不符合
                char leftBrack = stack.peek();
                if (leftBrack == '(' && c == ')') {
                    stack.pop();
                } else if (leftBrack == '[' && c == ']') {
                    stack.pop();
                } else if (leftBrack == '{' && c == '}') {
                    stack.pop();
                }else {
                    return false; //说明栈顶左括号和右括号不匹配
                }
            }

        }
        return stack.isEmpty();
    }




}
