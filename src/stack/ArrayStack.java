package stack;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 顺序栈
 * @author: hxw
 * @date: 4/14/2019 4:59 PM
 */
public class ArrayStack {

    private Integer[] items; //数组

    private int top; //用来指示栈顶元素的位置

    public ArrayStack(int size) {
        this.items = new Integer[size];
        top = -1;
    }

    /**
     * 压栈
     * @param item 压入的元素
     * @return
     */
    public boolean push(int item){
        if(top == items.length-1 && top != -1){ //说明元素已满，无法push
            return false;
        }
        items[++top] = item; //每push一个元素，top向上移动
        return true;
    }

    /**
     * 弹栈
     * @return -1表示为
     */
    public int pop(){
        if(top == -1){
            //top等于-1说明为空栈
            throw new UnsupportedOperationException();
        }
        int item = items[top];
        items[top] = null; //移除栈顶元素
        top --;         //再将top向下移动
        return item;
    }

    /**
     * 获取栈内的元素数量
     * @return
     */
    public int getElementNum(){
        return top + 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    public static void main(String[] args){
        ArrayStack stack = new ArrayStack(4);
        stack.push(1);
        System.out.println(stack);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        stack.push(4);
        System.out.println(stack.push(5));
        System.out.println(stack);
        int p1 = stack.pop();
        int p2 = stack.pop();
        System.out.println(stack);
        int p3 = stack.pop();
        System.out.println(stack);
        int p4 = stack.pop();
        System.out.println(stack);
        stack.pop();
    }
}
