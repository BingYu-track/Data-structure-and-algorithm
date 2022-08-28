package lessons.week6.example.btree.example3;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 二叉树的后序遍历
 * @author: bingyu
 * @date: 2022/8/24
 */
public class PostorderTraversal {

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
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        List<Integer> list = postorderTraversal(root);
        System.out.println(list);
    }

    /*
            1    [1,2,3,4,5,6,7]  -->预期[4,5,2,6,7,3,1]
          /   \
         2     3
        / \   /  \
       4   5 6    7
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root,list);
        return list;
    }

    //假设此时root是最后一层的4
    private static void postorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorder(root.left,list); //为null返回
        postorder(root.right,list); //为null返回
        list.add(root.val); //把4存进去，
    }


}
