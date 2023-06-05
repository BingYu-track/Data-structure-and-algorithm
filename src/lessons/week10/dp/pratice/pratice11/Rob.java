package lessons.week10.dp.pratice.pratice11;

import lessons.common.TreeNode;

/**
 * @version 1.0 打家劫舍 III (树形DP)
 * @Description: 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
 * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 *
 * 示例 1:
 *             3
 *          /    \
 *         2     3
 *          \     \
 *           3     1
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 *
 * 示例 2:
 *              3
 *            /   \
 *           4     5
 *         /   \    \
 *        1    3     1
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 * 提示：
 *
 * 树的节点数在[1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
 *
 * @author: bingyu
 * @date: 2023/6/1
 */
public class Rob {

    public static void main(String[] args) {
        Rob r = new Rob();
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        root.left = node1;
        root.right = node2;

        node1.right = node3;
        node2.right = node4;
        int res = r.rob(root);
        System.out.println(res);
    }


    /*
    TODO：
     这道题目是一个树形dp，每个节点都有2个状态，偷和不偷，动态规划是转换为重复子问题，要想得到，这棵树的最大金额，
     那么，我们只需要求子节点偷的最大值即可，然后通过子节点再将状态转移到父节点，因为我们之前处理的是线性的dp，是从左往右
     一个个进行考察的；而在树形dp中由于根节点依赖于子节点，最后一步步依赖下去，发现最终都是依赖于树的叶子节点，因此我们的
     起点应该是从叶子节点开始，然后一步步向上进行推导!

     其状态转移方程为
     1.root[0] = Math.max(root.left[1],root.left[0]) + Math.max(root.right[1],root.right[0]);
        root节点的不偷 = 左子节点left中偷与不偷的最大值 +  右子节点right中偷与不偷的最大值

     2.root[1] =  root.left[0] + root.right[0] + root.val
        root节点偷 = 左子节点left不偷 + 右子节点right不偷 + root本身的值

       具体写代码还是有很多需要注意的，由于需要从叶子节点开始，因此我们要用二叉树的后序遍历(先遍历所有子节点，再遍历根节点)
    */
    public int rob(TreeNode root) {
        int[] nums = postOrder(root);
        return Math.max(nums[0],nums[1]);
    }

    /*
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：43 MB, 在所有 Java 提交中击败了5.14%的用户
    */
    private int[] postOrder(TreeNode root) {
        if (root == null) return new int[] {0,0}; //如果为空，都是0
        int[] left = postOrder(root.left); //得到偷root左子节点的最大值和不偷左子节点最大值

        //执行到这里说明现在的root就是叶子节点了
        int[] right = postOrder(root.right); //得到偷root右子节点的最大值和不偷右子节点的最大值
        int[] rootValue = new int[2];
        rootValue[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]); //root不偷
        rootValue[1] = left[0] + right[0] + root.val; //root偷
        return rootValue;
    }

}
