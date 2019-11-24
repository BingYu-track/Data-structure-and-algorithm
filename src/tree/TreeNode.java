package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2019/11/18 19:33
 */
public class TreeNode {

    private int val;
    private boolean flag;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(){}

    public TreeNode(int val){
        this.val = val;
    }

    //前序遍历：同样根节点，先经过先访问
    public static void preOrder(TreeNode root) {
        System.out.print("前序遍历开始：");
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { //不等于null说明有左子树
                stack.push(cur);
                System.out.print(cur.val + ",");
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop(); //根节点出栈并打印根节点
                cur = pop.right;
            }
        }
    }


    //非递归中序遍历：思路：树的经历结点完整的路径是"根节点-->左子树-->根节点-->右子树-->根节点" ，每个节点都必须经过三次，由于是中序遍历，
    // 根节点是先经过，但此时不能访问，等访问左子树才能再访问根节点，因此根节点我们需要提前存入，先存的后访问，自然就想到"栈"这个数据结构
    public static void inOrder(TreeNode root){
        System.out.print("中序遍历开始：");
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { //不等于null说明有左子树
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop(); //根节点出栈并打印根节点
                System.out.print(pop.val + ",");
                cur = pop.right;
            }
        }
    }

    //后序非递归遍历
    public static void postOrder(TreeNode root) {
        System.out.print("后序遍历开始：");
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {

        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode a = new TreeNode(6);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(9);
        TreeNode d = new TreeNode(3);
        root.left = a;
        root.right = b;
        root.left.right = c;
        root.right.right = d;
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "val=" + val + '}';
    }


}
