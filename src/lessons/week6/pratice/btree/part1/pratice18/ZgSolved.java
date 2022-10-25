package lessons.week6.pratice.btree.part1.pratice18;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 剑指 Offer 54. 二叉搜索树的第k大节点---重复练习
 * @author: bingyu
 * @date: 2022/10/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);

        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);

        TreeNode node5 = new TreeNode(1);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node3.left = node5;
        int i = kthLargest(root,4);
        System.out.println(i);
    }

    /*
     利用二叉搜索树的性质，由于中序遍历的结果是有序的,因此后序遍历就是倒序的
     我们只要得到后序序遍历的结果，然后取第k个即可
     */
    private static int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        postorder(root,list);
        System.out.println(list);
        return list.get(k-1);
    }

    private static void postorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorder(root.right,list);
        list.add(root.val);
        postorder(root.left,list);
    }


}
