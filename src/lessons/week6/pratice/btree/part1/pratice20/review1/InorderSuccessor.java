package lessons.week6.pratice.btree.part1.pratice20.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 面试题 04.06. 后继者 -- 复习
 * @Description: 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *         [1,2,3,4,5,6]
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * 输出: null
 *
 * @author: bingyu
 * @date: 2022/11/24
 */
public class InorderSuccessor {

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
        System.out.println(node.left);
        System.out.println(node.right);
    }

    private static boolean flag = false; //用来记录是否是指定节点的下一个节点
    private static TreeNode node = null;

    /*
     思路:  根据中序遍历的顺序，找出指定节点的下一个节点并返回
    */
    private static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inOrder(root,p);
        return node;
    }

    private static void inOrder(TreeNode root, TreeNode p) {
        if (root == null) return;
        inOrder(root.left,p);
        if (flag) {
            flag = false;
            node = root;
            return;
        }
        if (root == p) {
            flag = true;
        }
        inOrder(root.right,p);
    }


}
