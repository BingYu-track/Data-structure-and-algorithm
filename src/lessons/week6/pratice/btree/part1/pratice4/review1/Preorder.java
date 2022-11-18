package lessons.week6.pratice.btree.part1.pratice4.review1;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @version 1.0  N叉树的前序遍历
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
 * @date: 2022/11/14
 */
public class Preorder {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        List<Node> children1 = new ArrayList<>();
        children1.add(node2);
        children1.add(node3);
        children1.add(node4);
        children1.add(node5);
        root.children = children1;

        node3.children.add(node6);
        node3.children.add(node7);
        node7.children.add(node11);
        node11.children.add(node14);

        node4.children.add(node8);
        node8.children.add(node12);

        node5.children.add(node9);
        node5.children.add(node10);
        node9.children.add(node13);

        List<Integer> preorder = preorder2(root);
        System.out.println(preorder);

        List<Integer> postorder = postorder2(root);
        System.out.println(postorder);
    }

    //注意N叉树只有前序遍历和后序遍历，没有中序遍历
    //前序遍历:先根节点->子节点
    //后序遍历:先子节点->根节点
    public static List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root,list);
        return list;
    }

    private static void preorder(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        List<Node> children = root.children;
        for (Node child : children) {
            preorder(child,list);
        }
    }


    //N叉树的后序遍历
    public static List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder(root,list);
        return list;
    }

    private static void postorder(Node root, List<Integer> list) {
        if (root == null) return;
        List<Node> children = root.children;
        for (Node child : children) {
            postorder(child,list);
        }
        list.add(root.val);
    }


    /*
     N叉树的前序遍历--非递归解法
     */
    public static List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root==null) return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            List<Node> children = node.children;
            int size = children.size();
            for (int i = size-1;i>=0;i--) {
                Node no = children.get(i);
                stack.push(no);
            }
        }
        return list;
    }

    /*
     N叉树的后序遍历--非递归解法
     思路: 使用队列，先进先出，然后用链表进行存储，按顺序每次将元素放入头部，前面的元素就会被移动到后面
     [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
     */
    public static List<Integer> postorder2(Node root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Deque<Node> queue = new LinkedList<>(); //[10,5,1]
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeLast(); //移除最后面的元素
            if (node!=null) {
                list.add(0,node.val); //将元素插入头部
                List<Node> children = node.children;
                for (Node child : children) {
                    queue.add(child);
                }
            }
        }
        return list;
    }

    /*
                   1
                /
               2  3  4  5

    */

}
