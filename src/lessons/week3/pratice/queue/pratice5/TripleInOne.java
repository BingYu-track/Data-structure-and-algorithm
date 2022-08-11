package lessons.week3.pratice.queue.pratice5;

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
 * [1,2,3]
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

    //这个题目的难点是如何找出判断栈为空的依据,我的思路是，用三个游标代表push元素的末尾，根据这些游标来判断是否栈满或者栈空

    //0、1、2 //3、4、5 //6、7、8
    private int [] arr; //[1,2,3,4,5,6,7,8,9]

    private int stackSize; //每个栈的大小

    private int curfir; //第一个栈的游标

    private int curSec; //第二个栈的游标

    private int curThd; //第三个栈的游标

    public TripleInOne(int stackSize) { //创建指定容量大小的栈
        arr = new int[3 * stackSize]; //创建数组的大小
        this.stackSize = stackSize;
        curfir = 0;
        curSec = stackSize * 1;
        curThd = stackSize * 2;
    }

    public void push(int stackNum, int value) { //注意--当栈满时不压入元素,注意0 <= stackNum <= 2
        //判断指定栈是否满了
        int first = stackNum * stackSize; //获取指定栈的起始数组下标
        int end = stackNum * stackSize + stackSize - 1;
        int cur = chooseCur(stackNum);
        if (cur > end) return; //表示栈满了
        //执行到这里说明有空，开始往栈里存数据
        if (stackNum == 0 && curfir <= end) {
            arr[curfir] = value;
            curfir++;
        }else if (stackNum == 1 && curSec <= end) {
            arr[curSec] = value;
            curSec++;
        }else if (stackNum == 2 && curThd <= end) {
            arr[curThd] = value;
            curThd++;
        }
    }

    public int pop(int stackNum) {
        boolean empty = isEmpty(stackNum);
        if (empty) {
            return -1;
        }
        int cur = chooseCur(stackNum);
        int result = arr[cur - 1];
        arr[cur - 1] = 0; //弹出后设置为0，更方便调试
        if (stackNum == 0) {
            curfir--;
        }else if (stackNum == 1) {
            curSec--;
        }else if (stackNum == 2) {
            curThd--;
        }
        return result;
    }

    public int peek(int stackNum) {
        boolean empty = isEmpty(stackNum);
        if (empty) {
            return -1;
        }
        int cur = chooseCur(stackNum);
        int result = arr[cur - 1];
        return result;
    }

    public boolean isEmpty(int stackNum) { //判断是指定数组否为空
        if (stackNum == 0) {
            return curfir == 0;
        }else if (stackNum == 1) {
            return curSec == stackSize * stackNum;
        }else if (stackNum == 2) {
            return curThd == stackSize * stackNum; //36
        }
        return false;
    }

    public int chooseCur(int stackNum) {
        int cur = -1;
        if (stackNum == 0) {
            return cur = curfir;
        }else if (stackNum == 1) {
            return cur = curSec;
        }else if (stackNum == 2) {
            return cur = curThd;
        }
        return cur;
    }

    public static void main(String[] args) {
        TripleInOne tripleInOne = new TripleInOne(15);
        tripleInOne.push(0,38);
        tripleInOne.push(0,30);
        tripleInOne.push(0,35);
        tripleInOne.push(0,36);
        tripleInOne.push(0,55);
        tripleInOne.push(0,62);
        tripleInOne.push(0,21);
        tripleInOne.push(0,3);
        tripleInOne.push(0,24);
        tripleInOne.push(0,4);
        tripleInOne.push(0,11);
        tripleInOne.push(0,18);
        tripleInOne.push(0,56);
        tripleInOne.push(0,32);
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.pop(0));
    }

    /**
    [59,37,28,6,15,64,36,3]
     [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,32,56,3,36,63,18,11,4,64,15,14,24,3,6,28,59,37,0,21,39,-1,-1,62,-1,12,48,63,25,55,31,36,35]
     [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,32,56,3,36,63,18,11,4,64,15,14,24,3,6,28,59,37,59,21,39,-1,-1,62,-1,12,48,63,25,55,31,36,35]

     "TripleInOne", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop"]
     [[15], [1, 59], [1, 37], [2, 31], [1, 28], [2, 25], [0, 38], [0, 6], [2, 63], [0, 30], [0, 35], [2, 48], [0, 36], [2, 12], [0, 55], [0, 62], [1, 6], [2, 39], [1, 15], [0, 21], [1, 64], [0, 3], [0, 24], [1, 36], [2, 59], [2, 14], [0, 4], [1, 3], [0, 11], [0, 18], [0, 56], [0, 32], [2, 63], [0], [0], [1], [1], [2], [0], [0], [0], [1], [1], [2], [0], [0], [1], [1], [2], [1], [1], [0], [2], [1], [1], [0], [1], [2], [2], [2], [2], [0], [2], [0], [0]]
     */
}
