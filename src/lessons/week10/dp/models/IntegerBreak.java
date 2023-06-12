package lessons.week10.dp.models;

/**
 * @version 1.0  整数拆分
 * @Description: 给定一个正整数n，将其拆分为 k个正整数的和（k >= 2），并使这些整数的乘积最大化。
 * 返回 你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例2:
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 提示:
 * 2 <= n <= 58
 *
 * @author: bingyu
 * @date: 2023/6/9
 */
public class IntegerBreak {

    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        int res = ib.integerBreak(10);
        System.out.println(res);
    }


    /*

      dp[i]定义: 拆分数字i，可以得到的最大乘积为dp[i]。
      错误的状态转移方程: dp[i] = dp[j] * dp[i-j] 这个是错误的，dp[4] = dp[2] * dp[2] = 1 * 1 = 1，正确结果应该是4,明显不对
      dp[i-j]就是拆分i-j这个数字得到的最大乘积
      dp[j]就是拆分j这个数字得到的最大乘积
     TODO： 为什么dp[i] = dp[j] * dp[i-j]就不对呢?
        因为dp[j]是对数字j进行拆分，dp[i-j]是对数字i-j再进行拆分；本来数字i就已经拆分成了j和i-j，再这样拆分就拆分的数字就强制拆成4份以及4份以上了。
        从而忽略了拆分2份或者3份的情况，因此正确的状态转移方程应该是dp[i] = Math.max(dp[i], Math.max(j*dp[i-j],j*(i-j));
        j*(i-j)表示只拆分成2份；j*dp[i-j]表示拆分成3份或3份以上，这样就覆盖了所有的可能性!

    执行用时：1 ms, 在所有 Java 提交中击败了76.42%的用户
    内存消耗：38.1 MB, 在所有 Java 提交中击败了82.05%的用户
    */
    public int integerBreak(int n) {
        int[] dp = new int[n+1];

        //初始化
        dp[1] = 1;
        dp[2] = 1; //2分为2个1，其乘积也只有1

        for (int i = 3;i<=n;i++) {
            for (int j = 1 ;j<i;j++) { //乘积取[1,i-1]
                dp[i] = Math.max(dp[i],Math.max(j*dp[i-j],j*(i-j)));
            }
        }
        return dp[n];
    }
  //1 2   1 1 1
    // 1 1


    /*
     dp[i]表示拆分数字i，可以得到的最大乘积
    */
    public int integerBreak2(int n) {
        int[] dp = new int[n+1];
        for (int i = 0;i<n;i++) { // 初始化从0到10，表示所有可能组成10的数字
            dp[i] = i;
        }

        for (int i = 2;i<=n;i++) { //从数字2~9都进行拆分
            for (int j = 1;j<i;j++) { //j数字从1~i-1，作为拆出来的数字
                dp[i] = Math.max(dp[i],dp[i-j] * j);
            }
        }
        return dp[n];
    }


}
