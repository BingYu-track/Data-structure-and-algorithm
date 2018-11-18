package linkedlist.upgrade;

import linkedlist.SingleLinkedList;

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

    //无头结点
    //表头部插入
    //这种操作将于输入的顺序相反，逆序
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

    //顺序插入
    //链表尾部插入
    public void insertTail(int value){
        Node newNode = new Node(value, null);
        //空链表，可以插入新节点作为head，也可以不操作
        if (head == null){
            head = newNode;

        }else{
            Node q = head;
            while(q.next != null){
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
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

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //判断true or false(在判断回文串时调用)
    public boolean TFResult(Node left, Node right){
        Node l = left;
        Node r = right;
        System.out.println("left_:"+l.data);
        System.out.println("right_:"+r.data);
        while(l != null && r != null){
            if (l.data == r.data){ //将两列表一个个取出做比较相同，继续比较下一个，否则不是回文串
                l = l.next;
                r = r.next;
                continue;
            }else{
                break;
            }

        }
        System.out.println("什么结果");
        if (l==null && r==null){
            System.out.println("什么结果");
            return true;
        }else{
            return false;
        }
    }

    //　判断是否为回文(注意，回文串是指正读和反读都是一样的)
    public boolean palindrome(){
        if (head == null){
            return false;//空链表直接返回
        }else{
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            if (p.next == null){
                System.out.println("只有一个元素");//链表只有一个元素当然是回文串
                return true;
            }
            //注意：这里使用快进慢进法----两组引用，从头开始，p组一次进一，q组一次进二，q组到终点时，p组位置即为链表中间结点
            while( q.next != null && q.next.next != null){
                p = p.next;
                q = q.next.next;

            }

            System.out.println("中间节点" + p.data);
            System.out.println("开始执行奇数节点的回文判断");
            Node leftLink = null;
            Node rightLink = null;
            if(q.next == null){
                //　当q的下个节点为null时，说明p 一定为整个链表的中点，且节点数目为奇数
                //链表反转(注意这里是反转从头结点到中间结点的那段链表)
                rightLink = new Node(p.data,p.next);
                leftLink = inverseLinkList(p);
                System.out.println("左边第一个节点"+leftLink.data);
                System.out.println("右边第一个节点"+p.next.data);
                printAll(leftLink);
                printAll(rightLink);

            }else{
                //q.next不为空说明节点数目是偶数 p q均为中点
                leftLink = inverseLinkList(p);
                rightLink = q;
                printAll(leftLink);
                printAll(rightLink);
            }
            return TFResult(leftLink, rightLink);//对比左右两边是否相同

        }
    }

    public static void main(String[]args){
        SinglyLinkedList link = new SinglyLinkedList();
        System.out.println("hello");
        //int data[] = {1};
        //int data[] = {1,2,3,1},;
        int data[] = {1,2,4,5,6,5,4,6};
        //int data[] = {1,2,2,1};
        //int data[] = {1,2,5,2,1};
        for(int i =0; i < data.length; i++){
            //link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }
        link.printAll();
        //System.out.println(link.palindrome());
        /*Node p = link.inverseLinkList_head(link.head);
        while(p != null){
            System.out.println(p.data);
            p = p.next;
        }*/
        //System.out.println("打印原始:");
        //link.printAll();
        if (link.palindrome()){
            System.out.println("回文");
        }else{
            System.out.println("不是回文");
        }
    }

    //带结点的链表翻转
    public Node inverseLinkList_head(Node p){
        //　Head　为新建的一个头结点
        Node Head = new Node(9999,null);
        // p　为原来整个链表的头结点,现在Head指向　整个链表
        Head.next = p;
        /*
        带头结点的链表翻转等价于
        从第二个元素开始重新头插法建立链表
        */
        Node Cur = p.next;
        p.next = null;
        Node next = null;
        while(Cur != null){
            next = Cur.next;
            Cur.next = Head.next;
            Head.next = Cur;
            System.out.println("first " + Head.data);
            Cur = next;
        }

        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return Head;

    }

    //反转头结点到p结点这段链表(此时参数p为链表的中间结点)
    public Node inverseLinkList(Node p){
        Node pre = null;
        //再次获取头结点
        Node r = head;
        System.out.println("z---" + r.data);
        Node next= null;//保存p下个节点的引用
        //从头结点遍历到中间结点这段链表进行
        while(r !=p){
            next = r.next;
            r.next = pre;
            pre = r; //pre成了原先r节点
            r = next;//r成了原先r的下个节点
        }
        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }



    /**
     * 该方法是传入整个链表的头节点，结果得到其反转链表后的头结点
     * @param node
     * @return
     */
    public Node reverse(Node node) {
        Node prev = null;//上个节点
        Node now = node; //当前节点
        while (now != null) {
            //这里要先保存当前节点的下个节点，否则直接指向prev的话就断链了
            Node next = now.next;
            //将当前节点指向上个节点(注意：由于now在第一次循环时是头节点，反转后必然就是尾结点了，而尾结点的下个节点就是null，因此这里Node prev = null就是now的上个节点)
            now.next = prev;
            //下面两行代码操作就是将prev和now向后移动一位
            prev = now;
            now = next;
        }
        //当循环到now为null时，now的上个结点prev就是反转链表后的头节点
        return prev;
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

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }


}
