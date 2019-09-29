package sort.selectionsort;

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
    public static void selectionSort(int[] arr){
        int i,j,min;
        for (i=0;i<arr.length-1;++i){ //如果有n个无序的数字，则选出最小值只需比较n-1次
            min = i; //假设当前下标的值为最小值
            for (j = i+1;j<arr.length;++j){
                if(arr[min]>arr[j]){
                    min = j; //给最小值重新赋值
                }
            }
            if(i!=min){
                swap(arr,i,min); //将
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {9,1,5,8,3,7,4,6,2};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
