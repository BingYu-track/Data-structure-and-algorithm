package lessons.week7.pratice.heap;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/12/19
 */
public class BuildHeap {

    // Driver Code
    public static void main(String args[]) {
        // Binary Tree Representation
        // of input array
        //            1
        //         /      \
        //       3        5
        //     /   \     / \
        //  4       6   13 10
        // / \    /  \
        // 9  8  15   17
        //int arr[] = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        int arr[] = {1,8,5,4,10,2,6,1,1,1,9};
        int N = arr.length;
        heapSort(arr);
        //buildHeap(arr, N);
        //printHeap(arr, N);
        System.out.println(Arrays.toString(arr));
    }

    /*
     堆排序
    */
    private static void heapSort(int[] arr) {
        //1.初始化建堆
        int count = arr.length;
        buildHeap(arr,count); //这里建堆是从最后一个非叶子节点开始，自上而下进行堆化
        //2.开始进行排序
        while (count>0) {
            int swap = arr[0]; //2.1根节点和堆中的最后一个元素进行交换，然后开始进行堆化
            arr[0] = arr[count-1];
            arr[count-1] = swap;
            count--;
            heapify(arr,count,0); //从根节点开始进行堆化
        }


    }

    /*
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：40.8 MB, 在所有 Java 提交中击败了84.90%的用户
     *
     * @param arr
     * @param N 数组的长度
     */
    private static void buildHeap(int arr[], int N) {
        // Index of last non-leaf node
        //获取最后一个叶子节点的下标
        int startIdx = (N / 2) - 1;

        for (int i = startIdx; i >= 0; i--) { //从最后一个非叶子节点开始，从后往前进行堆化
            heapify(arr, N, i);
        }
    }

    /**
     * 从最后一个非叶子节点开始，从指定i下标开始，自上而下进行堆化
     * @param arr
     * @param N 堆的元素个数
     * @param i 当前节点的下标，从最后一个非叶子节点开始
     */
    private static void heapify(int arr[], int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; //左子节点的下标
        int r = 2 * i + 2; //右子节点的下标

        //如果左子节点大于根节点，则变更下标
        if (l < N && arr[l] > arr[largest]) {
            largest = l;
        }

        //如果右子节点大于上一个largest，继续变更下标
        if (r < N && arr[r] > arr[largest]) {
            largest = r;
        }

        //如果此时下标的位置和开始下标的位置不一样，说明需要进行交换
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            //递归调用
            heapify(arr, N, largest); //TODO:这里是重点，位置发生交换后，还要再不断比较新的子节点，看是否仍满足大于子节点，否则也需要进行交换
        }
    }

    // A utility function to print the array
    // representation of Heap
    private static void printHeap(int arr[], int N) {
        System.out.println("Array representation of Heap is:");
        for (int i = 0; i < N; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


}
