package lessons.week6.pratice.btree.part1.pratice17;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 验证二叉搜索树--重复练习
 * @author: bingyu
 * @date: 2022/9/21
 */
public class RepeatPratice {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(32);
        TreeNode node1 = new TreeNode(26);
        TreeNode node2 = new TreeNode(47);
        TreeNode node3 = new TreeNode(19);
        TreeNode node4 = new TreeNode(56);
        TreeNode node5 = new TreeNode(27);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node3.right = node5;
        boolean validBST = isValidBST(root);
        System.out.println(validBST);
    }

    private static boolean isValidBST(TreeNode root) {
        if (root == null) return true; //如果是空节点，返回true
        int[] result = verifBST(root);
        return result[0] == 1;
    }

    //[0]表示树是否是二叉树,1表示是二叉搜索树，-1表示不是二叉搜索树
    //[1]表示树的最大值,[2]表示树的最小值;
    //TODO: 我独自写的代码，如何将该代码简化?
    private static int[] verifBST(TreeNode root) {
        if (root == null) return null; //到叶子节点
        int[] left = verifBST(root.left); //校验左子树，并返回其最大值和最小值
        if (left != null && left[0] == -1) return left; //校验左子树是否是二叉搜索树

        int[] right = verifBST(root.right); //校验右子树，并返回其最大值和最小值
        if (right != null && right[0] == -1) return right; //校验右子树是否是二叉搜索树
        int rootValue = root.val;

        int max = rootValue;
        int min = rootValue;
        //执行到这里，开始去左右子树的最大值和最小值
        if (left == null && right!=null) { //左子树为空,比较右子树和root的值
            int rightMax = right[1];
            int rightMin = right[2];
            if (rootValue >= rightMin) return new int[] {-1,0,0}; //root值比右子树的最小值还大，说明不是二叉搜索树
            max = rightMax; //右子树的最大值作为树的最大值
            min = rootValue; //因为没有左子树，因此最小值就是root值
        }else if (right == null && left!=null) { //右子树为空，比较左子树和root的值
            int leftMax = left[1];
            int leftMin = left[2];
            if (rootValue <= leftMax) return new int[] {-1,0,0}; //root值比左子树的最大值还小，说明不是二叉搜索树
            max = rootValue;//因为没有右子树，因此最大值就是root值
            min = leftMin; //最小值是左子树的最小值
        }else if (left!=null && right!=null) { //左右子树均不为空
            if (rootValue <= left[1] || rootValue >= right[2]) {
                //执行到这里说明不是二叉搜索树
                return new int[] {-1,0,0};
            }
            max = right[1]; //右子树的最大值作为树的最大值
            min = left[2]; //左子树的最小值作为树的最小值

        }
        //TODO left个right都为空的话，那么max.min直接取root值
        return new int[] {1,max,min};
    }
}
