package lessons.week4.pratice.sorted.pratice.pratice5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0 合并区间
 * @Description: 以数组intervals表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
 *              请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]  --这是要原地合并
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 *
 * @author: bingyu
 * @date: 2022/6/21
 */
public class Merge {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18},{3,5}}; //
        int[][] merge = merge(intervals);
        System.out.println(Arrays.deepToString(merge));
    }

    /*
      我的思路:首先按照区间的starti进行从小到大排序，然后用endi和下一个区间的starti进行比较，大于等于下个区间的starti就说明重合了，记录合并的
              位置，继续下一个
              [[1,2],[4,8],[7,10]]
            难点是合并后，空出来的元素如何处理?

       执行用时：6 ms, 在所有 Java 提交中击败了97.93%的用户
       内存消耗：46.9 MB, 在所有 Java 提交中击败了5.04%的用户
    */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int count = 1; //因为后面循环到length-1就结束了，因此最后一共非空元素是不会遇到的，因此从1开始
        for (int i = 0;i < intervals.length - 1;i++) {
            if(intervals[i][1] >= intervals[i+1][0]) { //当前区间与下一个区间有重合，进行合并，判断当前区间是否完全包含下个区间
                if (intervals[i][1] >= intervals[i+1][1]) { //完全包含下一个区间
                    intervals[i+1] = intervals[i]; //直接将下一个区间替代为当前区间，然后intervals[i] = null去除当前位置的区间
                }else {
                    intervals[i+1][0] = intervals[i][0]; //将当前区间的开始数值合并到下个区间的开始数值，然后去除当前区间
                }
                intervals[i] = null;
            }else {
                //执行到这里说明前后会议没有冲突，继续向后面遍历
                count++;
            }
        }
        int[][] arr = new int[count][2]; //新开辟一个数组，将之前的元素填充到新数组中(为什么要新开辟数组？因为原数组可能包含null)
        for (int i = 0,j = i;i < intervals.length;i++) {
            if (intervals[i] != null) {
                arr[j] = intervals[i];
                j++;
            }
        }
        return arr;
    }



}
