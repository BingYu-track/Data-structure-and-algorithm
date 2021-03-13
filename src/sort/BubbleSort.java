package sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 冒泡排序
 * @author: bingyu
 * @date: 2019/9/21 15:39
 */
public class BubbleSort {

    //元素交换
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //冒泡排序初级版
    public static void bubbleSort1(int[] arr){
        for(int i = 0;i<arr.length-1;i++){ //有n个数时，需要冒泡n-1次；(外层循环用来控制冒泡次数)
            for (int j = 0;j<arr.length-1;j++){ //每次冒泡比较n-1次(内层循环用来控制每次冒泡中比较的次数)
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1); //发现前面的数字大于后面的数字，就开始交换
                }
            }
        }
    }

    //冒泡排序第一次优化
    public static void bubbleSortOptimize1(int[] arr){
        for (int i = 0;i<arr.length-1;i++){
            //每轮冒泡完成后，都会有一个元素排列到正确的位置，除了第一轮冒泡需要比较n-1次外，
            //后面的冒泡只需要比较n-1-i次即可,i表示前面已完成的冒泡次数
            for(int j = 0;j< arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    //冒泡排序第二次优化
    public static void bubbleSortOptimize2(int[] arr){
        if (arr.length<=1) { //只有一个元素直接返回
            return;
        }
        for (int i = 0;i<arr.length-1;i++){
            boolean flag = true; //增加标记变量区分冒泡中是否交换了元素
            for(int j = 0;j< arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    flag = false;
                    swap(arr,j,j+1);
                }
            }
            if (flag){ //只要是true说明前面冒泡没有交换元素，说明元素都已经是有序，打断循环
                break;
            }
        }
    }




    /*
    总结：1.冒泡排序是一个原地排序，因为它无需算法辅助空间，空间复杂度为常量
         2.冒泡排序是一个稳定排序，冒泡排序中只有元素交换时才会改变顺序，相同大小的元素不会交换，因此是稳定排序
         3.冒泡排序的时间复杂度分析--,最佳时间复杂度，即已经是有序时，只需进行一次冒泡，n个元素需要执行n-1次交换，因此最佳时间复杂度是O(n);
            最差时间复杂度，即刚好是逆序，n个元素需要执行n-1次冒泡，比较次数随着冒泡的轮数依次递减形成等差数列；
            执行交换的元素次数为n-1(第一轮冒泡的比较次数)+n-2(第二轮冒泡的比较次数)+n-3(第三轮冒泡的比较次数)+....+1(最后一轮冒泡的比较次数)=n(n-1)/2;因此最差时间复杂度是0(n^2)

    */

    public static void test(int[] arr){
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("冒泡排序开始---------------------");
        long l1 = System.currentTimeMillis();
        bubbleSort1(ints);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("冒泡排序结束，花费时间："+ l + "毫秒");
    }

    public static void main(String[] args){
        //int[] arr = {9,1,5,8,3,7,4,6,2};
        int[] arr = {2,1,3,4,5,6};
        bubbleSortOptimize2(arr);
        System.out.println(Arrays.toString(arr));

    }
}
