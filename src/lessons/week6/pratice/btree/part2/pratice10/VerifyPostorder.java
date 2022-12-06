package lessons.week6.pratice.btree.part2.pratice10;

import lessons.common.TreeNode;

/**
 * @version 1.0 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * @Description: 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。
 *              假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 *
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 *
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 * 提示：
 * 数组长度 <= 1000
 *
 * @author: bingyu
 * @date: 2022/9/16
 */
public class VerifyPostorder {

    public static void main(String[] args) {
        int[] postorder = {1,3,2,6,9,8,5};
        int[] postorder2 = {1,6,3,2,5}; //如果6是5的右子树的话，那么6应该在2的后面
        boolean result = verifyPostorder(postorder);
        System.out.println(result);
    }

    /*
    我的思路: 判断一个数组是不是某二叉搜索树的后序遍历结果，就是看这个数组按照后序，能否构造出一个二叉搜索树;
     首先取后序数组最后一个元素，最后一个元素肯定是树的根节点，然后看它前面紧挨着的元素，这个元素可能是在左
     子树，也可能是在右子树，具体在哪个根据它和根节点的值比较大小可以判断，只要每次递归根节点时，满足根节点>左子树的最大值 并且根节点<右子树的最小值
     TODO: 总结就是:先构造二叉树，在构造二叉树期间校验二叉树是否是二叉搜索树(虽然不太推荐，比较麻烦)

     二叉搜索树后序遍历特点: 根节点前面紧挨着的应该要么是比root大的右节点，要么是比root小的左节点；
     1.如果是比root小的，则说明root没有右子树，即后面不应该出现比root大的元素;
     2.如果前面紧挨着root的是比root大的，那么说明是有右子树的

                                                  后序: 1,3,2,6,9,8,5 这个例子说明即使是二叉搜索树的后序也无法唯一确定一个二叉搜索树
                              5                     5                5
                            /                      /  \             /  \
                           2                      2    8           2    8
                            \                    / \    \         / \  / \
                             3                  1   3    9       1  3  6  9
                            / \                         /
                           1   6                       6
    */

    private static boolean flag = true;

    /*
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：39.5 MB, 在所有 Java 提交中击败了5.03%的用户
    */
    public static boolean verifyPostorder(int[] postorder) {
        if (postorder==null || postorder.length==0) return true;
        TreeNode root = buildBST(postorder,0,postorder.length-1); //先构建二叉搜索树
        System.out.println(root);
        int[] result = verifBST(root); //再进行二叉搜索树校验
        return result[0]==1;
    }

    //我的思路是先构建二叉搜索树，然后再校验生成的二叉搜索树是否是真的二叉搜索树
    public static TreeNode buildBST(int[] postorder,int i,int j) {
        if (i>j) return null;
        if (!flag) return null; //提前退出
        int rootValue = postorder[j]; //从后序数组获取root值
        TreeNode root = new TreeNode(rootValue);
        if (i == j) return root;
        int k = postorder[j - 1]; //获取根节点(感觉会成为负值)
        int p = j;
        if (k > rootValue) {
            //说明k是右子树,再从左到右找第一个比root小的数，如果找到了，说明这个数就是root的左子树根节点
            while (p>=0 && postorder[p]>=rootValue) {
                p--;
            }
            if (p < 0) { //执行到这里说明没有找到小于root的元素，即root没有左子树，只有右子树，此时右子树范围是[i,j-1]，这样只需要检测右子树有没有小于等于root的即可
                TreeNode rightNode = buildBST(postorder, i, j - 1);
                root.right = rightNode;
            }else { //说明左子树，右子树都有,此时左子树范围是[i,p]  右子树范围是[p+1,j-1]
                TreeNode leftNode = buildBST(postorder, i, p);
                TreeNode rightNode = buildBST(postorder, p + 1, j - 1);
                root.left = leftNode;
                root.right = rightNode;
            }
        }
        if (k < rootValue) {
            //说明k是左子树，并且root下没有右子树，那么当前左子树的范围就是[i,j-1],这样只需要检测左子树有没有大于等于root的即可，
            // 我这里继续调用是为了构建出树
            TreeNode leftNode = buildBST(postorder, i, j - 1);
            root.left = leftNode;
        }
        return root;
    }

    /*
     校验是否是二叉搜索树 [0]表示树是否是二叉树,1表示是二叉搜索树，-1表示不是二叉搜索树,0表示为null，没有节点
     [1]表示树的最大值,[2]表示树的最小值;
     需要用root比较左子树的最大值和右子树的最小值;而且在找最大值和最小值的过程中校验所有节点是否都是二叉搜索树
    */
    private static int[] verifBST(TreeNode root) {
        if (root == null) return null; //到叶子节点
        int[] left = verifBST(root.left); //获取左子树的最大值和最小值
        if (left != null && left[0] == -1) return left; //校验左子树是否是二叉搜索树

        int[] right = verifBST(root.right); //获取右子树的最大值和最小值
        if (right != null && right[0] == -1) return right; //校验右子树是否是二叉搜索树
        int rootValue = root.val;

        int max = rootValue;
        int min = rootValue;
        //执行到这里，开始去左右子树的最大值和最小值
        if (left == null && right!=null) { //左子树为空,比较右子树和root的值
            int rightMax = right[1];
            int rightMin = right[2];
            if (rootValue >= rightMin) return new int[] {-1,0,0};
            max = rightMax;
            min = rootValue;
        }else if (right == null && left!=null) { //右子树为空，比较左子树和root的值
            int leftMax = left[1];
            int leftMin = left[2];
            if (rootValue <= leftMax) return new int[] {-1,0,0};
            max = rootValue;
            min = leftMin;
        }else if (left!=null && right!=null) { //左右子树均不为空
            if (rootValue <= left[1] || rootValue >= right[2]) {
                //执行到这里说明不是二叉搜索树
                return new int[] {-1,0,0};
            }
            max = right[1]; //右子树的最大值
            min = left[2]; //左子树的最小值

        }
        //TODO left个right都为空的话，那么max.min直接取root值
        return new int[] {1,max,min};
    }

    /*
                5
              /
             14
            /
           1
    */
}
