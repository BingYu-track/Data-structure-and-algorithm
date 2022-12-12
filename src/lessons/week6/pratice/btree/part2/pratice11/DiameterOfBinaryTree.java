package lessons.week6.pratice.btree.part2.pratice11;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树的直径 (比较难理解，需要多次练习推敲)
 * @Description: 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * @author: bingyu
 * @date: 2022/9/21
 */
public class DiameterOfBinaryTree {

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
      我的思路: 该题就是求二叉树最长的路径，注意最长的路径不一定就是是将根节点的左子树最大深度的叶子节点，和根节点的右子树最大深度的叶子节点之间的路径;
           也可能就在左子树或者右子树里，没有经过根节点;例如下面这个例子: TODO 右子树的-4到-2的路径就要比-7到-4的路径长
           因此解题思路应该是改成: "树任意2个叶子节点之间的距离，而不是分成左右子树的叶子节点之间的距离"，左子树/右子树下的两个叶子结点就可能是最长的
                               4
                          /         \
                        -7          -3
                                /        \
                             -9          -3
                           /     \       /
                          9       -7    4
                         /       /   \
                        6       -6   -6
                    /      \    /     /
                   0        6  5     9
                    \      /        /
                    -1   -4        -2
    */
    private static int max = 0; //记录经过每个root节点时的最多节点个数


    /*
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：41.1 MB, 在所有 Java 提交中击败了62.14%的用户
     求解思路: 首先我们知道一条路径的长度为该路径经过的节点数减1，所以求直径（即求路径长度的最大值）等效于求路径经过节点数的最大值减1。
      如何找到最多节点数的那一个路径呢?

      TODO:  最长路径，那么起始节点和结束节点肯定必须都是叶子节点!
        root树的最长路径相当于在以下3个路径和中选一个最大值!
        1.左子树max(root.left)的最长路径
        2.右子树max(root.right)的最长路径
        3.经过root的倒V子型路径，等于左子树最大高度 +右子树的最大高度 + 1
    */
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max - 1; //这里需要减1，因为高度要比路径多1个，因为叶子节点高度是算1
    }

    private static int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftHigh = dfs(root.left); //1.获得root左子树的最大高度
        int rightHigh = dfs(root.right); //2.获得root右子树的最大高度
        int rootMaxSum = leftHigh + rightHigh + 1; //3.计算经过root节点的倒V字型的最长路径
        int sum = Math.max(Math.max(leftHigh, rightHigh), rootMaxSum); //从3个
        if (sum > max) max = sum;
        return Math.max(leftHigh,rightHigh) + 1; //返回root的最大高度
    }


}
