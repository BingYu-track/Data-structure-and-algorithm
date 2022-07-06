package lessons.week4.pratice.sorted.pratice.pratice5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0
 * @Description: 合并区间
 * @author: bingyu
 * @date: 2022/6/21
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18},{3,5}}; //
        int[][] merge = merge(intervals);
        System.out.println(Arrays.deepToString(merge));
    }

    /*
     争哥解法: 解法和我基本一致
    */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) { //按照起始点排序
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> result = new ArrayList<int[]>();
        int curLeft = intervals[0][0]; //区间起始点指针
        int curRight = intervals[0][1]; //区间结束点指针
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] <= curRight) { //如果下一个区间的起始点(intervals[i][0])指针小于等于当前区间的结束点
                if (intervals[i][1] > curRight) { //下一个区间的结束点大于当前区间结束点，说明是部分重合
                    curRight = intervals[i][1]; //将下一个区间的结束点取代当前区间的结束点
                }
            } else { //执行到这说明没有重合，起始点和结束点往后移动
                result.add(new int[]{curLeft, curRight});
                curLeft = intervals[i][0];
                curRight = intervals[i][1];
            }
        }
        result.add(new int[]{curLeft, curRight});
        return result.toArray(new int[result.size()][]);
    }
}
