package lessons.week3.pratice.stack.pratice2;

import java.util.Stack;

/**
 * @version 1.0
 * @Description: 基本计算器 III 争哥解法
 * @author: bingyu
 * @date: 2022/4/19
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "(2+6*3+5-(3*14/7+2)*5)+3"; //10
        int calculate = calculate(s);
        System.out.println(calculate);
    }

    /**
     * 争哥解法；和我基本一样，不过争哥的写法更容易让人理解
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int i = 0;
        int n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ') { // 跳过空格
                i++;
            } else if (isDigit(c)) { //数字
                int number = 0;
                while (i < n && isDigit(s.charAt(i))) { //遇到连续的数字
                    number = number*10+(s.charAt(i)-'0');
                    i++;
                }
                nums.push(number);
            } else if (c == '(') { //遇到左括号，直接存入字符栈
                ops.push(c);
                i++;
            } else if (c == ')') { //遇到右括号，开始计算，并将计算结果存入数字栈，重复执行，直到遇到栈顶字符为左括号为止，并且还要弹出左括号
                while (!ops.isEmpty() && ops.peek() != '(') {
                    fetchAndCal(nums, ops);
                }
                ops.pop(); // 弹出'('
                i++;
            } else { //TODO: 遇到非括号的运算符，字符栈为空或者字符栈顶元素是左括号，运算符直接存入字符栈
                if (ops.isEmpty() || prior(c, ops.peek())) {
                    ops.push(c);
                } else {
                    while (!ops.isEmpty() && !prior(c, ops.peek())) { //栈顶运算符优先级大于当前运算符，开始运算
                        fetchAndCal(nums, ops);
                    }
                    ops.push(c);
                }
                i++;
            }
        }
        while (!ops.isEmpty()) {
            fetchAndCal(nums, ops);
        }
        return nums.pop();
    }

    //计算
    private static void fetchAndCal(Stack<Integer> nums, Stack<Character> ops) {
        int number2 = nums.pop();
        int number1 = nums.pop();
        char op = ops.pop();
        int ret = cal(op, number1, number2);
        nums.push(ret);
    }

    private static int cal(char op, int number1, int number2) {
        switch(op) {
            case '+': return number1+number2;
            case '-': return number1-number2;
            case '*': return number1*number2;
            case '/': return number1/number2;
        }
        return -1;
    }

    //比较运算符优先级
    private static boolean prior(char a, char b) {
        if ((a == '*' || a == '/')
                && (b == '+' || b == '-')) {
            return true;
        }
        if (b == '(') return true;
        return false;
    }

    //判断是否是数字
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

}
