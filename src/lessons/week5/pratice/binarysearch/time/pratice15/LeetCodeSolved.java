package lessons.week5.pratice.binarysearch.time.pratice15;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/8/6
 */
public class LeetCodeSolved {

    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int h = 7;
        int i = minEatingSpeed(piles, h);
        System.out.println(i);
    }

    /**
     * leetcode官方题解
     * @param piles
     * @param h
     * @return
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile); //首先找出数组中最大的那个值
        }
        int k = high;
        while (low < high) { //TODO:为什么这里不是<=
            int speed = (high - low) / 2 + low; //开始二分
            long time = getTime(piles, speed); //获取吃完香蕉的所需时间
            if (time <= h) { //时间小于等于给定的h，说明可以吃完，
                k = speed;
                high = speed; //TODO: 这里还不是太理解
            } else { //不能吃完，需要增加吃的速度，因此将mid向后移动
                low = speed + 1;
            }
        }
        return k;
    }

    private static long getTime(int[] piles, int speed) {
        long time = 0;
        for (int pile : piles) {
            int curTime = (pile + speed - 1) / speed; //这行代码是干什么的？
            time += curTime;
        }
        return time;
    }


}
