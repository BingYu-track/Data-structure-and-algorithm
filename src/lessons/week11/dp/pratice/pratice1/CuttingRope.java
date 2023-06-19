package lessons.week11.dp.pratice.pratice1;

/**
 * @version 1.0 剑指 Offer 14- I. 剪绳子
 * @Description: 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为
 * k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度
 * 分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 * 2 <= n <= 58
 *
 * @author: bingyu
 * @date: 2023/6/19
 */
public class CuttingRope {

    public static void main(String[] args) {
        CuttingRope cp = new CuttingRope();
        int res = cp.cuttingRope(10);
        System.out.println(res);
    }


    /*
     长度为n，可以分成 2段、或者2段以上 即[2 ~ n-1]段
     dp[i]表示将绳子为i长度，分隔后的最大乘积
     dp[i] = (i-j) * j  表示将长度为i的绳子分成了i-j和j两段；
     dp[i] = dp[i-j] * j  dp[i-j]表示将i-j长度的绳子的最大乘积，也就是说,i-j继续往下分，因为如果dp[i-j]不分的话，它的长度就是i-j了
     明显dp[i-j]意思就是继续往下分割

     假设: n=4
     dp[4] = 2*2=4; 分成2段
     dp[4] = dp[2] * 2 =  1*1*2; 分成3段及以上，从你这里可以知道dp[2] = 1;即绳子长度为2时分割后的最大乘积，后面再不可能分了，因此我们初始化
     应该是从dp[2]开始;dp[1]也要进行初始化，否则

     执行用时：1 ms, 在所有 Java 提交中击败了50.20%的用户
     内存消耗：38.2 MB, 在所有 Java 提交中击败了53.74%的用户
    */
    public int cuttingRope(int n) {
        int[] dp = new int[n+1];

        //如何进行初始化?
        dp[2] = 1;
        for (int i = 3;i<=n;i++) { //i表示当前绳子的长度
            for (int j = 1;j<i;j++) { //j表示分割的长度
                dp[i] = Math.max(dp[i],Math.max(dp[i-j] * j , (i-j)*j));
                //TODO： Math.max(dp[i-j] * j , (i-j)*j);是求分割的j固定，其不同种分割策略的最大值，然后我们还要比较分隔了不同j的最大乘积
                 // 和拆分数字是同一类题型!
            }
        }
        return dp[n];
    }


}
