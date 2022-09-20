package lessons.week6.pratice.btree.part2.pratice7;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 从前序与中序遍历序列构造二叉树--重复练习
 * @author: bingyu
 * @date: 2022/9/20
 */
public class RepeatPratice {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root);
    }

    /*
            示例 1:
                 3
                /  \
               9   20
                  /  \
                 15   7
    输入: preorder = [3,9,20,15,7],
         inorder = [9,3,15,20,7]
    输出: [3,9,20,null,null,15,7]

    思路: 前序和中序可以唯一确定一棵二叉树，因此我们要找前序和中序里root的左右子树的范围；用双指针来标记其范围即可
    */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildMyTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private static TreeNode buildMyTree(int[] preorder, int i, int j, int[] inorder, int p, int r) {
        if (i > j) return null;
        int rootValue = preorder[i]; //在前序数组获取root值
        //再到中序数组找root所在的位置
        int k = p;
        while (inorder[k] != rootValue) {
            k++;
        }
        //此时在中序数组中，左子树范围:[p,k-1]     右子树范围:[k+1,r]
        int leftTreeSize = k - p;
        int rightTreeSize = r - k;
        //此时在前序数组中，左子树范围:[i+1,i+leftTreeSize]    右子树范围:[i+leftTreeSize+1,i+leftTreeSize+rightTreeSize]
        TreeNode left = buildMyTree(preorder, i + 1, i + leftTreeSize, inorder, p, k - 1);
        TreeNode right = buildMyTree(preorder, i + leftTreeSize + 1, i + leftTreeSize + rightTreeSize, inorder, k + 1, r);
        TreeNode root = new TreeNode(rootValue);
        root.left = left;
        root.right = right;
        return root;
    }

}
