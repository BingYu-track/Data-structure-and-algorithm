package lessons.week3.pratice.queue.pratice6;

import java.util.Stack;

/**
 * @version 1.0
 * @Description: 有效的括号--争哥解法
 * @author: bingyu
 * @date: 2022/4/18
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "}}";
        boolean valid = isValid(s);
        System.out.println(valid);
    }

    //争哥解法思路和我是一模一样的！
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else { //右括号
                if (stack.empty()) return false;
                char popC = stack.pop();
                if (c == ')' && popC != '(') {
                    return false;
                }
                if (c == ']' && popC != '[') {
                    return false;
                }
                if (c == '}' && popC != '{') {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
