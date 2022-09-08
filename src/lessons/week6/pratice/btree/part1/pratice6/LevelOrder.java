package lessons.week6.pratice.btree.part1.pratice6;

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
 *
 * @author: bingyu
 * @date: 2022/8/29
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

    /*TODO: 题型2: 二叉树按层遍历
     我的思路: 使用队列，将节点存入队列；出队时将节点值存入结果数组，同时将左右节点入队；出队时再进行同样的操作直到队列为空
     执行用时：1 ms, 在所有 Java 提交中击败了97.78%的用户
     内存消耗：41.3 MB, 在所有 Java 提交中击败了60.29%的用户
    */
    public static int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                list.add(node.val);
                queue.add(node.left);
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
