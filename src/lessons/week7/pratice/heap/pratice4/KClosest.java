package lessons.week7.pratice.heap.pratice4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0  最接近原点的K个点
 * @Description: 给定一个数组 points，其中points[i] = [xi, yi]表示 X-Y 平面上的一个点，并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点。
 * 这里，平面上两点之间的距离是欧几里德距离（√(x1- x2)2+ (y1- y2)2）。
 *
 * 你可以按 任何顺序 返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[1,3],[-2,2]], k = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 *
 *
 * 示例 2：
 *
 * 输入：points = [[3,3],[5,-1],[-2,4]], k = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 * 提示：
 * 1 <= k <= points.length <= 10^4
 * -10^4< xi, yi< 10^4
 *
 * @author: bingyu
 * @date: 2022/12/9
 */
public class KClosest {

    public static void main(String[] args) {
        int[] point1 = {-9,9};
        int[] point2 = {8,9};
        int[] point3 = {-10,-7};
        int k = 3;
        int[][] points = new int[][]{point1,point2,point3};
        int[][] result = kClosest(points, k);
        System.out.println(result);
    }

    /*
        执行用时：27 ms, 在所有 Java 提交中击败了52.38%的用户
        内存消耗：49.4 MB, 在所有 Java 提交中击败了84.37%的用户
     该题目的意思是给一组点，让你找出离原点最近的那个点即可
     解题思路: 用hashMap存储点和其到远点的距离，然后用小顶堆来进行处理
     */
    public static int[][] kClosest(int[][] points, int k) {
        //创建小顶堆
        PriorityQueue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (int) (o1.distance - o2.distance);
            }
        });
        //创建点和对应到原点距离的对象，并放入小顶堆中，每次堆化，都会使最小的点放到小顶堆的堆顶
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            double distance = Math.pow(Math.abs(x-0),2) + Math.pow(Math.abs(y-0),2); //计算每个点到原点的距离
            Point p = new Point(point, distance);
            minHeap.offer(p);
        }
        int[][] result = new int[k][2];
        for (int i = 0;i<k;i++) {
            result[i] = minHeap.poll().point;
        }
        return result;
    }

   static class Point {

        private int[] point;

        private double distance;

        public Point(int[] point, double distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "point=" + Arrays.toString(point) +
                    ", distance=" + distance +
                    '}';
        }
    }

}
