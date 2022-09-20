package lessons.week6.pratice.btree.part2.pratice4;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 面试题 17.12. BiNode--重复练习
 * @author: bingyu
 * @date: 2022/9/20
 */
public class RepeatPratice {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(0);
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        TreeNode node6 = new TreeNode(6);
        node2.right = node6;

        TreeNode treeNode = convertBiNode(root);
        System.out.println(treeNode);
    }

    /*
        示例：
                      4
                    /   \
                   2     5          ---->   0->1->2->3->4->5->6
                  / \     \
                 1   3     6
                /
               0
        输入： [4,2,5,1,3,null,6,0]
        输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]

        发现要使展开后的链表同样符合二叉搜索树，需要进行中序遍历展开
    */

    private static TreeNode dummyHead = new TreeNode(-1); //虚拟头节点
    private static TreeNode tail = dummyHead;
    public static TreeNode convertBiNode(TreeNode root) {
        inOrder(root);
        return dummyHead.right;
    }

    private static void inOrder(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;

        inOrder(left);
        tail.right = root;
        root.left = null;
        tail = root;
        inOrder(right);
    }

}
