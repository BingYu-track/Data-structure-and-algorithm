package leetcode.tree;

import tree.Tree;

/**
 * @version 1.0
 * @Description: 翻转二叉树
 * @author: bingyu
 * @date: 2019/11/11 20:11
 */
public class InvertBinaryTree {

    //方法一：递归
    public Tree.TreeNode invertTree(Tree.TreeNode root) {
        //1.终止条件
        if (root == null) {
            return null;
        }

        //2.处理当前层
        Tree.TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //3.drill down
        invertTree(root.left); //翻转左子树
        invertTree(root.right); //翻转右子树

        return root; //返回当前的根结点
        //4.恢复状态
    }

    public static void main(String[] args) {
        Tree.TreeNode root = new Tree.TreeNode(4);
        Tree.TreeNode a = new Tree.TreeNode(2);
        Tree.TreeNode b = new Tree.TreeNode(1);
        Tree.TreeNode c = new Tree.TreeNode(3);
        Tree.TreeNode d = new Tree.TreeNode(7);
        Tree.TreeNode e = new Tree.TreeNode(6);
        Tree.TreeNode f = new Tree.TreeNode(9);
        root.left = a; root.right = d;
        a.left = b; a.right = c;
        d.left = e; d.right = f;

    }
}
