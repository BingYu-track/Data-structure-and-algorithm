package lessons.week3.pratice.part1.pratice2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/4/13
 */
public class MyStack2 {

    /** [1,2,3]  [3,2,1,0]
     * TODO 推荐的解法-核心思路； 只使用一个队列来实现栈，思路是先得到队列里的元素个数，比如有n个元素;
     *   1.当出栈时，那么我们将队列不断出队，然后再进队，这样元素就跑到后面去了。这样执行n-1后，最后进来的元素就到了队头部，后面直接出就行；
     *   2.当入栈时，先将元素入队，然后得到n-1要执行的次数，我们将队列不断出队，然后再进队，这样各个加入的元素就跑到队列头部了
     * [3] [1,2]
     */
    private Queue<Integer> queue = new LinkedList<Integer>();


    public MyStack2() {

    }

    public void push(int x) {
        queue.add(x);
        int k = queue.size() - 1;
        while (k!=0) {
            int num = queue.poll();
            queue.add(num);
            k--;
        }
    }

    public int pop() {
        if (queue.isEmpty()) return -1;
        return queue.poll();
    }

    public int top() {
        if (queue.isEmpty()) return -1;
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack2 stack = new MyStack2();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
