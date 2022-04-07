package lessons.week2;

/**
 * @version 1.0
 * @Description: 链表类型题目解题技巧总结
 * @author: bingyu
 * @date: 2022/3/15
 */
public class Summary {

    /*
     链表相关的问题都会涉及"遍历"，核心是通过"画图举例"确定遍历的"三要素"
     1.遍历的结束条件：p==null or p.next==null ...
     2.指针的初始值：p=head or ...
     3.遍历的核心逻辑: ...(视题目要求而定)
     4.多使用结果链表的思想！
     5.多使用快慢指针的思想来解题!
     6.需要掌握的基础知识
       (1).链表中删除节点
           current.next = current.next.next
       (2).反转链表核心逻辑--头插,一直指向头节点指针
           current.next = dummyHead;
           dummyHead = current;
     还要注意特殊情况的处理: 是否需要对头节点、尾节点、空链表等做特殊处理？
     引入虚拟节点: 是否可以通过添加虚拟节点避免特殊情况的逻辑处理，简化编程？
     */
    public static void main(String[] args) {

    }
    //TODO:需要多练习part1的第1题、第4题、第6题
}
