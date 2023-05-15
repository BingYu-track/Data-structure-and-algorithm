package lessons.week10.dp.pratice.pratice1;

/**
 * @version 1.0  416. 分割等和子集
 * @Description: 给你一个只包含正整数的非空数组nums。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5]和[11]。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * @author: bingyu
 * @date: 2023/5/10
 */
public class CanPartition {

    public static void main(String[] args) {
        CanPartition cp = new CanPartition();
        int[] nums = {1,5,11,5};
        boolean res = cp.canPartition(nums);
        System.out.println(res);
    }

    /*
      如何将该问题抽象成dp模型，要存在重复子问题
      注意: 题目中要求是只分隔成2个子集，且2个子集和是相等的，也就是说明只要求子集等于和的一半即可，
      这样就可以抽象成从背包中放入物品，看是否等于其和的一半即可

      执行用时：33 ms, 在所有 Java 提交中击败了41.96%的用户
      内存消耗：45.7 MB, 在所有 Java 提交中击败了14.45%的用户
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0 || nums.length<=1) return false; //除以2有余数，说明非偶数，不满足题意
        int n = nums.length;
        int w = sum / 2;
        boolean[][] dp = new boolean[n][w+1];
        //初始化第0行
        dp[0][0] = true; //不放入第0个物品
        //TODO： 注意由于重量只有求和的一半，因此这里nums[0]可能会越界，比如[9,5]第一个元素9就超过了(9+5)/2=7因此越界
        if (w-nums[0]>=0) {
            dp[0][nums[0]] = true; //放入了第0个物品
        }

        for (int i = 1;i < n;i++) {
            for (int j = 0;j <= w;j++) {
                if (dp[i-1][j] || (j-nums[i]>=0 && dp[i-1][j-nums[i]])) { //上一层可达
//                    if (dp[i-1][j]) {
//                        System.out.println("dp[" + i + "][" + j + "]来自 dp[" + (i-1) + "][" + j+"]" );
//                    }
//                    if (j-nums[i]>=0 && dp[i-1][j-nums[i]]) {
//                        System.out.println("dp[" + i + "][" + j + "]来自 dp[" + (i-1) + "][" + (j-nums[i]) + "]");
//                    }
                    dp[i][j] = true;
                }
            }
        }
        return dp[n-1][w];
    }


    /*
     使用空间优化
     执行用时：22 ms, 在所有 Java 提交中击败了91.38%的用户
     内存消耗：39.8 MB, 在所有 Java 提交中击败了98.92%的用户
    */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0 || nums.length<=1) return false; //除以2有余数，说明非偶数，不满足题意
        int n = nums.length;
        int w = sum / 2;
        boolean[] dp = new boolean[w+1]; //只用一维数组

        //初始化第0行
        dp[0] = true; //1.不放入
        if (nums[0]<=w) { //2.不放入
            dp[nums[0]] = true;
        }

        for (int i = 1;i < n;i++) {
            //一维数组要从后往前处理，处理完的部分就是当前dp位置，如果是从前往后处理，由于只有一行，往后会处理到本来是当前阶段的位置，这样是错误的
            for (int j = w;j>=0;j--) {
                if (dp[j] || (j-nums[i] >=0 && dp[j-nums[i]])) {
                    dp[j] = true;
                }
            }
        }
        return dp[w];
    }


}
