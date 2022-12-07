package lessons.week6.pratice.btree.part2.pratice8.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 根据前序和后序遍历构造二叉树 -- 需要多次复习，练习
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
 * @date: 2022/12/1
 */
public class ConstructFromPrePost {

    public static void main(String[] args) {
//        int[] preorder = {1,2,4,5,3,6,7};
//        int[] postorder = {4,5,2,6,7,3,1};
        int[] preorder = {2,1};
        int[] postorder = {1,2};
        //TreeNode treeNode = constructFromPrePost_Error(preorder, postorder);
        TreeNode treeNode = constructFromPrePost(preorder, postorder);
        System.out.println(treeNode);
    }

    /*

    */
    private static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if (preorder == null || postorder == null) return null;
        return buildTree(preorder,0,preorder.length-1,postorder,0,postorder.length-1);
    }

    /*
     思路: 由于前序遍历和后序遍历无法唯一确定一个数组，因此我们只需要创建其中一个即可!
     '1'毫无疑问是树的根节点，'3'在postorder可以看出是1的左子节点或者右子节点;在inorder里可以看出2应该是1的左子节点或者右子节点，也就是说
     1有2个子节点，分别是2和3，那么2肯定就是1的左子节点，3是1的右子节点,在inorder里2和3之前的元素就是2的子节点
     前序: [1,2,4,5,3,6,7]
     后序: [4,5,2,6,7,3,1]
              1
           /     \
          /       \
    */
    private static TreeNode buildTree(int[] preorder, int i, int j, int[] postorder, int p, int q) {
        //TODO：为什么前序和中序可以是||，而前序和后序必须是&&呢?
        if (i>j) return null; //TODO: i>j说明当前没有节点了，也不需要添加p>q，因为都是同一个树的遍历结果，元素个数都是一样的

        int rootValue = preorder[i]; //根节点
        TreeNode root = new TreeNode(rootValue);
        if (i==j) { //TODO: 之所以要加一个这样的判断是因为i==j后，后面的preorder[i + 1]就越界了
            return root;
        }
        int value = preorder[i + 1]; //前序、后序光根据root无法划分左子树和右子树了，需要继续考察后面的
        // 此时value可能是root的左子树，也可能是root的右子树
        int k = p;
        while (postorder[k] != value) { //后序中寻找指定节点
            k++;
        }
        //执行到这里说明k所在的位置就是root左子树的根节点
        if(k < q - 1) { //在后序中小于q-1，说明k和q中间隔着元素，说明value一定是左子树根节点

        }else {
            //k是紧挨着q前面的，value可能是左子树也可能是右子树。因此后面统一将value作为左子树根节点处理

        }
        //TODO： 注意-从这里开始构建左子树和右子树时极其容易出错
        int leftSize = k - p + 1; //因为k是左子树节点，后序中k再往后就是右节点了，因此可以计算出左子树节点的个数

        //构建左子树
        TreeNode leftTree = buildTree(preorder, i + 1, i + leftSize , postorder, p, k);
        //构建右子树
        TreeNode rightTree = buildTree(preorder, i + leftSize + 1,j, postorder, k + 1, q - 1);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }


}
