package lessons.week6.pratice.btree.part1.pratice14.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 合并二叉树 -- 复习
 * @Description: 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * 示例 1：
 *
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 *
 * 示例 2：
 *
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 *
 * 提示：
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 *
 * @author: bingyu
 * @date: 2022/11/22
 */
public class MergeTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node13 = new TreeNode(3);
        TreeNode node12 = new TreeNode(2);
        TreeNode node15 = new TreeNode(5);
        root1.left = node13;
        root1.right = node12;
        node13.left = node15;
        TreeNode root2 = new TreeNode(2);
        TreeNode node21 = new TreeNode(1);
        TreeNode node23 = new TreeNode(3);
        TreeNode node24 = new TreeNode(4);
        TreeNode node27 = new TreeNode(7);
        root2.left = node21;
        root2.right = node23;
        node21.right = node24;
        node23.right = node27;
        TreeNode mergeTrees = mergeTrees(root1, root2);
        System.out.println(mergeTrees);
    }
    /*
            1               2                    3
          /   \           /   \                /   \
         3     2   +     1     3      =       4     5
        /                 \     \            / \     \
       5                   4     7          5   4     7
    */


    /*
       思路:
    */
    private static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        //执行到这里说明roo1和roo2均不为null
        root2.val = root2.val + root1.val;
        TreeNode leftNode = mergeTrees(root1.left, root2.left);
        TreeNode rightNode = mergeTrees(root1.right, root2.right);
        root2.left = leftNode;
        root2.right = rightNode;
        return root2;
    }




}
