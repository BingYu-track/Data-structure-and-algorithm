package lessons.week6.pratice.btree.part2.pratice9;

import lessons.common.TreeNode;

/**
 * @version 1.0 从中序与后序遍历序列构造二叉树--复习
 * @Description: 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，
 *              请你构造并返回这颗二叉树。
 *
 * 示例 1:
 *                 3
 *               /  \
 *              9   20
 *                 / \
 *                15  7
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 *
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder和postorder都由 不同 的值组成
 * postorder中每一个值都在inorder中
 * inorder保证是树的中序遍历
 * postorder保证是树的后序遍历
 *
 * @author: bingyu
 * @date: 2022/12/7
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode treeNode = buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

    /*

    */
    private static TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildMyTree(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }


    private static TreeNode buildMyTree(int[] inorder, int i, int j, int[] postorder, int p, int q) {
        if (i > j) return null;
        int rootValue = postorder[q];
        TreeNode root = new TreeNode(rootValue);
        int k = i;
        while (inorder[k] != rootValue) {
            k++;
        }
        //执行到这里说明k所在位置是根节点，[i,k-1]是左子树;[k+1,j]是右子树
        int leftSize = k - i;
        TreeNode leftTree = buildMyTree(inorder, i, k - 1, postorder, p, p + leftSize - 1);
        TreeNode rightTree = buildMyTree(inorder, k + 1, j, postorder, p + leftSize, q - 1);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }


}
