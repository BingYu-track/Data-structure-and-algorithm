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

    private Integer count; //排序栈的元素数目

    //我的思路解法:使用一个临时栈tmp存储比当前元素小的元素

    public SortedStack() {

    }

    public void push(int val) {
        if (count == 0) {

        }
    }

    public void pop() {

    }

    public int peek() {
        return 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
