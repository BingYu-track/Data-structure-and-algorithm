package lessons.week6.pratice.btree.part1.pratice5;


import lessons.common.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0 N叉树的后序遍历
 * @Description: 给定一个 n叉树的根节点root，返回 其节点值的 后序遍历 。
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 * 示例 1：
 *                1
 *             /  | \
 *            3   2  4
 *           / \
 *          5   6
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]   1 3 2 4 5 6
 *
 * 示例 2：
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *
 *
 * 提示：
 * 节点总数在范围 [0, 10^4] 内
 * 0 <= Node.val <= 10^4
 * n 叉树的高度小于或等于 1000
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * @author: bingyu
 * @date: 2022/8/25
 */
public class Postorder {

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

        List<Integer> postorder = postorder(root);
        System.out.println(postorder);
    }

    /*
     递归
     [5,6,3,2,4,1]
    */
    public static List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root,list);
        return list;
    }

    /*

    */

    /* 推荐该方法
      错误
    */
    private static void postOrder(Node root, List<Integer> list) {
        if (root == null) return;
        List<Node> children = root.children;
        if (children!=null && children.size()>0) { //有子元素的话，就继续向下遍历，直到最后一层
            for (Node child : children) {
                postorder(child);
            }
        }
        list.add(root.val); //执行到这里说明childern为null，或者是之前已经遍历完成的
    }


    /*
    迭代法
    */
    public static List<Integer> postorder2(Node root) {
        List<Integer> list = new ArrayList<>();
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
