package lessons.week3.pratice.part2.pratice5;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: 每日温度--争哥解法
 * @author: bingyu
 * @date: 2022/5/6
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result)); //[1, 1, 4, 2, 1, 1, 0, 0]
    }//[]

    /**复习
     * TODO: 争哥的解法和我是一样的，独立做比较难做出来，需要多复习
     *     利用栈存储前一个元素，在遍历到后面的元素时用栈顶元素进行比较，栈顶小的就存入结果数组，直到栈顶元素不小于，就将当前元素也放入栈中，继续向后遍历
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> maxStack = new Stack<>(); //用来存比后面小的元素
        int length = temperatures.length;
        int[] result = new int[length];
        for (int i = 0;i<length;i++) {
            int temperature = temperatures[i];
            while (!maxStack.isEmpty() && temperatures[maxStack.peek()] < temperature) { //栈顶元素比后面的小，开始将结果存入结果数组
                result[maxStack.peek()] = i - maxStack.pop();
            }
            maxStack.push(i); //栈为空会直接进来
        }
        return result;
    }

}
