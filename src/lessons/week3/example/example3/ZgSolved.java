package lessons.week3.example.example3;

import java.util.Stack;

/**
 * @version 1.0
 * @Description: 计算器
 * @author: bingyu
 * @date: 2022/4/11
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "1+2*5-3+6/4*2"; //1+3+2=6
        int number = calculate(s);
        System.out.println(number);
    }

    /**
     * TODO 核心逻辑：1.遇到数字直接入数字栈,如果数字字符后面是连续的数字，要将其合并成一个数字再入栈
     *              2.遇到运算符，
     *                  (1).如果运算符栈是空，直接入栈。
     *                  (2).如果运算符栈不为空，拿出运算符栈的栈顶元素t(也就是前面一个运算符)，与当前运算符进行比较，如果当前运算符优先级大于t，
     *                     则当前运算符直接入栈，等后面的数字来临后再进行运算；如果当前运算符优先级小于等于t，则弹出栈顶的运算符开始进行运算，
     *                     这是一个不断循环的过程，每次运算都需要将t和当前运算符作比较，直到t优先级大于当前运算符，或者栈为空时停止！
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<Integer> nums = new Stack<>(); //数字栈
        Stack<Character> ops = new Stack<>(); //运算符栈
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i); //获取当前字符
            if (c==' ') { //去除空格
                i++;
                continue;
            }
            if (isDigit(c)) { //数字
                int number = 0; //记录多位数字
                while (i < n && isDigit(s.charAt(i))) { //只要是数字，就不断合并计算
                    number = number * 10 + (s.charAt(i)-'0');
                    i++;
                }
                nums.push(number);
            }else { //运算符
                if (ops.empty()) {
                    ops.push(c);
                }else {
                    //运算符栈不为空，比较运算符栈的栈顶元素
                    if (privo(c,ops.peek())) { //比栈顶运算符优先级高，存进栈中
                        ops.push(c);
                    }else { //优先级小于等于栈顶运算符，开始进行计算
                        while (!ops.isEmpty() && !privo(c,ops.peek())) { //不断将当前运算符和栈顶运算符作比较。优先级低就不断计算
                            int result = calculate(nums.pop(),nums.pop(),ops.pop());
                            nums.push(result);
                        }
                        ops.push(c);
                    }
                }
                i++;
            }
        }

        while (!ops.isEmpty()) {
            int result = calculate(nums.pop(), nums.pop(), ops.pop());
            nums.push(result);
        }
        return nums.pop();
    }

    private static int calculate(Integer numSecond, Integer numFirst, Character op) {
        int result = 0;
        if (op == '+') {
            result = numFirst + numSecond;
        }else if (op == '-') {
            result = numFirst - numSecond;
        }else if (op == '*') {
            result = numFirst * numSecond;
        }else {
            result = numFirst / numSecond;
        }
        return result;
    }

    /**
     * 判断优先级
     * @param current 当前运算符
     * @param last 上一个运算符
     * @return
     */
    private static boolean privo(char current,char last) {
        if ((current == '*' || current == '/') && (last == '+' || last == '-')) {
            return true;
        }
        return false;
    }

    //判断字符是否是数字
    private static boolean isDigit(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

}
