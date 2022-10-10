package lessons.week6.pratice.btree.part1.pratice4;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: N叉树的前序遍历--复习
 * @author: bingyu
 * @date: 2022/8/28
 */
public class ZgSolved {

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

    private static List<Integer> list = new ArrayList<>();
    /*
     复习1--递归解法
    */
    private static List<Integer> preorder(Node root) {
        if (root == null) return list;
        list.add(root.val);
        List<Node> children = root.children;
        for (Node child : children) {
            preorder(child);
        }
        return list;
    }

    //复习1--迭代解法,使用栈这个数据结构来模拟递归里的方法调用栈
    private static List<Integer> preorder2(Node root) {
        if (root == null) return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            List<Node> children = node.children;
            int size = children.size();
            for (int i = size - 1;i>=0;i--) { //倒序放入栈中
                Node child = children.get(i);
                stack.push(child);
            }
        }
        return list;
    }

}
