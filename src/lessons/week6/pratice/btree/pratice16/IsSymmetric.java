package lessons.week6.pratice.btree.pratice16;

import lessons.common.TreeNode;


/**
 * @version 1.0 对称二叉树
 * @Description: 给你一个二叉树的根节点 root，检查它是否轴对称。
 *
 * 示例 1：
                     1
                   /   \
                  2     2
                 / \   / \
                3  4  4   3

 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 * @author: bingyu
 * @date: 2022/9/1
 */
public class IsSymmetric {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode leftNode2 = new TreeNode(2);
        TreeNode rightNode2 = new TreeNode(2);
        TreeNode leftNode3 = new TreeNode(3);
        TreeNode rightNode3 = new TreeNode(3);
        TreeNode leftNode4 = new TreeNode(4);
        TreeNode rightNode4 = new TreeNode(4);
        TreeNode leftNode5 = new TreeNode(5);
        TreeNode rightNode5 = new TreeNode(5);
        TreeNode leftNode6 = new TreeNode(6);
        TreeNode rightNode6 = new TreeNode(6);
        root.left = leftNode2;
        root.right = rightNode2;

        leftNode2.left = leftNode3;
        leftNode2.right = rightNode4;

        rightNode2.left = leftNode4;
        rightNode2.right = rightNode3;

//        leftNode3.left = leftNode5;
//        rightNode4.right = leftNode6;
//
//        rightNode3.left = rightNode5;
//        leftNode4.right = rightNode6;
        boolean symmetric = isSymmetric2(root);
        System.out.println(symmetric);
        /*
                      1
                    /   \
                   2     2
                  / \   / \
                 3   4 4   3
                / \ /\ /\  /\
               7  8 9 66 9 8 7
        */
    }

    /*
    我的思路: 判断一棵二叉树是否是对称二叉树，不能通过左右子树是对称就说明该树是对称的比如下面的例子:
                7
              /   \
             5     6
            / \   / \
           4  4  3   3
        TODO 根节点7的左右子树都是对称二叉树，但是整棵树并不是对称二叉树，子问题不能推导出原问题的解，因此不能按照题目原模原样的去抽象成递归,需要
             我们将其转换为其它原问题从而能被其子问题推导出来。
        我们先思考这样一个问题，判断2棵树是不是互为镜像对称的?
        判断2棵是否镜像对称，假设2棵树分别是t1、t2，那么就先要判断t1.left和t2.right以及t1.right和t2.left是否镜像对称；如果满足，然后
        t1.val==t2.val后，就能说明t1与t2互为镜像对称，这样就可以将"一棵树是否是对称树"转换为"左右子树是否互为镜像对称"这样的问题，
         并且这样是可以通过子问题推导出原问题的解的

         执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         内存消耗：39.3 MB, 在所有 Java 提交中击败了96.56%的用户
    */
    private static boolean symmetric = true;

    public static boolean isSymmetric2(TreeNode root) {
        symmetric(root.left,root.right);
        return symmetric;
    }

    /*
     比较左右子树是否互为镜像对称，左子树的left和右子树的right对比
    */
    private static void symmetric(TreeNode t1,TreeNode t2) {
        if (t1 == null && t2 == null) return;
        if (t1 == null || t2 == null) { //如果t1或者t2有一个为null就说明非镜像
            symmetric = false;
            return;
        }
        if (!symmetric) return; //提前退出
        //执行到这里说明t1和t2都不为null
        if (t1.val != t2.val) { //如果左右节点值不一样，则非镜像对称
            symmetric = false;
            return;
        }
        symmetric(t1.left,t2.right); //比较t1的左子树和t2的右子树
        symmetric(t1.right,t2.left); //比较t1的右子树和t2的左子树
    }


}
