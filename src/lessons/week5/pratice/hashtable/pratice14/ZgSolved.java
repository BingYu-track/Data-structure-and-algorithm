package lessons.week5.pratice.hashtable.pratice14;

import java.util.LinkedList;

/**
 * @version 1.0
 * @Description: 设计哈希映射--争哥解法
 * @author: bingyu
 * @date: 2022/8/20
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved myHashMap = new ZgSolved();
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

    private class Pair {
        public int key;
        public int value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private static final int SLOTS_COUNT = 3535;
    private LinkedList<Pair>[] slots;
    public ZgSolved() {
        slots = new LinkedList[SLOTS_COUNT];
    }
    private int hash(int key) {
        return key % SLOTS_COUNT;
    }

    public void put(int key, int value) {
        LinkedList<Pair> slot = slots[hash(key)];
        if (slot == null) {
            slots[hash(key)] = new LinkedList<>();
            slot = slots[hash(key)];
        }
        for (int i = 0; i < slot.size(); ++i) {
            Pair pair = slot.get(i);
            if (pair.key == key) {
                slot.remove(i);
                break;
            }
        }
        slot.add(new Pair(key, value));
    }


    public int get(int key) {
        LinkedList<Pair> slot = slots[hash(key)];
        if (slot == null) {
            return -1;
        }
        for (int i = 0; i < slot.size(); ++i) {
            Pair pair = slot.get(i);
            if (pair.key == key) {
                return pair.value;
            }
        }
        return -1;
    }

    public void remove(int key) {
        LinkedList<Pair> slot = slots[hash(key)];
        if (slot == null) {
            return;
        }
        for (int i = 0; i < slot.size(); ++i) {
            Pair pair = slot.get(i);
            if (pair.key == key) {
                slot.remove(i);
                break;
            }
        }
    }


}
