package lessons.week5.pratice.hashtable.pratice14;



/**
 * @version 1.0  设计哈希映射
 * @Description: 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *
 * 示例：
 *
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 *
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 *
 * 提示：
 * 0 <= key, value <= 10^6
 * 最多调用 104 次 put、get 和 remove 方法
 *
 * @author: bingyu
 * @date: 2022/8/17
 */
public class MyHashMap {

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(74, 64);
        myHashMap.put(28,48);
        myHashMap.put(2,48);
        int i1 = myHashMap.get(2);
        myHashMap.put(99,78);
        myHashMap.put(29,66);
        myHashMap.remove(99);
        myHashMap.put(43,38);
        myHashMap.remove(28);
        myHashMap.put(63,9);
        myHashMap.remove(2);
        myHashMap.put(88,26);
        myHashMap.put(50,28);
        int i2 = myHashMap.get(43);
        myHashMap.put(7,7);
        myHashMap.put(31,84);
        myHashMap.put(23,77);
        myHashMap.put(53,60); //这里进行扩容时添加到桶位链表末尾
        myHashMap.put(71,49);
        myHashMap.put(28,23);
        myHashMap.put(19,74);
        myHashMap.put(98,72);
        int i3 = myHashMap.get(71);
        myHashMap.put(77,45);
        myHashMap.put(25,67);
        myHashMap.put(68,44);
        myHashMap.put(68,88);
        int i4 = myHashMap.get(48);
        myHashMap.put(8,21);
        myHashMap.put(35,86);
        myHashMap.remove(43);
        myHashMap.put(52,89);
        myHashMap.remove(63);
        int i5 = myHashMap.get(63); //-1 这里删除出现错误，刚好要删除的是头结点
        myHashMap.remove(23);
        myHashMap.put(72,91);
        int i6 = myHashMap.get(28); //23
        myHashMap.put(26,10);
        myHashMap.put(12,25);
        myHashMap.put(34,61);
        myHashMap.put(76,99);
        myHashMap.remove(34);
        System.out.println();



    }

    /* TODO: 需要重点复习
     我的思路: 使用数组和链表两个数据结构结合
            1.创建时 --创建固定大小数组，数组用来存储链表(初始值设为16)
            2.put方法 --先判断哈希表是否到达阈值(根据负载因子计算，即阈值=容量*负载因子)
                          a.没到，就将数值进行哈希，得到对应的hash值后，将数值存入对应数组位置的链表末尾
                          b.达到了阈值，将数组进行扩容为原来的2倍，并将之前已存的元素重新进行哈希，存入对应数组位置的链表，然后再将目标值存入哈希表
            3.get方法 --同样计算hash值，然后根据hash值到对应的位置寻找，找到了就返回，没找到就返回-1
            4.remove方法 --先计算hash值，然后根据hash值到对应的位置寻找，找到了就删除，没找到就返回

            问题1:hashMap中的hash计算为什么用(h = key.hashCode()) ^ (h >>> 16)这个计算公式?
           TODO: 这样做的目的是用来做哈希值优化的，把哈希值右移16位，也就正好是自己长度的一半，之后与原哈希值做异或运算，
                 这样就混合了原哈希值中的高位和低位，增大了随机性，hash方法就是为了增加随机性，让数据元素更加均衡的分布，减少碰撞

            问题2:为什么hashMap中选择桶位要根据 value & (length-1)来映射? 为什么不直接用求模的计算方式?
           TODO: 首先，我们知道hashMap的length都是2的n次方，因此length-1的二进制低位全是1，例如16-1=15的二进制等于1111
                 32-1=31的二进制等于11111，根据上面main方法的举例，可知道value & (length-1)这个算法就是取最低不为0的二进制数，
                 这个公式的计算结果只有2种可能，元素的位置要么是在原位置，要么是在原位置加原数组长度的位置;这样就使当前桶中的元素总数
                 一定小于等于原来桶中的元素数量，避免了更严重的hash冲突，均匀的把之前冲突的节点分散到新的桶中去。事实上这个计算方式就
                 是求模，二者是等价的，但是位运算要比用%更快!

            问题3:但是就产生了新的疑问，为啥value & (length-1)的结果恰好是 value % length的值呢?
             TODO: 暂缓

        执行用时：15 ms, 在所有 Java 提交中击败了92.21%的用户
        内存消耗：45.2 MB, 在所有 Java 提交中击败了94.37%的用户
    */
    private ListNode[] table = null;
    private final int DEFAULT_CAPACITY = 1 << 4; //初始容量 10000
    private final float DEFAULT_LOAD_FACTORY = 0.75f; //默认负载因子
    private int CAPACITY = 0; //当前哈希表的容量
    private int size = 0; //元素个数

    public MyHashMap() {
        table = new ListNode[DEFAULT_CAPACITY];
        CAPACITY = DEFAULT_CAPACITY;
    }

    /*
     有个难点，如果之前已经存入相同的key了，那么如何更新key对应的value? 解决方法: node里不仅要存value，还要存key才行
    */
    public void put(int key, int value) {
        float threshold = CAPACITY * DEFAULT_LOAD_FACTORY; //计算当前阈值
        if (threshold > size) {
            int hash = key & (CAPACITY - 1); //位运算取模
            if (table[hash] == null) {
                table[hash] = new ListNode(key,value);
                size++;
            }else {
                //有节点，则遍历链表，判断是否有key
                ListNode node = table[hash];
                boolean flag = false; //用来记录node链表里是否已包含key
                while (node.next!=null) { //必须要使用这样的条件负责下面在尾部添加结点会报空指针
                    if (node.key == key) { //如果遇到相同的key，则更新value
                        node.val = value;
                        flag = true;
                        break;
                    }
                    node = node.next;
                }
                if (!flag) {
                    if (node.key!=key) {
                        //执行到这里都没找到key，说明确实不存在，创建节点并添加到末尾
                        node.next = new ListNode(key,value);
                        size++;
                    }else {
                        node.val = value;
                    }

                }
            }
        }else {
            //需要扩容--hashMap的扩容机制
            ListNode[] oldTable = table; //旧数组
            int oldCap = CAPACITY; //记录扩容前旧数组的长度
            CAPACITY = CAPACITY << 1; //扩容后的数组长度
            ListNode[] newTable = new ListNode[CAPACITY]; //创建新的数组
            table = newTable;
            for (int i=0;i<oldCap;i++) {
                ListNode e; //记录bucket的头节点
                if ((e = oldTable[i]) != null) { //只处理数组中有节点的bucket
                    oldTable[i] = null; //将旧数组中的节点移除
                    if (e.next == null) { //如果该bucket刚好只有一个链表头结点，则进行取模后放到新数组的bucket
                        newTable[e.key & (CAPACITY - 1)] = e;
                    }else { //说明有多个节点，循环处理
                        ListNode loHead = null, loTail = null;//定义两个指针，分别指向低位链表的头部和尾部
                        ListNode hiHead = null, hiTail = null;//定义两个指针，分别指向高位链表的头部和尾部
                        ListNode next;
                        do { //TODO 这里是循环体
                            next = e.next; //从头结点移动到下一个节点
                            /*假设e.key=9(01001)  oldCap=16(10000)  newCap=32(100000)
               //TODO 等于0说明key的最高位小于oldCap的最高位1，后面扩容后的newCap-1的最高位和oldCap的最高位是一样的，因此和newCap-1进行与计算的结果就是原来的桶位
                             (01001 & oldCap) = 0
                             (01001 & oldCap-1) = 01001 & 1111 = 1001
                             (01001 & newCap-1) = 01001 & 11111 = 1001

                             e.key=25(11001)  oldCap=16(10000)  newCap=32(100000)
                             (11001 & oldCap) = 10000
                             (11001 & oldCap-1) = 11001 & 1111 = 1001
                             (11001 & newCap-1) = 11001 & 11111 = 11001


                            这里e.key & oldCap计算的结果，要么是0,要么就是原数组长度;如果是0，就说明后面(key & newCap-1)对应桶位位置不变
                            如果结果是原数组长度，那么后面(key & newCap-1)对应桶位位置需要加上原来数组的容量;
                            TODO 注意原数组同一个bucket的key虽然是相同的，但是扩容后bucket就不一定一样了，比如[7=7,23=77]在容量为16时都
                                 在bucket=7这个位置，但是扩容为32后,23=77这个节点就到bucket=23这个桶位了，因此发送了变化，所以扩容后需要
                                 我们将每个桶位的链表节点一个一个的重新计算新桶位
                            TODO；下面这块代码是记录链表的头结点和尾结点

                             */
                            if ((e.key & oldCap) == 0) {
                                if (loTail == null) { //如果计算结果为0，就将节点插入低位链表
                                    loHead = e; //设置头部节点
                                }else {
                                    loTail.next = e; //如果尾部结点已存在，继续添加到尾部
                                }
                                loTail = e; //设置尾部结点
                            }else { //如果计算结果为数组长度，就将节点插入高位链表
                                if (hiTail == null) {
                                    hiHead = e;
                                }else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null); //e是头节点，从头节点开始不断向后遍历，直到末尾

                        if (loTail != null) {
                            loTail.next = null; //这里置为null是防止后面链表节点跟着这里存进错误的新桶位
                            newTable[i] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTable[i + oldCap] = hiHead;
                        }
                    }
                }

            }
            //执行到这说明扩容完毕，将元素放进扩容后的数组
            int hash = key & (CAPACITY - 1); //位运算取模
            if (table[hash] == null) {
                table[hash] = new ListNode(key,value);
                size++;
            }else {
                //有节点，则遍历链表，判断是否有key
                ListNode node = table[hash];
                boolean flag = false; //用来记录node链表里是否已包含key
                while (node.next!=null) {
                    if (node.key == key) { //如果遇到相同的key，则更新value
                        node.val = value;
                        flag = true;
                        break;
                    }
                    node = node.next;
                }
                if (!flag) {
                    if (node.key!=key) {
                        node.next = new ListNode(key,value);
                        size++;
                    }else {
                        node.val = value;
                    }
                }
            }

        }

    }

    public int get(int key) {
        int hash = key & (CAPACITY - 1);
        ListNode node = table[hash];
        if (node == null) {
            return -1;
        }else {
            while (node!=null) {
                if (node.key == key) {
                    return node.val;
                }
                node = node.next;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int hash = key & (CAPACITY - 1);
        ListNode node = table[hash];
        if (node == null) {
            return;
        }else {
            if (node.key == key) { //要删除的node刚好是头结点
                table[hash] = node.next;
                node = null;
            }else {
                //删除的不是头结点
                while (node.next!=null) {
                    if (node.next.key == key) {
                        node.next = node.next.next;
                        return;
                    }
                    node = node.next;
                }
            }
        }
    }

     class ListNode {
        public int key;
        public int val;
        public ListNode next;

        public ListNode() {

        }

        public ListNode(int key,int val) {
            this.key = key;
            this.val = val;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            sb.append(key).append("=").append(val);
            ListNode tmp = next;
            while (tmp != null) {
                sb.append("," + tmp.key).append("=").append(tmp.val);
                tmp = tmp.next;
            }
            sb.append("]");
            return sb.toString();
        }

    }


}


