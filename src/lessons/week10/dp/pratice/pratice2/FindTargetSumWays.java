package lessons.week10.dp.pratice.pratice2;

/**
 * @version 1.0  494. 目标和
 * @Description: 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 *
 * @author: bingyu
 * @date: 2023/5/15
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        FindTargetSumWays f = new FindTargetSumWays();
        int[] nums = {1,1,1,1,1};
        int target = 3;
        int res = f.findTargetSumWays(nums, target);
        System.out.println(res);
    }

    /* 争个解法:
     如何将其抽象成背包模型?
     可以抽象成，往背包放东西，要么是减重量，要么是加重量；求背包中重量为target的放法有多少?
     注意target可能为负数的,注意物品重量都是正数
     状态转移方程: dp[i][j] = dp[i-1][j-num[i]](num[i]作为正数放入背包) + dp[i-1][j + num[i]](此时num[i]作为负数放入背包)
     TODO: 这题难点是如何保存负数的状态使其不越界？
      由于题目中是0 <= sum(nums[i]) <= 1000，因此负数最低到-1000，正数最大到1000，如果是target
      我们只需要从target + 1000开始计算，这样就算为-1也不可能越界了，但是要注意重量最大不能设置为1000+target，因为可能后面
      一直加超过了target，随后又遇到负数减到target了，因此最好是用2000将其全部可能性包含在内
    */
    public int findTargetSumWays(int[] nums, int target) {
        if (target<-1000 || target>1000) return 0;
        int n = nums.length;
        int w = 2000;
        int offset = 1000; //偏移量
        int[][] dp = new int[n][w+1];

        //初始化第0行，从1000开始作为第0列，为什么这里要+=1，而不是直接=1呢？
        //因为如果nums[0]=0时，如果直接=1，那么加和减就计算起来就为1了，但是很明显加0和减0是两种装法，因此我们要用+=
        dp[0][offset + nums[0]] += 1; //加物品
        dp[0][offset - nums[0]] += 1; //减物品

        //开始正式进行状态转移
        for (int i = 1;i<n;i++) {
            //TODO： 这里j必须从0开始，因为如果从offset=1000开始，就无法检测负数位置是否可达了，比如dp[0][offset - nums[0]]这个的列就小于
            // 1000，后面就永远无法考察1000前面的位置了
            for (int j = 0;j<=w;j++) {
                if (j-nums[i]>=0 && j-nums[i]<=w) {
                    dp[i][j] = dp[i-1][j-nums[i]];
                }
                if (j+nums[i]>=0 && j+nums[i]<=w) {
                    dp[i][j] += dp[i-1][j+nums[i]];
                }

            }
        }
        return dp[n-1][offset + target];
    }




}
