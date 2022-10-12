package lessons.week6.pratice.btree.part1.pratice12;

import lessons.common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: N叉树的最大深度--复习
 * @author: bingyu
 * @date: 2022/10/12
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
        int depth = maxDepth(root);
        System.out.println(depth);
    }

    private static int maxDepth(Node root) { //习惯是很难可以改变的
        return dfs(root,0);
    }

    /*
     N叉树的最大深度--需要多多理解
    */
    private static int dfs(Node root, int depth) {
        if (root == null) return depth;
        List<Node> children = root.children;
        int maxDepth = 0;
        for (Node child : children) { //遍历子节点
            int dep = dfs(child,depth);  //将子节点继续向下遍历;这里会不断递归深度遍历直到遇到叶子节点返回，会返回child的深度
            maxDepth = Math.max(maxDepth,dep);  //然后这里是进行children中所有节点深度进行比较，并取最大值
        }
        return maxDepth + 1;
    }


}
