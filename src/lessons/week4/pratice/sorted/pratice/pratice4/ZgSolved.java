package lessons.week4.pratice.sorted.pratice.pratice4;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Description: 会议室
 * @author: bingyu
 * @date: 2022/6/20
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[][] intervals = {{0,1},{30,80},{15,20},{5,18}};
        //int[][] intervals = {{13,15},{1,13}};
        //int[][] intervals = {{7,10},{2,4}};
        boolean b = canAttendMeetings(intervals);
        System.out.println(b);
    }

    /*
     争哥解法: 和我解法一样！
       判断会议是否冲突，即是否上一个会议的结束时间大于下一个会议的开始时间，只要遇到这种情况就说明不能参加所有的会议

    */
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] < intervals[i-1][1]) return false;
        }
        return true;
    }
}
