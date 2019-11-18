package leetcode.tree;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 验证二叉搜索树(中等)
 * @author: bingyu
 * @date: 2019/11/11 21:16
 */
public class ValidateBinarySearchTree {

    public static class TreeNode{
        public int val;
        public TreeNode left,right;

        public TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public boolean helper(Tree.TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }

    //方法一：中序遍历树并存入数组中，如果是升序，说明是二叉搜索树
    public static boolean isValidBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        inOrder(root,list); //中序遍历二叉树并放入list中
        //检验list里的数是否升序排列
        for(int i = 0;i<list.size()-1;++i){
            if (list.get(i).compareTo(list.get(i+1))>= 0) return false;
        }
        return true;
    }

    //中序遍历
    public static void inOrder(TreeNode root, List<Integer> nodes){
        if (root == null) {
            return ;
        }
        if (root.left != null) {
            inOrder(root.left, nodes);
        }
        nodes.add(root.val);
        if (root.right != null) {
            inOrder(root.right, nodes);
        }
    }

    //方法二：递归 (官方题解)
    public static boolean isValidBST2(TreeNode root,long min,long max){
        if (root == null) {
            return true;
        }
        System.out.println("root:"+ root.val+ "的取值范围是 " + min + "--" + max);
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST2(root.left,min,root.val) && isValidBST2(root.right,root.val,max);
    }

    /**
     * 自己用递归的方法验证二叉搜索树(思路：就是看根节点root的左子树的根节点left_root和右子树的根节点right_root是否满足left_root < root < right_root
     * 且左子树的最大值left_root_max < root <右子树的最小值right_root_min)
     * @param root 根节点
     * @return
     */
    public static boolean isValidBSTBySelf(TreeNode root){
        //1.终止条件
        if (root == null) {
            return true;
        }

        //2.处理当前层
        if (root.left != null) { //如果有左子树
            if(root.left.val >= root.val || getMax(root.left) >= root.val) return false; //如果左子树的根节点大于等于整个树的根节点，或者左子树最大的根节点大于等于整棵树的根节点，则不是搜索二叉树
        }

        if (root.right != null) { //如果有右子树
            if (root.right.val <= root.val || getMin(root.right) <= root.val) return false; //反之亦然
        }

        //3,drill down
        return isValidBSTBySelf(root.left) && isValidBSTBySelf(root.right);
    }


    //查找二叉搜索树的最大值
    public static int getMax(TreeNode root){
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.val;
    }

    //查找二叉搜索树的最小值
    public static int getMin(TreeNode root){
        TreeNode cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(15);
        TreeNode c = new TreeNode(13);
        TreeNode d = new TreeNode(20);
        TreeNode e = new TreeNode(11);
        TreeNode f = new TreeNode(14);
        root.left = a; root.right = b;
        b.left = c; b.right = d;
        c.left = e; c.right = f;
        //boolean bst = isValidBST2(root,Long.MIN_VALUE,Long.MAX_VALUE);
        System.out.println(isValidBSTBySelf(root));
    }
}
