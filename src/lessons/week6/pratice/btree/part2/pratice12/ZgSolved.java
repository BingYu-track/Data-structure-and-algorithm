package lessons.week6.pratice.btree.part2.pratice12;

import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 剑指 Offer 34. 二叉树中和为某一值的路径
 * @Description: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有从根节点到叶子节点路径总和等于给定目标和的路径。
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
 * @date: 2022/12/7
 */
public class ZgSolved {

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


    private static List<List<Integer>> pathSum = new ArrayList<>();

    private static int sum = 0;

    /*
     解题思路: 题目描述是根节点到子节点，因此应该是根节点遍历到子节点时记录，路径上的和
     执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：41.7 MB, 在所有 Java 提交中击败了64.67%的用户
    */
    private static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<Integer> list = new ArrayList<>();
        dfs(root,target,list);
        return pathSum;
    }

    //TODO: 这题是我独自思考解决的!
    private static void dfs(TreeNode root, int target, List<Integer> list) {
        if (root == null) {
            return; //root等于null说明已经到了叶子节点
        }
        int rootValue = root.val;
        sum += rootValue;
        list.add(rootValue);
        dfs(root.left, target, list);
        dfs(root.right, target, list);
        //执行到这里说明当前root下面已经遍历完成，并且root如果是叶子节点，还要比较目标值和路径和；
        if (sum == target && root.left == null && root.right == null) {
            pathSum.add(new ArrayList<>(list)); //这里list在后面元素会不断变化，因此需要copy备份
        }
        //处理完后就需要向上返回，因此减去当前root值;list也需要减去相应的元素
        sum = sum - rootValue;
        list.remove(list.size()-1);
    }


}
