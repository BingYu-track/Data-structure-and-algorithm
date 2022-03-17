package lessons.week2;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/3/16
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }

}
