package lessons.week6.pratice.btree.part2.pratice10.review;

/**
 * @version 1.0 剑指 Offer 33. 二叉搜索树的后序遍历序列 --复习
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
 * @date: 2022/12/5
 */
public class VerifyPostorder {

    public static void main(String[] args) {
        int[] postorder = {1,3,2,6,9,8,5};
        int[] postorder2 = {1,2,5,10,6,9,4,3}; //如果6是5的右子树的话，那么6应该在2的后面
        boolean result = verifyPostorder(postorder2);
        System.out.println(result);
    }

    /*
    如何判断一个后序遍历是否是属于某个二叉搜索树?
    思路: 即遍历每个节点，并判断该节点下的所有左子树节点是否小于该节点，所有右子树节点是否大于该节点
         根据 后序遍历 左子树->右子树->根 这个规律，找出第一个大于根节点的位置k，则如果该树的BST的话，k前面的所有数字应该属于左子树
         k后面的所有数字(包括k)应该是属于右子树
    */
    private static boolean verifyPostorder(int[] postorder) {
        return myVerify(postorder, 0, postorder.length-1);
    }

    /**
     * 1,3,2,6,9,8,5
     * @param postorder 后序遍历的数组
     * @param i 开始下标
     * @param j 结束下标
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.9 MB, 在所有 Java 提交中击败了68.94%的用户
     */
    private static boolean myVerify(int[] postorder, int i, int j) {
        if (i > j) return true; //当i>j时说明没有节点了，而这时还没退出说明之前校验都符合要求，肯定返回true
        int rootValue = postorder[j]; //当前树的根节点
        int k = i;
        while (postorder[k] < rootValue) {
            k++;
        }
        //执行到这说明[0,k)是都小于根节点的，属于左子树；[k,j-1]应该都是大于根节点的，属于右子树
        for (int n = k;n<j;n++) {
            if (postorder[n] <= rootValue) return false; //如果右子树出现小于等于根节点的，说明不是BST
        }
        //如果前面没有返回false说明当前根节点大于所有的左子树节点，小于所有的右子树节点
        boolean left = myVerify(postorder, i, k - 1); //再同样验证左子树
        boolean right = myVerify(postorder, k, j - 1); //验证右子树
        return left && right;
    }


}
