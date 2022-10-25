package lessons.week6.pratice.btree.part1.pratice20;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 面试题 04.06. 后继者--重复练习
 * @author: bingyu
 * @date: 2022/10/25
 */
public class ZgSolved {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(1);
        node3.left = node5;
        TreeNode node = inorderSuccessor(root, node5);
        System.out.println(node);
    }

    private static TreeNode result = null; //用来记录结果节点

    private static boolean flag = false; //用于标识下一个节点就是要取的
    /*
      寻找中序遍历期间指定的后序节点
     */
    private static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root,p);
        return result;
    }

    private static void inorder(TreeNode root, TreeNode p) {
        if (root == null) return;
        inorder(root.left,p);
        if (flag && result == null) result = root;
        if (root == p) {
            flag = true;
        }
        inorder(root.right,p);
    }


}
