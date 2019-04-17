package stack;

import com.sun.imageio.plugins.common.I18N;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @Description: 逆波兰还原计算机四则运算原理
 * @author: bingyu
 * @date: 2019/4/17 23:22
 */
public class RPNConvert {

    private static LinkeStack<String> stack;

    static {
        stack = new LinkeStack<String>();
    }

    /**
     *
     * @param expression 四则运算表达式
     * @return 返回逆波兰表达式
     */
    public static String convert(String expression){
        StringBuilder sb = new StringBuilder();//用来存储逆波兰表达式
        String[] array = getDataArray(expression);
        for (int i = 0;i<array.length;i++){
            if(isNumeric(array[i])){ //如果是数字，添加
                sb.append(array[i]);
            }else { //这里说明是符号

            }
        }
        return "";
    }

    /**
     *
     * @param RPN 逆波兰表达式
     * @return 返回运算结果
     */
    public static int operation(String RPN){

        return 0;
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
        return str;
    }

    public static void main(String[] args){
        String str = "9+(3-1)*3+10/2";
        String[] array = getDataArray(str);
        System.out.println(Arrays.asList(array));
    }
}
