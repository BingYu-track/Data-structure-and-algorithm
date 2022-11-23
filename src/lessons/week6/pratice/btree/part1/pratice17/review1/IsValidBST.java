package lessons.week6.pratice.btree.part1.pratice17.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 验证二叉搜索树 TODO： 重中之重，很难理解
 * @Description: 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1：
 *          2
 *        /   \
 *       1     3
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 *              5
 *            /   \
 *           1    4
 *               / \
 *              3   6
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是5 ，但是右子节点的值是4。
 *
 *
 * 提示：
 * 树中节点数目范围在[1, 10^4] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * @author: bingyu
 * @date: 2022/11/23
 */
public class IsValidBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(14);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(6);
        root.left = node1;
        node1.left = node2;
        boolean validBST = isValidBST(root);
        System.out.println(validBST);
    }

    /*
                    5
                   /  \
                  14
                 /
                1
     */


    private static boolean isValidBST = true;

    /*
    思路: 根节点大于左子树的最大元素，小于右子树的最小元素,因此每次遍历子树需要找到其子树的最大值和最小值，需要返回2个参数，
         因此需要用数组
    */
    private static boolean isValidBST(TreeNode root) {
        validBST(root);
        return isValidBST;
    }

    //数组第一个存放最小值，第2个存放在最大值
    private static int[] validBST(TreeNode root) {
        if (root == null) return null;
        int[] left = validBST(root.left);
        int[] right = validBST(root.right);
        int min = root.val;
        int max = root.val;
        //每次进行比较root值和left，right子树的大小
        if (left == null && right!=null) { //左子树为空，只有右子树
            if (right[0] <= root.val) isValidBST = false; //右子树最小值小于等于root，说明不是二叉搜索树
            //然后将右子树和root组合，形成新的最大值和最小值来
            min = Math.min(right[0], root.val);
            max = Math.max(right[1], root.val);
        }
        if (right == null && left!=null) { //右子树为空，只有左子树
            if (left[1] >= root.val) isValidBST = false; //左子树最大值大于等于root,说明不是二叉搜索树
            //然后将左子树和root组合，形成新的最大值和最小值来
            min = Math.min(left[0], root.val);
            max = Math.max(left[1], root.val);
        }
        //左右子树都不为空
        if (right!=null && left!=null) {
            if (right[0] <= root.val || left[1] >= root.val) isValidBST = false;
            min = Math.min(left[0], right[0]);
            max = Math.max(left[1], right[1]);
        }
        return new int[]{min,max};
    }


}
