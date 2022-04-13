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

    /**
     * 解法2核心思路； 我们需要队列A和临时队列B，
     *          (1).元素入队时先存到队列A，当后面元素入队时，把前一个元素出队列A，然后入到队列B，也就是说队列A始终只保持1个最新入队的元素。
     *          (2).元素出队时直接队列A出队，然后再把队列B的元素全部挪移到队列A即可
     *          优点是入栈快;缺点是出栈慢
     * [3] [1,2]
     */
    private Queue<Integer> queue = new LinkedList<Integer>();

    private Queue<Integer> tmp = new LinkedList<Integer>();

    public MyStack2() {

    }

    public void push(int x) {

    }

    public int pop() {

        return 0;
    }

    public int top() {

        return 0;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
