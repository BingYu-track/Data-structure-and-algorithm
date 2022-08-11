package lessons.week3.pratice.queue.pratice4;

import java.util.Stack;

/**
 * @version 1.0
 * @Description: 最小栈--争哥解法
 * @author: bingyu
 * @date: 2022/4/13
 */
public class ZgSolvedMinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> tmp = new Stack<>();

    /** //TODO： 推荐使用
     * 争哥的解法思路:一个栈存数据，另一个临时栈tmp用来存对应的最小值，比如我们要存 1、2、3这3个数字，那么，临时栈底部先存第一个数字，然后再存后面的
     *  的数字时和栈顶数字进行比较，如果临时栈顶的数更小，就换成最小的放入临时栈，这样的话，后面getMin时只需要用tmp临时栈peek即可，只是出栈时要记得同步
     *  出临时栈
     */

    public ZgSolvedMinStack() {

    }

    public void push(int val) {
        stack.push(val);
        if (!tmp.isEmpty() && tmp.peek() < val) {
            tmp.push(tmp.peek());
        }else {
            tmp.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            tmp.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return tmp.peek();
    }

    public static void main(String[] args) {
        ZgSolvedMinStack stack = new ZgSolvedMinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.getMin());
        System.out.println();
    }
}
