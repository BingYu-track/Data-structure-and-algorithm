package lessons.week3.pratice.part2.pratice2;

import java.util.Stack;

/**
 * @version 1.0 基本计算器 III
 * @Description: 实现一个基本的计算器来计算简单的表达式字符串。
 *
 * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 。整数除法需要 向下截断 。
 * 你可以假定给定的表达式总是有效的。所有的中间结果的范围为 [-2^31, 2^31-1] 。
 *
 *
 *
 * 示例 1：
 * 输入：s = "1+1"
 * 输出：2
 *
 * 示例 2：
 * 输入：s = "6-4/2"
 * 输出：4
 *
 * 示例 3：
 * 输入：s = "2*(5+5*2)/3+(6/2+8)"
 * 输出：21
 *
 * 示例 4：
 * 输入：s = "(2+6*3+5-(3*14/7+2)*5)+3"
 * 输出：-12
 *
 * 示例 5：
 * 输入：s = "0"
 * 输出：0
 *
 * 提示：
 * 1 <= s <= 104
 * s 由整数、'+'、'-'、'*'、'/'、'(' 和 ')' 组成
 * s 是一个 有效的 表达式
 *
 * 进阶：你可以在不使用内置库函数的情况下解决此问题吗？
 *
 * @author: bingyu
 * @date: 2022/4/19
 */
public class CalculateIII {

    public static void main(String[] args) {
        String s = "(2+6*3+5-(3*14/7+2)*5)+3"; //10
        int calculate = calculate(s);
        System.out.println(calculate);
    }

    //核心思想: 同样需要2个栈,栈A存数字，栈B存运算符；
    //  1.当栈B栈顶的运算符优先级大于等于当前遍历运算符的优先级，就去除栈顶运算符开始运算，直到优先级小于当前遍历的运算符，或者栈顶运算符是左括号时停止
    //  2.当遇到右括号时，将运算符取出开始运算直到栈顶元素是左括号时停止
    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        char[] chars = s.toCharArray();
        int length = s.length();
        for (int i = 0;i<length;i++) {
            char c = chars[i];
            if (isDigit(c)) {
                int num = c - '0';
                while (i+1<length && isDigit(chars[i+1])) {
                    num = num * 10 + (chars[i+1] - '0');
                    i++;
                }
                numStack.push(num);
            }else {
                //1.栈顶运算符不为左括号，而且
                //2.栈顶运算符优先级大于等于当前遍历运算符的优先级或者当前遍历运算符为右括号开始运算，直到栈顶为左括号为止
                while (!opStack.isEmpty() && opStack.peek() != '('
                        && ((chars[i] !='(' && privo(opStack.peek()) >= privo(chars[i]))
                        || chars[i] == ')')) {
                    char op = opStack.pop(); //获取运算符
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    int result = calculate(num1,num2,op);
                    numStack.push(result);
                }
                if (chars[i] != ')') { //如果当前遍历的不是右括号，直接存进去
                    opStack.push(chars[i]);
                }else {
                    //执行到这说明遇到了右括号,由于上面while循环遇到栈顶为左括号就停止，因此这里符号栈的栈顶应该就是左括号
                    opStack.pop(); //移出左括号
                }
            }
        }
        while (!opStack.isEmpty() && opStack.peek() != '(') {
            char op = opStack.pop(); //获取运算符
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            int result = calculate(num1,num2,op);
            numStack.push(result);
        }
        return numStack.pop();
    }

    private static int calculate(int num1, int num2, char op) {
        if (op == '*') {
            return num1 * num2;
        }else if (op == '/') {
            return num1 / num2;
        }else if (op == '+') {
            return num1 + num2;
        }else if (op == '-') {
            return num1 - num2;
        }
        return 0;
    }

    //是否是数字
    private static boolean isDigit(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

    //获取运算符优先级
    private static int privo(char c) {
        if (c == '*' || c == '/') return 1;
        if (c == '+' || c == '-') return 0;
        return -1;
    }


}
