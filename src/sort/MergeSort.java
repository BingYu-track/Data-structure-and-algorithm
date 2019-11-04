package sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 归并排序
 * @author: bingyu
 * @date: 2019/10/3 19:57
 */
public class MergeSort {

    /**
     * @param arr 数组
     * @param length 数组长度
     */
   /* public static void mergeSort(int[] arr,int length){
        mergeSortElement(arr, 0, length-1);
    }

    *//**
     *
     * @param arr 数组
     * @param begin 开始下标
     * @param last 结束下标
     *//*
    public static void mergeSortElement(int[] arr,int begin,int last){
        if(begin >= last){ //终止条件
            return;
        }
        int q = (begin + last)/2; //获取中间位置
        mergeSortElement(arr,begin,q); //对前半部分归并排序
        mergeSortElement(arr,q+1,last);  //对后半部分进行归并排序
        merge(arr,q,last); //合并元素排序，这才是真正的排序操作
    }

    //合并

    *//**
     * @param arr 整个数组
     * @param q 前半截数组的最后一个元素下标
     * @param last 后半截数组的最后一个元素下标
     *//*
    public static void merge(int[] arr,int q,int last){
        int[] temp = new int [arr.length]; //分配一个和arr空间一样的数组
        int i = 0; //前半初始位置
        int j = q+1; //后半初始位置
        int k = 0; //temp数组的初始下标
        while (i<=q && j<=last){ //前半初始位置不能大于中间位置 && 后半初始位置不能大于最后的位置
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }

        //判断哪个子数组有剩余元素
        int start = i,end = q;
        if(j <= last){
            start = j;
            end = last;
        }
        //将剩余的数据拷贝到临时数组temp
        while (start <= end){
            temp[k++] = arr[start++];
        }

        //将tmp中的数组拷贝回数组arr
        for (i =0;i<arr.length;i++){
            arr[i] = temp[i];
        }
    }*/

    public void mergeSort(int arr[],int arr1[],int start,int end){
        int m;
        int[] arr2 = new int[arr.length];
    }

    public static void main(String[] args){
        int[] data = new int[]{4,3,2,1};
        System.out.println(3/2);
    }
}
