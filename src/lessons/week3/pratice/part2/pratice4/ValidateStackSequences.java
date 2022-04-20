package lessons.week3.pratice.part2.pratice4;

import java.util.Stack;

/**
 * @version 1.0 栈的压入、弹出序列
 * @Description: 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 *         例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 * 提示：
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed是popped的排列
 *
 * @author: bingyu
 * @date: 2022/4/20
 */
public class ValidateStackSequences {

    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {1,2,5,4,3}; //4,5,3,1,2
        boolean result = validateStackSequences(pushed, popped);
        System.out.println(result);
    }

    /* [1,2,3] [5]
     * pushed = [1,2,3,4,5]
     * pushed: 1[pop]2[pop]3[pop]4[pop]5[pop] ---每个元素之间都可能进行pop操作
     * popped: 4 5 3 2 1
     * popped: 4,3,5,1,2
     *
     * 核心思路: 根据popped遍历的元素，去pushed数组找到对应位置，然后随着后面popped的遍历，对应的只要是相邻的元素就可以消掉，表示可以
     * 弹出栈，但是如果是不相邻的，说明这个出栈顺序就是错的！
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) { //[1,2,5,4,3]
        Stack<Integer> stack = new Stack<>(); //[]  [5,4,3] 5
        Stack<Integer> tmp = new Stack<>();
        for(int i = 0;i<pushed.length;i++) {
            stack.push(pushed[i]);
        }
        int i = 0;
        while (stack.peek() != popped[i]) {  //在栈中找弹出的一个元素，
            tmp.push(stack.pop());
        }
        //执行到这里说明找到了,i向后移动一位
        i++;
        stack.pop();
        //获取pop的第一个元素
        while (!stack.isEmpty() || !tmp.isEmpty()) { //在栈中找pop的元素
            int pop = popped[i];
            if (!stack.isEmpty() && stack.peek() == popped[i]) { //如果栈顶遇到了
                stack.pop();
                i++;
            }else if(!tmp.isEmpty() && tmp.peek() == popped[i]){ //如果临时栈遇到了
                tmp.pop();
                i++;
            }else {
                return false;
            }
        }
        return true;
    }

}
