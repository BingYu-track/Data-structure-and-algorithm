package lessons.week5.example.hashtable.example4;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0 LRU缓存(腾讯暑期实习) 要求内存容量大小为5，按照key-value形式存储，结构的头部为最近最常使用的结点，尾部为最近最少使用的结点
 * LRU有两个操作：get和put，每次get和put操作都将查询的值变为最近最常使用的结点，当put容量已满时，删除最近最少使用的结点
 *
 * @Description: 缓存有大小限制，假设只能存储n个数据，当缓存已满，再有数据插入时，缓存会将最久未被访问的数据从缓存中删除，然后再将新数据插入
 *             缓存主要包含3个操作:
 *             1.在缓存中查找一个数据;
 *             2.从缓存中删除一个数据;
 *             3.往缓存中添加一个数据;
 *
 *          基于哈希表+双向链表的实现方案:
 *          1.借助哈希表，是为了能快速得到要查找、要删除的结点，因为查找的时间复杂度为O(1)。
 *          2.借助双向有序链表，维护数据的有序性(按照访问时间)
 *
 *          双向有序链表:
 *          1.头放最新的数据，尾放最老的数据
 *          2.头放最老的数据，尾放最新的数据
 * @author: bingyu
 * @date: 2022/8/10
 */
public class LRUCache {

    /*
     执行用时：144 ms, 在所有 Java 提交中击败了44.00%的用户
     内存消耗：91.3 MB, 在所有 Java 提交中击败了15.39%的用户
    */
    public static void main(String[] args) { //不能用LinkedHashMap，因为它本身就是一个LRU缓存的实现
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);
        lruCache.put(5,5);
        lruCache.put(6,6);
        System.out.println();
    }

    private class DLinkedNode { //双向链表
        public int key;  //id，标识
        public int value; //数据信息
        public DLinkedNode prev; //前驱结点
        public DLinkedNode next; //后继结点

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    private Map<Integer,DLinkedNode> hashtable = new HashMap<Integer,DLinkedNode>();
    private int size; //已经存储的元素个数
    private int capacity; //容量
    private DLinkedNode head;
    private DLinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLinkedNode(-1,-1); //双向链表需要创建2个虚拟头结点
        this.tail = new DLinkedNode(-1,-1);
        this.head.prev = null;
        this.head.next = tail;
        this.tail.prev = head;
        this.tail.next = null;
    }

    /**
     * 我的思路；构建一个头放最新的数据，尾放最老的数据的LRU缓存
     *  1.在缓存中查找一个数据; --查找到元素时，将元素移动到头部(因为是最新访问的元素，需要更新其访问时间)
     *  2.从缓存中删除一个数据; --先查找元素，找到了就直接删除，因为是双向链表，操作很简单
     *  3.往缓存中添加一个数据; --先查看缓存中有没有待添加的元素，如果有不用添加，但是相当于访问了，需要将
     *    元素放到头部，如果没有该元素，则判断LRU缓存是否已满，满了，就删除末尾元素再插入元素;没满,直接插入
     */

    //通过key查找LRU缓存中的元素
    public int get(int key) {
        if (size == 0) return -1;
        DLinkedNode node = hashtable.get(key);
        if (node == null) return -1;
        //执行到这里，说明存在node,将该node移动到双向链表头部
        removeNode(node); //先删除该节点的位置
        addNodeAtHead(node); //再将该节点添加到头部
        return node.value;
    }

    //删除节点 vm->vm->null
    public void removeNode(DLinkedNode node) {
        node.prev.next = node.next; //将node的前驱结点的next指针，指向node的后继结点
        node.next.prev = node.prev; //将node的后继结点的prev指针，指向node的前驱结点
        /*
        TODO： 注意如果上面初始化双向链表时没有用虚拟节点，当遇到以下特殊情况时，上面这个代码是会报空指针的
             1.当删除尾结点时，node.next是等于null的，因此node.next.prev = node.prev就会报错，所以需要我们添加虚拟尾结点
             2.当只剩下一个结点时，删除这最后一个结点，node.prev等于null，因此node.prev.next = node.next就会报错，所以需要我们添加虚拟头结点
        */
    }

    //将结点插入头部位置
    public void addNodeAtHead(DLinkedNode node) {
        node.next = head.next; //node的next指针指向头结点的后继结点
        node.prev = head; //node的prev指针指向头结点
        head.next.prev = node; //头节点的后继结点的prev指针，指向node
        head.next = node; //头节点的next指针，指向node
    }

    //删除操作
    public void remove(int key) {
        DLinkedNode node = hashtable.get(key);
        if (node != null) {
            removeNode(node);
            hashtable.remove(key);
        }
    }

    //添加数据
    public void put(int key,int value) {
        //先查看是否存在同样的key，如果有，就覆盖旧的value值
        DLinkedNode node = hashtable.get(key);
        if (node != null) {
            node.value = value; //虽然存在，但是仍然算是访问了该节点，需要将该节点移动到头部
            removeNode(node);
            addNodeAtHead(node);
        }else { //没有相同的key也就是没有找到该元素，需要添加进去
            if (size == capacity) { //判断容量是否满了，满了就删除尾节点
                removeNode(tail.prev); //删除虚拟头节点前面的结点
                size--;
            }
            DLinkedNode newNode = new DLinkedNode(key, value);
            hashtable.put(key,newNode);
            addNodeAtHead(newNode);
            size++;
        }
        //再将结点添加到头部节点

    }


}
