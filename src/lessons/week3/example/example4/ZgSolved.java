package lessons.week3.example.example4;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: 每日温度
 * @author: bingyu
 * @date: 2022/4/11
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] arr = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(arr)); //[1, 1, 4, 2, 1, 1, 0, 0]
    }

    //解法1：暴力解决法，遍历元素，一旦遇到后面比当前元素高的，就将后面元素的索引减去当前元素的索引得到天数，再将其存入数组中
    /**
     * 争哥解法2 -- TODO 核心思路；使用单调栈，在栈里存数组元素的索引，如果栈为空，直接把元素所在的索引存进栈里，当遍历到下一个元素时，将其元素和栈顶元素比较，如果
     *               栈顶元素值小于当前元素值，说明气温升高了，需要将当前元素索引-栈顶元素索引并存入数组中，直到小于栈顶元素位置。就这样不断操作即可！
     * @param temperatures 73,74,75,71,69,72,76,73
     *              73 7
     *         stack: [6,7]
     * @return nums : [1,1,4,2,1,1,0,0]
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] nums = new int[n];
        for (int i = 0;i<n;i++) {
            int temp = temperatures[i];
            //TODO: 这里的精髓就在于，当元素一直大于栈顶元素的话，就会不断的循环处理
            while (!stack.isEmpty() && temperatures[stack.peek()] < temp) { //当前元素温度大于栈顶元素温度
                Integer index = stack.pop(); //栈顶元素索引
                nums[index] = i - index; //当前元素索引-栈顶元素索引
            }
            stack.push(i);
        }
        return nums;
    }

}
