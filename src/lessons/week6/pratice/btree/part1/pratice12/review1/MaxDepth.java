package lessons.week6.pratice.btree.part1.pratice12.review1;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 N叉树的最大深度 -- 复习
 * @Description: 给定一个 N叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * 示例 1：
 *             1
 *         /   |   \
 *        3    2    4
 *      /  \
 *     5   6
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 *
 * 示例 2：
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 *
 *
 * 提示：
 * 树的深度不会超过1000 。
 * 树的节点数目位于 [0,10^4] 之间。
 *
 * @author: bingyu
 * @date: 2022/11/22
 */
public class MaxDepth {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> list = new ArrayList<>();
        list.add(node3);
        list.add(node2);
        list.add(node4);
        root.children = list;

        List<Node> list1 = new ArrayList<>();
        list1.add(node5);
        list1.add(node6);
        node3.children = list1;
        int depth = maxDepth(root);
        System.out.println(depth);
    }

    /*
        执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗：41.5 MB, 在所有 Java 提交中击败了60.68%的用户
    */
    private static int maxDepth(Node root) {
        if (root == null) return 0;
        List<Node> children = root.children;
        int maxDepth = 0;
        for (Node child : children) {
            int depth = maxDepth(child);
            maxDepth = Math.max(depth,maxDepth);
        }
        return maxDepth + 1;
    }


}
