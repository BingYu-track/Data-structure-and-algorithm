package stack;

/**
 * @version 1.0
 * @Description: 链式栈
 * @author: hxw
 * @date: 2019/4/14 21:08
 */
public class LinedStack {





    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
