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

    //链表节点数
    static int count;

    static {
        //保证创建链表时始终存在头结点
        head = new Node();
        count++;
    }


    /**
     * 获取第i个元素(初始条件：1<=i<=length)
     * @param i 要读取的元素的位置
     * @return
     */
    public Object getElement(int i){
        if(i > count || i< 1){
            System.out.println("ERROR：该位置超过范围");
            return false;
        }
        int j = 1;
        //获取头结点
        Node node = this.head;
        //获取第i-1位置的节点
        while (j < i){
            node = node.next;
            j++;
        }
        Object element = node.next.data;
        return element;
    }

    /**
     * 链表元素插入方法(初始条件：1<=i<=length)
     * @param i 插入的位置
     * @param element 要插入的元素
     * @return
     */
    public boolean linkedListInsert(int i,Object element){
        if(i > count || i<1){
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
        count++;
        return true;
    }

    /**
     * 删除指定位置的元素(初始条件：1<=i<=length)
     * @param i 要删除的元素位置
     * @return
     */
    public boolean delete(int i){
        if(i > count || i<1){
            System.out.println("ERROR：删除位置必须在范围内");
            return false;
        }
        //设置计数器
        int j = 1;
        //获取头节点
        Node node = this.head;
        //寻找第i-1的节点并且第i节点不为空,如果第i节点为空，这说明第i个节点是不存在的
        while (j < i && node.next!=null){
            node = node.next;
            j++;
        }
        if(node.next == null){
            System.out.println("第"+i+"个节点不存在");
            return false;
        }
        //执行到这里说明第i个节点存在
        //将第i+1节点赋给第i-1节点的next引用
        node.next = node.next.next;
        //节点数减1
        count--;
        return true;
    }

    /**
     * 清空链表
     */
    public void clear(){
        //获取头结点
        Node node = this.head;
        while (node!=null){
            Node next = node.next;
            //将下个节点赋给头结点
            node = next;
        }
    }

    /**
     * 获取链表长度
     * @return
     */
    public int size(){
        return count-1;
    }

    public static void main(String[] args){
        LinkedList linkedList = new LinkedList();
        linkedList.linkedListInsert(1,"a");
        linkedList.linkedListInsert(2,"b");
        linkedList.linkedListInsert(3,"c");
        linkedList.linkedListInsert(4,"d");
        System.out.println("获取第三个元素:" + linkedList.getElement(3)); //获取第三个元素:c (abcd)
        linkedList.linkedListInsert(2,"e");
        System.out.println("获取第三个元素:" + linkedList.getElement(3));//获取第三个元素:b (aebcd)
        linkedList.delete(3);
        System.out.println("获取第三个元素:" + linkedList.getElement(3));//获取第三个元素:c (aecd)
        linkedList.clear();
    }
}
