package lessons.week6.pratice.btree.part1.pratice8;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** TODO:需要多次练习
 * @version 1.0 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * @Description: 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 *
 *                     3
 *                /         \
 *               9           20
 *            /     \      /    \
 *           10     11    15     7
 *          / \    / \   /  \   / \
 *         13 14 16  17 18 19  21 22
 * 返回其层次遍历结果：
 *
 * [[3],[20,9],[10,11,15,7],[22,21,19,18,17,16,14,13]]
 *
 *
 * 提示：
 * 节点总数 <= 1000
 *
 * @author: bingyu
 * @date: 2022/8/30
 */
public class LevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);
        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;
        List<List<Integer>> lists = levelOrder2(root);
        System.out.println(lists);
    }

    /*
     方法一: 使用单向队列，当前层为偶数时,可以使用Collections.reverse对list原来的顺序进行反转
     执行用时： 1 ms , 在所有 Java 提交中击败了 96.57% 的用户
     内存消耗： 41.3 MB , 在所有 Java 提交中击败了 84.66% 的用户
     O(h)*O()
    */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1; //记录当前是第几层
        while (!queue.isEmpty()) { //第一层循环执行和树的高度有关
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=1;i<=size;i++) { //这里和每层的节点数有关
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                    list.add(node.val);
                }
            }
            if (!list.isEmpty()) {
                if (level % 2 == 0) { //是偶数层，反转原来的元素顺序
                    Collections.reverse(list);
                }
                lists.add(list);
            }
            level++;
        }
        return lists;
    }

    /*
    方法二: 官方题解思路；使用2个队列，不使用api进行反转
    对当前层节点的存储我们维护一个变量isOrderLeft 记录是从左至右还是从右至左的：
    如果从左至右，我们每次将被遍历到的元素插入至子列表双端队列的末尾。
    如果从右至左，我们每次将被遍历到的元素插入至子列表双端队列的头部。

    执行用时：1 ms, 在所有 Java 提交中击败了96.57%的用户
    内存消耗：41.8 MB, 在所有 Java 提交中击败了12.18%的用户
    */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>(); //双端队列
        deque.add(root);
        boolean isOrderLeft = true;
        while (!deque.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>(); //使用2个双端队列
            int size = deque.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = deque.poll();
                if (curNode != null) {
                    if (isOrderLeft) { //奇数层，是从左到右的顺序，将当前层元素添加到子列表的末尾
                        levelList.add(curNode.val);
                    } else {
                        levelList.addFirst(curNode.val); //偶数层，是从右到左，将当前层元素添加到子列表的头部
                    }
                    //队列正常增加元素
                    if (curNode.left != null) {
                        deque.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        deque.add(curNode.right);
                    }
                }
            }
            if (!levelList.isEmpty()) {
                lists.add(new LinkedList<Integer>(levelList));
            }
            isOrderLeft = !isOrderLeft;
        }
        return lists;
    }

}
