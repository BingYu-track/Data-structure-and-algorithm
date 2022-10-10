package lessons.week6.pratice.btree.part1.pratice6;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 剑指 Offer 32 - I. 从上到下打印二叉树 --复习
 * @author: bingyu
 * @date: 2022/10/10
 */
public class ZgSolved {

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

    /*
     按层遍历
    */
    public static int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                if (node!=null) { //不为空才继续向下处理
                    list.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i=0;i<result.length;i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}
