package lessons.week6.pratice.btree.part2.pratice5;

/**
 * @version 1.0 剑指 Offer 36. 二叉搜索树与双向链表
 * @Description: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *                4
 *              /   \
 *             2     5
 *            / \
 *           1  3
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，
 * 第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 *
 *
 *
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 * @author: bingyu
 * @date: 2022/9/14
 */
public class TreeToDoublyList {

    public static void main(String[] args) {
        Node root = new Node(4);
        Node node1 = new Node(2);
        Node node2 = new Node(5);
        root.left = node1;
        root.right = node2;

        Node node3 = new Node(1);
        Node node4 = new Node(3);
        node1.left = node3;
        node1.right = node4;
        Node node = treeToDoublyList(null);
        System.out.println(node);
    }


    private static Node dummyHead = new Node(-1); //虚拟头节点
    private static Node tail = dummyHead; //尾指针
    /*
     我的思路: 在中序遍历的过程中巧妙的使用tail指针和虚拟头节点dummyHead，使尾部结点的right指向虚拟头结点，虚拟头节点的left指向尾部结点；
             这样，遍历完后，就得到了一个以虚拟头节点的循环双向链表，后面只需要去掉虚拟头节点即可!
    */
    public static Node treeToDoublyList(Node root) {
        if (root == null) return root;
        inOrder(root);
        //执行到这里，tail就是指向的最尾部
        Node head = dummyHead.right;
        tail.right = head;
        head.left = tail;
        return head;
    }

    private static void inOrder(Node root) {
        if (root == null) return;
        Node left = root.left;
        Node right = root.right;
        inOrder(left);
        //TODO 构建双向链表简单，关键是用虚拟节点将链表的首尾连接起来
        tail.right = root;
        root.left = tail;
        tail = root;
        inOrder(right);
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

}
