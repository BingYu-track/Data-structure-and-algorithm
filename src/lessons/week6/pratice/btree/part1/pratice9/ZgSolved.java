package lessons.week6.pratice.btree.part1.pratice9;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: N叉树的层序遍历--复习
 * @author: bingyu
 * @date: 2022/10/11
 */
public class ZgSolved {

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
        List<List<Integer>> lists = levelOrder(null);
        System.out.println(lists);
    }


    /*

    */
    private static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0;i<size;i++) {
                Node node = queue.poll();
                if (node!=null) {
                    List<Node> children = node.children;
                    for (Node child : children) {
                        queue.add(child);
                    }
                    list.add(node.val);
                }
            }
            if (list.size()>0) {
                lists.add(list);
            }
        }
        return lists;
    }


}
