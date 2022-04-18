package lessons.week3.pratice.part1.pratice3;

import java.util.Stack;

/**
 * @version 1.0 栈排序
 * @Description: 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构
 * （如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek返回 -1。
 *
 * 示例1:
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：
 * [null,null,null,1,null,2]
 *
 * 示例2:
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：
 * [null,null,null,null,null,true]
 *
 * 说明:
 * 栈中的元素数目在[0, 5000]范围内。
 *
 * @author: bingyu
 * @date: 2022/4/13
 */
public class SortedStack {

    private Stack<Integer> stack = new Stack<>();

    private Stack<Integer> tmp = new Stack<>(); //临时栈

    private Integer count; //排序栈的元素数目

    //TODO: 注意题目中说的"只能使用一个其他的临时栈存放数据"，意思不是只能使用一个栈，而是额外的临时栈只能使用一个
    //我的思路解法:需要2个栈，一个栈A和一个临时栈B，栈A先存第一个数据，当后面遇到大的数据就将栈A的数据移动到栈B，然后大的数据放入栈A，再把栈B移动到栈A即可

    public SortedStack() {

    }

    public void push(int val) {
        while (!stack.isEmpty() && stack.peek() < val) { //如果栈顶元素小于当前要放入的元素，则将栈顶放入临时栈
            tmp.push(stack.pop());
        }
        //执行到这里说明栈顶元素不小于val了，可以将当前元素放入栈中
        stack.push(val);
        //再把临时栈的所有元素移动到栈中
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        if (!stack.isEmpty()) {
          return stack.peek();
        }
        return -1;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        SortedStack sortedStack = new SortedStack();
        sortedStack.push(1);
        sortedStack.push(2);
        sortedStack.push(3);
        sortedStack.push(4);
        sortedStack.push(5);
        System.out.println(sortedStack.peek());
    }
}
