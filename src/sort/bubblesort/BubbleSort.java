package sort.bubblesort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 冒泡排序
 * @author: bingyu
 * @date: 2019/9/21 15:39
 */
public class BubbleSort {

    /**
     * 交换
     * @param l 数组
     * @param i 带交换的下标的元素
     * @param j 同上
     */
    public static void swap(int[] l,int i,int j){
        int temp = l[i];
        l[i] = l[j];
        l[j] = temp;
    }

    //经典的冒泡排序方法
    public static void bubbleSort(int[] arr){
        int i,j;
        for (i=0;i<arr.length;i++){
            for (j=arr.length-2;j>=i;j--){ //j从后往前遍历 (这里的for循环是控制对比的次数，因为如果前面有冒泡一次，前面就已经有一个元素最上面的位置，)
                if(arr[j]>arr[j+1]){ //第一次始终是倒数第一个和最好一个对比
                    swap(arr,j,j+1);
                }
            }
        }
    }

    //冒泡算法排序优化(避免因已经有序的情况下无意义的循环)
    public static void bubbleSortBe(int[] arr){
        int i,j,count=0;
        boolean flag = true;
        for (i = 0;i<arr.length && flag;i++){
            flag = false; //如果下面没有数据交换，那么flag一直会为false,说明冒泡一次后没有数据交换，说明已经有序，无需再次冒泡，最外层循环将会停止
            for (j=arr.length-2;j>=i;j--){
                if(arr[j]>arr[j+1]){
                    flag = true;
                    swap(arr,j,j+1);
                }
            }
            count++;
        }
        System.out.println(count);
    }

    /*
    总结：1.冒泡排序是一个原地排序，因为它无需算法辅助空间，空间复杂度为常量
         2.冒泡排序是一个稳定排序，冒泡排序中只有元素交换时才会改变顺序，相同大小的元素不会交换，因此是稳定排序
         3.冒泡排序的时间复杂度分析--,最佳时间复杂度，即已经是有序时，只需进行一次冒泡，n个元素需要执行n-1次交换，因此最佳时间复杂度是O(n);
            最差时间复杂度，即刚好是逆序，n个元素需要执行n次冒泡，执行交换的元素次数为n-1+n-2+n-3+....+1=n(n+1)/2;因此最差时间复杂度是0(n^2)

    */
    public static void main(String[] args){
        int[] arr = {9,8,4,7,5,6,1};
        bubbleSortBe(arr);
        System.out.println(Arrays.toString(arr));
    }
}
