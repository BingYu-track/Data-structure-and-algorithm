package stack;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @Description: 计算机四则运算原理的基本实现(由于是简单实现，所以没有添加非法验证)
 * @author: bingyu
 * @date: 2019/4/17 23:22
 */
public class RPNConvert {

    private static LinkeStack<String> stack;

    private static Map<String,Integer> map; //存储运算符优先级

    static {
        stack = new LinkeStack<String>();
        map = new HashMap<String, Integer>();
        map.put("+",0);
        map.put("-",0);
        map.put("*",1);
        map.put("/",1);
        map.put("(",-1);
        map.put(")",-1);
    }

    /**
     * 逆波兰表达式转换
     * @param expression 四则运算表达式
     * @return 返回逆波兰表达式(以空格为分割)
     */
    public static String convert(String expression){
        StringBuilder sb = new StringBuilder();//用来存储逆波兰表达式
        String[] array = getDataArray(expression);
        for (int i = 0;i<array.length;i++){
            if(isNumeric(array[i])){ //如果是数字,直接输出
                sb.append(array[i]+" ");
            }else { //如果是符号
                if(stack.isEmpty()){ //符号第一次进栈无需验证优先级，直接进栈;
                    stack.push(array[i]);
                }else {
                    //这里说明栈不为空，将当前遍历的运算和栈顶运算符进行优先级比对
                    String topData = stack.getTopData(); //获取栈顶元素值
                    int level = map.get(array[i]); //获取当前遍历运算符优先级
                    if(level != -1){ //非括号运算符优先级不高于栈顶运算符，依次出栈并输出；再将其压入栈
                        if(level <= map.get(topData) && !stack.isEmpty()){
                            while (level <= map.get(topData) && !stack.isEmpty()){
                                sb.append(stack.pop()+" ");
                            }
                            stack.push(array[i]);
                        }else {
                            stack.push(array[i]);
                        }
                    }else if(array[i].equals(")")){ //如果当前遍历元素是右括号
                        String element = null;//临时变量
                        while (!("(").equals(element)){ //将左括号和右括号包括中间运算符都出栈，并且只输出非括号运算符
                             element = stack.pop();
                             if(map.get(element) != -1){
                                 sb.append(element+" ");
                             }
                        }
                    }else {//左括号
                        stack.push(array[i]);
                    }
                }
            }
        }
        //执行到这说明表达式都遍历了一遍,将栈里剩下的运算符输出
        while (!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }
        return sb.toString();
    }

    /**
     *
     * @param RPN 逆波兰表达式
     * @return 返回运算结果
     */
    public static int operation(String RPN){
        String[] str = RPN.split(" ");
        for (int i=0;i<str.length;i++){
            if(isNumeric(str[i])){ //如果是数字，入栈
                stack.push(str[i]);
            }else { //如果是运算符，弹出两个元素并且该元素肯定是两个数字
                String num2 = stack.pop();
                String num1 = stack.pop();
                int ch = map.get(str[i]);
                if(ch == 0){ //说明该运算符为+或-
                    if("+".equals(str[i])){
                        Integer num3 = Integer.parseInt(num1) + Integer.parseInt(num2);
                        stack.push(num3.toString());
                    }else {
                        Integer num3 = Integer.parseInt(num1) - Integer.parseInt(num2);
                        stack.push(num3.toString());
                    }
                }else if(ch == 1){ //说明运算符为*或/
                    if("*".equals(str[i])){
                        Integer num3 = Integer.parseInt(num1) * Integer.parseInt(num2);
                        stack.push(num3.toString());
                    }else {
                        Integer num3 = Integer.parseInt(num1) / Integer.parseInt(num2);
                        stack.push(num3.toString());
                    }
                }
            }
        }
        //所有逆波兰表达式遍历完，意味着计算已经完成，将栈中计算的最终结果取出
        return Integer.parseInt(stack.pop());
    }


    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 将原始表达式进行初步处理
     * @param expression
     * @return
     */
    public static String[] getDataArray(String expression){
        StringBuilder sb = new StringBuilder();
        expression = expression.replace(" ", ""); //去除空格
        String[] str = new String[expression.length()];
        char[] chars = expression.toCharArray();
        int i = 0,j = 0;
        while (i < chars.length){
            if(Character.isDigit(chars[i])){
                while (i <chars.length && Character.isDigit(chars[i])){ //如果是数字，继续判断下一个字符是否是数字，直到遇到运算符为止
                    sb.append(chars[i]);
                    i++;
                }
                str[j] = sb.toString(); //将数字放入字符串数组
                sb.setLength(0); //清空字符
                j++;
            }else { //符号
                str[j] = Character.toString(chars[i]);
                i++;
                j++;
            }
        }
        String[] copyOf = Arrays.copyOf(str, j); //去除多余空间并生成新的数组
        //去空值
        return copyOf;
    }

    public static void main(String[] args){
        String str = "3*8 + ((2 + 3) * 4) - 5";
        String NPR = convert(str);
        System.out.println(NPR);
        System.out.println(operation(NPR));
    }
}
