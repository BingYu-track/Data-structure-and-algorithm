package lessons.week3.example.example3;

import java.util.Stack;

/**
 * @version 1.0 计算器(很难，不容易，建议后面复习)
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
 * 请不要使用内置的库函数 eval。
 *
 * @author: bingyu
 * @date: 2022/4/7
 */
public class Calculate {

    //1 1 1
    //- +
    public static void main(String[] args) {
        String s = "1+2*5/3+6/4*2"; //1+3+2=6
        int number = calculate2(s);
        System.out.println(number);
    }

    /**
     * 该题需要不断复习编写
     * TODO: 争哥解法思路
     */
    public static int calculate(String s) {

        return 0;
    }

    /** TODO: 特殊情况1-表达式里可能带空格，记住要去除空格。 特殊情况2: 计算的数字可能是多位的
     * 我的解法:
     * 解题思路1(不考虑括号):将正则运算表达是通过栈转换成逆波兰表达式，即可运算,具体逻辑是将字符遍历存入栈中，如果遇到
     * 解题思路2: 使用2个栈，一个栈A用来存数字，另一个栈B用来存运算符，当存入字符时要看下该运算符的优先级，如果运算符的优先级是乘除，则等到遍历
     *          后面的数字再进行乘数运算；如果出入的运算符是加减运算，当前就需要进行运算数字运算！
     *
     *
     * @param s
     * @return
     */
    public static int calculate2(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stackNumber = new Stack<Integer>(); //数字栈
        Stack<Character> stackOp = new Stack<Character>(); //运算符栈
        boolean flag = false; //用来判断需不需要运算
        int number = 0; //用来记录数字
        for (int i = 0;i<chars.length;i++) {
            char item = chars[i];
            if (item == ' ') continue; //去除空格
            if (isDigist(item)) { //遇到数字
                number = number * 10 + (item - '0');
                if (i+1 <chars.length && isDigist(chars[i+1])) { //如果下一个字符仍然是数字，说明数字不是个数，需要进行合并数字，并跳过后面的逻辑
                    continue;
                }
                if (flag) { //执行到这里说明之前的运算符是*或者/需要和当前数进行运算
                    char op = stackOp.pop();
                    Integer numberOne = stackNumber.pop();
                    number = calculateNumber(numberOne, number, op);
                    flag = false; //计算完就重置
                }
                stackNumber.push(number);
                number = 0; //重置数字
            } else { //遇到符号
                int level = getLevel(item); //获取当前运算符的优先级
                if (level == 1) { //如果当前运算符优先级是乘除运算符，则在下一个数字进行计算
                    flag = true;
                }else if(!stackOp.isEmpty()){
                    //执行到这里说明当前运算符是加减运算，当前就需要进行运算
                    char op = stackOp.pop();
                    Integer numberTwo = stackNumber.pop();
                    Integer numberOne = stackNumber.pop();
                    int result = calculateNumber(numberOne, numberTwo, op);
                    stackNumber.push(result);
                }
                stackOp.push(item);
            }
        }
        //执行到这里说明计算只剩最后一步
        while (!stackOp.isEmpty()) {
            char op = stackOp.pop();
            Integer numberTwo = stackNumber.pop();
            Integer numberOne = stackNumber.pop();
            int result = calculateNumber(numberOne, numberTwo, op);
            stackNumber.push(result);
        }
        return stackNumber.pop();
    }

    //获取运算符的优先级
    public static int getLevel(char op) {
        if (op == '+' || op == '-') {
            return 0;
        } else if (op == '*' || op == '/') {
            return 1;
        }
        return -1;
    }

    /**
     * 运算数字
     * @param num
     * @param number
     * @param op
     * @return
     */
    public static int calculateNumber(int num, int number,char op) {
        int result = 0;
        if(op == '+') {
            result = num + number;
        } else if (op == '-')  {
            result = num - number;
        } else if (op == '*') {
            result = num * number;
        } else {
            result = num / number;
        }
        return result;
    }

    //判断字符是否是数字
    public static boolean isDigist(char c) {
        if (c < '0' || c >'9') {
            return false;
        }
        return true;
    }



}
