package linkedlist;

/**
 * @version 1.0
 * @Description: 单链表实现
 * @author: hxw
 * @date: 2018/11/14 21:23
 */
public class SingleLinkedList {

    //头结点(头结点负责存储链表长度,后面才是第一结点)
    private Node head = new Node(true,0,null);

    /**
     * 插入结点(尾插)
     * @param newNode
     * @return true插入成功 false插入失败
     */
    public boolean insertNode(Node newNode){
        //存储节点引用
        Node node = head;
        //node.next不为null说明node不是尾结点,继续遍历
        while (node.next!=null){
            //获取下个节点
            node = node.next;
        }
        //执行到这里说明node已经是尾结点了
        node.next = newNode;
        //插入成功后长度加1
        head.data ++;
        return true;
    }

    /**
     * 在指定位置前插入结点
     * @param newNode 要插入的结点
     * @param position 位置
     * @return
     */
    public boolean insertNode(Node newNode,int position){
        //获取链表长度
        Integer length = head.data;
        if(position > length || position < 1){
            System.out.println("ERROR：输入的位置超过链表范围");
            return false;
        }
        Node node = head;
        //找到position-1位置的结点
        for (int i=0;i<position-1;i++){
            node = node.next;
        }
        //将position位置的结点赋给新插入的结点的next引用
        newNode.next = node.next;
        //再将新结点赋给position-1位置的结点的next引用
        node.next = newNode;
        //长度加1
        head.data ++;
        return true;
    }

    /**
     * 通过位置查找结点
     * @param position 要查找的位置
     * @return 查找失败返回null
     */
    public Node findNode(int position){
        //获取链表长度
        Integer length = head.data;
        if(position > length || position < 1){
            System.out.println("ERROR：输入的位置超过链表范围");
            return null;
        }
        Node node = head;
        //找到position位置节点
        for (int i=0;i<position;i++){
            node = node.next;
        }
        return node;
    }

    /**
     * 删除指定位置结点
     * @param position 结点位置
     * @return
     */
    public boolean deleteNode(int position){
        //获取链表长度
        Integer length = head.data;
        if(position > length || position < 1){
            System.out.println("ERROR：删除的位置超过链表范围");
            return false;
        }
        Node node = head;
        //找到position-1位置的结点
        for (int i=0;i<position-1;i++){
            node = node.next;
        }
        //执行到这，此时node就是position-1位置的结点
        //将position+1结点赋给node.next
        node.next = node.next.next;
        //长度减一
        head.data --;
        return true;
    }

    /**
     * 查找指定结点后一段链表的中点
     * @return
     */
    public static Node findMidpoint(Node node){
         Node a,b;
        //如果传过来的节点是头结点，并且没有第一结点，那么算作该链表长度为0
        if(node.isHead && node.next==null){
            System.out.println("ERROR：该链表长度为0，没有中点");
            return null;
        }
        //如果传过来的节点不是头结点，并且没有后继结点，则中点就是它本身
        if(!node.isHead && node.next==null){
            return node;
        }
        //如果传过来的节点是头结点，并且有后继节点，则从第一结点开始
        if(node.isHead && node.next!=null){
            a = node.next;
            b = node.next;
        }else {
            //执行到这说明该结点既不是头结点，还有后继节点，则直接使用node
            a = node;
            b = node;
        }
        //a一次进1，b一次进2;这里有两种情况：
        // 情况1：当链表长度为奇数时，b.next为null时，a就是中点(即b走到终点，此时a刚好处于中点)。
        //情况2：当链表长度为偶数时，b.next.next为null时,a和a.next两个结点都是中点。
        while (b.next!=null && b.next.next!=null){ //注意细节：这里while判断条件，顺序不能变，否则会出现空指针异常
            a = a.next;
            b = b.next.next;
        }
        //这里b.next==null说明链表长度为奇数，a节点为中点
        /*if(b.next==null){
            return a;
        }else {
            //这里则链表为偶数,中点同样是a，因此统一返回a结点即可
            return a;
        }*/
        return a;
    }

    /**
     * 链表反转(不能传头结点，只能传的第一结点~~~尾结点，这里是反转以指定node开头的链表反转)
     * @param node
     * @return
     */
    public Node reverseStart(Node node){
        //如果传过来的节点是头结点，并且没有第一结点
        if(node.isHead){
            System.out.println("ERROR:不允许传入头节点");
            return null;
        }
        //开始反转
        //用来保存反转后的头结点的引用
        Node headNode = null;
        //保存前驱节点的引用
        Node preNode = null;
        //保存当前节点的引用
        Node currentNode = node;
        //保存后继结点的引用
        Node nextNode = null;
        while (currentNode!=null){
            nextNode = currentNode.next;
            //每次循环都进行判断如果后置节点为空，说明当前节点就是链表反转后的头结点
            if (nextNode == null) {
                headNode = currentNode;
            }
            //反转引用指向
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return headNode;
    }

    /**
     * 链表反转(不能传头结点，只能传的第一结点~~~尾结点，这里是反转以第一结点开头到后面指定结点node结尾的这段范围链表的反转)
     * @param node
     * @return
     */
    public Node reverseEnd(Node node){
        //如果传过来的节点是头结点，并且没有第一结点
        if(node.isHead){
            System.out.println("ERROR:不允许传入头节点");
            return null;
        }
        //开始反转
        Node headNode = node;//注意这里一定要将node赋给另一个变量，让另一个变量来操作，否则会影响
        //保存前驱节点的引用
        Node preNode = null;
        //保存当前节点的引用(此时当前节点为第一结点)
        Node currentNode = head.next;
        //保存后继结点的引用
        Node nextNode = null;
       while (currentNode!=headNode){
           nextNode = currentNode.next;
           currentNode.next = preNode; //开始反转引用
           preNode = currentNode;
           currentNode = nextNode;
       }
       //执行到这里说明currentNode==node属于头结点，但要注意，此时不能直接返回，因为现在currentNode的指向还没有反转
        //反转结点指向
        currentNode.next = preNode;
        return currentNode;
    }

    /**
     * 判断该链表是否是回文串
     * @param node 链表的第一结点
     * @return
     */
    public boolean isPalindromeString(Node node){
        if(node.isHead){
            System.out.println("ERROR:不允许传入头节点");
            return false;
        }
        Node a,b;
        //如果传过来的节点不是头结点，并且没有后继结点，则中点就是它本身,此时只有一个结点，当然是回文串
        if(!node.isHead && node.next==null){
            return true;
        }
        //如果传过来的节点是头结点，并且有后继节点，则从第一结点开始
        if(node.isHead && node.next!=null){
            a = node.next;
            b = node.next;
        }else {
            //执行到这说明该结点既不是头结点，还有后继节点，则直接使用node
            a = node;
            b = node;
        }
        //a一次进1，b一次进2;这里有两种情况：
        // 情况1：当链表长度为奇数时，b.next为null时，a就是中点(即b走到终点，此时a刚好处于中点)。
        //情况2：当链表长度为偶数时，b.next.next为null时,a和a.next两个结点都是中点。
        while (b.next!=null && b.next.next!=null){ //注意细节：这里while判断条件，顺序不能变，否则会出现空指针异常
            a = a.next;
            b = b.next.next;
        }
        Node rightList,leftList;
        //这里b.next==null说明链表长度为奇数，a节点为中点
        if(b.next==null){
            //获取右半部分链表(这里不能直接赋值，需要复制一份新的链表)
            //rightList = a; 错
            rightList = new Node(a.data,a.next);
            //获取左半部分的反转链表(左边的链表反转后再与右半边的链表比较，相同就是回文串)
            leftList = reverseEnd(a);//注意这里反转a后，如果前面a是直接赋给rightList，反转a的同时会影响到rightList，因为两个rightList、leftList实际指向的是同一个引用
        }else {
            //这里则链表为偶数,中点有两个，分别是a和a.next
            rightList = a.next;
            leftList = reverseEnd(a);
        }
        printAll(rightList);
        printAll(leftList);

        //开始对比左右两链表
        return compareList(rightList,leftList);
    }

    /**
     * 对比两链表是否相同
     * @param rightList
     * @param leftList
     * @return
     */
    public boolean compareList(Node rightList, Node leftList) {
        //当两者都没到尾结点时比较其数值
        while(leftList != null && rightList != null){
            if (leftList.data == rightList.data){ //将两列表一个个取出做比较相同，继续比较下一个，否则不是回文串
                rightList = rightList.next;
                leftList = leftList.next;
                continue;
            }else{
                //一旦数据不一致返回false
                return false;
            }
        }
        return true;
    }

    /**
     * 删除倒数第i个结点
     * 定义两个指针p，q。它们之间间隔n+1个节点，再使p，q同时移动，直到其中一个最先到达链表尾部为止
     * @return
     */
    public boolean deleteNodeBack(int i){
        if(head.next==null){
            System.out.println("ERROR：链表为空,不能操作");
            return false;
        }
        Node p,q;
        //第一结点赋给q
        p = head.next;
        q = head.next;
        int count = 0;
        //执行i次，让结点q领先p结点i次
        while (q!=null && count<i){
            q = q.next;
            count ++;
        }
        if(q==null && count<i){
            System.out.println("ERROR:删除的范围超过链表范围");
            return false;
        }

        //如果q为null说明是删除第一个结点
        if(q==null){
            head.next = head.next.next;
            return true;
        }
        while (q.next!=null){
            q = q.next;
            p = p.next;
        }
        //执行到这此时p就是倒数i结点的前一个结点
        p.next = p.next.next;
        return true;
    }


    //注意这里必须是public否则如果是private修饰的话，外部就无法返回该结点
    public class Node {
        //是否是头结点，true是,false不是

        private boolean isHead;
        private Integer data;
        private Node next;

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(boolean isHead, Integer data, Node next) {
            this.isHead = isHead;
            this.data = data;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    //对外部开放头结点
    public Node getHead() {
        return head;
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //重写toString方法
    @Override
    public String toString() {
        Node node = head;
        StringBuilder strb = new StringBuilder();
        strb.append("size:"+ head.data +" [");
        while (node.next!=null){
            node = node.next;
            strb.append(node.data+", ");
        }
        String s = strb.toString();
        if(head.data==0){
            return s + "]";
        }
        return s.substring(0,s.length()-2)+"]";
    }


}
