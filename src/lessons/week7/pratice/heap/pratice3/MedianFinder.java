package lessons.week7.pratice.heap.pratice3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0 数据流的中位数
 * @Description: 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4]的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * @author: bingyu
 * @date: 2022/12/9
 */
public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        int[] nums = {8,9,4,2,3,4,5,6,2,3,4,6,7,2};
//        for (int num : nums) {
//            medianFinder.addNum(num);
//        }
        /*
            ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
            [[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
            输出：[null,null,-1.00000,null,-1.50000,null,-1.00000,null,-2.50000,null,-3.00000]
            预期结果：[null,null,-1.0,null,-1.5,null,-2.0,null,-2.5,null,-3.0]
         */
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
    }


    /*
    我的解法:
    执行用时：100 ms, 在所有 Java 提交中击败了64.03%的用户
    内存消耗：59.5 MB, 在所有 Java 提交中击败了78.01%的用户
     */
    private PriorityQueue<Integer> minHeap; //小顶堆

    private PriorityQueue<Integer> maxHeap; //大顶堆

    private int size = 0;

    public MedianFinder() {
        minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }



    /*
     插入数据
    */
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
        }else if (minHeap.isEmpty()) {
            minHeap.offer(num);
        }else {
            //执行到这里说明两个堆均不为空,比较两堆的堆顶元素大小
            if (maxHeap.peek() > minHeap.peek()) {
                Integer minTop = minHeap.poll();
                Integer maxTop = maxHeap.poll();
                maxHeap.offer(minTop);
                minHeap.offer(maxTop);
            }
            //如果num大于小顶堆堆顶元素，就放入小顶堆，否则放入大顶堆
            if (num > minHeap.peek()) {
                minHeap.offer(num);
            }else {
                maxHeap.offer(num);
            }
            //如果大顶堆元素个数小于小顶堆，就从小顶堆拿出元素放入大顶堆
            if (maxHeap.size() < minHeap.size()) {
                Integer minTop = minHeap.poll();
                maxHeap.offer(minTop);
            }
            if (minHeap.size() < maxHeap.size()-1) {
                Integer maxTop = maxHeap.poll();
                minHeap.offer(maxTop);
            }
        }
        size++;
    }

    /*
     寻找中位数，使用一个大顶堆，一个小顶堆，大顶堆插入数据要比小顶堆的数据都小
     */
    public double findMedian() {
        //if ()
        double result = -1;
        if (size % 2 == 1) { //是奇数
            result = maxHeap.peek();
        } else { //是偶数
            result = (double) (maxHeap.peek() + minHeap.peek()) /2;
        }
        return result;
    }

}
