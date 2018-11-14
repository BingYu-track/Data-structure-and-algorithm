package linkedlist;

import linkedlist.upgrade.SinglyLinkedList;
import org.junit.Test;

/**
 * @version 1.0
 * @Description:
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
}
