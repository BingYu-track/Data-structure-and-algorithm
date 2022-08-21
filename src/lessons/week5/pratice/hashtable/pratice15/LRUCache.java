package lessons.week5.pratice.hashtable.pratice15;

import java.util.HashMap;

/**
 * @version 1.0 LRU 缓存
 * @Description: 请你设计并实现一个满足LRU (最近最少使用) 缓存约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量capacity初始化LRU缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
 *  如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 示例：
 *
 * 输入:
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 *
 * 输出:
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 *
 * @author: bingyu
 * @date: 2022/8/20
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {2=1}
        lRUCache.put(1, 1); // 缓存是 {1=1, 2=1}
        lRUCache.put(2, 3); // 缓存是 {2=3, 1=1}
        lRUCache.put(4, 1); // 缓存是 {4=1,2=3}
        int i1 = lRUCache.get(1);//-1
        int i2 = lRUCache.get(2);//3
        System.out.println();
    }

    /* LRU缓存思路: LRU，把最近访问的数据放到前面；最少访问的数据放到后面，类似队列
       为什么不使用队列，而是双向链表，因为队列是先进先出;不符合LRU访问元素时就要将其放到第一个位置

       1.LRUCache(int capacity)
       2.int get(int key)--当访问数据时，根据key到hashMap中查找node，如果没有就返回-1；找到了就将该node移动到链表头部并返回值
       3.void put(int key,int value)--关键字key 已经存在，则变更其数据值value，并放到缓存开头;如果不存在，则向头部中插入该组key-value 。
            如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的数据，再将新的数据存入。

        TODO:
         问: hashMap在其中扮演的什么角色？
         答: 因为要求LRUCache的put和get方法时间复杂度为O(1)，那么要求数据结构的查询时间为O(1)，插入时间为O(1)，删除时间为O(1);
            插入时间和删除时间要求O(1)，那么肯定不能使用数组，当然就只能是用链表了，插入删除都是指针的修改，因此链表满足插入和删除的时间复杂度都是O(1)；
            但是链表的查询时间复杂度是O(n)，因此我们想到用hashMap去来查找，因此我们只需要用hashMap根据key映射对应的链表节点，即可满足查询时间复杂度为
            O(1)的条件！注意链表使用虚拟节点
    执行用时：43 ms, 在所有 Java 提交中击败了67.91%的用户
    内存消耗：108.9 MB, 在所有 Java 提交中击败了54.47%的用户
    */

    private int capacity; //缓存容量
    private int size; //已经存储的元素个数
    private Node head; //头节点
    private Node tail; //尾节点

    private HashMap<Integer,Node> hashTable = new HashMap();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head; //尾节点的prev指向头节点
    }

    public int get(int key) {
        Node node = hashTable.get(key);
        if (node == null) return -1;
        //将node节点移除原来的位置
        node.prev.next = node.next; //如果没有设置虚拟头节点，这里就会报空指针异常
        node.next.prev = node.prev;
        //插入头部
        appendHeadNode(node); //每次查询必须添加到头部，这样才能使长久没用的数据慢慢移动到尾部，直到删除
        return node.val;
    }

    public void put(int key, int value) {
        Node node = hashTable.get(key);
        if (size >= capacity && node==null) { //超过容量并且不存在当前元素时，需要先移除
            //超过容量，先删除尾部的节点
            int prevKey = tail.prev.key;
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
            hashTable.remove(prevKey);
            size--;
        }
        if (node == null) {
            //Lru没有该元素，将该元素插入头部节点
            node = new Node(key, value);
            appendHeadNode(node);
            size++;
        }else {
            //Lru已经有该元素了,更新value值
            node.val = value;
            //将node节点移除原来的位置
            node.prev.next = node.next; //如果没有设置虚拟头节点，这里就会报空指针异常
            node.next.prev = node.prev;
            //插入头部
            appendHeadNode(node);
        }
        //插入到头部后再存入hashMap映射
        hashTable.put(key,node);
    }


    public void appendHeadNode(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public class Node {
        private Node prev; //前驱节点
        private Node next; //后继节点
        private int key;
        private int val; //节点值

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


}
