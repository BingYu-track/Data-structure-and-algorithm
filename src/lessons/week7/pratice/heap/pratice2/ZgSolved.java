package lessons.week7.pratice.heap.pratice2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @version 1.0 前K个高频元素--争哥解法
 * @Description: 给你一个整数数组nums和一个整数k，请你返回其中出现频率前k高的元素。你可以按任意顺序返回答案。
 * @author: bingyu
 * @date: 2022/12/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {8,9,4,2,3,4,5,6,2,3,4,6,7,2};
        int[] result = topKFrequent(nums, 4);
        System.out.println(Arrays.toString(result));
    }



    /*
     争哥解法: 用小顶堆用来存储元素和对应出现的频次，并且用出现频次来作为堆化的依据，这样每次插入元素和删除元素，都会根据出现频次来进行堆化，
     因为堆顶遇到比它大的，堆顶元素就会移除，插入较大的那一个，那么此时堆里k个元素就是出现频次前K高的元素，注意，题目要求不规定前k频次的元素按照顺序返回
    */
    private static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1); //用hashMap记录每个元素出现的频次
            // (getOrDefault方法返回指定键映射到的值，如果此映射不包含键的映射，则返回默认值)
        }
        //创建小顶堆，堆中存入数字和其对应出现频次对象；出现频次越少的，越靠堆顶
        PriorityQueue<QElement> queue = new PriorityQueue<>(new Comparator<QElement>() {
            public int compare(QElement e1, QElement e2) {
                return e1.count - e2.count;
            }
        });
        //遍历hashMap，得到元素和出现频次的对应关系，然后就按照Top K的思路用堆去处理
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (queue.size() < k) {
                queue.offer(new QElement(num, count));
            } else { //元素个数大于k，则将堆顶元素的频次和新的元素频次进行比较，如果新元素的频次大于堆顶，就删除堆顶元素，然后插入新元素
                if (queue.peek().count < count) {
                    queue.poll();
                    queue.offer(new QElement(num, count));
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; ++i) {
            result[i] = queue.poll().val;
        }
        return result;
    }

    private static class QElement {
        int val; //元素
        int count; //用来记录出现的频次
        public QElement(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }


}
