package lessons.week6.pratice.btree.pratice10;

import lessons.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0 找树左下角的值
 * @Description: 给定一个二叉树的 根节点 root，请找出该二叉树的最底层最左边节点的值。
 * 假设二叉树中至少有一个节点。
 *
 * 示例 1:
 *      2
 *     / \
 *    1   3
 * 输入: root = [2,1,3]
 * 输出: 1
 *
 * 示例 2:
 *                 1
 *              /     \
 *             2       3
 *            /       / \
 *           4       5   6
 *                  /
 *                 7
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *
 *
 * 提示:
 * 二叉树的节点个数的范围是 [1,10^4]
 * -231<= Node.val <= 2^31- 1
 *
 * @author: bingyu
 * @date: 2022/8/30
 */
public class FindBottomLeftValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node5.left = node7;
        int blv = findBottomLeftValue(root);
        System.out.println(blv);
    }

    /*
    我的思路: 注意是求最左边的，当前层的所有节点都没有子节点，说明就是最后一层，我们只需要记录每一层第一个不为null的节点值
            执行完后，最后记录的就是最后一层最左值
    执行用时：2 ms, 在所有 Java 提交中击败了15.40%的用户
    内存消耗：41.1 MB, 在所有 Java 提交中击败了49.03%的用户
    */
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int leftValue = root.val; //记录每次遍历的节点
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            for (int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                if(node!=null) {
                    count++;
                    //TODO： 记录每一层第一个不为null的节点值
                    if (count==1) leftValue = node.val;
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        return leftValue;
    }

}
