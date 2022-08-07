package lessons.week5.pratice.binarysearch.time.pratice15;

import java.util.Arrays;
import java.util.Collections;

/**
 * @version 1.0 爱吃香蕉的珂珂
 * @Description: 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在h小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，
 * 她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在h小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 *
 *
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 *
 * 示例 2：
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 *
 * 示例 3：
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *
 * 提示：
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * @author: bingyu
 * @date: 2022/8/4
 */
public class MinEatingSpeed {

    //有个数组，长度为n;可可每小时吃k根;
    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int h = 7;
        int i = minEatingSpeed(piles, h);
        System.out.println(i);
    }

    /*
       求: 能在h小时内吃掉所有香蕉的最小速度k
       TODO 我的思路: k就相当于执行的次数;在规定的次数内减完数组里所有的元素，且要使k为最小值
       h==length时，k肯定是取数组中最大的元素，当h开始大于length时,k才有取最小值的可能,

       k是与h成反比的,h越小，k越大;h越大,k越小；重点在于找到h和k之间的关系
       让k在[1,30]进行二分，将mid带进函数里面，用给定的h里查看是否会吃完，没吃完,说明是mid小了，需要向右移动；如果吃完了说明mid>=Kmin，
       我们需要向左移动探测；当遇到mid吃完了所有香蕉，但是mid-1没吃完，说明mid就是我们要找的(如果mid+1都没吃完，香蕉，那mid更不会吃完)

       举例: 当h每增加i时，
       h=5  Kmin = 30
       h=6  Kmin = 23<=k<30
       h=7  Kmin = 20<=k<23
       h=8  Kmin = 15<=k<20
       h=9  Kmin = 12<=k<15
       h=10 Kmin = k=11
       h=11 Kmin = k=10
       .....
            Kmin = k=1
       [1,30]

       执行用时：18 ms, 在所有 Java 提交中击败了22.38%的用户
       内存消耗：42.7 MB, 在所有 Java 提交中击败了5.06%的用户
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int length = piles.length;
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile); //首先找出数组中最大的那个值
        }
        if (h == length) return piles[length - 1];
        //执行到这里说明执行的次数h大于数组的长度,需要找最小值k(因为提示中h>=length，因此不会出现h小于数组长度这种情况)
        while (low <= high) {
            int mid = low + (high - low)/2;
            boolean f1 = isEating(piles, mid, h);
            boolean f2 = isEating(piles, mid - 1, h);
            if (mid > 0 && f1 && !f2) {
                return mid;
            }else if (f1) { //向左移动
                high = mid - 1;
            }else if (!f1){ //向右移动
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 判断指定的k和h是否能吃完香蕉
     * @param piles
     * @param k
     * @param h
     * @return
     */
    private static boolean isEating(int[] piles, int k, int h) {
        if (k==0) return false;
        int time = 0;//用来记录吃掉的香蕉花费的时间
        for (int i = 0;i<piles.length;i++) {
            int pile = piles[i];
//            int re = pile / k;
//            if (re > 0) {
//                time = time + re;
//            }else if (re == 0){
//                time++;
//            }
//            if (re!= 0 && k * re < pile) {
//                time++;
//            }
            //TODO：注意这行代码的使用技巧，可以替代上面注释掉的代码
            int re = (pile + k - 1) / k;
            time += re;
        }
        if (time > h) { //如果时间超过了给定值的时间，肯定吃不完
            return false;
        }
        return true;
    }


}
