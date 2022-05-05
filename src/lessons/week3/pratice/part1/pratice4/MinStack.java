package lessons.week3.pratice.part1.pratice4;

import java.util.Stack;

/**
 * @version 1.0 最小栈
 * @Description: 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 *
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 *
 * 提示：
 * -2^31<= val <= 2^31- 1
 * pop、top和getMin操作总是在非空栈上调用
 * push,pop,top,and getMin最多被调用3 * 10^4次
 *
 * @author: bingyu
 * @date: 2022/4/13
 */
public class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> tmp = new Stack<>();

    private int min; //用来记录最小值

    private int minCount; //用来记录最小值的个数

    //思路-就是
    public MinStack() {

    }

    public void push(int val) {
        if (stack.isEmpty()) min = val; //栈为空的话，将新加入的元素设置为最小值
        if (!stack.isEmpty() && val == min) { //入栈的元素是最小值，最小值个数记录加1
            minCount++;
        }
        if (!stack.isEmpty() && val < min) { //如果小于最小值，更新其最小值
            min = val;
            minCount = 1; //因为更新了最小值，所以重置最小值个数为1
        }
        stack.push(val);
    }

    /**
     * 我的核心思路
     * TODO : 1.入栈时，将入栈元素和最小值进行比较，小于最小值就更新栈中的最小值；而且还要在入栈时记录遇到最小值的次数(这样的目的是，
     *          如果最小值在栈中有多个，当出栈时刚好是最小值，我们只需减去次数即可，最小值不用变化)
     *        2.出栈时，我们需要使用临时栈，
     *              (1).首先判断出栈的元素是否是最小值，不是最小值直接出栈
     *              (2).如果是出栈的元素是最小值，再判断该最小值在栈有几个，如果还有多个只需要减去记录最小值的个数就行，整个栈的最小值仍然不变
     *                  如果此时出栈的最小值元素是最后一个，那么我们就需要将栈里剩下的所有元素遍历存入到临时栈，并在遍历过程中记录下新的最小值
     *                  和最小值的个数，遍历完后，再将临时栈里的元素再移动回最初的栈中即可！
     */
    public void pop() { //每次pop也需要更新最小值
        Integer pop = stack.pop();
        if (pop == min) {
            if (minCount == 1 && !stack.isEmpty()) { //如果弹出的刚好是最小值，并且最小值个数就只有1个，此时需要找出剩余的最小值，并更新最小值
                min = stack.pop(); //再次弹出栈顶并暂时作为最小值
                tmp.push(min);
                while (!stack.isEmpty()) { //栈里找出剩余的最小值
                    Integer item = stack.pop();
                    if (item == min) { //如果新的最小值遇到多次，次数需要加1,这样目的是一直能记录到最小值的个数
                        minCount++;
                    }
                    if (item < min) {
                        min = item; //更新最小值
                        minCount = 1;
                    }
                    tmp.push(item); //存入临时栈
                }
                //栈遍历完成后说明找到最小值，再将临时栈里的内容移动到栈里
                while (!tmp.isEmpty()) {
                    stack.push(tmp.pop());
                }
            }else { //执行到这里说明弹出的是最小值，且个数有多个，减去一个即可
                minCount--;
            }

        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if(stack.isEmpty()) throw new UnsupportedOperationException();
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());
        minStack.pop(); //[2,0,3]
        System.out.println(minStack.getMin());
        minStack.pop(); //[2,0]
        System.out.println(minStack.getMin());
        minStack.pop(); //[2]
        System.out.println(minStack.getMin());
    }

}
