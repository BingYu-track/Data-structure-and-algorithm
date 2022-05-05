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
public class CQueue2 {

    //TODO: 解法二:入队-用两个栈倒腾； 出队-直接出栈(好像没办法这样做，只能取的时候进行挪移)
    /**
     * 一个栈A，一个临时栈B，第一个数据先入栈A，当后面有数据进入时将栈A的数据都移动到临时栈，然后在将新数据存入栈A中；再把临时栈的数据全部移动到
     * 栈A，这样最先进去的数据就到栈A的栈顶了！
     */

    private Stack<Integer> stack = new Stack<Integer>();

    private Stack<Integer> temp = new Stack<Integer>();

    public CQueue2() {

    }

    public void appendTail(int value) {
        while (!stack.empty()) {
            temp.push(stack.pop()); //将栈的数据全部移动到临时栈
        }
        stack.push(value); //新数据放入栈底
        while (!temp.empty()) {
            stack.push(temp.pop()); //再把临时栈的数据全部移动回栈中，这样的话最先进栈的就到栈顶了，后面就可以直接出
        }
    }

    public int deleteHead() {
        if (stack.empty()) return -1;
        return stack.pop();
    }

    public static void main(String[] args) {
        CQueue2 queue2 = new CQueue2();
        queue2.appendTail(1);
        queue2.appendTail(2);
        queue2.appendTail(3);
        System.out.println(queue2.deleteHead());
    }

}
