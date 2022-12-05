package lessons.week6.pratice.btree.part2.pratice8;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 根据前序和后序遍历构造二叉树--争哥解法
 * @author: bingyu
 * @date: 2022/9/15
 */
public class ZgSolved {

    public static void main(String[] args) {
//        int[] preorder = {1,2,4,5,3,6,7};
//        int[] postorder = {4,5,2,6,7,3,1};
        int[] preorder = {2,1};
        int[] postorder = {1,2};
        TreeNode treeNode = constructFromPrePost(preorder, postorder);
        System.out.println(treeNode);
    }

    /*
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：40.9 MB, 在所有 Java 提交中击败了85.16%的用户
    */
    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildTree(preorder,0,preorder.length - 1,postorder,0,postorder.length - 1);
    }

    /*
     争哥解法思路:
     前序: 1,2,4,5,3,6,7
     后序: 4,5,2,6,7,3,1
    */
    private static TreeNode buildTree(int[] pre, int i, int j, int[] post, int p, int r) {
        if (i>j) return null;
        TreeNode root = new TreeNode(pre[i]);
        if (i == j) return root;//注意这⼀⾏跟前⾯⼏题不⼀样
        // 在post中，查询pre[i+1]所在的位置q，[p, q] [q+1, r-1] r(root)
        int q = p;
        while (post[q] != pre[i+1]) {
            q++;
        }
        //左⼦树⼤⼩
        int leftTreeSize = q-p+1;
        // 构建左⼦树
        TreeNode leftNode = buildTree(pre, i+1, i+leftTreeSize, post, p, q);
        // 构建右⼦树
        TreeNode rightNode = buildTree(pre, i+leftTreeSize+1, j, post, q+1, r-1);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }


}
