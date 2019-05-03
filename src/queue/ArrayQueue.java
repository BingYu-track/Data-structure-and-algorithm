package queue;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 顺序存储队列实现
 * @author: bingyu
 * @date: 2019/5/2 21:33
 */
public class ArrayQueue {

    private int[] items;

    private int capacity;

    private int head; //header pointer of ArrayQueue

    private int tail; //tail pointer of queue


    //initialize capacity
    public ArrayQueue(int capacity){
        items = new int[capacity];
        this.capacity = capacity;
    }

    /**
     * 入队
     * @param element
     * @return true or false
     */
    public boolean enqueue(int element){
        if(tail == capacity){ //
            if(head == 0){ //tail == capacity && head == 0 The queue was full
                return false;
            }else{ //Queue is not really full,We need moving all elements forward.
                int n = tail - head;
                for(int i=0;i<n;i++){
                    items[i] = items[head];
                    items[head] = 0; //
                    head++;
                }
                head = 0;
                tail = n;
            }
        }
        items[tail++] = element;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public int dequeue(){
        if(tail == head){ //This have two case: 1.head and tail is zero.  2.head and tail greater than zero
            throw new UnsupportedOperationException("the queue is empty");
        }
        //dequeue opreation
        int i = items[head];
        items[head] = 0;
        head++;
        return i;
    }

    public String toString(){
        return Arrays.toString(items);
    }

    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.enqueue(5)); //true
        System.out.println(queue.enqueue(6));//false
        System.out.println(queue); //[1, 2, 3, 4, 5]
        System.out.println("出队");
        System.out.println(queue.dequeue()); //1
        System.out.println(queue.dequeue()); //2
        System.out.println(queue); //[0, 0, 3, 4, 5]
        System.out.println(queue.dequeue()); //3
        System.out.println(queue.dequeue()); //4
        System.out.println(queue); //[0, 0, 0, 0, 5]
        System.out.println(queue.dequeue());//5
        //System.out.println(queue.dequeue()); //the queue is empty
        test();
    }

    public static void test(){
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.enqueue(5)); //true
        System.out.println(queue.enqueue(6));//false
        System.out.println(queue); //[1, 2, 3, 4, 5]
        System.out.println("出队");
        System.out.println(queue.dequeue()); //1
        System.out.println(queue.dequeue()); //2
        System.out.println(queue); //[0, 0, 3, 4, 5]
        queue.enqueue(6); //触发一次元素迁移
        System.out.println(queue); //[3, 4, 5, 6, 0]
        queue.enqueue(7);
        queue.enqueue(8);
        System.out.println(queue); //[3, 4, 5, 6, 7]
        queue.enqueue(9);
    }
}
