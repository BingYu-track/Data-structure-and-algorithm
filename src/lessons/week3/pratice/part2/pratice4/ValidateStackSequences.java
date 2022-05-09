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
     * popped: 1,2,5,4,3  [1]
     * popped: 4,3,5,1,2(错误) [1,2,3]
     *
     * 我自己的核心思路: 使用栈，遍历poped要移出的元素，如果栈不包含该元素，则从pushed数组里遍历数据存到栈中，直到栈里包含
     *         了要移除的元素，开始进行出栈操作，如果找不到出栈的元素，说明出栈顺序不对;如果出栈指定元素后继续遍历poped数组;
     *
     */
    // pushed = [1,2,3,4,5]
    // popped = [1,2,5,4,3]
    //stack [1,2,3]
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>(); //该栈用来表示安照上诉入栈，出栈顺序的
        int i = 0,j = 0;
        while (j < popped.length) { //i=2 j=1
            int pop = popped[j]; //这里的元素是栈第一次要出的元素，如果栈里有该元素，就出栈
            if (!stack.contains(pop) && i < pushed.length) { //栈不包含要出的元素就先入栈
                int push = pushed[i]; //3
                stack.push(push);
                i++;
            }else {
                //执行到这里说明元素入栈后，栈包含了要移出的元素，找到要移出的元素
                while (!stack.isEmpty() && stack.peek() != pop) {
                    stack.pop();
                }
                //栈没空，说明找到了要移出的元素
                if (!stack.isEmpty()) {
                    stack.pop(); //移出要删除的元素
                    j++;
                }else { //执行到这里栈为空了，没找到要出栈的元素，说明该出栈顺序不是该栈的出栈顺序；
                    return false;
                }

            }
        }
        return true;
    }

}
