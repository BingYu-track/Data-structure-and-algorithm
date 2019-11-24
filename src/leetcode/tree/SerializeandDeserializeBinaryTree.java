package leetcode.tree;

/**
 * @version 1.0
 * @Description: 297.二叉树的序列化与反序列化(困难)
 * @author: bingyu
 * @date: 2019/11/18 20:25
 */
public class SerializeandDeserializeBinaryTree {

    //序列化
    public static String serialize(TreeNode root) {

        return null;
    }

    //反序列化
    public TreeNode deserialize(String data) {

        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1; root.right = node2;
        node2.left = node3; node2.right = node4;
    }
}
