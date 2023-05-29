package leetcode.competions.season1.problem1;

import java.util.Arrays;

/**
 * @version 1.0 符文储备
 * @Description: 远征队在出发前需要携带一些「符文」，作为后续的冒险储备。runes[i] 表示第 i 枚符文的魔力值。
 *
 * 他们将从中选取若干符文进行携带，并对这些符文进行重新排列，以确保任意相邻的两块符文之间的魔力值相差不超过 1。
 *
 * 请返回他们能够携带的符文 最大数量。
 *
 * 示例 1：
 *
 * 输入：runes = [1,3,5,4,1,7]
 *
 * 输出：3
 *
 * 解释：最佳的选择方案为[3,5,4]
 * 将其排列为 [3,4,5] 后，任意相邻的两块符文魔力值均不超过 1，携带数量为 3
 * 其他满足条件的方案为 [1,1] 和 [7]，数量均小于 3。
 * 因此返回可携带的最大数量 3。
 *
 * 示例 2：
 *
 * 输入：runes = [1,1,3,3,2,4]
 *
 * 输出：6
 *
 * 解释：排列为 [1,1,2,3,3,4]，可携带所有的符文
 *
 * 提示：
 *
 * 1 <= runes.length <= 10^4
 * 0 <= runes[i] <= 10^4
 * @author: bingyu
 * @date: 2023/5/7
 */
public class RuneReserve {

    public static void main(String[] args) {
        RuneReserve r = new RuneReserve();
        int[] runes = {1,1,3,3,2,4};
        int res = r.runeReserve(runes);
        System.out.println(res);
    }

    private int countMax = 0;

    /*
    符文不能相差1,[1,1,3,4,5,7]
     */
    public int runeReserve(int[] runes) {
        Arrays.sort(runes);
        int count = 1;
        for (int i = 0;i<runes.length;i++) {
            int j = i + 1;
            if (j>=runes.length) { //说明已经到了末尾
                if (countMax < count) countMax = count;
                break;
            }
            int diff = runes[j] - runes[i];
            if (diff<=1) {
                count++;
            }else {
                //执行到这说明不能连续了，后面需要从j开始重新检测
                if (countMax < count) countMax = count;
                count = 1;
            }
        }
        return countMax;
    }

}
