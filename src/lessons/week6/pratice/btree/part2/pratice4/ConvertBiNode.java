package lessons.week6.pratice.btree.part2.pratice4;

import lessons.common.TreeNode;

/**
 * @version 1.0 面试题 17.12. BiNode
 * @Description: 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，
 * 把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 *               4                               0
 *             /   \
 *            2     5          ---->
 *           / \     \
 *          1   3     6
 *         /
 *        0
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 *
 * 提示：
 * 节点数量不会超过 100000。
 *
 * @author: bingyu
 * @date: 2022/9/13
 */
public class ConvertBiNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(0);
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        TreeNode node6 = new TreeNode(6);
        node2.right = node6;

        TreeNode treeNode = convertBiNode(root);
        System.out.println(treeNode);
    }

    /*
     我的思路: 因为要求二叉搜索树展开后也要符合二叉搜索树的特性，因此，这个展开后的单链表肯定是递增的，
       那么利用二叉搜索树中序遍历的顺序特点来解题

       执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
       内存消耗：46 MB, 在所有 Java 提交中击败了58.05%的用户
    */
    private static TreeNode dummyHead = new TreeNode(-1);
    private static TreeNode tail = dummyHead;
    public static TreeNode convertBiNode(TreeNode root) {
        inOrder(root);
        return dummyHead.right;
    }

    private static void inOrder(TreeNode root) {
        if (root == null) return;
        //提前将每一个节点的左右子结点展开
        TreeNode left = root.left;
        TreeNode right = root.right;
        inOrder(left);
        tail.right = root; //执行到这里说明 root就是左子树的叶子节点
        tail = root;
        root.left = null;
        inOrder(right);
    }


}
