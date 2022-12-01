package lessons.week6.pratice.btree.part2.pratice7.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 从前序与中序遍历序列构造二叉树--复习
 * @Description: 给定两个整数数组preorder和inorder，其中preorder是二叉树的先序遍历，inorder是同一棵树的中序遍历，
 * 请构造二叉树并返回其根节点。
 *
 * 示例 1:
 *              3
 *            /  \
 *           6    20
 *         /  \   /  \
 *        9   8  15   7
 * 输入: preorder = [3,6,9,8,20,15,7],
 *      inorder = [9,6,8,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 *
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder和inorder均 无重复 元素
 * inorder均出现在preorder
 * preorder保证 为二叉树的前序遍历序列
 * inorder保证 为二叉树的中序遍历序列
 *
 * @author: bingyu
 * @date: 2022/11/30
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preorder = {3,6,9,8,20,15,7};
        int[] inorder = {9,6,8,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root);
    }


    /*TODO： 需要多多复习练习才行!
    思路: 已知前序遍历和中序遍历，来构建二叉树
    */
    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    /**
     *
     * @param preorder 树的前序遍历
     * @param i 前序遍历起始下标
     * @param j 前序遍历终止下标
     * @param inorder 树的中序遍历
     * @param p 中序遍历起始下标
     * @param q 中序遍历终止下标
     * @return
     */
    private static TreeNode build(int[] preorder, int i, int j, int[] inorder, int p, int q) {
        if (i>j || p>q) return null; //递归终止条件--表示
        if (preorder == null || inorder == null) return null; //只要其中一个为null，就无法构建二叉树
        int k = p;
        while (inorder[k] != preorder[i]) { //在中序遍历数组中寻找前序遍历元素的根节点的位置
            k++;
        }
        //执行到这里说明,k是根节点位置，p到k-1的所有位置元素是根节点k的左子树;k+1到q的所有元素是根节点k的右子树
        int leftTreeSize = k - p; //左子树元素个数
        int rightTreeSize = q - k; //右子树元素个数
        /*
            构建左子树
            1.因为i是根节点位置，因此左子树的根节点就是前序遍历的i+1位置
            2.由于前序是根->左->右，在知道左子树节点个数的情况下，就可以确定该左子树最后节点的位置，即i+1+leftTreeSize-1=i + leftTreeSize
            3.由于在前面我们确定了根节点k在inorder的位置，因此k-1是左子树在inorder的结束位置,起始位置是k-1-leftTreeSize+1=k-leftTreeSize
        */
        TreeNode leftTree = build(preorder, i + 1, i + leftTreeSize, inorder, k-leftTreeSize , k - 1);
        /*
         构建右子树
         1.前序遍历数组的右子树起始位置: 由于前面构建左子树时知道了左子树在前序遍历的范围是[i + 1,i + leftTreeSize];
           因此右子树在inorder的起始位置就是i+leftTreeSize+1，结束位置是i+leftTreeSize+rightTreeSize
         2.中序遍历数组右子树的起始位置: k+1(k是根节点，在中序中右边就是右子树元素)
         3.中序遍历数组右子树的结束位置: k+1+rightTreeSize-1=k+rightTreeSize
        */
        TreeNode rightTree = build(preorder, i + leftTreeSize + 1, i + leftTreeSize + rightTreeSize, inorder, k + 1, k + rightTreeSize);
        TreeNode root = new TreeNode(inorder[k]);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }


}
