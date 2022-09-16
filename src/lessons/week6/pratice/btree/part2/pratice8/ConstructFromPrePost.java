package lessons.week6.pratice.btree.part2.pratice8;

import lessons.common.TreeNode;

/** TODO: 这题需要多多练习，熟练
 * @version 1.0 根据前序和后序遍历构造二叉树
 * @Description: 给定两个整数数组，preorder和 postorder，其中preorder是一个具有无重复 值的二叉树的前序遍历，postorder是同一棵树的后序遍历，
 * 重构并返回二叉树。
 * 如果存在多个答案，您可以返回其中 任何 一个。
 *
 * 示例 1：
 *                1
 *             /     \
 *            2       3
 *           / \     / \
 *          4   5   6   7
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *
 * 示例 2:
 * 输入: preorder = [1], postorder = [1]
 * 输出: [1]
 *
 * 提示：
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * preorder中所有值都不同
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * postorder中所有值都不同
 * 保证 preorder和 postorder是同一棵二叉树的前序遍历和后序遍历
 *
 * @author: bingyu
 * @date: 2022/9/14
 */
public class ConstructFromPrePost {

    public static void main(String[] args) {
//        int[] preorder = {1,2,4,5,3,6,7};
//        int[] postorder = {4,5,2,6,7,3,1};
        int[] preorder = {2,1};
        int[] postorder = {1,2};
        TreeNode treeNode = constructFromPrePost_Error(preorder, postorder);
        System.out.println(treeNode);
    }


    /* TODO 解题前置知识--前序数组排列特点:根、左子树所有元素、右子树所有元素
                       后序数组排列特点:左子树所有元素、右子树所有元素、根
                  另外一般情况下前序、后序是无法唯一确定一棵二叉树的，只有前序，中序或者中序，后序是可以唯一可以确定一棵树的，
                 比如:  前序: 1,2,4,5,3,6,7
                       后序: 4,5,6,7,3,2,1
                这样的话，2可以是左子树的根节点，没有右子树；同样2也可以是右子树的根节点，没有左子树

我的错误思路: 根据前序和后序的特点，知道前序的第一个元素就是根节点，紧挨着后面的就是该根节点左子树根节点；后序数组的最后一个元素是根节点，
            紧挨着前面的一个元素就是该根节点右子树根节点；这样找到右子树根节点后，又知道左子树根节点，就能到前序数组里分隔左子树和右子树了，
            在后序数组之前已经知道了右子树根节点，又知道了左子树根节点，同样就能确定左右子树的范围了
            然后在此基础上进行构造树

        TODO 这里错误的一点是: 前序数组根节点后面紧挨着不一定是左子树的根节点，同样后序数组根节点前面紧挨着的也不一定是右子树的根节点，因此不能根据
         这2个位置来确定左右子树的范围；只有前序和后序是无法确定具体子节点是在左子树还是右子树的，比如前序: 2,1 后序:1,2
         该题目举的例子比较特殊，是一个满二叉树，因此它的前序、后序是可以唯一确定的；但是换个例子就不一定了！
    */
    public static TreeNode constructFromPrePost_Error(int[] preorder, int[] postorder) {
        return buildTree(preorder,0,preorder.length - 1,postorder,0,postorder.length - 1);
    }
    //错误解法:  输入 preorder=[2,1] postorder=[1,2]
    //         输出 [2,1,1]
    // 我这里错误的解法是因为1即可能是2的左子树，也可能是2的右子树，因此我按照我代码处理，1会放到左右子树中，导致输出结果错误，
    //因此我们再处理前序。后序构造二叉树时，只能选择一个种可能来处理，要么是左子树，要么是右子树
    private static TreeNode buildTree(int[] preorder, int i, int j, int[] postorder, int p, int r) {
        if (i>j && p>r) return null;
        int rootValue = preorder[i]; //当前根节点的值
        TreeNode root = new TreeNode(rootValue);
        if (i==j || p==r) return root;
        int rightValue = postorder[r - 1]; //右子树根节点的值
        int k = i + 1;
        while (preorder[k] != rightValue) { //查找右子树根节点在前序数组的位置
            k++;
        }
        //执行到这说明k就是右子树根节点在前序数组的位置，这样左右子树在前序数组的取值范围就确定了,左子树范围:[i+1,k-1] 右子树范围:[k,j]

        int leftValue = preorder[i + 1]; //左子树根节点的值
        int q = p;
        while (postorder[q] != leftValue) { //查找左子树根节点在后序数组的位置
            q++;
        }
        //执行到这说明q就是左子树根节点在后序数组的位置，这样左右子树在后序数组的范围也确定了，左子树范围:[p,q]  右子树范围:[q+1,r-1]
        //TODO： 可以开始构造树
        //构造左子树
        TreeNode leftNode = buildTree(preorder, i + 1, k - 1, postorder, p, q);
        //构造右子树
        TreeNode rightNode = buildTree(preorder, k, j, postorder, q + 1, r - 1);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }


}
