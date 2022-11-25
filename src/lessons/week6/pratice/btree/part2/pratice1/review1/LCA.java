package lessons.week6.pratice.btree.part2.pratice1.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树的最近公共祖先 --- 复习(人需要多练习)
 * @Description: 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足x是 p、q 的祖先且 x 的深度尽可能大
 *  （一个节点也可以是它自己的祖先）。”
 *
 * 示例 1：
 *                          3
 *                       /     \
 *                      5       1
 *                     / \     / \
 *                    6   2   0   8
 *                       / \
 *                      7  4
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 *
 *
 * 示例 2：
 *                             3
 *                          /     \
 *                         5       1
 *                        / \     / \
 *                       6   2   0   8
 *                          / \
 *                         7  4
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 * 提示：
 * 树中节点数目在范围 [2, 10^5] 内。
 * -10^9 <= Node.val <= 10^9
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * @author: bingyu
 * @date: 2022/11/24
 */
public class LCA {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        node2.left = node5;
        node2.right = node6;

        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        node4.left = node7;
        node4.right = node8;
        TreeNode node = lowestCommonAncestor(root, node5, node6); //p=5 q=4
        System.out.println(node);
    }

    private static TreeNode lca = null;

    /*
     LCA问题思路: 首先两子节点必须要在同一棵树，且树的根节点深度最大,遍历过程中判断哪一个根节点的直接左右子树下就是p或者q节点，找到
     该根节点说明该节点就是LCA
    */
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LCA(root,p,q);
        return lca;
    }

    /**
     *
     * @param root
     * @param p
     * @param q
     * @return 0:表示root不包含p或者q;  1:表示root包含p或者q;  2:表示root即包含p，也包含q;
     * 难点是如何判断p下面是否有q节点，或者q下面是否有p节点
     */
    private static int LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;
        if (lca != null) return 2;
        int k = 0;
        int left = 0;
        int right = 0;
        if (root == p) { //根节点是p，则从p节点往下寻找q
            k = 1;
            left = LCA(p.left, q, q);
            right = LCA(p.right, q, q);
        }else if (root == q) { //根节点是q，则从q节点往下寻找p
            k = 1;
            left = LCA(q.left, p, p);
            right = LCA(q.right, p, p);
        }else {         //根节点不是p或者q的话，继续正常执行
            left = LCA(root.left, p, q);
            right = LCA(root.right, p, q);
        }
        if (k + left + right == 2 && lca == null) { //防止后面lca被覆盖
            lca = root;
        }
        return k + left + right;
    }




}
