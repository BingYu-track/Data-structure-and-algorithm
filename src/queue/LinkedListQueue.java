package queue;

/**
 * @version 1.0
 * @Description: 链式队列
 * @author: bingyu
 * @date: 2019/5/3 19:39
 */
public class LinkedListQueue<T> {

    private Node<T> head,tail;

    public LinkedListQueue(){ //初始化时
        head = new Node<T>(null, null);
    }

    /**
     * 入队 (链式队列无需考虑容量不足的问题)
     * @param element
     * @return
     */
    public boolean enqueue(T element){
        if(tail == null){
            Node<T> newNode = new Node<>(element, null); //开始，队头和队尾指向同一个链表头结点
            tail = newNode;
            head.next = tail;
        }else {
            tail.next = new Node<>(element,null);
            tail = tail.next; //将尾部指针指向下一个结点
        }
        return true;
    }

    /**
     * 出队
     * @return
     */
    public T dequeue(){
        if(head == tail){
            throw new UnsupportedOperationException("queue is empty");
        }
        Node<T> next = head.next; //获取头结点的后继结点
        T data = next.getData();//获取头部后继结点的值
        head.next = next.next; //将头部的后继结点移除，然后指向原后继结点的后继结点
        if(tail == next){ //如果头部紧跟的刚好是尾部，则将队尾指向队头
            tail = head;
        }
        return data;
    }

    public void printAll() {
        Node p = head.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    private static class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }

    public static void main(String[] args){
        LinkedListQueue<String> queue = new LinkedListQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        queue.printAll();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printAll();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printAll();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.printAll();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printAll();
    }
}
