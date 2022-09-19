package lessons.week6.pratice.btree.part2.pratice3;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 二叉树展开为链表--重复练习
 * @author: bingyu
 * @date: 2022/9/19
 */
public class RepeatPratice {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        flatten(root);
        System.out.println(root);
    }

    /*
    示例：
            1
         /    \
        2      5     ---->     1->2->3->4->5->6
       / \      \
      3   4      6
    输入：root = [1,2,5,3,4,null,6]
    输出：[1,null,2,null,3,null,4,null,5,null,6]
    思路；从题意得到要前序遍历顺序展开为链表

    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：41.2 MB, 在所有 Java 提交中击败了33.15%的用户
    */
    private static TreeNode dummyHead = new TreeNode(-1); //虚拟头节点
    private static TreeNode tail = dummyHead; //尾指针
    public static void flatten(TreeNode root) {
        flat(root);
    }

    private static void flat(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left; //这里我需要暂存的
        TreeNode right = root.right; //这里我需要暂存的
        //TODO 这里是关键逻辑处理
        tail.right = root;
        root.left = null;
        tail = root;

        flat(left);
        flat(right);
    }


}
