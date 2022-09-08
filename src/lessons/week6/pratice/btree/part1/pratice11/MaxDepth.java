package lessons.week6.pratice.btree.part1.pratice11;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树的最大深度
 * @Description: 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明:叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度3 。
 *
 * @author: bingyu
 * @date: 2022/8/31
 */
public class MaxDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;

        node2.left = node3;
        node2.right = node4;
        int depth = maxDepth(root);
        System.out.println(depth);
    }

    /*TODO: 题型3: 二叉树上的递归
     我的思路: 树的最大深度=Math.max(左子树深度,右子树深度) + 1
      树中的节点，以及子节点问题都可以用这个递推公式解决
      时间复杂度:O(n)  n为节点个数
      空间复杂度:O(h)  h为树的高度，这里消耗的空间主要是递归调用方法时栈的最大深度

      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：41.6 MB, 在所有 Java 提交中击败了6.46%的用户
    */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
       return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }


}
