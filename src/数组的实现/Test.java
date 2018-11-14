package 数组的实现;

import linkedlist.upgrade.SinglyLinkedList;


/**
 * @version 1.0
 * @Description:
 * @author: hxw
 * @date: 2018/11/11 22:49
 */
public class Test {

    // 在数组 a 中，查找 key，返回 key 所在的位置
    // 其中，n 表示数组 a 的长度
    // a = {4, 2, 3, 5, 9, 6}  n=6 key = 7
    // a = {4, 2, 3, 5, 9, 6}  n=6 key = 6
    int find(int[] a, int n, int key) {
        if(a == null || n <= 0) {
            return -1;
        }

        // 这里因为要将 a[n-1] 的值替换成 key，所以要特殊处理这个值
        if (a[n-1] == key) {
            return n-1;
        }

        // 把 a[n-1] 的值临时保存在变量 tmp 中，以便之后恢复。tmp=6。
        // 之所以这样做的目的是：希望 find() 代码不要改变 a 数组中的内容
        int tmp = a[n-1];
        // 把 key 的值放到 a[n-1] 中，此时 a = {4, 2, 3, 5, 9, 7}
        a[n-1] = key;

        int i = 0;
        // while 循环比起代码一，少了 i<n 这个比较操作
        while (a[i] != key) { //因为这里一直遍历到末尾是可以匹配上key的，因此循环中断(设计非常巧妙)
            ++i;
        }

        // 恢复 a[n-1] 原来的值, 此时 a= {4, 2, 3, 5, 9, 6}
        a[n-1] = tmp;

        if (i == n-1) {
            // 如果 i == n-1 说明，在 0...n-2 之间都没有 key，所以返回 -1
            return -1;
        } else {
            // 否则，返回 i，就是等于 key 值的元素的下标
            return i;
        }
    }

    public static void main(String[] args){
        /*Test test = new Test();
        int[] a = new int[]{4, 2, 3, 5, 9, 6};
        int position = test.find(a, a.length, 1);
        System.out.println(position);*/
        SinglyLinkedList link = new SinglyLinkedList();
        int data[] = {1,2,4,5};
        for(int i =0; i < data.length; i++){
            link.insertTail(data[i]);
        }
        SinglyLinkedList.Node head = link.getHead();
        SinglyLinkedList.Node pre = link.reverse(head);
    }

    @org.junit.Test
    public void test(){
        Student student = new Student();
    }
}
