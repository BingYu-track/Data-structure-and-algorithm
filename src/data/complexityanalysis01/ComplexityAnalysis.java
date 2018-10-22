package data.complexityanalysis01;

import org.junit.Test;

/**
 * @version 1.0
 * @Description: 复杂度分析
 * @author: hxw
 * @date: 2018/10/16 21:56
 */
public class ComplexityAnalysis {


    /*// array 表示一个长度为 n 的数组
    // 代码中的 array.length 就等于 n
    int[] array = new int[100];
    int count = 0;

    void insert(int val) {
        //如果count等于数组长度，求数组里所有数的和并把总和赋给数组的第一个元素,再把count赋为1
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }
        //如果
        array[count] = val;
        ++count;
    }*/




    // 全局变量，大小为 10 的数组 array，长度 len，下标 i。
    int array[] = new int[10];
    int len = 10;
    int i = 0;

    // 往数组中添加一个元素
    void add(int element) {
        if (i >= len) { // 数组空间不够了
            // 重新申请一个 2 倍大小的数组空间
            int new_array[] = new int[len*2];
            // 把原来 array 数组中的数据依次 copy 到 new_array
            for (int j = 0; j < len; ++j) {
                new_array[j] = array[j];
            }
            // new_array 复制给 array，array 现在大小就是 2 倍 len 了
            array = new_array;
            len = 2 * len;
        }
        // 将 element 放到下标为 i 的位置，下标 i 加一
        array[i] = element;
        ++i;
    }


    public static void main(String[] args){
        int a[][]={{1,2,3},{4,5,6}};

    }
}
