package lessons.week6.pratice.btree.part1.pratice8;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: 剑指 Offer 32 - III. 从上到下打印二叉树 III --争哥解法
 * @author: bingyu
 * @date: 2022/9/8
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode leve2node1 = new TreeNode(9);
        TreeNode leve2node2 = new TreeNode(20);
        TreeNode leve3node1 = new TreeNode(10);
        TreeNode leve3node2 = new TreeNode(11);
        TreeNode leve3node3 = new TreeNode(15);
        TreeNode leve3node4 = new TreeNode(7);

        root.left = leve2node1;
        root.right = leve2node2;

        leve2node1.left = leve3node1;
        leve2node1.right = leve3node2;

        leve2node2.left = leve3node3;
        leve2node2.right = leve3node4;
        List<List<Integer>> lists = levelOrder3(null);
        System.out.println(lists);
    }

    /*
     争哥解法: 使用栈数据结构来解决，因为按照题目意思，节点顺序是不断变化的，就很适合栈!
            注意一个栈是不够用的，需要联合使用2个栈，
            推荐该方法--需要多次练习
    */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Stack<TreeNode>[] stacks = new Stack[2];
        for (int i = 0; i < 2; ++i) {
            stacks[i] = new Stack<TreeNode>();
        }
        int turn = 0;
        stacks[turn].add(root);
        while (!stacks[turn].isEmpty()) {
            List<Integer> curLevelNodes = new ArrayList<>(); // 记录本层节点
            while(!stacks[turn].isEmpty()) { //标识的栈为空停止循环
                TreeNode treeNode = stacks[turn].pop();
                curLevelNodes.add(treeNode.val);
                if (turn==0) { //先左后右
                    if (treeNode.left != null) {
                        stacks[1].push(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        stacks[1].push(treeNode.right);
                    }
                } else { //先右后左
                    if (treeNode.right != null) {
                        stacks[0].push(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        stacks[0].push(treeNode.left);
                    }
                }
            }
            result.add(curLevelNodes); //执行到这里说明turn标识的栈为空了，需要到另外一个栈去处理
            turn = (turn + 1) % 2; //这里是 如果turn是0，就返回1，如果turn是1就返回0
        }
        return result;
    }

    /*
    TODO: 需要多多练习
     复习解法1: 使用2种队列，其中核心思想是在层级用临时双向队列存储;
     这样遇到"从左至右"或者"从右至左"时，可以灵活将节点添加到尾部还是头部

    */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isOrderLeft = true; //对当前层节点的存储我们维护一个变量isOrderLeft 记录是从左至右还是从右至左的
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> levelList = new LinkedList<>(); //临时队列
            for (int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                if (node!=null) {
                    if (isOrderLeft) {
                        //node的当前层是从左至右，先将node放到放入层级临时队列末尾，再将其子节点放入队列
                        levelList.add(node.val);
                    }else {
                        //node的当前层是从右至左，先将node放入层级临时队列队头，再将其子节点放入队列
                        levelList.addFirst(node.val);
                    }
                    if (node.left!=null) {
                        queue.add(node.left);
                    }
                    if (node.right!=null) {
                        queue.add(node.right);
                    }
                }
            }
            if (!levelList.isEmpty()) {
                lists.add(new LinkedList<Integer>(levelList));
            }
            isOrderLeft = !isOrderLeft;
        }
        return lists;
    }

    /*
     后面使用2个栈试试,现将顺序放入栈1，栈1出栈时刚好就是从右至左的顺序，并放入栈2;栈2出栈时再将其对应的子节点放入栈1，此时顺序刚好是从左至右
     |  3 |   |    |    | 7  |
     |    |-->|    | -->| 15 |
     |____|   | 9  |    | 11 |
     栈1      |_20__|   |_10_|
                栈2      栈1
     1.根节点从栈1出栈
     2.子节点入栈2: 20、9
     3.出栈2，子节点入栈1: 10、11、15、7
     4.出栈1，子节点入栈2: 22、21、19、18.....刚刚好满足

     执行用时：1 ms, 在所有 Java 提交中击败了96.08%的用户
     内存消耗：41.6 MB, 在所有 Java 提交中击败了44.65%的用户
     推荐该方法
    */
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root == null) return lists;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        lists.add(list);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            while (!stack1.isEmpty()) { //栈1不为空，栈2为空；将栈1里的元素出栈，并将其子节点先右后左的方式存入栈2
                TreeNode node = stack1.pop();
                if (node.right!=null) { //判断左右子节点是否为空
                    stack2.push(node.right);
                    levelList.add(node.right.val);
                }
                if (node.left!=null) {
                    stack2.push(node.left);
                    levelList.add(node.left.val);
                }
            }
            if (levelList.size()>0) {
                lists.add(levelList);
            }

            levelList = new ArrayList<>();
            while (!stack2.isEmpty()) { //执行到这里说明栈1为空，栈2不为空；将栈2里的元素出栈，在将其子节点方式存入栈1
                TreeNode node = stack2.pop();
                if (node.left!=null) {
                    stack1.push(node.left);
                    levelList.add(node.left.val);
                }
                if (node.right!=null) {
                    stack1.push(node.right);
                    levelList.add(node.right.val);
                }
            }
            if (levelList.size()>0) {
                lists.add(levelList);
            }
        }
        return lists;
    }

}
