package lessons.week6.pratice.btree.part2.pratice1;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树的最近公共祖先
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
 * @date: 2022/9/8
 */
public class LowestCommonAncestor {

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
        TreeNode node = lowestCommonAncestor(root, node1, node8); //p=5 q=4
        System.out.println(node);
    }

    /*
     争哥思路: LCA有一个共同特点，就是左子树有一个p或者q,右子树有一个q或者p;其它不是最近的公共祖先，那么只会有一个子树存在p,q;
        因此这道题就转换成了，找左右子树包含多少个p/q，然后根节点注意是否是p/q即可

        执行用时：6 ms, 在所有 Java 提交中击败了99.99%的用户
        内存消耗：42.7 MB, 在所有 Java 提交中击败了64.53%的用户
    */
    private static TreeNode lca = null;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return lca;
    }

    /**
     *
     * @param root 根节点
     * @param p
     * @param q
     * @return
     */
    private static int dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;
        int constNum = 0;
        if (root == p || root == q) { //每次遍历时判断根节点是否是p或者q节点
            constNum = 1;
        }
        int leftNum = dfs(root.left, p, q); //root的左子树存在多少p或者q节点
        if (lca != null) return 2; //
        int rightNum = dfs(root.right, p, q); //root的右子树存在多少p或者q节点
        if (lca != null) return 2; //
        if (constNum == 0 && leftNum == 1 && rightNum == 1) { //根节点不是p/q，但是左右子树都有p/q，则root就是lca
            lca = root;
        }
        if (constNum == 1 && (leftNum == 1 || rightNum == 1)) {
            lca = root;
        }
        return constNum + leftNum + rightNum;
    }


}
