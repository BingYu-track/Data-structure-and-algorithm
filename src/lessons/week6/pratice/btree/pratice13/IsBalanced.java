package lessons.week6.pratice.btree.pratice13;

import lessons.common.TreeNode;

/**
 * @version 1.0 剑指 Offer 55 - II. 平衡二叉树
 * @Description: 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回false 。
 *
 * 限制：
 * 0 <= 树的结点个数 <= 10000
 *
 *
 * @author: bingyu
 * @date: 2022/8/31
 */
public class IsBalanced {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        boolean balanced = isBalanced(root);
        System.out.println(balanced);
    }

    /*avl的定义；所有节点左右子树高度相差不能大于1
     我的思路: 注意一个节点的左右子树都是avl的话，并不能证明该节点就是avl，示例2就是一个反例
     因此我们还是要遍历二叉树，去检查每一个节点的子树高度是否相差大于1才行(即每一个节点都必须是avl)。

     时间复杂度: O(n)
     空间复杂度:O(h)
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：40.8 MB, 在所有 Java 提交中击败了83.80%的用户
    */

    private static boolean isAvl = true;

    public static boolean isBalanced(TreeNode root) {
        getNodeHeight(root);
        return isAvl;
    }

    public static int getNodeHeight(TreeNode root) { //
        if (root == null) return 0;
        int lh = getNodeHeight(root.left); //获取左子树的高度
        int rh = getNodeHeight(root.right); //获取右子树的高度
        if (Math.abs(lh - rh) > 1) {
            isAvl = false;
        }
        return Math.max(lh,rh) + 1; //返回当前root节点的高度
    }

}
