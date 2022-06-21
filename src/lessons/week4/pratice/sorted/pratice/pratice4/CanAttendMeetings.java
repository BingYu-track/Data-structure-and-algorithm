package lessons.week4.pratice.sorted.pratice.pratice4;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0 会议室
 * @Description: 给定一个会议时间安排的数组 intervals，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 *               请你判断一个人是否能够参加这里面的全部会议。
 *
 * 示例 1：
 * 输入：intervals = [[0,30],[40,50],[25,35]]
 * 输出：false
 *
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：true
 *
 * 提示：
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <endi <= 10^6
 *
 * @author: bingyu
 * @date: 2022/6/20
 */
public class CanAttendMeetings {

    public static void main(String[] args) {
        int[][] intervals = {{0,1},{30,80},{15,20},{5,18}};
        //int[][] intervals = {{13,15},{1,13}};
        //int[][] intervals = {{7,10},{2,4}};
        boolean b = canAttendMeetings2(intervals);
        System.out.println(b);
    }

    /**
     * 解题思路2(来自leetcode):先按照会议开始时间进行排序，排序后，挨个遍历检查前面会议的结束时间是否大于后面会议的开始时间，大于就说明重合了！
     * 执行用时：4 ms, 在所有 Java 提交中击败了97.96%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了81.54%的用户
     */
    public static boolean canAttendMeetings2(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] meet1, int[] meet2) { //将会议按照开始时间排序
                return meet1[0] - meet2[0];
            }
        });
        boolean flag = true;
        for (int i = 0;i<intervals.length - 1;i++) {
            if(intervals[i][1] > intervals[i+1][0]) { //如果前面的会议结束时间大于后面会议的开始时间，那么就会有冲突
                flag = false;
                break;
            }
        }
        return flag;
    }


    /*
     我的思路: 只要2个会议存在时间重合，就说明不能参加所有会议,最粗暴的解法就是2层遍历，但是这样会比较慢;
        执行用时：140 ms, 在所有 Java 提交中击败了6.12%的用户
        内存消耗：41.8 MB, 在所有 Java 提交中击败了5.10%的用户
    */
    public static boolean canAttendMeetings(int[][] intervals) {
        for (int i = 0;i<intervals.length;i++) {
            for (int j = i+1;j<intervals.length;j++) {
                boolean noConflict = noConflict(intervals[i], intervals[j]); //判断是否会冲突
                if (!noConflict) return false;
            }
        }
        return true;
    }

    /**a1    b1 a2    b2
     * |_____|__|_____|
     *
     * [[9,10],[4,9],[4,17]]
     * [[13,15],[1,13]]
     * [[8,11],[17,20],[17,20]] --只判断了是否在会议里，
     * [[6,10],[13,14],[12,14]]
     * 判断会议是否冲突,只要会议2的开始时间点或者结束时间点在会议1里就说明冲突
     * 可以更简洁地编写上述代码中的交叠条件。
     * TODO 考虑两个不交叠的会议。前面的会议在后面的会议开始之前结束。
     *    因此，两次会议的最小结束时间,小于或等于两次会议的最大开始时间，就说明没有相交，否则就是会相交--如何理解这句话？
     *
     * @param meet1 会议1 {9,10}
     * @param meet2 会议2 {4,17}
     * @return
     */
    public static boolean conflictError(int[] meet1,int[] meet2) {
        //注意不能直接按照"只要会议2的开始时间点或者结束时间点在会议1里,就说明冲突"用这个思路来写重合判断代码，例如:会议1 {9,10}，会议2 {4,17}
        //按照上面这样结果是不会重合的，但是实际是会议1里的开始时间在会议2里，导致重合，因此用该思路写代码会很冗长，且极易出错，代码如下，虽然是错的
        if ((((meet2[0] > meet1[0] && meet2[0] < meet1[1] ) || (meet2[1] > meet1[0] && meet2[1] < meet1[1]) ) || //检查会议2的开始时间或者结束时间是否在会议1里
                ((meet1[0] > meet2[0] && meet1[0] < meet2[1]) || (meet1[1] > meet2[0] && meet1[1] < meet2[1]))) && //检查会议1的开始时间或者结束时间是否在会议2里
                        ((meet1[0] != meet2[0] && meet1[0] != meet2[1]) && meet1[1] != meet2[0] && meet1[1] != meet2[1])) { //是否会完全重合
            return true;
        }
        return false;
    }

    /*[[8,15],[9,10]]
      TODO 根据"两次会议的最小结束时间,小于或等于两次会议的最大开始时间，就说明没有相交"这个思路来判断是否有冲突--这个是核心思路，比较难理解！
      为true说明
    */
    public static boolean noConflict(int[] meet1,int[] meet2) {
        return Math.min(meet1[1],meet2[1]) <= Math.max(meet1[0],meet2[0]);
    }



}
