package lessons.week3.pratice.stack.pratice1;

import java.util.Stack;

/**
 * @version 1.0 争哥解法
 * @Description: 计算器
 * @author: bingyu
 * @date: 2022/4/19
 */
public class ZgSolved {

    public static void main(String[] args) {
        String expre = "6*3+2*2-20*5/6"; //22-16=6
        int calculate = calculate(expre);
        System.out.println(calculate);
    }

    /**
     * TODO: 争哥的解法和我相同: 也是数字字符进数字栈。运算符进字符栈；当遍历到运算符时，如果字符栈是空的直接存入，不为空，则判断当前运算符优先级
     *       是否高于运算符栈的栈顶元素，高的话，运算符栈栈顶元素先不计算，把当前遍历的优先级高的存进去；如果优先级小于等于栈顶，则把栈顶的运算符
     *       取出并计算,然后把运算的结果存入数字栈;然后不断重复，直到字符栈的栈顶运算符优先级小于当前遍历的运算符；如果小于就将当前遍历的运算符直接存入字符栈即可。遍历完
     *       后会发现字符栈里只会有一个运算符了，再进行运算即可！
     *       1.要注意连续数字
     *       2.要去除空格
     *       3.注意数组越界问题
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c == ' ') continue; //去除空格
            if (isDigit(c)) { //如果是数字
                int num = c - '0';
                while ((i+1) < s.length() && isDigit(s.charAt(i+1))) { //后面还是数字(注意不要数组越界)
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                numStack.push(num);
            }else {
                //这里都是运算符
                if (opStack.isEmpty()) {
                    opStack.push(c);
                }else {
                    //比较栈顶运算符和当前运算符优先级，如果栈顶的优先级大于等于
                    while (!opStack.isEmpty() && !prior(opStack.peek(),c)) {
                        char op = opStack.pop();
                        int num2 = numStack.pop();
                        int num1 = numStack.pop();
                        int result = calculate(num1,num2,op);
                        numStack.push(result);
                    }
                    opStack.push(c); //将运算符存入栈中
                }
            }
        }
        while (!opStack.isEmpty()) {
            char op = opStack.pop();
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            int result = calculate(num1,num2,op);
            numStack.push(result);
        }
        return numStack.peek();
    }

    private static int calculate(int num1, int num2, char op) {
        int result = 0;
        if (op == '+') {
            result = num1 + num2;
        } else if (op == '-') {
            result = num1 - num2;
        } else if (op == '*') {
            result = num1 * num2;
        } else if (op == '/') {
            result = num1 / num2;
        }
        return result;
    }

    /**
     * 比较c1栈顶字符是否比c2字符优先级低！
     * @param c1
     * @param c2
     * @return
     */
    private static boolean prior(char c1,char c2) {
        if ((c2 == '*' || c2 == '/') && (c1 == '+' || c1 == '-')) {
            return true;
        }
        return false;
    }

    //判断字符是否是数字
    private static boolean isDigit(char c) {
        if (c >='0' && c<='9') {
            return true;
        }
        return false;
    }

}
