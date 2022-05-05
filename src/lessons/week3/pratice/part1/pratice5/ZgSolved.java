package lessons.week3.pratice.part1.pratice5;

/**
 * @version 1.0
 * @Description: 三合一--争哥解法:
 * @author: bingyu
 * @date: 2022/4/18
 */
public class ZgSolved {

    /**
     * 争哥的第一个解法就是和我一样的思路，用游标
     * //TODO: 推荐---争哥的第二个解法也是游标，只不过存的方式有点不一样，之前是3个栈连续固定的长度，现在是3个栈的每个同样的位置元素是连续的，即
     *         同一个栈中的一个元素下标为i，则下一个元素的下标就是i+3
     *
     * @param stackSize
     */

    private int[] array;
    private int n;
    private int[] top; //保存每个栈的栈顶下标

    public ZgSolved(int stackSize) {
        array = new int[3 * stackSize];
        n = 3*stackSize;
        top = new int[3];
        top[0] = -3;
        top[1] = -2;
        top[2] = -1;
    }

    public void push(int stackNum, int value) {
        if (top[stackNum] + 3 >= n) { //如果栈满了直接返回
            return;
        }
        top[stackNum] += 3; //没满直接下标加3，并在对应位置赋值
        array[top[stackNum]] = value;
    }

    public int pop(int stackNum) {
        if (top[stackNum] < 0) {
            return -1;
        }
        int ret = array[top[stackNum]]; //取出对应游标位置的元素
        top[stackNum] -= 3; //游标位置向前移动3位
        return ret;
    }

    public int peek(int stackNum) {
        if (top[stackNum] < 0) {
            return -1;
        }
        return array[top[stackNum]];
    }

    public boolean isEmpty(int stackNum) {
        return top[stackNum] < 0;
    }

    public static void main(String[] args) {

    }


}
