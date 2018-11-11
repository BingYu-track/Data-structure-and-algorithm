package linkedlist;

/**
 * @version 1.0
 * @Description: 单链表java代码实现
 * @author: hxw
 * @date: 2018/11/11 10:39
 */
public class LinkedList {

    //头结点
    static Node head;

    //链表长度(头结点不算在长度内)
    static int length;

    static {
        //保证创建链表时始终存在头结点
        head = new Node();
    }


    /**
     * 获取第i个元素(初始条件：1<=i<=length)
     * @param i
     * @return
     */
    public Object getElement(int i){

        return null;
    }

    /**
     * 链表元素插入方法(初始条件：1<=i<=length)
     * @param i 插入的位置
     * @param element 要插入的元素
     * @return
     */
    public boolean linkedListInsert(int i,Object element){
        if(i > length || i<1){
            System.out.println("ERROR：插入位置必须在范围内");
            return false;
        }
        //设置计数器
        int j = 1;
        //第一次获取头节点
        Node node = this.head;
        //遍历i-1次，寻找第i-1个节点
        while (j < i){
            //获取
            node = node.next;
            j++;
        }
        if(j > i){ //当j大于要插入的位置i时报错
            System.out.println("Error");
            return false;
        }
        //生成新节点
        Node newNode = new Node();
        //给新节点储存数值
        newNode.data = element;
        //将原来的i位置的节点赋给新节点的next引用
        newNode.next = node.next;
        //将新节点赋给i-1位置节点的next引用
        node.next = newNode;
        //插入完成，链表长度加1
        length++;
        return true;
    }

    public static void main(String[] args){
        LinkedList linkedList = new LinkedList();
        linkedList.linkedListInsert(1,"a");
        linkedList.linkedListInsert(2,"b");
        linkedList.linkedListInsert(3,"c");
        linkedList.linkedListInsert(4,"d");
        linkedList.linkedListInsert(2,"e");
    }
}
