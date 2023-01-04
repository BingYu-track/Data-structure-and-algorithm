package lessons.week7.pratice.heap;

import lessons.common.ListNode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Description: 使用小顶堆来实现优先队列
 * @author: bingyu
 * @date: 2023/1/4
 */
public class PriorityQueue {

    /*
      注意: Queue接口中比较常用的接口add(e)，offer(e)，poll()，peek()方法，其中add和offer方法的区别是add失败会抛出异常，
      offer方法失败返回false，有些实现类add方法直接调用offer方法。poll和peek的区别就是poll删除第一个元素并返回，peek直接返回第一个元素不删除
     */

    private int[] queue;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 11; //默认初始化优先队列容量

    //优先队列创建
    public PriorityQueue() {
        this.queue = new int[DEFAULT_INITIAL_CAPACITY];;
    }

    /*
       入队操作，即向堆插入元素，把新的元素放入数组末尾，然后从新的元素开始自下而上进行堆化
     */
    public void add(int num) {
        int i = size;
        if (i>=queue.length) {
            //进行扩容
            int oldCapacity = queue.length;
            //如果容量小于64，就再原有基础上加2，否则进行右移，增加原容量的50%
            int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1));
            queue = Arrays.copyOf(queue, newCapacity); //复制新数组
        }
        queue[size] = num;
        size++;
        heapifyUp(queue,size,size-1); //从末尾自下而上
    }

    /**
     * 自下而上进行堆化
     * @param queue
     * @param size
     * @param i
     */
    private void heapifyUp(int[] queue, int size, int i) {
        int p = (i-1)/2; //得到父节点的下标
        if (p>=0 && queue[i] < queue[p]) {
            int temp = queue[i];
            queue[i] = queue[p];
            queue[p] = temp;
            heapifyUp(queue,size,p);
        }
    }

    /**
     * 出队操作，堆中删除堆顶元素，然后将末尾元素放到堆顶再自上而下进行堆化
     */
    public void remove() {
        int i = size - 1;
        //尾部元素放到根节点
        queue[0] = queue[i];
        queue[i] = 0;
        size--;
        heapifyDown(queue,size,0); //从根节点开始自上而下进行堆化
    }


    /**
     * 自上而下进行堆化
     * @param queue
     * @param size
     * @param i
     */
    public void heapifyDown(int[] queue, int size, int i) {
        int min = i;
        int left = 2*i + 1; //左子节点
        int right = 2*i + 2; //右子节点
        if (left < size && queue[left] < queue[min]) {
            min = left;
        }
        if (right < size && queue[right] < queue[min]) {
            min = right;
        }
        if (min!=i) {
            int temp = queue[min];
            queue[min] = queue[i];
            queue[i] = temp;
            heapifyDown(queue,size,min);
        }
    }



    //返回优先队列的元素个数
    public int size() {
        return size;
    }

    //测试用堆实现的优先队列
    public static void main(String[] args) {
        int[] arr = {9,4,4,5,3,4,7,3,2,7,4,8,2};
        PriorityQueue priorityQueue = new PriorityQueue();
        java.util.PriorityQueue<Integer> minQ = new java.util.PriorityQueue<>(new Comparator<Integer>() { //小顶堆优先队列
            @Override
            public int compare(Integer q1, Integer q2) {
                return q1 - q2;
            }
        });
        for (int i : arr) {
            priorityQueue.add(i);
            minQ.offer(i);
        }
        System.out.println(priorityQueue); //[2,3,2,3,4,4,7,9,4,7,5,8,4]
        System.out.println(minQ);
        while (priorityQueue.size>0) {
            priorityQueue.remove();
            minQ.remove();
            System.out.println();
        }
    }
 /*
                    2
                /       \
               3         2
            /    \      / \
           3     4     4   7
         / \     /\   /\
        9   4   7  5  8 4
  */
}
