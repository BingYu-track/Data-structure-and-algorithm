package leetcode.dp.L096UniqueBinarySearchTrees;

/**
 * @version 1.0  96. 不同的二叉搜索树
 * @Description: 给你一个整数 n ，求恰由n个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 *
 * 示例 1：
 *     1           1            2            3        3
 *      \           \         /   \         /        /
 *      3            2       1     3      2         1
 *     /              \                  /          \
 *    2                3                1            2
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 * 1 <= n <= 19
 * @author: bingyu
 * @date: 2023/8/29
 */
public class NumTrees {

    public static void main(String[] args) {
        NumTrees t = new NumTrees();
        int res = t.numTrees(3);
        System.out.println(res);
    }

    /*
        1 2 3 4
        定义dp[i]表示给定i个节点可以构成多少个BST
        dp[0] = 0
        dp[1] = 1  1个节点也算是一个子树
        dp[2] = 2  2个节点2个子树
        dp[3] = 5
        dp[4] = ?

    核心思想: 当前节点的BST个数 = 左子树的BST个数 * 右子树的BST个数--每个数字作为1次根节点，然后将其每个根节点对应的BST数目累加起来，就是
        节点数目所包含的所有BST数目!
     TODO dp[0] * dp[i-1]的解释: "左子树没有节点，构成右子树种类的所有节点就只剩下i-1个，然后将其右子树再看成一棵单独的数，就相当于dp[i-1]"
       用 i个节点构建 BST，除去根节点，剩i−1个节点构建左、右子树，左子树分配0个，则右子树分配到i−1个，后面以此类推.......
       dp[i] = dp[0] * dp[i-1] + dp[1] * dp[i-2] + .....+ dp[i-1] * dp[0]
                 1作为根节点            2作为根节点                i作为根节点

    */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        //初始化
        dp[0] = 1;
        for (int i = 1;i<dp.length;i++) { //这里i表示节点的总个数
            for (int j = 0;j<=i-1;j++) { //TODO: 这里j表示左子树的节点个数,这里j<i-1是指 左子树节点数范围只可能是0~i-1
                dp[i] += dp[j] * dp[i-j-1]; //dp[j]相当于左子树j个节点数的BST数目，dp[i-j-1]就是 总节点数 - 左子树的节点数 - 根节点数=右子树节点数
            }
        }
        return dp[n];
    }


}
