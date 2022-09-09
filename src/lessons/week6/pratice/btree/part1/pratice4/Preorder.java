package lessons.week6.pratice.btree.part1.pratice4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0 N叉树的前序遍历
 * @Description: 给定一个 n叉树的根节点root，返回 其节点值的 前序遍历 。
 * n叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *
 * 示例 1：
 *                  1
 *               /  | \
 *              3   2  4
 *             / \
 *            5   6
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 *
 * 示例 2：
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 *
 * 提示：
 * 节点总数在范围[0, 10^4]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * @author: bingyu
 * @date: 2022/8/25
 */
public class Preorder {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(3);
        Node node2 = new Node(2);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        root.children = nodes;

        List<Node> list = new ArrayList<>();
        list.add(node4);
        list.add(node5);
        node1.children = list;
        List<Integer> preorder = preorder2(root);
        System.out.println(preorder);
    }

    /*标准解法
     我的思路: 和使用递归前序遍历思路是一样的
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：42.5 MB, 在所有 Java 提交中击败了12.12%的用户
    */
    public static List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root,list);
        return list;
    }

    private static void preOrder(Node root, List<Integer> list) {
        if (root == null) return;
        List<Node> children = root.children;
        list.add(root.val);
        for (Node child : children) {
            preOrder(child,list);
        }
    }

    /*
     使用迭代法(目前只能使用栈) [1, 3, 5, 6, 2, 4]
     空间复杂度:O(h) 时间复杂度O(n)
     执行用时：3 ms, 在所有 Java 提交中击败了16.66%的用户
     内存消耗：42.2 MB, 在所有 Java 提交中击败了49.56%的用户
    */
    public static List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop(); //只在弹出的时候将节点值放入结果数组
            list.add(pop.val);
            List<Node> children = pop.children;
            int size = children.size();
            while (size > 0) { //TODO 逆序存入栈中，这样栈顶就是最左边的节点了
                stack.push(children.get(size - 1));
                size--;
            }
        }
        return list;
    }


    static class Node {
        public int val;
        public List<Node> children = new ArrayList<>();

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


}
