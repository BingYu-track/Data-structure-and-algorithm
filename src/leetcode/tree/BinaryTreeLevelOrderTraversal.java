package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 102. 二叉树的层次遍历(中等)
 * @author: bingyu
 * @date: 2019/11/24 23:04
 */
public class BinaryTreeLevelOrderTraversal {

    //自己想到的方法： 使用队列(推荐)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            int size = queue.size(); //这里返回的每一层结点的个数
            List<Integer> tlist = new ArrayList<>();
            while (size-- >0) { //这里就是通过队列的结点数来控制每层结点的循环
                TreeNode poll = queue.poll();
                tlist.add(poll.val); //这里将每层结点添加进list当中
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            list.add(tlist);
        }
        return list;
    }

    //方法二：递归
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        return list;
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
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }
}
