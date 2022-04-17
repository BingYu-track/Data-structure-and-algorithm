package lessons.week3.pratice.part1.pratice5;

/**
 * @version 1.0 三合一
 * @Description: 三合一。描述如何只用一个数组来实现三个栈。
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 * 输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *
 * 输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 *
 * 示例2:
 * 输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *
 *  输出：
 * [null, null, null, null, 2, 1, -1, -1]
 *
 * 提示：
 * 0 <= stackNum <= 2
 *
 * @author: bingyu
 * @date: 2022/4/14
 */
public class TripleInOne {

    public TripleInOne(int stackSize) {

    }

    public void push(int stackNum, int value) {

    }

    public int pop(int stackNum) {
        return 0;
    }

    public int peek(int stackNum) {
        return 0;
    }

    public boolean isEmpty(int stackNum) {
        return false;
    }

    public static void main(String[] args) {

    }

}
