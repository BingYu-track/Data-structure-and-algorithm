package lessons.week6.pratice.btree.part2.pratice8;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 根据前序和后序遍历构造二叉树--争哥解法
 * @author: bingyu
 * @date: 2022/9/15
 */
public class ZgSolved {

    public static void main(String[] args) {
//        int[] preorder = {1,2,4,5,3,6,7};
//        int[] postorder = {4,5,2,6,7,3,1};
        int[] preorder = {2,1};
        int[] postorder = {1,2};
        TreeNode treeNode = constructFromPrePost(preorder, postorder);
        System.out.println(treeNode);
    }

    /*
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：40.9 MB, 在所有 Java 提交中击败了85.16%的用户
    */
    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildTree(preorder,0,preorder.length - 1,postorder,0,postorder.length - 1);
    }

    /*
     争哥解法思路:
     前序: 1,2,4,5,3,6,7
     后序: 4,5,2,6,7,3,1
    */
    private static TreeNode buildTree(int[] preorder, int i, int j, int[] postorder, int p, int r) {
        if (i>j && p>r) return null;
        int rootValue = preorder[i]; //当前根节点的值
        TreeNode root = new TreeNode(rootValue);
        if (i==j || p==r) return root; //当相等时说明当前root下面没有子节点了，直接返回根节点

        int leftValue = preorder[i + 1]; //假设，根节点后面紧挨着的就是左子树根节点的值(这里即可能是左子树，又可能是右子树，我们只选择其中一种可能)
        int q = p;
        while (postorder[q] != leftValue) { //查找左子树根节点在后序数组的位置
            q++;
        }
        //执行到这说明q就是左子树根节点在后序数组的位置，这样左右子树在后序数组的范围也确定了，左子树范围:[p,q]  右子树范围:[q+1,r-1]

        int leftTreeSize = q - p + 1; //左子树大小
        int rightTreeSize = r - 1 - q; //右子树大小
        //得到左子树和右子树的大小后，我们就可以在前序数组划分左右子树的范围了  左子树范围:[i+1,i+leftTreeSize]
        // 右子树范围:[i+leftTreeSize+1 , i+leftTreeSize+rightTreeSize]

        //TODO： 可以开始构造树

        //构造左子树
        TreeNode leftNode = buildTree(preorder, i + 1, i+leftTreeSize, postorder, p, q);
        //构造右子树
        TreeNode rightNode = buildTree(preorder, i+leftTreeSize+1, i+leftTreeSize+rightTreeSize, postorder, q + 1, r - 1);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }


}
