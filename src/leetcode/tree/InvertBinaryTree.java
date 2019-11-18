package leetcode.tree;

import tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 翻转二叉树
 * @author: bingyu
 * @date: 2019/11/11 20:11
 */
public class InvertBinaryTree {

    //方法一：递归
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        //左右子树交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //drill down
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //方法二：迭代法(比递归时间用的更多一点)
    public static TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        System.out.println(queue);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); //出队当前节点，再得到当前节点的左右子节点，交换位置后放入队列中
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
            System.out.println(queue);
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);
        root.left = node1; root.right = node2;
        node1.left = node3; node1.right = node4;
        node2.left = node5; node2.right = node6;
        TreeNode invertTree = invertTree(root);
        TreeNode invertTree2 = invertTree2(root);
        System.out.println(invertTree2);
    }
}
