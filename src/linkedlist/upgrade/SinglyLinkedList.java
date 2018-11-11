package linkedlist.upgrade;

/**
 * @version 1.0
 * @Description: 他人单向链表实现
 * @author: hxw
 * @date: 2018/11/11 23:27
 */
public class SinglyLinkedList {

    private Node head = null;

    /**
     * 根据值查找其值相同的节点
     * @param value
     * @return
     */
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    /**
     * 根据位置查找节点
     * @param index
     * @return
     */
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        //链表不为空并且没遍历到对应位置index，就继续next
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**插入头节点
     * @param value
     */
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    /**
     * 将新节点插入到p节点后面
     * @param p
     * @param newNode
     */
    public void insertAfter(Node p, Node newNode) {
        if (p == null) return;

        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    /**
     * 将新节点插入到p节点之前
     * @param p
     * @param newNode
     */
    public void insertBefore(Node p, Node newNode) {
        if (p == null) return;
        //如果p是头节点
        if (head == p) {
            insertToHead(newNode);
            return;
        }

        Node q = head;
        //链表不为空并且，查找p节点的前驱节点
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        newNode.next = p;
        q.next = newNode; //此时q为p的前驱节点

    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) return;

        if (p == head) {
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        q.next = q.next.next;
    }

    public void deleteByValue(int value) {
        if (head == null) return;

        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }

        if (p == null) return;

        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }

        // 可重复删除指定value的代码
    /*
    if (head != null && head.data == value) {
    	head = head.next;
    }
    Node pNode = head;
    while (pNode != null) {
    	if (pNode.next.data == data) {
    		pNode.next = pNode.next.next;
    		continue;
    	}
    	pNode = pNode.next;
    }
    */
    }

    /**
     * 遍历打印链表
     */
    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 创建新链表
     * @param value
     * @return
     */
    public static Node createNode(int value) {
        return new Node(value, null);
    }

    /**
     * 静态内部类定义Node
     */
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
