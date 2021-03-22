package sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 堆排序
 * @author: bingyu
 * @date: 2021/3/21
 */
public class HeapSort {

    /**
     * 小顶堆排序，排序结果是逆序的
     * @param arr
     */
    public static void smallHeapSort(int[] arr) {
        //构造小顶堆
        buildSmallHeap(arr);
        //排序，将最小的节点放在堆尾，然后从根节点重新调整
        for (int i = arr.length - 1; i >= 1; i--) { //当i=0时，就只剩最后一个元素了，无须再调整，所以i>=1即可
            swap(arr,0,i); //交换元素，将小顶堆里的根元素与堆尾元素交换
            smallHeapAdjust(arr, 0, i); //由于之前已经输出了一个元素，剩下总的元素个数就是i = arr.length - 1
        }
    }

    //元素位置交换
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 将无序的元素构建成小顶堆
     * @param arr
     */
    public static void buildSmallHeap(int[] arr){
        int i,n = arr.length;
        //i=n/2表示是从最后一个非叶子节点开始调整(因为最后一个叶子节点的位置就是n)，但是这里需要减1，因为是从0开始的
        //每调整完一个元素后就需要移动到前面一个元素为基准进行调整，直到根元素被调整完
        for (i = n/2-1; i >= 0; i--) {
            smallHeapAdjust(arr, i, n);
        }

    }

    /**
     * 小顶堆调整方法  找出当前元素的左右子节点的最小值并进行交换，直到最后自己是最小值为止
     * @param arr 判断
     * @param index 最后一个非叶子节点的索引
     * @param len 元素个数
     */
    private static void smallHeapAdjust(int[] arr, int index,int len) {
        while (true) {
            //左子节点的索引
            int leftNode = 2 * index + 1;
            //右子节点的索引
            int rightNode = 2 * index + 2;
            //设置最小值
            int min = index;

            //左子节点比较，如果左子节点值更小，就将左子节点作为最小值
            if(leftNode<len && arr[leftNode]<arr[min]){ //这里leftNode<len是为了判断index下是否有左子节点
                min = leftNode;
            }
            //和右子节点比较，如果右子节点值更小，就将右子节点作为最小值
            if(rightNode<len && arr[rightNode]<arr[min]){ //这里rightNode<len是为了判断index下是否有右子节点
                min = rightNode;
            }
            //如果index不是最小值的话，在前面程序执行的过程中，min的值就会改变，表示需要交换数据；否则就无须交换，说明index就是最小值
            if (min == index){
                break;
            }
            //执行到这说明找到最小值的位置了并且不是index，开始替换
            swap(arr,min,index);
            index = min; //交换位置后，将当前元素的索引更新，然后下次循环在这个新的位置进行调整
        }

    }


    /**
     * 大顶堆排序 ，排序结果是正序的
     * @param arr
     */
    public static void bigHeapSort(int[] arr) {
        //构造大顶堆
        buildBigHeap(arr);
        //排序，将最小的节点放在堆尾，然后从根节点重新调整
        for (int i = arr.length - 1; i >= 1; i--) {  //当i=0时，就只剩最后一个元素了，无须再调整，所以i>=1即可
            swap(arr,0,i); //交换元素，将大顶堆里的根元素与堆尾元素交换
            bigHeapAdjust(arr, 0, i); //由于之前已经输出了一个元素，剩下总的元素个数就是i = arr.length - 1
        }
    }


    /**
     * 将无序的元素创建成大顶堆
     * @param arr
     */
    public static void buildBigHeap(int[] arr){
        int i,n = arr.length;
        //i=n/2表示是从最后一个非叶子节点开始调整(因为最后一个叶子节点的位置就是n)，
        //每调整完一个元素后就需要移动到前面一个元素为基准进行调整，直到根元素被调整完
        for (i = n/2-1; i >= 0; i--) {
            bigHeapAdjust(arr, i, n);
        }
    }

    /**
     * 大顶堆调整
     * @param arr
     * @param index 最后一个叶子节点的父节点的索引(也是最后一个非叶子节点)
     * @param len 元素总个数
     */
    public static void bigHeapAdjust(int[] arr,int index,int len){
        while (true) {
            int max = index; //假设当前位置是最大值
            int leftChild = 2 * index + 1;  // 左孩子索引。
            int rightChild = 2 * index + 2; // 右孩子索引。

            // 若左孩子大于最大值，则更新最大值。
            if (leftChild < len && arr[leftChild] > arr[max]) {
                max = leftChild;
            }

            // 若右孩子大于最大值，则更新最大值。
            if (rightChild < len && arr[rightChild] > arr[max]) {
                max = rightChild;
            }

            if (max == index) { //说明已经是最大值
                break;
            }
            swap(arr, index, max);   //执行到这说明找到最大值的位置了并且不是index，开始替换
            index = max; //交换位置后，将当前元素的索引更新，然后下次循环在这个新的位置进行调整

        }
    }

    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49};
        //smallHeapSort(arr);
        bigHeapSort(arr);
        System.out.println(Arrays.toString(arr)); //[13, 38, 27, 49, 76, 65, 49, 97]
    }

    public static void test(int[] arr){
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("堆排序开始---------------------");
        long l1 = System.currentTimeMillis();
        bigHeapSort(ints);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("堆排序结束，花费时间："+ l + "毫秒");
        //System.out.println(Arrays.toString(ints));
    }
}
