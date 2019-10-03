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
     * 希尔排序增量插入
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

    /**
     * @param arr 待排序的数组 (该方法使用的Hibbard增量序列)
     */
    public static void shellSort(int[] arr){
        double log = LogarithmUtils.log(arr.length + 1, 2); //根据数组的长度得到接近k的值   2^k-1=length转换成求对数 k = log2(length+1),这一步是重点
        int k = (int)Math.round(log); //四舍五入获取k(使用Hibbard增量序列2^k-1中的k值)
        //获取到k值后面就简单了，只需要进行k次增量插入排序即可
        for (int i=k;i>=1;--i){
            int increment = (int)Math.pow(2,i)-1; //计算2^k-1 得到当前的增量
            shellInsert(arr,increment);
        }
    }

    //希尔排序(希尔增量)
    public static void shellSort2(int[] arr) {
        int increment = arr.length / 2;
        //获取到k值后后面就简单了，只需要进行k次增量插入排序即可
        for (int i=increment;i>=1;i=i/2){
            shellInsert(arr,i);
        }
    }

    /*
    总结：从Test类的测试上看，希尔排序效率要远远高于另外三个排序算法
    希尔排序仍然是基于插入排序，其关键在于将相隔某个增量的一组数据进行排序，实现跳跃式移动，大大提高排序效率
    ，希尔排序的效率主要取决于增量序列的选取，使用希尔增量的时间复杂度为仍为0(n^2)，而使用Hibbard增量的时间复杂度为O(n^3/2)
    */

    public static void test(int[] arr){
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("希尔排序开始---------------------");
        long l1 = System.currentTimeMillis();
        shellSort(ints);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("希尔排序结束，花费时间："+ l + "毫秒");
        System.out.println(Arrays.toString(ints));
    }


    public static void main(String[] args){
       int[] arr = {6,5,4,3,2,1};
        shellSort(arr);  //Hibbard增量
        //shellSort2(arr); //希尔增量
        System.out.println(Arrays.toString(arr));
    }
}
