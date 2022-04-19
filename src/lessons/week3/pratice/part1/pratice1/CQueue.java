package lessons.week3.pratice.part1.pratice1;

import java.util.Stack;

/**
 * @version 1.0 剑指 Offer 09. 用两个栈实现队列
 * @Description: 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对appendTail、deleteHead 进行10000次调用
 *
 * @author: bingyu 我使用的是解法一
 * @date: 2022/4/7
 */
public class CQueue {

    //TODO: 解法一:入队-直接入栈； 出队-用两个栈
    /**
     * 一个栈A先存进去数字，另一个栈B作为临时栈，存的时候数据直接存入栈A，取数据的时候,先判断栈B是否为空，如果为空先将栈A的数据全部挪移到栈B，然后弹出栈B的栈顶元素，此时该元素
     * 就是之前栈A的栈底元素，后面所有的取元素就都从栈B里取，当栈B空后，再将栈A的元素挪移到栈B里；优缺点--优点是入队操作O(1)，出队操作也是O(1)，在特别情况下出队是O(n)的
     */

    private Stack<Integer> stack;

    private Stack<Integer> tmp;

    public CQueue() {
        this.stack = new Stack<Integer>();
        this.tmp = new Stack<Integer>(); //创建临时栈
    }

    public void appendTail(int value) {
        stack.push(value);
    }

    public int deleteHead() {
        if (tmp.isEmpty() && stack.isEmpty()) return -1;
        if (tmp.isEmpty()) { //如果临时栈为空，则将栈全部挪移到临时栈
            while (!stack.empty()) {
                Integer item = stack.pop(); //出栈并存入临时栈
                tmp.push(item);
            }
        }
        int head = tmp.pop();
        return head;
    }





    //TODO: 解法二:入队-用两个栈； 出队-直接出栈
    /**
     * 一个栈A，一个临时栈B，数据先入临时栈B，此时最早进的数据在栈底，然后再将临时栈B的数据全挪移到栈A，此时在栈B底部的数据，在栈A就处于栈顶了，
     * 出栈就能直接出;  优缺点: 入队慢，出队快！
     */
}
