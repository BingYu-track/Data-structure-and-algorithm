package stack;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 共享空间栈
 * @author: hxw
 * @date: 2019/4/14 21:28
 */
public class SpDoubleStack {

    private Integer[] data;

    private int top1; //栈1 栈顶指针

    private int top2; //栈2 栈顶指针

    public SpDoubleStack(int size){ //初始化
        data = new Integer[size];
        top1 = -1;
        top2 = size;
    }

    /**
     * 压栈
     * @param element 压入的元素
     * @param stackNumber 选择的栈
     * @return
     */
    public boolean push(int element,int stackNumber){
        if(top1 + 1 == top2){ //表示栈已满
            return false;
        }
        if(stackNumber == 1){ //栈1有元素进栈
            data[++top1] = element;
        }else if(stackNumber == 2){ //栈2有元素进栈
            data[--top2] = element;
        }
        return true;
    }

    /**
     * 弹栈
     * @param stackNumber 选择栈
     * @return
     */
    public int pop(int stackNumber){
        Integer i = null;
        if(stackNumber == 1){
            if(top1 == -1){
                throw new UnsupportedOperationException();
            }
            i = data[top1];
            data[top1--] = null;
        }else if(stackNumber == 2){
            if(top2 == data.length){
                throw new UnsupportedOperationException();
            }
            i = data[top2];
            data[top2++] = null;
        }
        return i;
    }

    public String toString(){
        return Arrays.toString(data);
    }

    //测试
    public static void main(String[] args){
        SpDoubleStack stack1 = new SpDoubleStack(10);
        //栈1满
        int i = 1;
        while (i <=10){
            stack1.push(i,1);
            i++;
        }
        System.out.println("stack1: " + stack1); //stack1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        SpDoubleStack stack2 = new SpDoubleStack(10);
        //栈2满
        i = 1;
        while (i <=10){
            stack2.push(i,2);
            i++;
        }
        System.out.println("stack2: " + stack2); //stack2: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

        //普通情况
        SpDoubleStack stack3 = new SpDoubleStack(10);
        i = 1;
        while (i <=10){
            if(i<=6){
                stack3.push(i,1);
            }else {
                stack3.push(i,2);
            }
            i++;
        }
        System.out.println("stack3: " + stack3); //stack3: [1, 2, 3, 4, 5, 6, 10, 9, 8, 7]

    }
}
