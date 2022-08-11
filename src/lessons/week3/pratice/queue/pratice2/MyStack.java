package lessons.week3.pratice.queue.pratice2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0 用队列实现栈
 * @Description: 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是push to back、peek/pop from front、size 和is empty这些操作。
 * 你所使用的语言也许不支持队列。你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 *
 * 示例：
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *
 * 提示：
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 *
 * @author: bingyu
 * @date: 2022/4/13
 */
public class MyStack {

    /**
     * 解法1核心思路； 我们需要队列A和临时队列B，
     *          (1).入队时直接存到队列A。
     *          (2).出队时将队列A的元素依次出队入到临时队列B，直到队列A只有一个元素了，把这个元素出队；然后再把队列B的元素全部挪移到队列A中即可
     *          优点是入栈快;缺点是出栈慢
     * [1 2 3] []
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了16.60%的用户
     */
    private Queue<Integer> queue = new LinkedList<Integer>();

    private Queue<Integer> tmp = new LinkedList<Integer>();

    public MyStack() {

    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() { //出栈
        while (queue.size() != 1) {
            Integer num = queue.poll();
            tmp.add(num);
        }
        Integer num = queue.poll();
        while (!tmp.isEmpty()) {
            queue.add(tmp.poll());
        }
        return num;
    }

    public int top() { //查看栈顶元素 [1,2,3]-->[] + [1,2,3]
        while (queue.size() != 1) {
            Integer num = queue.poll();
            tmp.add(num);
        }
        Integer num = queue.poll(); //注意这里不能用peek，因为这样queue里面就还有一个元素，后面tmp里的元素挪移过来后，会变成最先入队的元素，
                                    // 因此这里直接删除最后一个元素，然后再放到临时队列里
        tmp.add(num);
        while (!tmp.isEmpty()) {
            queue.add(tmp.poll());
        }
        return num;
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.top();
        stack.pop();
        stack.pop();
        System.out.println(stack.empty());
    }
}
