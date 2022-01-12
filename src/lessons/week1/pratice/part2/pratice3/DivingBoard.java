package lessons.week1.pratice.part2.pratice3;

import java.util.Arrays;

/**
 * @version 1.0 跳水板
 * @Description: 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
 * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。返回的长度需要从小到大排列。
 *
 * 示例 1
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 *
 * 输出： [3,4,5,6]
 * 解释：可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
 *
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * @author: bingyu
 * @date: 2022/1/4
 */
public class DivingBoard {

    public static void main(String[] args) {
        int[] nums = divingBoard(1, 1, 0);
        System.out.println(Arrays.toString(nums));
    }


    //思路: 1. k * shorter ~ k * longer，我的思路是找出规律后发现，规律就是数学里的等差数列
    public static int[] divingBoard(int shorter, int longer, int k) {
        int[] nums;
        if (shorter > longer || shorter < 0 || k>100000 || k < 0 || k==0) { //如果超出范围，或者k等于0,返回空数组
            nums = new int[0];
             return nums;
         }
        int min = k * shorter; //得到最短的木板
        int max = k * longer; //得到最长的木板
        if (min == max) { //如果长木板个短木板长度一样，则只有一种可能的木板长度
            nums = new int[1];
            nums[0] = max;
            return nums;
        }
        nums = new int[k + 1]; //自己多举例子，可以总结归纳为，类似等差数列,生成木板可能的长度个数为k+1
        int length = nums.length;
        int d = longer - shorter; //等差就是长木板和短木板之间的差值
        nums[0] = min;
        nums[length - 1] = max;
        for (int i = 1;i<length - 1;i++) {
            nums[i] = min + i*d;
        }
        return  nums;
    }
    /**
     输入: shorter = 1 longer = 2 k=3
     输出: [3,4,5,6]

     输入: shorter = 1 longer = 2 k=4
     输出: [4,5,6,7,8]

     输入:  shorter = 2 longer = 4 k=3
     输出: [6,8,10,12]

     输入:  shorter = 2 longer = 4 k=4
     输出: [8,10,12,14,16]

     输入:  shorter = 2 longer = 4 k=5
     输出: [10,12,14,16,18,20]

     从上面的例子，可以总结归纳为，类似等差数列,生成木板可能的长度种数为k+1
     */

}
