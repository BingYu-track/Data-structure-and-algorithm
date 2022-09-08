package lessons.week6.pratice.btree.part1.pratice7;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0 二叉树的层序遍历
 * @Description: 你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例 1：
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 *
 * @author: bingyu
 * @date: 2022/8/29
 */
public class LevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node2.right = node4;
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    /* [1,2,3,4,null,null,5]
         1
        / \
       2   3
      /     \
     4       5
     我的思路: 同样使用队列这个思路，想不到如何分层了，分层要具体到对应的节点
     执行用时：1 ms, 在所有 Java 提交中击败了57.94%的用户
     内存消耗：41.4 MB, 在所有 Java 提交中击败了70.26%的用户
     时间复杂度: 内部循环执行次数是1+2+4+...+2^n-1就相当于节点的总个数,因此时间复杂度为O(n)
     空间复杂度:O(n)
     TODO: 需要多次练习
    */
    public static List<List<Integer>> levelOrder(TreeNode root) { //
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) { //log2n *
            int size = queue.size(); //当前层开始，提前记录好每层的节点个数
            List<Integer> list = new ArrayList<>();
            for (int i = 1;i<=size;i++) { //TODO: 核心思路--每层遍历完后，后面陆续进入队列元素数量刚好就是下次的节点数目
                TreeNode node = queue.poll();
                if (node!=null) {
                    queue.add(node.left);
                    queue.add(node.right);
                    list.add(node.val);
                }
            }
            if (!list.isEmpty()) {
                lists.add(list);
            }
        }
        return lists;
    }

}
