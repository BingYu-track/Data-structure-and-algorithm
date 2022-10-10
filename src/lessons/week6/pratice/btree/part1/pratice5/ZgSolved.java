package lessons.week6.pratice.btree.part1.pratice5;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: N叉树的后序遍历--复习
 * @author: bingyu
 * @date: 2022/10/10
 */
public class ZgSolved {

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

        List<Node> children3 = new ArrayList<>();
        children3.add(node6);
        children3.add(node7);
        node3.children = children3;

        List<Node> children7 = new ArrayList<>();
        children7.add(node11);
        node7.children = children7;

        List<Node> children11 = new ArrayList<>();
        children11.add(node14);
        node11.children = children11;

        List<Node> children4 = new ArrayList<>();
        children4.add(node8);
        node4.children = children4;

        List<Node> children8 = new ArrayList<>();
        children8.add(node12);
        node8.children = children8;

        List<Node> children5 = new ArrayList<>();
        children5.add(node9);
        children5.add(node10);
        node5.children = children5;

        List<Node> children9 = new ArrayList<>();
        children9.add(node13);
        node9.children = children9;

        List<Integer> postorder = postorder2(root);
        System.out.println(postorder);
    }

    private static List<Integer> list = new ArrayList<>();

    //TODO: 需要多多复习
    /*
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：42.1 MB, 在所有 Java 提交中击败了65.45%的用户
    */
    private static List<Integer> postorder(Node root) {
        if (root == null) return list;
        List<Node> children = root.children;
        if (children!=null && children.size()>0) { //有子元素的话，就继续向下遍历，直到最后一层
            for (Node child : children) {
                postorder(child);
            }
        }
        list.add(root.val); //执行到这里说明childern为null，或者是之前已经遍历完成的
        return list;
    }

    //迭代法--思路:使用栈按顺序存每层的节点，并弹出栈顶元素，再将栈顶元素放入list当中，会发现list和后序遍历顺序刚好是相反的
    private static List<Integer> postorder2(Node root) {
        if (root == null) return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            List<Node> children = node.children;
            for (int i = 0;i<children.size();i++) {
                stack.push(children.get(i));
            }
        }
        Collections.reverse(list);
        return list;
    }


}
