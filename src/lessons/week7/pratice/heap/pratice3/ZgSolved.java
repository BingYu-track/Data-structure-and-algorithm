package lessons.week7.pratice.heap.pratice3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0 数据流的中位数--争哥解法
 * @Description: 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * @author: bingyu
 * @date: 2022/12/9
 */
public class ZgSolved {

    public static void main(String[] args) {

    }

    private PriorityQueue<Integer> minQueue = new PriorityQueue(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });
    private PriorityQueue<Integer> maxQueue = new PriorityQueue(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public ZgSolved() {

    }

    /*
     争哥解法和我差不多
     */
    public void addNum(int num) {
        if (maxQueue.isEmpty() || num <= maxQueue.peek()) {
            maxQueue.add(num);
        } else {
            minQueue.add(num);
        }
        while (maxQueue.size() < minQueue.size()) {
            Integer e = minQueue.poll();
            maxQueue.add(e);
        }
        while (minQueue.size() < maxQueue.size()-1) {
            Integer e = maxQueue.poll();
            minQueue.add(e);
        }
    }

    public double findMedian() {
        if (maxQueue.size() > minQueue.size()) {
            return maxQueue.peek();
        } else {
            return (maxQueue.peek()+minQueue.peek())/2f;
        }
    }

}
