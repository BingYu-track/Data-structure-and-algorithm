package lessons.week6.pratice.btree.part1.pratice16.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 对称二叉树 -- 复习
 * @Description: 给你一个二叉树的根节点 root，检查它是否轴对称。
 *
 * 示例 1：
      1
    /   \
   2     2
  / \   / \
 3   4 4   3
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 * @author: bingyu
 * @date: 2022/11/22
 */
public class IsSymmetric {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode leftNode2 = new TreeNode(2);
        TreeNode rightNode2 = new TreeNode(2);
        TreeNode leftNode3 = new TreeNode(3);
        TreeNode rightNode3 = new TreeNode(3);
        TreeNode leftNode4 = new TreeNode(4);
        TreeNode rightNode4 = new TreeNode(4);
        TreeNode leftNode5 = new TreeNode(5);
        TreeNode rightNode5 = new TreeNode(5);
        TreeNode leftNode6 = new TreeNode(6);
        TreeNode rightNode6 = new TreeNode(6);
        root.left = leftNode2;
        root.right = rightNode2;

        leftNode2.left = leftNode3;
        leftNode2.right = rightNode4;

        rightNode2.left = leftNode4;
        rightNode2.right = rightNode3;

//        leftNode3.left = leftNode5;
//        rightNode4.right = leftNode6;
//
//        rightNode3.left = rightNode5;
//        leftNode4.right = rightNode6;
        boolean symmetric = isSymmetric(root);
        System.out.println(symmetric);
    }

    /*
                      1
                   /     \
                  2       2
                 / \     / \
                3   4   4   3
               / \ / \ / \ / \
              7  8 9 6 6 9 8  7

        2 3 7 8 4 9 6 | 6 9 4 8 7 3 2
        */

    /*
     思路: 判断二叉树是否是轴对称,
     方法一: 可以前序遍历左子树，后序遍历右子树，如果遍历结果顺序颠倒说明是左右对称
     方法二: 只需要不断对比左右子树是否是镜像对称即可!
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：40.2 MB, 在所有 Java 提交中击败了7.04%的用户
    */

    private static boolean symmetric = true;

    private static boolean isSymmetric(TreeNode root) {
        if (root.left == null && root.right == null) return true;
        symmetric(root.left,root.right); //对比左右子树
        return symmetric;
    }

    private static void symmetric(TreeNode t1,TreeNode t2) {
        if (t1==null && t2==null) return;
        if (t1==null || t2==null || t1.val!=t2.val) { //左右子树一个为空;或者都不为空，但是左右子树根节点值不一样，肯定不是对称的;
            symmetric = false;
            return;
        }
        symmetric(t1.left,t2.right);
        symmetric(t1.right,t2.left);
    }


}
