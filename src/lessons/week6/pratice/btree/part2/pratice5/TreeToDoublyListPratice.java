package lessons.week6.pratice.btree.part2.pratice5;

/**
 * @version 1.0
 * @Description: 剑指 Offer 36. 二叉搜索树与双向链表--重复练习
 * @author: bingyu
 * @date: 2022/9/20
 */
public class TreeToDoublyListPratice {

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
        Node node = treeToDoublyList(root);
        System.out.println(node);
    }

    /*将树展开为双向循环链表
              4
            /   \        1->2->3->4->5
           2     5
          / \
         1   3
         执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         内存消耗：40.7 MB, 在所有 Java 提交中击败了78.98%的用户
    */
    private static Node dummyHead = new Node(-1);
    private static Node tail = dummyHead;
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

        public Node(int _val, Node _left, Node _right) {
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
