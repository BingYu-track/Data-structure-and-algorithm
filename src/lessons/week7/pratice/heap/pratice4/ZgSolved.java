package lessons.week7.pratice.heap.pratice4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0 最接近原点的K个点--争哥解法
 * @Description: 给定一个数组 points，其中points[i] = [xi, yi]表示 X-Y 平面上的一个点，并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点。
 * 这里，平面上两点之间的距离是欧几里德距离（√(x1- x2)2+ (y1- y2)2）。
 * @author: bingyu
 * @date: 2022/12/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] point1 = {3,3};
        int[] point2 = {5,-1};
        int[] point3 = {2,4};
        int k = 2;
        int[][] points = new int[][]{point1,point2,point3};
        int[][] result = kClosest(points, k);
        System.out.println(result);
    }

    /*
     争哥解法: 这里思路是维护有k个容量的大顶堆，只要排除k个元素之外最大的元素，那么这个大顶堆里所有的元素就都是我们要找的
    */
    public static int[][] kClosest(int[][] points, int k) {//points数组是存储的一组点,k是要求的前k个点
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            //TODO : 这里int[0]存的是到原点的距离，int[1]存的是点在points数组的下标位置
            public int compare(int[] array1, int[] array2) {
                return array2[0] - array1[0];
            }
        });
        //向大顶堆中插入points数组中前k个，原地距离--点坐标 一一对应的数组
        for (int i = 0; i < k; ++i) {
            //计算point到原点的距离 points[i][0]是i下标位置的点的横坐标，points[i][1]是i下标位置的点的纵坐标
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        int n = points.length;
        for (int i = k; i < n; ++i) { //从第k个下标位置开始遍历，并计算点到原点的距离
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) { //如果小于大顶堆顶部的，就移除堆顶元素，插入新的
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        //执行到这里，大顶堆里所有的元素就都是我们要找的
        int[][] ans = new int[k][2]; //
        for (int i = 0; i < k; ++i) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }

}
