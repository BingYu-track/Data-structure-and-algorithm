package lessons.week6.pratice.btree.part2.pratice12;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0 剑指 Offer 34. 二叉树中和为某一值的路径
 * @Description: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 *                   5
 *                 /   \
 *                4    8
 *               /    / \
 *              11   13  4
 *             /  \     / \
 *            7    2   5   1
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 *
 * 示例 2：
 *         1
 *       /  \
 *      2   3
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 *
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 * 实例4:
 *                          1
 *                        /
 *                       2
 *                     /
 *                    3
 *                   /
 *                  4
 *                 /
 *                5
 * 输入: root = [1,2,null,3,null,4,null,5] , targetSum = 6
 * 输出: []
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * @author: bingyu
 * @date: 2022/9/28
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;

        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
//        TreeNode root = new TreeNode(1);
//        TreeNode node = new TreeNode(2);
//        root.left = node;
        List<List<Integer>> lists = pathSum(root,22);
        System.out.println(lists);
    }

    /*
     我的思路: 我采用后序遍历，在遍历期间，压栈时累加，弹栈时减去当前节点;当遇到空节点时说明到了末尾，
     判断和目标值是否相同，相同就存进去
    */
    private static List<List<Integer>> lists = new ArrayList<>();
    private static int sum = 0; //记录路径的和
    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<Integer> list = new ArrayList<>();
        dfs(root,target,list);
        return lists;
    }

    private static void dfs(TreeNode root, int target,List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        sum = sum + root.val;
        dfs(root.left,target,list);
        if(root.left == null && root.right == null) { //左右都没有子节点，才是叶子节点
            if (sum == target) { //到了叶子节点末尾，如果路径和等于目标值，就放入lists
                List<Integer> result = new ArrayList<>();
                for (Integer num : list) {
                    result.add(num);
                }
                lists.add(result);
            }
        }else if (root.left != null){
            //弹栈
            sum = sum - root.left.val;
            list.remove(list.size() - 1);
        }

        dfs(root.right,target,list);
        if (root.right == null && root.left == null) {
            if (sum == target) { //到了叶子节点末尾，如果路径和等于目标值，就放入lists(
                // 注意:如果左子节点和右子节点都为null，可能会重复添加2次,因此这里需要进行限制
                List<Integer> result = new ArrayList<>();
                for (Integer num : list) {
                    result.add(num);
                }
                lists.add(result);
            }
        }else if (root.right != null){
            sum = sum - root.right.val;
            list.remove(list.size() - 1);
        }

    }

}
