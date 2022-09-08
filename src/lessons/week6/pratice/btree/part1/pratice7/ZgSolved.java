package lessons.week6.pratice.btree.part1.pratice7;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 二叉树的层序遍历--争哥解法
 * @author: bingyu
 * @date: 2022/9/8
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node2.right = node4;
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    public static class TreeNodeWithLevel {
        TreeNode node;
        int level;
        public TreeNodeWithLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    //争哥解法1: 记录每个节点的层号（⾮递归），有点麻烦，不推荐!
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<TreeNodeWithLevel> q = new LinkedList<>();
        q.add(new TreeNodeWithLevel(root, 0));
        while (!q.isEmpty()) {
            TreeNodeWithLevel tl = q.poll();
            if (tl.level > result.size()-1) { //层数不够最大容量，就加一个List
                result.add(new ArrayList<>());
            }
            result.get(tl.level).add(tl.node.val); //将节点放到对应的层数里
            if (tl.node.left != null) {
                q.add(new TreeNodeWithLevel(tl.node.left, tl.level+1));
            }
            if (tl.node.right != null) {
                q.add(new TreeNodeWithLevel(tl.node.right, tl.level+1));
            }
        }
        return result;
    }

    //争哥解法2: 用null做每层的分隔
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            List<Integer> curLevelNodes = new ArrayList<>();
            while (!q.isEmpty()) {
                TreeNode node = q.poll(); //这里不停的出队，直到遇到分隔null停下来
                if (node == null) {
                    break;
                }
                curLevelNodes.add(node.val); //执行到这里说明没有遇到null，将该节点加入结果数组，然后将左右子节点入队
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            //执行到这里说明一层执行完成，因为肯定是遇到分隔null后跳出循环的
            if (!curLevelNodes.isEmpty()) {
                result.add(curLevelNodes);
                q.add(null);
            }
        }
        return result;
    }


    /*
    争哥解法3:递归解法
    */
    private static List<List<Integer>> result = new ArrayList<List<Integer>>();

    public static List<List<Integer>> levelOrder3(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private static void dfs(TreeNode root, int level) {
        if (root == null) return;
        if (level > result.size()-1) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        dfs(root.left, level+1); //这里深度遍历，没递归一次就是往下一层，因此层号是需要加1
        dfs(root.right, level+1);
    }

}
