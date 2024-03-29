package lessons.week6.pratice.btree.part2.pratice11;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 二叉树的直径--争哥解法
 * @author: bingyu
 * @date: 2022/9/28
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode nodeA1 = new TreeNode(-7);
        TreeNode nodeA2 = new TreeNode(-3);
        root.left = nodeA1;
        root.right = nodeA2;

        TreeNode nodeB1 = new TreeNode(-9);
        TreeNode nodeB2 = new TreeNode(-3);
        nodeA2.left = nodeB1;
        nodeA2.right = nodeB2;

        TreeNode nodeC1 = new TreeNode(9);
        TreeNode nodeC2 = new TreeNode(-7);
        TreeNode nodeC3 = new TreeNode(4);
        nodeB1.left = nodeC1;
        nodeB1.right = nodeC2;
        nodeB2.left = nodeC3;

        TreeNode nodeD1 = new TreeNode(6);
        TreeNode nodeD2 = new TreeNode(-6);
        TreeNode nodeD3 = new TreeNode(-6);
        nodeC1.left = nodeD1;
        nodeC2.left = nodeD2;
        nodeC2.right = nodeD3;

        TreeNode nodeE1 = new TreeNode(0);
        TreeNode nodeE2 = new TreeNode(6);
        TreeNode nodeE3 = new TreeNode(5);
        TreeNode nodeE4 = new TreeNode(9);
        nodeD1.left = nodeE1;
        nodeD1.right = nodeE2;
        nodeD2.left = nodeE3;
        nodeD3.left = nodeE4;

        TreeNode nodeF1 = new TreeNode(-1);
        TreeNode nodeF2 = new TreeNode(-4);
        TreeNode nodeF3 = new TreeNode(-2);
        nodeE1.right = nodeF1;
        nodeE2.left = nodeF2;
        nodeE4.left = nodeF3;
        int sum = diameterOfBinaryTree(root);
        System.out.println(sum);
    }

    /*
     争哥思路: 首先单纯根据左右子树的直径是无法简单推导出当前树的直径的，因为树的直径可能是经过根节点的，左右子树的直径并不是加上一起那么简单的，可能连左右子树
     的根节点也没经过，因此不能使用这种递归
      正确思路应该是遍历时找每个节点上经过的所有路径，并取其中的最大路径；这样的话就要用成员遍历保存记录
      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：41.1 MB, 在所有 Java 提交中击败了62.92%的用户
    */
    private static int max = 0; //用来记录最大路径
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max; //
    }

    private static int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftHigh = dfs(root.left); //1.获得root左子树的最大高度
        int rightHigh = dfs(root.right); //2.获得root右子树的最大高度
        int rootMaxSum = leftHigh + rightHigh; //3.计算经过root节点的倒V字型的最长路径，这里没有加1，是因为高度要比路径多1个，因为叶子节点高度是算1
        int sum = Math.max(Math.max(leftHigh, rightHigh), rootMaxSum); //从3个
        if (sum > max) max = sum;
        return Math.max(leftHigh,rightHigh) + 1; //返回root的最大高度
    }


}
