package lessons.week6.pratice.btree.pratice12;


import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 N叉树的最大深度
 * @Description: 给定一个 N 叉树，找到其最大深度。
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
 * @date: 2022/8/31
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

    private static int max = Integer.MIN_VALUE;

    /*
     我的思路: 比较节点下所有子树深度后取最大深度，然后加1
        难点:  如何在多个节点中获取最深的那一个?
        时间复杂度:  O(n)*O(h)
                   递的过程是O(n)的，和当前节点下的所有子节点数量成正比
                   归的过程是常量
        空间复杂度: O(h)
        执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗：41.6 MB, 在所有 Java 提交中击败了41.62%的用户
    */
    public static int maxDepth(Node root) {
        if (root == null) return 0;
        int maxDepth = 0;
        List<Node> children = root.children;
        if (children != null) {
            for (int i = 0;i< children.size();i++) {
                maxDepth = Math.max(maxDepth(children.get(i)),maxDepth);
            }
        }
        //执行到这里说明，得到了子节点最大深度
        return maxDepth + 1;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


}
