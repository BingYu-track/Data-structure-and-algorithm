package lessons.week6.pratice.btree.pratice17;

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
 * @date: 2022/9/2
 */
public class IsValidBST {

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

    private static boolean isValidBST = true;
    private static int rootVal = 0;
    /*
     我开始的思路(错误):root要比左子树的所有元素都大，比右子树的所有元素都小;注意这里重点就是要对比子树下的所有元素；因此最直接的方法
          就是遍历每一个根节点，然后让根节点去和所有的子节点进行比较,但是这样时间复杂度会很高;
          TODO: 注意点1--左子树是二叉搜索树、右子树是二叉搜索树，并不能说明根节点就是二叉搜索树
               举例: 左边只有一个节点3，右边只有一个节点2，根节点是啥也不可能构成一个二叉搜索树

          我最开始是这样想的:
          如果节点全在根节点的左边，递归校验每节点的left < parent(parent小于root); right<root && right>parent
          如果节点全在根节点的右边，递归校验每层的right > parent; left>root && left<parent
          发现这样并不能正确校验树是否是二叉搜索树，如下就是反例，因为左右子节点只和父节点和根节点比较，没有和父节点和根节点之间的节点进行比较，导致错误!
                    32
                   /  \
                  26  47
                 /      \
                19      56
                 \
                 27

        TODO: 下面是错误的题解

    */
    public static boolean isValidBST_Error(TreeNode root) {
        if (root!=null) {
            rootVal = root.val;
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left!=null && left.val>=rootVal || right!=null && right.val<=rootVal) return false;
            validBST_Error(left,true);
            validBST_Error(right,false);
        }else {
            return false;
        }
        return isValidBST;
    }

    //
    private static void validBST_Error(TreeNode parent,boolean flag) {
        if(parent == null) return;
        if (!isValidBST) return; //已经是false，提前退出
        TreeNode left = parent.left;
        TreeNode right = parent.right;
        if ((right!=null && right.val<=parent.val) || (left!=null && left.val>=parent.val)) { //检查左右子节点和父节点的大小
            isValidBST = false;
            return;
        }
        if (flag) { //在根节点左边
            if (right!=null && right.val>=rootVal) {
                isValidBST = false;
                return;
            }
        }else { //在根节点右边
            if (left!=null && left.val <= rootVal) {
                isValidBST = false;
                return;
            }
        }

        validBST_Error(left,flag);
        validBST_Error(right,flag);
    }



    /*
     如何正确判断左右子树是二叉搜索树? 如何将问题转换成求二叉树的最大值，最小值?
     TODO: 正确思路是左子树是二叉搜索树，右子树是二叉搜索树，且根节点的值大于左子树的最大值，小于右子树的最小值，这样才能说明该根节点的树为二叉搜索树
           注意其前提就是左、右子树已经是二叉搜索树，因此"核心思路就是在找左、右子树的最大值和最小值的过程中顺便判断每个子树是否是二叉搜索树"?
           每次处理节点需要返回其节点的左子树的最大值和其右子树的最小值，因此递归函数需要返回2个值，因此选择数组作为返回数据
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true; //如果是空节点，返回true
        dfs(root);
        return isValid;
    }

    private static boolean isValid = true;

    /**
     * 争哥解法:
     * @return 返回 右子树的最小值[0] 和左子树的最大值[1](注意2个空间不够，还有)
     */
    private static int[] dfs(TreeNode root) {
        int min = root.val;
        int max = root.val;
        if (root.left != null) {
            int[] leftMinMax = dfs(root.left); //求root左子树的最小值和最大值
            if (isValid == false) return null;//左子树不是二叉搜索树，提前退出条件，之所以放在这是因为前面dfs递归函数刚刚处理完毕，可能其左子树是非二叉搜索树
            if (leftMinMax[1] >= root.val) { //如果root左子树的最大值大于等于root，说明不是二叉搜索树
                isValid = false;
                return null;
            }
            min = leftMinMax[0]; //得到root左子树的最大值
        }
        if (root.right != null) {
            int[] rightMinMax = dfs(root.right); //求root右子树的最小值和最大值
            if (isValid == false) return null;//提前退出条件
            if (rightMinMax[0] <= root.val) { //如果root右子树的最小值小于等于root，说明不是二叉搜索树
                isValid = false;
                return null;
            }
            max = rightMinMax[1]; //得到root右子树的最小值
        }
        return new int[] {min, max};
    }


}
