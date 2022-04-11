package lessons.week3.example.example2;

import java.util.Stack;

/**
 * @version 1.0 删除连续重复字符
 * @Description: 字符串删除掉连续的3个重复的字符。比如"abbbc"，返回"ac";“abbbaac”，返回"c"
 * 举例: a b b a a a b c c
 * 这题就是一道连连消的题目！
 * @author: bingyu
 * @date: 2022/4/7
 */
public class RemoveDuplicatedChar {

    public static void main(String[] args) {
        String result = removeDuplicatedChar("abbaaabcc", 3);
        System.out.println(result);
    }


    /**
     *  TODO 核心思想:用栈存储不能消除的元素，并且还要记录栈中每个元素连续重复的次数，一旦重复次数到了3，就将该元素出栈，因此我们只需要2个栈，
     *      一个栈记录字符，一个栈用来记录同等位置的连续次数
     * @param str
     * @param k 规定删除连续k次的字符
     * @return
     */
    public static String removeDuplicatedChar(String str,int k) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;i++) {
            char item = chars[i]; //获取字符
            if (!stack.isEmpty() && stack.peek() == item) { //如果当前字符和栈顶元素字符相同，则将连续次数加1
                Integer count = countStack.pop();
                count++;
                if (count < k) {
                    countStack.push(count); //小于k次才重新放进栈
                }else {
                    stack.pop(); //大于等于k次，将栈顶元素弹出
                }
            }else {
                //执行到这说明和栈顶元素不相同
                stack.push(item);
                countStack.push(1);
            }
        }
        StringBuilder sb = new StringBuilder();
        //开始组装删除后的字符串
        while (!stack.isEmpty()) {
            char item = stack.pop(); //获取字符
            Integer count = countStack.pop(); //获取字符重复次数
            for (int i=0;i<count;i++) {
                sb.insert(0,item);
            }
        }
        return sb.toString();
    }

}
