package sort.shellsort;

import util.LogarithmUtils;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 希尔排序
 * @author: bingyu
 * @date: 2019/10/2 21:13
 */
public class ShellSort {

    /**
     * @param arr 待排序的数组 (该方法使用的Hibbard增量序列)
     */
    public static void shellSort(int[] arr){
        double log = LogarithmUtils.log(arr.length + 1, 2); //根据数组的长度得到k的值   2^k-1=length转换成求对数 k = log2(length+1)
        int k = (int)Math.round(log); //获取k(使用Hibbard增量序列2^k-1中的k值,这是重点)
        //获取到k值后后面就简单了，只需要进行k次增量插入排序即可
        for (int i=k;i>=1;--i){
            int increment = (int)Math.pow(2,i)-1; //计算2^k-1 得到当前的增量
            shellInsert(arr,increment);
        }
    }

    /**
     *
     * @param arr 数组
     * @param increment 增量
     */
    public static void shellInsert(int[] arr,int increment){
        int i,j,temp;
        for (i=increment;i<arr.length;++i){
            temp = arr[i]; //注意是将要排序的数字保存到临时变量
            j = i-increment;
            for (;j>=0 && temp<arr[j];j=j-increment){ //如果后面的元素小于前面元素，向后移动，还要注意的是j>=0这个判断条件是为了防止越界，因为temp如果交换的话每次都会前移
                arr[j+increment] = arr[j];
            }
            arr[j+increment] = temp;
        }
    }

    //希尔排序(希尔增量)
    public static void shellSort2(int[] array) {
        int number = array.length / 2; //使用的希尔增量
        int i;
        int j;
        int temp;
        while (number >= 1) {
            for (i = number; i < array.length; i++) {
                temp = array[i];
                j = i - number;
                while (j >= 0 && array[j] > temp) { //需要注意的是，這裡array[j] < temp將會使數组從大到小排序。
                    array[j + number] = array[j];
                    j = j - number;
                }
                array[j + number] = temp;
            }
            number = number / 2;
        }
    }


    public static void main(String[] args){
       int[] arr = {6,5,4,3,2,1};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
