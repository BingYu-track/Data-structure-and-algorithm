package lessons.week6.pratice.btree.pratice20;

import lessons.common.TreeNode;

/**
 * @version 1.0 面试题 04.06. 后继者
 * @Description: 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *         [1,2,3,4,5,6]
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * 输出: null
 *
 * @author: bingyu
 * @date: 2022/9/6
 */
public class InorderSuccessor {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(4);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(6);
//        root.left = node1;
//        root.right = node2;
//        TreeNode node3 = new TreeNode(0);
//        TreeNode node4 = new TreeNode(2);
//        node1.left = node3;
//        node1.right = node4;
//        TreeNode node5 = new TreeNode(3);
//        node4.right = node5;
//        TreeNode node6 = new TreeNode(5);
//        TreeNode node7 = new TreeNode(7);
//        node2.left = node6;
//        node2.right = node7;
//        TreeNode node8 = new TreeNode(8);
//        node7.right = node8;

        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(1);
        node3.left = node5;
        TreeNode node = inorderSuccessor(root, node5);
        System.out.println(node);
    }

    private static boolean flag = false;
    private static TreeNode node = null;

    /*
     我的思路: 由于题意是求中序遍历中指定节点后面的节点，即左->根->右
            将二叉搜索树中序遍历，并将node放入list集合中，然后遍历集合校验，但是空间复杂度较高,因此
            我选择递归中寻找到p节点后用布尔值打上标识，然后下次遍历时用成员变量保存目标节点

      执行用时：3 ms, 在所有 Java 提交中击败了53.45%的用户
      内存消耗：42.1 MB, 在所有 Java 提交中击败了87.71%的用户
    */
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root,p);
        return node;
    }

    public static void inorder(TreeNode root, TreeNode p) {
        if (root == null) return;
        inorder(root.left,p); //
        if (flag) { //到这里且标准为true说明是p的下个节点
            if (node == null) { //这样是为了只让第一次flag为true后会赋值，避免后面重复赋值
                node = root;
            }
            return;
        }
        if (root == p) { //执行到这里说明root.left为null，root就是此时中序要打印的元素，此时比较p节点
            flag = true;
        }
        inorder(root.right,p);
//        if (root == p) { //执行到这里说明root.right为null，这里执行完后会返回到root父节点的
//            flag = true;
//        }
    }
    /*
     4->1->0->null ->[0]->null->[1]->2->null->[2]->[3]
                    5
                   / \
                  3   6
                 / \
                 2  4
                /
                1
    */

}
