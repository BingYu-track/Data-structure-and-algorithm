package lessons.week6.pratice.btree.part1.pratice8;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: 剑指 Offer 32 - III. 从上到下打印二叉树 III --争哥解法
 * @author: bingyu
 * @date: 2022/9/8
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);
        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    /*
     争哥解法: 使用栈数据结构来解决，因为按照题目意思，节点顺序是不断变化的，就很适合栈!
            注意一个栈是不够用的，需要联合使用2个栈，
            推荐该方法--需要多次练习
    */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Stack<TreeNode>[] stacks = new Stack[2];
        for (int i = 0; i < 2; ++i) {
            stacks[i] = new Stack<TreeNode>();
        }
        int turn = 0;
        stacks[turn].add(root);
        while (!stacks[turn].isEmpty()) {
            List<Integer> curLevelNodes = new ArrayList<>(); // 记录本层节点
            while(!stacks[turn].isEmpty()) { //标识的栈为空停止循环
                TreeNode treeNode = stacks[turn].pop();
                curLevelNodes.add(treeNode.val);
                if (turn==0) { //先左后右
                    if (treeNode.left != null) {
                        stacks[1].push(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        stacks[1].push(treeNode.right);
                    }
                } else { //先右后左
                    if (treeNode.right != null) {
                        stacks[0].push(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        stacks[0].push(treeNode.left);
                    }
                }
            }
            result.add(curLevelNodes); //执行到这里说明turn标识的栈为空了，需要到另外一个栈去处理
            turn = (turn + 1) % 2; //这里是 如果turn是0，就返回1，如果turn是1就返回0
        }
        return result;
    }

}
