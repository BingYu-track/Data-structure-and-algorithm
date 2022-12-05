package lessons.week6.pratice.btree.part2.pratice7;

import lessons.common.TreeNode;

/** TODO: 需要重点练习，多多练习
 * @version 1.0 从前序与中序遍历序列构造二叉树
 * @Description: 给定两个整数数组preorder和inorder，其中preorder是二叉树的先序遍历，inorder是同一棵树的中序遍历，
 * 请构造二叉树并返回其根节点。
 *
 * 示例 1:
 *              3
 *            /  \
 *           9   20
 *              /  \
 *             15   7
 * 输入: preorder = [3,9,20,15,7],
 *      inorder = [9,3,15,20,7]
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
 * @date: 2022/9/14
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root);
    }

    /*
     争哥思路: 找到左右子树的前中后序遍历结果，然后如何构建？ 从根-->左/右子节点
     执行用时：3 ms, 在所有 Java 提交中击败了46.40%的用户
     内存消耗：41.4 MB, 在所有 Java 提交中击败了31.42%的用户
    */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildMyTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    /*
     i和j表示的是当前根节点左子树的元素范围
     p和r表示的是当前根节点右子树的元素范围
     前序: 3,9,20,15,7
     中序: 9,3,15,20,7
  思路: 先在前序数组找到root节点，然后再到中序数组找到root的位置，这样在中序数组，根节点左边的就都是左子树；
       然后我们要到这些左子树和右子树中取寻找根节点;由于在中序数组确定了左子树和右子树的范围，因此我们再到前序
       数组，此时就可以在前序数组里确定左子树和右子树的范围了.
       (TODO 具体确定方法:因为前序遍历是，根->左子->右子排列，因此只需要知道左子的size和右子的size
             我们就可以确定前序数组的左子、右子树的范围了)
       然后下一个根节点就是范围里的第一个元素，找到后就可以开始构建树了，然后重复上述操作
    */
    private static TreeNode buildMyTree(int[] preorder,int i, int j, int[] inorder,int p,int r) {
        if (i>j && p>r) return null; //递归终止条件--表示
        int rootVal = preorder[i]; //前序数组的第一个元素为根节点
        int k = p;
        while (inorder[k] != rootVal) { //查找根节点在中序数组的位置
            k++;
        }
        //执行到这找到中序数组中根节点的位置，然后其左边就都是左子树[p,k-1]，右边都是右子树[k+1,r]
        int leftTreeSize = k - p; //左子树的节点数目
        int rightTreeSize = r - k; //右子树的节点数目
        //然后去前序数组去确定左子树和右子树的范围 左子树范围[i+1,i+leftTreeSize]; 右子树范围[i+leftTreeSize+1,i+leftTreeSize+rightTreeSize]
        //TODO 这样确定了前序数组的左右子树的范围后，就能找到其中的根节点了，然后开始构建二叉树

        //开始构建左子树: 缩小范围，从左子树的范围里开始处理，这里返回的是左子树的根节点
        TreeNode leftNode = buildMyTree(preorder, i + 1, i + leftTreeSize, inorder, p, k - 1);
        //开始构建右子树
        TreeNode rightNode = buildMyTree(preorder, i + leftTreeSize + 1, i + leftTreeSize + rightTreeSize, inorder, k + 1, r);
        TreeNode root = new TreeNode(rootVal);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

}
