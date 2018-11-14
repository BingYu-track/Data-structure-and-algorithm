package linkedlist;

/**
 * @version 1.0
 * @Description: 单链表实现
 * @author: hxw
 * @date: 2018/11/14 21:23
 */
public class SingleLinkedList {

    //头结点(头结点负责存储链表长度,后面才是第一结点)
    private Node head = new Node(0,null);

    /**
     * 插入结点(尾插)
     * @param newNode
     * @return true插入成功 false插入失败
     */
    public boolean insertNode(Node newNode){
        //存储节点引用
        Node node = head;
        //node.next不为null说明node不是尾结点,继续遍历
        while (node.next!=null){
            //获取下个节点
            node = node.next;
        }
        //执行到这里说明node已经是尾结点了
        node.next = newNode;
        //插入成功后长度加1
        head.data ++;
        return true;
    }

    /**
     * 在指定位置前插入结点
     * @param newNode 要插入的结点
     * @param position 位置
     * @return
     */
    public boolean insertNode(Node newNode,int position){
        //获取链表长度
        Integer length = head.data;
        if(position > length || position < 1){
            System.out.println("ERROR：输入的位置超过链表范围");
            return false;
        }

        return true;
    }

    /**
     * 通过位置查找结点
     * @param position 要查找的位置
     * @return 查找失败返回null
     */
    public Node findNode(int position){
        //获取链表长度
        Integer length = head.data;
        if(position > length || position < 1){
            System.out.println("ERROR：输入的位置超过链表范围");
            return null;
        }
        Node node = head;
        //找到position位置节点
        for (int i=0;i<position;i++){
            node = node.next;
        }
        return node;
    }

    /**
     * 删除指定位置结点
     * @param position 结点位置
     * @return
     */
    public boolean deleteNode(int position){
        //获取链表长度
        Integer length = head.data;
        if(position > length || position < 1){
            System.out.println("ERROR：删除的位置超过链表范围");
            return false;
        }
        Node node = head;
        //找到position-1位置的结点
        for (int i=0;i<position-1;i++){
            node = node.next;
        }
        //执行到这，此时node就是position-1位置的结点
        //将position+1结点赋给node.next
        node.next = node.next.next;
        //长度减一
        head.data --;
        return true;
    }


    //注意这里必须是public否则如果是private修饰的话，外部就无法返回该结点
    public class Node {
        private Integer data;
        private Node next;

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    //对外部开放头结点
    public Node getHead() {
        return head;
    }

    //重写toString方法
    @Override
    public String toString() {
        Node node = head;
        StringBuilder strb = new StringBuilder();
        strb.append("size:"+ head.data +" [");
        while (node.next!=null){
            node = node.next;
            strb.append(node.data+", ");
        }
        String s = strb.toString();
        if(head.data==0){
            return s + "]";
        }
        return s.substring(0,s.length()-2)+"]";
    }


}
