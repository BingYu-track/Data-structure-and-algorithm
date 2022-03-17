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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(val);
        ListNode tmp = next;
        while (tmp != null) {
            sb.append("," + tmp.val);
            tmp = tmp.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
