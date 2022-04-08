package lessons.week3.example.example1;

/**
 * @version 1.0
 * @Description: 测试类
 * @author: bingyu
 * @date: 2022/4/8
 */
public class Test {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);

        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());

    }
}
