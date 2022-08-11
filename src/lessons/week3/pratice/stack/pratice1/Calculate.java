package lessons.week3.pratice.stack.pratice1;

import java.util.Stack;

/**
 * @version 1.0 计算器
 * @Description: 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格。 整数除法仅保留整数部分。
 *
 * 示例1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval
 *
 * @author: bingyu
 * @date: 2022/4/19
 */
public class Calculate {

    public static void main(String[] args) {
        String expre = "6*3+2*2-20*5/6";
        int calculate = calculate(expre);
        System.out.println(calculate);
    }

    /**[22,16]  [-]
     * 核心思路: 使用2个栈,栈A存数字，栈B存运算符，当遇到运算符时，栈B栈顶的运算符优先级大于等于后面遇到的运算符时，开始拿出栈顶运算符不断的进行计算
     *          直到栈顶运算符优先级小于当前遍历的运算符；反之，如果栈顶运算符优先级小于等于当前遍历的运算符，则直接把当前运算符存进栈B中。
     *  1.另外要注意的是如果遇到连续的数字字符，需要组合成数字
     *  2.要去除空格
     * @param s
     * @return
     */
    public static int calculate(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        for (int i = 0;i<length; i++) {
            char c = chars[i];
            if (c == ' ') continue; //排除空格
            if(isDigit(c)) { //数字
                int num = c - '0'; //20
                while (i+1 < length && isDigit(chars[i+1])) { //如果后面的字符也是数字
                    num = num * 10 + (chars[i+1] - '0');
                    i++;
                }
                numStack.push(num);
            }else {
                //运算符
                while (!opStack.isEmpty() && getOpPrivo(opStack.peek()) >= getOpPrivo(c)) { //如果栈顶运算符优先级大于遍历运算符优先级
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    char oper = opStack.pop();
                    int result = calculate(num1,num2,oper);
                    numStack.push(result);
                }
                opStack.push(chars[i]);
            }
        }
        //执行完后运算符栈还会剩下一个运算符
        while (!opStack.isEmpty()) {
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            char oper = opStack.pop();
            int result = calculate(num1,num2,oper);
            numStack.push(result);
        }
        return numStack.pop();
    }

    //计算
    private static int calculate(int num1, int num2, char oper) {
        if (oper == '+') {
            return num1 + num2;
        }else if (oper == '-') {
            return num1 - num2;
        }else if (oper == '*') {
            return num1 * num2;
        }else if (oper == '/') {
            return num1 / num2;
        }
        return 0;
    }

    //获取运算符的优先级
    private static int getOpPrivo(char op) {
        if (op == '*' || op == '/') return 1;
        return 0;
    }


    //判断是否是数字
    private static boolean isDigit(char c) {
        if (c >= '0' && c<= '9') {
            return true;
        }
        return false;
    }

}
