package lessons.week3.example.example4;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0 每日温度
 * @Description: 给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指在第i天之后，
 * 才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0来代替。是求过了多少天气温变高
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
 * 提示：
 * 1 <=temperatures.length <= 105
 * 30 <=temperatures[i]<= 100
 *
 * @author: bingyu
 * @date: 2022/4/7
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] arr = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(arr)); //[1, 1, 4, 2, 1, 1, 0, 0]
    }

    /**
     * 单调栈解法: 推荐，需要重复练习，逻辑比较难！
     * TODO 核心思路--使用栈来记录元素所在的数组索引，栈为空放入温度元素的索引，遍历温度时不断与栈顶的温度进行比较，大于栈顶温度的，就计算结果，
     *      直到栈没有元素或者栈顶温度小于当前温度；
     * 时间复杂度为O(n)
     * @param temperatures 73,74,75,71,69,72,76,73
     * @return  75 2 ,71 3,76 6; 73 7
     * 1,1
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); //存储温度对应索引的栈
        for(int i = 0;i<n;i++) {
            //不为空，比较栈顶元素对应的温度，如果小于栈顶的温度，则需要不断向后选择大于栈顶的温度
            //TODO：注意这里循环是由栈的元素数量决定的，因为我们可能会遇到小于的温度，因此我们要将小的温度也要放在栈里
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                Integer index = stack.pop(); //弹出栈顶温度索引
                result[index] = i - index; //注意这行代码，这里因为栈存的是之前温度的索引，虽然我们这里存入不是按顺序的，但是不会影响到前面的元素
            }
            stack.push(i);
        }
        return result;
    }


    //2.暴力解法 73,74,75,71,69,72,76,73
    //思路: 遍历期间，将下一个数字与当前数字比较，大的话就将其所在索引差存入结果数组
    //缺点:耗时很长，时间复杂度为O(n^2)
    public static int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        for (int i = 0;i<n;i++) {
            for (int j = i+1;j<n;j++) {
                if (temperatures[j] > temperatures[i]) { //比较前面和当前元素大小
                    result[i] = j - i;
                    break; //一旦满足条件就停止内层循环，否则还会不断向后找比当前元素大的
                }
            }
        }
        return result;
    }


}
