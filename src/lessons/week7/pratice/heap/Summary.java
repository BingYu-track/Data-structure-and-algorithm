package lessons.week7.pratice.heap;

/**
 * @version 1.0
 * @Description: 总结
 * @author: bingyu
 * @date: 2022/12/12
 */
public class Summary {


    /*
     一、堆的定义和存储
        1.堆必须是一个完全二叉树
        2.堆中的每个节点值必须大于等于(或者小于等于)其子树中每个节点的值

        如果堆中每个节点的值都大于等于子树中每个节点的值，我们把这种堆叫"大顶堆"
        如果堆中每个节点的值都小于等于子树中每个节点的值，我们把这种堆叫"小顶堆"

        堆是完全二叉树，所以，适合用数组来存储--具体存储方式是: 如果root下标为i，则左子节点存到2i,右子节点存到2i+1 (要从下标1开始存)


     二、堆上的操作
       1.往堆中插入数据
         将新数据插入到数组的末尾，然后执行自下而上的堆化操作。这里的堆化操作是指为了满足大顶堆或者小顶堆需要将新数据和其父节点作比较并交换到
         正确的位置，使其堆满足其特性!

       2.取堆顶元素

       3.删除堆顶元素
        把最后一个节点放到堆顶，然后利用自上而下的堆化方式让堆重新满足定义。

       4.更新元素值
     */


    /*大顶堆
                   28
                /      \
               25       27
              /  \     /  \
             18  16   13   9
            /  \
           8   12
     */
    public class Heap {
        private int a[]; //数组，从下标1开始存储数据
        private int n; //堆可以存储的最大数据个数
        private int count; //堆中已经存储的数据个数

        public Heap(int capacity) {
            a = new int[capacity + 1];
            n = capacity;
            count = 0;
        }

        //取堆顶元素
        public int top() {
            if (count == 0) return Integer.MIN_VALUE; //如果堆尾空，返回负数
            return a[1];
        }

        /*TODO： 向堆里插入数据，具体操作是将新的数据放入数组数据的尾部，在上述的堆数据结构看来就是放在16的左边，那么已知新的数据下标i，
           就可以得到16的下标i/2,再比较大小，如果大于父节点，就与父节点交换位置，否则当前位置就是正确的位置!
         */
        public void insert(int data) {
            if (count >= n) return; //堆满了
            count++;
            a[count] = data;
            int i = count;
            while (i/2 > 0 && a[i] > a[i/2]) { //自下往上堆化(这里我们的堆都是大顶堆)
                swap(a, i, i/2); //swap()函数作为:交换下标为1和i/2的两个元素
                i = i/2;
            }
        }

        //删除堆顶元素
        public void removeTop() {
            if (count == 0) return;
            a[1] = a[count]; //TODO:
            count--;
            heapify(a,count,1);
        }

        /**
         *
         * @param a
         * @param n 堆的大小
         * @param i
         */
        private void heapify(int[] a, int n, int i) { //自上而下堆化
            while (true) {
                int maxPos = i;
                if (i*2 <= n && a[i] < a[i*2]) maxPos = i*2;
                if (i*2+1 <= n && a[maxPos] < a[i*2+1]) maxPos = i*2+1;
                if (maxPos == i) break;
                swap(a,i,maxPos);
                i = maxPos;
            }
        }

        private void swap(int a[],int i,int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

    }



}
