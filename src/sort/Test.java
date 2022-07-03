package sort;

import util.LogarithmUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2019/10/3 18:48
 */
public class Test {

    public static void main(String[] args){
        int[] testarray = GenerateArray.generateArray(100000);
        BubbleSort.test(testarray); //冒泡排序
        SelectionSort.test(testarray); //选择排序
        InsertionSort.test(testarray); //插入排序
        ShellSort.test(testarray); //希尔排序
        HeapSort.test(testarray); //堆排序
        MergeSort.test(testarray); //归并排序
        QuickSort.test(testarray); //快速排序

    }
}
 /*
        结果：
        冒泡排序开始---------------------
        冒泡排序结束，花费时间：13759毫秒
        选择排序开始---------------------
        选择排序结束，花费时间：3137毫秒
        插入排序开始---------------------
        插入排序结束，花费时间：821毫秒
        希尔排序开始---------------------
        希尔排序结束，花费时间：14毫秒
        堆排序开始---------------------
        堆排序结束，花费时间：13毫秒
        归并排序开始---------------------
        归并排序结束，花费时间：15毫秒
        快速排序开始---------------------
        快速排序结束，花费时间：11毫秒
        */