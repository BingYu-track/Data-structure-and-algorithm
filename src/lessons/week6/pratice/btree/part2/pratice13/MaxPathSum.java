package lessons.week6.pratice.btree.part2.pratice13;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树中的最大路径和
 * @Description: 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。路径和是路径中各节点值的总和。 给你一个二叉树的根节点 root ，返回其最大路径和。
 *
 *
 * 示例 1：
 *       -1
 *      /  \
 *    -2   -3
 *         / \
 *        4  5
 *            \
 *            -6
 * 输入：root = [-1,-2,3,null,null,4,5,null,null,null,-6]
 * 输出：8
 * 解释：最优路径是 3 -> 5 ，路径和为 3 + 5 = 8
 *
 * 示例 2：
 *          -10
 *         /    \
 *        9     20
 *             /  \
 *            15   7
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * @author: bingyu
 * @date: 2022/12/7
 */
public class MaxPathSum {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5);
//        TreeNode node1 = new TreeNode(4);
//        TreeNode node2 = new TreeNode(8);
//        TreeNode node3 = new TreeNode(11);
//        TreeNode node4 = new TreeNode(13);
//        TreeNode node5 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(7);
//        TreeNode node7 = new TreeNode(2);
//        TreeNode node8 = new TreeNode(1);
//        root.left = node1;
//        root.right = node2;
//
//        node1.left = node3;
//        node3.left = node6;
//        node3.right = node7;
//
//        node2.left = node4;
//        node2.right = node5;
//        node5.right = node8;
        TreeNode root = new TreeNode(-10);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        node2.left = node3;
        node2.right = node4;
        int result = maxPathSum(root);
        System.out.println(result);
    }

    /* [-10,9,20,null,null,15,7]
                                 -10
                              /       \
                             9         20
                                      / \
                                     15  7

     */


    private static int maxSum = Integer.MIN_VALUE;

    /*
    我的解题思路: 如何找到最大的求和路径；
      首先要判断如何算是一条经过当前节点的路径?(由测试用例可知，不一定必须是到达叶子节点，任意2节点均可)
      需要计算每个非叶子节点到各个叶子节点的最大贡献值；这样当前节点就可以基于其子节点的最大贡献值算出自己的最大贡献值

      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：42.9 MB, 在所有 Java 提交中击败了60.58%的用户
    */
    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
        //TODO : 注意这里不能直接使用dfs(root)返回的路径和，因为这里返回的是整棵树的根节点的路径和，而树的根节点可能是负数，所以还是要用成员变量来记录
    }

    /*
            2
          /   \
         -1   -2
    */
    private static int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftSum = dfs(root.left); //左子树的最大路径和
        //执行到这里说明此时root是左子树的叶子节点
        int rightSum = dfs(root.right); //右子树的最大路径和
        int max = Math.max(leftSum, rightSum); //取左右子树中路径和最大的那个
        //TODO--第一种路径和：当前节点加上较大的一个子树路径和
        int rootSum = max + root.val;
        //TODO--第二种路径和: 起始和结束位置分别是左右子树的叶子节点，途径root的一个倒V字型路径
        int currentRootSum = root.val + leftSum + rightSum;
        if (rootSum < root.val) rootSum = root.val; //如果第一种路径和比root还小，说明子树路径和是负数，将root值作为root的最大路径和
        //TODO: 比较第一种和第二种路径和，并取较大的路径和, (这里需要注意，这里当currentRootsum > rootsum时，取到最大值的sum是不能作为
        // rootSum处理的，因为这里已经形成了一个路径，不能再作为rootSum返回了)
        int sum = Math.max(rootSum, currentRootSum);
        if (sum > maxSum) maxSum = sum; //TODO
        //执行到这里说明当前节点已经遍历完，将当前节点的最大路径和返回到上个节点
        return rootSum;
    }


}
