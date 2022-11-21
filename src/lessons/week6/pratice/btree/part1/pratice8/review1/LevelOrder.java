package lessons.week6.pratice.btree.part1.pratice8.review1;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0 剑指 Offer 32 - III. 从上到下打印二叉树 III --复习
 * @Description: 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
 * 第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 *
 *                     3
 *                /         \
 *               9           20
 *            /     \      /    \
 *           10     11    15     7
 *          / \    / \   /  \   / \
 *         13 14 16  17 18 19  21 22
 * 返回其层次遍历结果：
 *
 * [[3],[20,9],[10,11,15,7],[22,21,19,18,17,16,14,13]]
 *
 *
 * 提示：
 * 节点总数 <= 1000
 *
 * @author: bingyu
 * @date: 2022/11/21
 */
public class LevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(13);
        TreeNode node8 = new TreeNode(14);
        TreeNode node9 = new TreeNode(16);
        TreeNode node10 = new TreeNode(17);
        TreeNode node11 = new TreeNode(18);
        TreeNode node12 = new TreeNode(19);
        TreeNode node13 = new TreeNode(21);
        TreeNode node14 = new TreeNode(22);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = node7;
        node3.right = node8;

        node4.left = node9;
        node4.right = node10;

        node5.left = node11;
        node5.right = node12;

        node6.left = node13;
        node6.right = node14;
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    /*奇数正序，偶数倒序  root.left root.right
      遇到偶数时，倒序添加到list，子元素仍然正常添加到队列中
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false; //用来判断是否需要进行倒序
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                if (node!=null) {
                    if (flag) {
                        list.addFirst(node.val);
                        queue.add(node.left);
                        queue.add(node.right);
                    }else {
                        list.add(node.val);
                        queue.add(node.left);
                        queue.add(node.right);
                    }
                }
            }
            flag = !flag; //执行到这里说明一层执行完毕，需要改变状态
            if (list.size()>0) {
                lists.add(list);
            }
        }
        return lists;
    }


}
