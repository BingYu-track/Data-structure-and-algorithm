package lessons.week6.pratice.btree.part2.pratice1;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 二叉树的最近公共祖先--重复练习
 * @author: bingyu
 * @date: 2022/9/19
 */
public class RepeatPratice {

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
                         3
                      /     \
                     5       1
                    / \     / \
                   6   2   0   8
                      / \
                     7   4
          我的思路: 在递归遍历期间，
          1.如果root不是p或者q，则校验每个节点的左右子树是否都存在p或者q节点,如果存在，则该root就是我们要找的。
          2.如果root是p或者q，则校验root

     */
    private static TreeNode lca = null;
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfsLCA(root,p,q);
        return lca;
    }

    /*
    执行用时：6 ms, 在所有 Java 提交中击败了99.99%的用户
    内存消耗：42.7 MB, 在所有 Java 提交中击败了65.85%的用户
     */
    private static int dfsLCA(TreeNode root, TreeNode p, TreeNode q) {
        int flag = 0; //用来标记当前root是否是p或者q，0表示root不是p/q，1表示root是p/q
        if (root == null || lca != null) return flag;
        if (root==p || root==q) {
            flag = 1;
        }
        int leftNum = dfsLCA(root.left, p, q); //查找root左子树中p,q的节点个数
        int rightNum = dfsLCA(root.right, p, q); //查找root右子树p,q的节点个数
        if (flag == 0 && leftNum == 1 && rightNum == 1) { //如果左子树和右子树分别有p/q节点；
            lca = root;
        }
        if (flag == 1 && (leftNum == 1 || rightNum == 1)) { //root本身是p/q，并且左子树或者右子树其中一个有p/q
            lca = root;
        }
        return leftNum + rightNum + flag; //TODO: 注意这里要返回的是root整个树包含的p/q节点
    }


}
