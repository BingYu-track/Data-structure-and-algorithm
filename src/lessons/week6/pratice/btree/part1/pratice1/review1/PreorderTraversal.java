package lessons.week6.pratice.btree.part1.pratice1.review1;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 二叉树的前序遍历(复习)
 * @Description: 给你二叉树的根节点 root ，返回它节点值的前序遍历。
 *
 * 示例 1：
 * 1
 *  \
 *   2
 *  /
 * 3
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 *     1
 *    /
 *   2
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 *   1
 *    \
 *     2
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * leetcode的二叉树输入顺序是从上到小一个节点，一个节点的遍历
 *
 *
 * @author: bingyu
 * @date: 2022/11/14
 */
public class PreorderTraversal {

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
        List<Integer> preorder = preorderTraversal(root);
        System.out.println(preorder);

        List<Integer> inorder = inorderTraversal(root);
        System.out.println(inorder);

        List<Integer> postorder = postorderTraversal(root);
        System.out.println(postorder);
    }




    /*       前序遍历:根->左->右
             中序遍历:左->根->右
             后序遍历:左->右->根
          1    [1,2,3,4,5,6,7]  -->预期[1,2,4,5,3,6,7]
        /   \
       2     3
      / \   /  \
     4   5 6    7
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root,list);
        return list;
    }

    private static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preOrder(root.left,list);
        preOrder(root.right,list);
    }


    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root,list);
        return list;
    }

    private static void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrder(root.left,list);
        list.add(root.val);
        inOrder(root.right,list);
    }

    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root,list);
        return list;
    }

    private static void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postOrder(root.left,list);
        postOrder(root.right,list);
        list.add(root.val);
    }

}
