package lessons.week6.pratice.btree.part1.pratice19.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 把二叉搜索树转换为累加树 -- 复习
 * @Description: 给出二叉搜索树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点node的新值等于原树中
 *              大于或等于node.val的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *
 * 示例 1：
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * 示例 2：
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 *
 *
 * 示例 3：
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 *
 *
 * 示例 4：
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 *
 *
 * 提示：
 * 树中的节点数介于0和10^4之间。
 * 每个节点的值介于 -10^4和10^4之间。
 * 树中的所有值互不相同 。
 * 给定的树为二叉搜索树。
 *
 * @author: bingyu
 * @date: 2022/11/23
 */
public class ConvertBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(2);
        node1.left = node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode(3);
        node4.right = node5;
        TreeNode node6 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        node2.left = node6;
        node2.right = node7;
        TreeNode node8 = new TreeNode(8);
        node7.right = node8;
        TreeNode tree = convertBST(root);
        System.out.println();
        /*
                            4                              30
                        /       \                       /       \
                       1         6                     36        21
                      / \       / \    ----->         /  \      /  \
                     0   2     5   7                 36  35    26  15
                          \         \                     \         \
                           3         8                     33        8
          */
    }

    private static int sum = 0;

    /*
     思路: 从左子树->根->右子树
    */
    private static TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private static void convert(TreeNode root) {
        if (root == null) return;
        convert(root.right);
        root.val = sum + root.val;
        sum = root.val;
        convert(root.left);
    }


}
