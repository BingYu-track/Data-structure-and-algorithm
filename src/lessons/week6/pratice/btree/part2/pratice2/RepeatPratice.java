package lessons.week6.pratice.btree.part2.pratice2;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 二叉搜索树的最近公共祖先--重复练习
 * @author: bingyu
 * @date: 2022/9/19
 */
public class RepeatPratice {

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
    例如，给定如下二叉搜索树: root =[6,2,8,0,4,7,9,null,null,3,5]
    示例 1:
                         6
                      /     \
                     2       8
                    / \     / \
                   0   4   7   9
                      / \
                     3   5
    输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    输出: 6
    解释: 节点 2 和节点 8 的最近公共祖先是 6。

  TODO 思路:由于是二叉搜索树，这样的话，我们可以根据p/q的大小来确定p、q是否在root的左右子树;
        1.只要找到第一个节点的左右子树都有p/q节点即可；当前节点就是我们要找的，因为只有
        lca的左右子树才会有p/q节点;
        2.或者root节点直到等于p/q(在这之前都没有满足左右子树有p/q)。当前节点也属于我们要找的

        执行用时：5 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗：42.2 MB, 在所有 Java 提交中击败了87.57%的用户
    */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode tmp = root;
        while (true) {
            int rootValue = tmp.val;
            if (tmp!=p && tmp!=q) { //左右子树
                if ((rootValue > p.val && rootValue < q.val) || (rootValue < p.val && rootValue > q.val)) {
                    break;
                }else if (rootValue > p.val && rootValue > q.val) { //
                    tmp = tmp.left;
                }else if (rootValue < p.val && rootValue < q.val) { //
                    tmp = tmp.right;
                }
            }else { //tmp=p || tmp=q TODO 当前节点等于p或者q之前，都没有找到lca，说明lca就是p或者q节点的其中一个
                break;
            }
        }
        return tmp;
    }

}
