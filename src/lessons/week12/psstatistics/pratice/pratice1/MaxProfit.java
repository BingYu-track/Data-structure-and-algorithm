package lessons.week12.psstatistics.pratice.pratice1;

/**
 * @version 1.0 买卖股票的最佳时机
 * @Description: 给定一个数组 prices,它的第i个元素prices[i]表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 提示：
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 *
 * @author: bingyu
 * @date: 2023/7/27
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        MaxProfit m = new MaxProfit();
        int res = m.maxProfit(prices);
        System.out.println(res);
    }

    /*
      思路: 注意这题只能执行一次买卖操作，不能向之前dp题那样能进行多次买卖
      7,1,5,3,6,4
      用数组arr记录从下标i开始的后缀和(包含自己)即nums[5]=4, nums[4]=6+4, nums[3]=3+6+4 ......
      1.然后我们用max[i]数组存储下标i开始的后缀中最大的值，即nums[5]=4, nums[4]=6, nums[3]=6 .....
      2.然后我们遍历max数组，让max[i] - prices[i]求得的就是在第i天买股票能获得的最大利润，然后我们取其最大的差值即可
     TODO:为什么后缀的最大值减去当前价格，就是买利润最大?
      因为求最大利润是要用"当前位置后面卖出的最大价格 - 当前买入的价格"，因此我们就需要求得每个位置到后面能达到的最大卖出价格即可

    执行用时：3 ms, 在所有 Java 提交中击败了26.29%的用户
    内存消耗：55.5 MB, 在所有 Java 提交中击败了89.15%的用户
    */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] max = new int[n];
        for (int i = n-1;i>=0;i--) {
            if (i+1 == n) {
                max[i] = prices[i]; //这样做是防止i开始作为最后一个元素，导致max[i+1]越界
            }else {
                max[i] = Math.max(prices[i],max[i+1]); //因为max[i+1]就是prices[i]后面所有数字中最大的，因此求max[i]自然就是max[i+1]和prices[i]中取最大的值
            }
        }
        //执行到这里说明max里面存储的就是下标i开始的后缀中最大的值，可以开始计算最大利润了
        int maxProfit = 0;
        for (int i = 0;i<prices.length;i++) {
            maxProfit = Math.max(maxProfit,max[i] - prices[i]);
        }
        return maxProfit;
    }


}
