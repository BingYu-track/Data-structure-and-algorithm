package lessons.week6.pratice.btree.part2.pratice2;

import lessons.common.TreeNode;

/**
 * @version 1.0 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * @Description: 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度
 * 尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树: root =[6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 *                          6
 *                       /     \
 *                      2       8
 *                     / \     / \
 *                    0   4   7  9
 *                       / \
 *                      3   5
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中
 *
 * @author: bingyu
 * @date: 2022/9/9
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(8);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        node2.left = node5;
        node2.right = node6;

        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(5);
        node4.left = node7;
        node4.right = node8;
        TreeNode node = lowestCommonAncestor(root,node7,node8); //p=2 q=9
        System.out.println(node);
    }

    /*
    我的思路: 解法1--自然就是求左右子树节点数的解法，和练习1是一样的，但是这样时间复杂度会比较高
            解法2--由于是二叉搜索树，因此可以直接根据二叉搜索树的性质找p/q节点，只要找到第一个节点的左右子树都有p/q节点即可
             当前节点就是我们要找的，因为只有lca的左右子树才会有p/q节点
    */

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode tmp = root;
        while (true) {
            if (p.val < tmp.val && q.val < tmp.val) {
                tmp = tmp.left;
            }else if (p.val > tmp.val && q.val > tmp.val) {
                tmp = tmp.right;
            }else {
                //执行到这里说明q和p分别在root的左右子树上，那么此时根节点就是我们要找的
                return tmp;
            }
        }
    }


}
