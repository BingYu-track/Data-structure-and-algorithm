package lessons.week6.pratice.btree.part1.pratice14;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 合并二叉树--争哥解法
 * @author: bingyu
 * @date: 2022/9/13
 */
public class ZgSolved {

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
        TreeNode mergeTrees = mergeTrees2(root1, root2);
        System.out.println(mergeTrees);
    }

    //争哥解法(需要练习)。争哥的解法是自己新创建了新节点，比较费空间，我的是在原来树的基础上进行合并
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode newNode = new TreeNode(t1.val+t2.val); //对应的t1,t2结点都不为空，则相加
        // merge左⼦树
        TreeNode leftRoot = mergeTrees(t1.left, t2.left);
        // merge右⼦树
        TreeNode rightRoot = mergeTrees(t1.right, t2.right);
        // 拼接root、左⼦树、右⼦树
        newNode.left = leftRoot;
        newNode.right = rightRoot;
        return newNode;
    }


    /*
     复习:合并二叉树
               1                       2                                3
             /   \                   /   \                            /   \
            3     2                 1     3        ---->             4     5
           /                         \     \                        / \     \
          5                           4     7                      5   4     7
    */
    public static TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        //执行到这里说明t1,t2都不为null，开始进行合并，将t1的解答全部合并到t2上
        root2.val = root2.val + root1.val;
        TreeNode left = mergeTrees2(root1.left, root2.left);
        TreeNode right = mergeTrees2(root1.right, root2.right);
        root2.left = left; //这里必须重新赋值，因为root2的left或者right可能没有，但是另外一颗树上是有的
        root2.right = right;
        return root2;
    }

}
