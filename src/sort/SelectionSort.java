package sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 选择排序
 * @author: bingyu
 * @date: 2019/9/25 21:49
 */
public class SelectionSort {

    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //原理：将一排数字分为有序区间和无序区间，每次都从无序区间选出最小值放到有序区间的末尾
    public static void selectionSort(int[] arr) {
        int i,j,min;
        for (i=0;i<arr.length-1;++i){ //如果有n个无序的数字，则选出最小值只需比较n-1次
            min = i; //假设当前下标的值为最小值(i就是待交换的)
            for (j = i+1;j<arr.length;++j){ //内层循环是用来查找最小值
                if (arr[min]>arr[j]){
                    min = j; //给最小值重新赋值
                }
            }
            //内层循环执行完，说明找到了未排序序列的最小值
            if(i!=min){ //说明最小值不是自己，开始交换位置
                swap(arr,i,min);
            }
        }
    }


    public static void selectionSort1(int arr[]){
        int i,j,min;
        for(i = 0;i<arr.length;i++){
            min = i; //这里i是后面最小值待交换的小标
            for (j = i+1;j< arr.length;j++){
                if (arr[min]>arr[j]){
                    min = j;
                }
            }

            if (i!=min){
                swap(arr,i,min);
            }
        }
    }


    //
    public static void test(int[] arr) {
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("选择排序开始---------------------");
        long l1 = System.currentTimeMillis();
        selectionSort(ints);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("选择排序结束，花费时间："+ l + "毫秒");
    }

    public static void main(String[] args) {
        int[] arr = {86,74,65,52,47,39};
        selectionSort1(arr);
        System.out.println(Arrays.toString(arr));
    }
}
