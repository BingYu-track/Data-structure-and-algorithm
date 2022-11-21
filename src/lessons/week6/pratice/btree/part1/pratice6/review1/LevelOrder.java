package lessons.week6.pratice.btree.part1.pratice6.review1;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0 剑指 Offer 32 - I. 从上到下打印二叉树
 * @Description: 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回：
 * [3,9,20,15,7]
 *
 * 提示：
 *
 * 节点总数 <= 1000
 * @author: bingyu
 * @date: 2022/11/18
 */
public class LevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;

        node2.left = node3;
        node2.right = node4;
        int[] result = levelOrder(root);
        System.out.println(Arrays.toString(result));
    }

    private static int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }

        }
        int[] result = new int[list.size()];
        for (int i = 0;i<result.length;i++) {
            result[i] = list.get(i);
        }
        return result;
    }


}
