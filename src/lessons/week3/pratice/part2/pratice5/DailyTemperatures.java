package lessons.week3.pratice.part2.pratice5;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0 每日温度
 * @Description: 给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指在第 i 天之后，
 * 才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出:[1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出:[1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 * 1 <=temperatures.length <= 105
 * 30 <=temperatures[i]<= 100
 *
 * @author: bingyu
 * @date: 2022/4/21
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result));
    }

    /**
     *
     * 核心思路:利用栈存储比后面小的元素，在比较后面元素是不断使用栈的元素下标得到的值与其比较，小于就放在answer里，大于继续向后找
     * [73,74,75,71,69,72,76,73]
     * i = 7
     * stack  [6]
     * answer [1,1,4,2,1,1]
     *
     * 推荐:和争哥的解题思路是一样的
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] answer = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i < length;i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer peek = stack.peek();
                answer[peek] = i - stack.pop();
            }
            stack.push(i);
        }
        return answer;
    }

}
