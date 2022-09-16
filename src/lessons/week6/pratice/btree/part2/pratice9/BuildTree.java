package lessons.week6.pratice.btree.part2.pratice9;

import lessons.common.TreeNode;

/**
 * @version 1.0 从中序与后序遍历序列构造二叉树
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
 * @date: 2022/9/16
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode treeNode = buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

    /*
    执行用时：3 ms, 在所有 Java 提交中击败了47.61%的用户
    内存消耗：41.1 MB, 在所有 Java 提交中击败了63.15%的用户
    */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildMyTree(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    /*
     中序: 9,3,15,20,7
     后序: 9,15,7,20,3
    */
    private static TreeNode buildMyTree(int[] inorder, int i, int j, int[] postorder, int p, int r) {
        if (i>j && p>r) return null;
        int rootValue = postorder[r]; //后序的最后一个元素是根节点
        //然后将根节点放到中序里查找
        int k = i;
        while (inorder[k] != rootValue) {
            k++;
        }
        //执行到这说明k就是根节点在中序的位置，这样就确定了中序左右子树的范围 左子树范围:[i,k-1]  右子树范围:[k+1,j]
        int leftTreeSize = k - i;
        int rightTreeSize = j - k;
        //得到左右子树的大小，那么也就能确定后序中左右子树的范围了 左子树范围:[p,p+leftTreeSize-1]
        // 右子树范围:[p+leftTreeSize,p+leftTreeSize+rightTreeSize-1]

        //这样就可以开始构建二叉树了
        TreeNode root = new TreeNode(rootValue);
        //在左子树范围内继续构建树
        TreeNode leftNode = buildMyTree(inorder, i, k - 1, postorder, p, p + leftTreeSize - 1);
        //在右子树范围内继续构建树
        TreeNode rightNode = buildMyTree(inorder, k + 1, j, postorder, p + leftTreeSize, p + leftTreeSize + rightTreeSize - 1);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }


}
