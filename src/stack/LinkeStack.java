package stack;

/**
 * @version 1.0
 * @Description: 链式栈
 * @author: hxw
 * @date: 2019/4/14 21:08
 */
public class LinkeStack {

    private Node top; //将单链表头结点作为栈顶指针

    private static class Node {
        private Integer data;
        private Node next;

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public boolean push(int data){
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

    public int pop(){
        if(top == null){
            throw new UnsupportedOperationException();
        }
        int i = top.data;
        Node p = top; //将栈顶头结点赋值给p
        top = top.next;
        p = null; //释放
        return i;
    }

    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkeStack linkeStack = new LinkeStack();
        linkeStack.push(1);
        linkeStack.push(2);
        linkeStack.push(3);
        linkeStack.push(4);
        linkeStack.push(5);
        linkeStack.printAll();
        linkeStack.pop();
        linkeStack.pop();
        linkeStack.pop();
        linkeStack.printAll();
    }
}
