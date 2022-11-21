package lessons.week6.pratice.btree.part1.pratice10.review1;

import lessons.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0 找树左下角的值 -- 复习
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
 * -2^31<= Node.val <= 2^31- 1
 *
 * @author: bingyu
 * @date: 2022/11/21
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
     找最左下角的元素，就是找最后一层左边的元素，因此我们只需要按层遍历，记录到最后一层的第一个元素即可
     */
    private static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Integer temp = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                if (node!=null) {
                    if (i == 0) temp = node.val; //每遍历一层记录第一个节点元素即可，执行完后，该元素也自然就是最后一层的左边元素
                    if (node.left!=null) queue.add(node.left);
                    if (node.right!=null) queue.add(node.right);
                }
            }

        }
        return temp;
    }


}
