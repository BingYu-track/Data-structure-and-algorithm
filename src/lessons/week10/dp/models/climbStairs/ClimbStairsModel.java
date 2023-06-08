package lessons.week10.dp.models.climbStairs;

/**
 * @version 1.0
 * @Description: 爬楼梯dp模型
 * @author: bingyu
 * @date: 2023/6/6
 */
public class ClimbStairsModel {


    /*
     一、爬楼梯问题模型
     每一步可以走x个、y个、z个台阶，走完n个台阶，请问:
     a) 有多少种走法?(计数)
     b) 最少需要多少步?(最值)
     c) 能否正好走完台阶?(可行)

     二、多阶段决策模型:
     阶段个数不固定，每一阶段决策走多少个台阶、

     三、状态定义:
     a) int dp[n+1] dp[i]表示走完i个台阶有多少种走法  TODO: 走完0个台阶有1种走法?
     b) int dp[n+1] dp[i]表示走完i个台阶最少需要多少步
     c) boolean dp[n+1] dp[i]表示是否正好走完i个台阶

     四、状态转移方程:
     到达这个状态，那上一步只有可能是走x、y、z个台阶，也就是从状态i-x、i-y、i-z转化过来
     dp[i]值也由dp[i-x]、dp[i-y]、dp[i-z]推导出来
     a) dp[i] = dp[i-x] + dp[i-y] + dp[i-z]
     b) dp[i] = Min(dp[i-x], dp[i-y], dp[i-z]) + 1 TODO: 注意这里是求走多少步，一步是可以走x、y或者z个台阶的，自然这里是加1
     c) dp[i] = dp[i-x] || dp[i-y] || dp[i-z]

     TODO： 注意下面爬楼梯模型初始化值时的问题
    */

    public static void main(String[] args) {
        ClimbStairsModel c = new ClimbStairsModel();
        int n = 3;
        int res = c.climbStairs(n);
        System.out.println(res);
    }

    /*
    使用爬楼梯的dp模型来解题

    例题: 假设你正在爬楼梯。需要n阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

    示例 1：
    输入：n = 2
    输出：2
    解释：有两种方法可以爬到楼顶。
    1. 1 阶 + 1 阶
    2. 2 阶

    示例 2：
    输入：n = 3
    输出：3
    解释：有三种方法可以爬到楼顶。
    1. 1 阶 + 1 阶 + 1 阶
    2. 1 阶 + 2 阶
    3. 2 阶 + 1 阶

    提示：
    1 <= n <= 45
    、
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.4 MB, 在所有 Java 提交中击败了25.98%的用户
    */
    public int climbStairs(int n) {
        if (n == 1) return 1; //特殊情况，只有一个台阶
        int[] dp = new int[n+1];
        //TODO: dp[0]应该表示走到第0个台阶有几种走法，按照正常逻辑，应该是0，因为要么走1个台阶，要么是走2个台阶
        // 我们再将dp[0]=0代进dp[i] = dp[i-x] + dp[i-y] + dp[i-z]这个式子里看是否正确；dp[2] = dp[2-1] + dp[2-2]
        // dp[2] = dp[1] + dp[0]; dp[1]表示到1个台阶的走法，明显是1，dp[2]表示走到2个台阶的走法，明显有2种走法；但是
        // 我们将dp[0]=0代进去就回得到dp[2]=1，明显不对，因此我们将dp[0]要初始化为1，尽管这不符合我们的常识
        dp[0] = 1;
        for (int i = 1;i<=n;i++) {
            if (i-1>=0) dp[i] += dp[i-1];
            if (i-2>=0) dp[i] += dp[i-2];
        }
        return dp[n];
    }


}
