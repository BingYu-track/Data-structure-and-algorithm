package lessons.week6.pratice.btree.part1.pratice9.review1;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0 N叉树的层序遍历 --复习
 * @Description: 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 示例 1：
        1
    /   |   \
   3    2    4
 /  \
5    6
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 *
 * 示例 2：
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *
 * 提示：
 * 树的高度不会超过1000
 * 树的节点总数在 [0,10^4] 之间
 * @author: bingyu
 * @date: 2022/11/21
 */
public class LevelOrder {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        List<Node> list = new ArrayList<>();
        list.add(node3);
        list.add(node2);
        list.add(node4);
        root.children = list;

        List<Node> list1 = new ArrayList<>();
        list1.add(node5);
        list1.add(node6);
        node3.children = list1;

        List<Node> list2 = new ArrayList<>();
        list2.add(node7);
        node2.children = list2;

        List<Node> list4 = new ArrayList<>();
        list4.add(node8);
        node6.children = list4;

        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    /*
                    1
                /   |   \
               3    2    4
             /  \   /
            5    6 7
                  \
                   8
         如何判断一层是否遍历完毕?
     */
    private static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0;i<size;i++) {
                Node node = queue.poll(); //按size次数出队
                if (node != null) {
                    list.add(node.val); //每次放入list中
                    List<Node> children = node.children;
                    for (Node child : children) {
                        queue.add(child);
                    }
                }
            }
            if (list.size()>0) {
                lists.add(list);
            }
        }
        return lists;
    }


}
