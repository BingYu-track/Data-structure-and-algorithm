package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: 二叉树的遍历
 * @author: bingyu
 * @date: 2019/10/30 23:00
 */
public class Tree {

    public static class TreeNode{
        public int val;
        public TreeNode left,right;

        public TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    //TODO: 方法一  递归

    //前序遍历
    public void preOrder(TreeNode root){
        if (root != null) {  //如果根结点不为空
            System.out.print(root.val+",");
            if (root.left != null) { //如果左结点不为null，则将该左结点作为根结点继续遍历它的左结点
                preOrder(root.left);
            }
            //开始遍历右结点
            if (root.right != null) {
                preOrder(root.right);
            }
        }
    }

    //中序遍历
    public void inorder(TreeNode root){
        if (root != null) {  //如果根结点不为空
            if (root.left != null) { //如果左结点不为null，则将该左结点作为根结点继续遍历它的左结点
                inorder(root.left);
            }
            //执行到这里说明root.left==null，即左节点已遍历完成
            System.out.print(root.val+",");
            //开始遍历右结点
            if (root.right != null) {
                inorder(root.right);
            }
            //System.out.print(root.val+",");
        }
    }

    //后序遍历
    public void postOrder(TreeNode root){
        if (root != null) {  //如果根结点不为空
            if (root.left != null) { //如果左结点不为null，则将该左结点作为根结点继续遍历它的左结点
                postOrder(root.left);
            }
            //开始遍历右结点
            if (root.right != null) {
                postOrder(root.right);
            }
            System.out.print(root.val+",");
        }
    }

    //TODO 方法二：使用栈
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    public static void inOrder(TreeNode root){
        if (root != null) {
            if (root.left != null) {
                inOrder(root.left);
            }
        }
        System.out.print(root.val + ",");
        if (root.right != null) {
            inOrder(root.right);
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
        Tree tree = new Tree();
        tree.preOrder(root);
        //tree.inorder(root);
        //List<Integer> list = inorderTraversal(root);
        //System.out.println(list);
    }

}
