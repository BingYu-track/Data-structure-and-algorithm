package lessons.week6.pratice.btree.part1.pratice18.review1;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 剑指 Offer 54. 二叉搜索树的第k大节点 -- 复习
 * @Description: 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * @author: bingyu
 * @date: 2022/11/23
 */
public class KthLargest {

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
        int i = kthLargest(root,2);
        System.out.println(i);
    }

    /*
     思路: 二叉搜索树进行中序遍历得到顺序元素，然后倒序取k对应的元素即可!
    */
    private static int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        int size = list.size();
        return list.get(size - k);
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }


}
