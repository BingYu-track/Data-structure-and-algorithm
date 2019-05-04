package queue;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 顺序存储的循环队列
 * @author: bingyu
 * @date: 2019/5/3 17:13
 */
public class CirularQueue {

    private int[] items;

    private int capacity;

    private int head; //header pointer of queue

    private int tail; //tail pointer of queue

    public CirularQueue(int capacity){
        items = new int[capacity];
        this.capacity = capacity;
    }

    /**
     * 入队
     * @param element
     * @return true or false
     */
    public boolean enqueue(int element){
        //入队前判断队列是否占满
        if((tail+1) % capacity == head){ //这里重点就再与理解(tail+1)%capacity == head
            return false;
        }
        items[tail] = element;
        tail = (tail + 1) % capacity; //这行代码和下面被注释的代码是一个意思，被注释代码更容易理解，所以保留下来
        /*if(tail==capacity-1){ //如果尾部指针已经到了数组末尾，则重新指向数组头部
            tail = 0;
        }else {
            tail ++;
        }*/
        return true;
    }

    /**
     * 出队
     * @return
     */
    public int dequeue(){
        if(head == tail){
            throw  new UnsupportedOperationException("the queue is empty");
        }
        int i = items[head];
        items[head] = 0;
        head = (head + 1) % capacity;
       /* if(head == capacity-1){ //头部指针已经到达末尾，同样重新指向数组头部
            head = 0;
        }else {
            head ++;
        }*/
        return i;
    }

    public String toString(){
        return Arrays.toString(items) + " head: "+ head + " tail: " + tail;
    }

    public static void main(String[] args){
        CirularQueue queue = new CirularQueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.enqueue(5)); //队列已满，进队失败
        System.out.println(queue); //[1, 2, 3, 4, 0]
        System.out.println("--------------------------");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(5); //1
        queue.enqueue(6); //2
        System.out.println(queue); //[6, 0, 3, 4, 5]
        queue.enqueue(7); //队列已满，返回false
    }
}
