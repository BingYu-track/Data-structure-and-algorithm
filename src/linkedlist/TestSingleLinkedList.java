package linkedlist;

import linkedlist.upgrade.SinglyLinkedList;
import org.junit.Test;

/**
 * @version 1.0
 * @Description: 单链表方法测试
 * @author: hxw
 * @date: 2018/11/14 22:37
 */
public class TestSingleLinkedList {

    @Test
    public void testInsert(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,4,5};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println(linkedList);
    }

    @Test
    public void testDelete(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,4,5};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println("删除结点前"+linkedList);
        boolean b = linkedList.deleteNode(1);
        if(b){
            System.out.println("删除结点后 "+linkedList);
        }
    }

    @Test
    public void testFind(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,8,4,6};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println(linkedList);
        System.out.println(linkedList.findNode(5));
    }

    @Test
    public void testInsert2(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,4,5};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println("插入结点前:"+linkedList);
        linkedList.insertNode(linkedList.new Node(9,null),5);
        System.out.println("插入结点后:"+linkedList);
    }

    @Test
    public void testFindMidpoint(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,4,5,89,8,7,6,10,14};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        SingleLinkedList.Node midpoint = SingleLinkedList.findMidpoint(linkedList.getHead());
        System.out.println(midpoint);
    }

    @Test
    public void testReverseStart(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,4,5,89,8,7,6,10,14};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println("反转前："+linkedList);
        SingleLinkedList.Node reverse = linkedList.reverseStart(linkedList.getHead().getNext()); //传入第一结点
        SingleLinkedList.printAll(reverse);
    }

    @Test
    public void testReverseEnd(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,4,5,89,8,7,6,10,14};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println("反转前："+linkedList);
        SingleLinkedList.Node reverse = linkedList.reverseEnd(linkedList.findNode(8)); //传入第一结点
        SingleLinkedList.printAll(reverse);
    }

    @Test
    public void testIsPalindromeString(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,3,7,4,2,1};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println(linkedList);
        boolean palindromeString = linkedList.isPalindromeString(linkedList.getHead().getNext());
        System.out.println("是否是回文串："+palindromeString);
    }

    @Test
    public void testDeleteNodeBack(){
        SingleLinkedList linkedList = new SingleLinkedList();
        int[] a = {1,2,4,7,4,3,1,8};
        for (int i : a) {
            linkedList.insertNode(linkedList.new Node(i,null));
        }
        System.out.println("删除前："+linkedList);
        linkedList.deleteNodeBack(4);
        System.out.println("删除后："+linkedList);
    }

    @Test
    public void testUnionLinked(){
        SingleLinkedList al = new SingleLinkedList();
        SingleLinkedList bl = new SingleLinkedList();
        int[] a = {1,2,4,7,4,3,1,8};
        int[] b = {10,5,4,8,6,8};
        for (int i : a) {
            al.insertNode(al.new Node(i,null));
        }
        for (int i : b) {
            bl.insertNode(bl.new Node(i,null));
        }
        System.out.println("合并前al："+al+" bl:"+bl);
        al.unionLinked(bl);
        System.out.println("合并后："+al);
    }

    @Test
    public void testMergeSortedList(){
        SingleLinkedList al = new SingleLinkedList();
        SingleLinkedList bl = new SingleLinkedList();
        int[] a = {1,3,4,};
        int[] b = {2,3,4,6};
        for (int i : a) {
            al.insertNode(al.new Node(i,null));
        }
        for (int i : b) {
            bl.insertNode(bl.new Node(i,null));
        }
        System.out.println("合并前al："+al+" bl:"+bl);
        SingleLinkedList.Node node = al.mergeSortedList(bl);
        System.out.print("合并后：");
        SingleLinkedList.printAll(node);
    }
}
