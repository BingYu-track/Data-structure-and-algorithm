package lessons.week6.pratice.btree.pratice14;

import lessons.common.TreeNode;

/**
 * @version 1.0 合并二叉树
 * @Description: 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * 示例 1：
 *
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 *
 * 示例 2：
 *
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 *
 * 提示：
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 *
 * @author: bingyu
 * @date: 2022/8/31
 */
public class MergeTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node13 = new TreeNode(3);
        TreeNode node12 = new TreeNode(2);
        TreeNode node15 = new TreeNode(5);
        root1.left = node13;
        root1.right = node12;
        node13.left = node15;
        TreeNode root2 = new TreeNode(2);
        TreeNode node21 = new TreeNode(1);
        TreeNode node23 = new TreeNode(3);
        TreeNode node24 = new TreeNode(4);
        TreeNode node27 = new TreeNode(7);
        root2.left = node21;
        root2.right = node23;
        node21.right = node24;
        node23.right = node27;
        TreeNode mergeTrees = mergeTrees(root1, root2);
        System.out.println(mergeTrees);
    }



    /*
     我的思路: 就是两个树,t1,t2要一起遍历,使用递归进行处理;这里难点是终止条件；假设是两个树t1、t2我们以t1为基础合并；
                则，当进行合并时，t2的节点如果为null，那么t1的节点以及其子节点都没有必要合并下去了，可以直接返回;
                另外一个终止条件就是t1节点为null，t2对应的节点存在，直接将t2及其子节点全部移到t1节点对应的位置，
                因此还需要记录t1节点的父节点


     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：41.5 MB, 在所有 Java 提交中击败了50.88%的用户
    */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode parent = new TreeNode(-1); //虚拟根节点
        parent.left = root1; //将root1放到虚拟头节点的左子树下
        merge(root1,root2,parent,true);
        return parent.left; //返回也是返回的虚拟根节点的左子节点
    }

    /**
     *
     * @param root1
     * @param root2
     * @param parent root1的父节点
     * @param flag 判断当前操作的root1和root2是属于左子节点还是右子节点
     */
    private static void merge(TreeNode root1, TreeNode root2,TreeNode parent,boolean flag) {
        if (root2 == null) { //t2节点为空返回null，因为此时t1当前节点以及其子节点不需要任何操作，直接返回
            return;
        }else if (root1 != null) { //都不为空，将当前t1节点值变为两节点之和
            root1.val = root1.val + root2.val;
        }else { //root1 == null && root2 != null 如果当前位置，t1没有节点，但是t2有节点，直接将t2的节点赋值给t1那么，后面就不用再继续了直接返回
            if (flag) { //true说明是左子节点
                parent.left = root2;
            }else {
                parent.right = root2;
            }
            return;
        }
        merge(root1.left,root2.left,root1,true);
        merge(root1.right,root2.right,root1,false);
    }



}
