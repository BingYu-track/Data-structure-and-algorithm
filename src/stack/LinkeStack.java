package stack;

/**
 * @version 1.0
 * @Description: 链式栈
 * @author: bingyu
 * @date: 2019/4/14 21:08
 */
public class LinkeStack<T> {

    private Node<T> top; //将单链表头结点作为栈顶指针

    private static class Node<T> {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }

    public boolean push(T data){
        Node newNode = new Node(data, null); //创建新的结点
        // 判断是否栈空
        if (top == null) {
            top = newNode; //为空
        } else {
            newNode.next = top; // 不为空，将当前的栈顶指针指向的结点赋值个新结点的直接后继结点(注意：这里始终将头结点作为栈顶指针)
            top = newNode; //指针
        }
        return true;
    }

    public T pop(){
        if(top == null){
            throw new UnsupportedOperationException();
        }
        T i = top.data;
        Node p = top; //将栈顶头结点赋值给p
        top = top.next;
        p = null; //释放
        return i;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        if(top != null){
            return false;
        }
        return true;
    }

    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //获取栈顶元素值
    public T getTopData() {
        return top.getData();
    }

    //清空栈
    public void clear(){
        top = null;
    }

    public static void main(String[] args){
        LinkeStack<String> linkeStack = new LinkeStack<String>();
        linkeStack.push("1");
        linkeStack.push("2");
        linkeStack.push("3");
        linkeStack.push("4");
        linkeStack.push("5");
        linkeStack.printAll();
        linkeStack.pop();
        linkeStack.pop();
        linkeStack.pop();
        linkeStack.printAll();
    }
}
