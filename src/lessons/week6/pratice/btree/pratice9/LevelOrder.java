package lessons.week6.pratice.btree.pratice9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0 N叉树的层序遍历
 * @Description: 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 示例 1：
            1
        /   |   \
       3    2    4
     /  \
    5   6
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 *
 * 示例 2：
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *
 *
 * 提示：
 * 树的高度不会超过1000
 * 树的节点总数在 [0,10^4] 之间
 *
 * @author: bingyu
 * @date: 2022/8/30
 */
public class LevelOrder {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


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
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    /*
     我的思路: 和前面题目一样的思路，使用队列
     执行用时：3 ms, 在所有 Java 提交中击败了83.65%的用户
     内存消耗：42.2 MB, 在所有 Java 提交中击败了77.01%的用户
    */
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0;i < size;i++) {
                Node node = queue.poll();
                if (node != null) {
                    List<Node> children = node.children;
                    if (children!=null) {
                        for (Node child : children) {
                            queue.add(child);
                        }
                    }
                    list.add(node.val);
                }
            }
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        return result;
    }

}
