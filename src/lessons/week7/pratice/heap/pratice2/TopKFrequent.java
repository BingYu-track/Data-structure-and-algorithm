package lessons.week7.pratice.heap.pratice2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @version 1.0  前K个高频元素
 * @Description: 给你一个整数数组nums和一个整数k，请你返回其中出现频率前k高的元素。你可以按任意顺序返回答案。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数],题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小。
 *
 * @author: bingyu
 * @date: 2022/12/9
 */
public class TopKFrequent {

    public static void main(String[] args) {
        int[] nums = {8,9,4,2,3,4,5,6,2,3,4,6,7,2};
        System.out.println(topKLittle(nums,4)); //找出第2大的元素
        int[] result = topKFrequent(nums, 4);
        System.out.println(Arrays.toString(result));
    }

    //先实现TOPK问题，找第K大的元素
    public static int topKLarge(int[] arr,int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int i = 0;
        for (int num : arr) {
            if (i<k) {
                minHeap.offer(num); //堆中个数没到k，直接插入
                i++;
            }else {
                if (num > minHeap.peek()) { //大于k，要与堆顶元素进行比较
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }

        }
        return minHeap.peek();
    }

    /**
     * topk第k小的元素,维护一个k个元素的大顶堆，不断插入元素，当元素个数等于k时，比较堆顶的大小，如果小于堆顶元素，移除堆顶元素，插入新的
     * @param arr
     * @param k
     * @return
     */
    public static int topKLittle(int[] arr,int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int i = 0;
        for (int e : arr) {
            if (i<k) {
                maxHeap.offer(e);
                i++;
            }else {
                if (e < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(e);
                }
            }
        }
        return maxHeap.peek();
    }

    /*
     我的解题思路: 找前k个高频元素，需要先统计出元素和其出现频次，然后维护大小为k的一个小顶堆来处理
     执行用时：12 ms, 在所有 Java 提交中击败了90.47%的用户
     内存消耗：43.9 MB, 在所有 Java 提交中击败了69.41%的用户
     */
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) return nums;
        PriorityQueue<Number> minHeap = new PriorityQueue<>(new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return o1.count - o2.count;
            }
        });
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) { //将数组中的元素和出现频次存入Hash
            hashMap.put(num,hashMap.getOrDefault(num,0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer key = entry.getKey();
            Integer count = entry.getValue();
            Number number = new Number(key, count);
            if (i<k) { //堆中的元素个数小于k，就直接插入
                minHeap.offer(number);
                i++;
            }else { //如果堆元素个数已经等于k，则将堆顶元素的出现频次和新元素的出现频次进行比较，如果大于堆顶元素就移除堆顶元素，插入新元素
                if (count > minHeap.peek().count) {
                    minHeap.poll();
                    minHeap.offer(number);
                }
            }
        }
        int[] result = new int[k];
        for (int j = k-1;j>=0;j--) {
            result[j] = minHeap.poll().num;
        }
        return result;
    }

     static class Number{

        private int num;
        private int count;

         public Number(int num, int count) {
             this.num = num;
             this.count = count;
         }

         @Override
         public String toString() {
             return "Number{" +
                     "num=" + num +
                     ", count=" + count +
                     '}';
         }
     }




}
