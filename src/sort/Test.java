package sort;

import sort.bubblesort.BubbleSort;
import sort.insertionsort.InsertionSort;
import sort.selectionsort.SelectionSort;
import sort.shellsort.ShellSort;

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
        /*
        结果：
        冒泡排序开始---------------------
        冒泡排序结束，花费时间：14560毫秒
        选择排序开始---------------------
        选择排序结束，花费时间：3949毫秒
        插入排序开始---------------------
        插入排序结束，花费时间：764毫秒
        希尔排序开始---------------------
        希尔排序结束，花费时间：15毫秒
        */
    }
}
