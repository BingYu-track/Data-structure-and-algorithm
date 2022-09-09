package lessons.week6.pratice.btree.part1.pratice1;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0 TODO: 需要重点重复练习
 * @Description: 二叉树的前序遍历--争哥解法
 * @author: bingyu
 * @date: 2022/9/8
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        List<Integer> list = preorderTraversal(root);
        System.out.println(list);
    }

    /*
     争哥的迭代解法: 使用栈来模拟递归函数调用栈
                     1
                   /   \
                  2     3
                 / \
                4   5
                   / \
                   6  7
      TODO:主要看当前栈顶元素节点: 推荐该解法 需要多次练习掌握
            1.结点左、右子树均未遍历时---左子节点入栈
            2.左子树遍历完成，右子树还未遍历时---左子节点出栈，右子节点入栈
            3.左右子树都遍历完成 ---该节点出栈
    */

    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return result;
        Stack<SFrame> stack = new Stack<>();
        stack.push(new SFrame(1, root)); //刚入栈，为1状态
        while (!stack.isEmpty()) {
            //栈顶元素3种状态处理
            //TODO: 状态1--左右子树均未遍历，要开始遍历左子树
            if (stack.peek().status == 1) {
                result.add(stack.peek().node.val); // 前序(step=1)
                //TODO :注意要先变更状态，再将左子节点存入栈
                stack.peek().status = 2; //提前标识当前栈顶元素节点的左子树已经遍历，下次该节点暴露到栈顶之后发现状态为2，就知道要遍历其右子树
                if (stack.peek().node.left != null) { //左子节点不为空，将左子节点压入栈
                    stack.push(new SFrame(1, stack.peek().node.left));
                }
                continue; //继续遍历，此时栈顶节点就是前面"stack.peek().node.left"节点，即左子节点
            }
            //TODO：状态2--左子树遍历完成，开始遍历右子树
            if (stack.peek().status == 2) {
                stack.peek().status = 3;
                if (stack.peek().node.right != null) {
                    stack.push(new SFrame(1, stack.peek().node.right));
                }
                continue;
            }
            //TODO: 状态3--左右子树都遍历完成，当前根节点出栈
            if (stack.peek().status == 3) {
                stack.pop();
            }
        }
        return result;
    }

    /**
     * 定义的栈帧
     */
    private static class SFrame {
        public int status = 1; //用以表示节点遍历时的上面3种状态，对应3种栈的操作
        public TreeNode node = null;
        public SFrame(int status, TreeNode node) {
            this.status = status;
            this.node = node;
        }
    }
    static List<Integer> result = new ArrayList<>();


}
