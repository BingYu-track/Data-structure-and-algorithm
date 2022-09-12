package lessons.week6.pratice.btree.part1.pratice10;

import lessons.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 找树左下角的值--争哥解法
 * @author: bingyu
 * @date: 2022/9/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node5.left = node7;
        int blv = findBottomLeftValue(root);
        System.out.println(blv);
    }

    /*
     争哥解法: 和我的思路是一样的
    */
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = -1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result = node.val;
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return result;
    }

}
