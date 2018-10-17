package data.complexityanalysis01;

import org.junit.Test;

/**
 * @version 1.0
 * @Description: 复杂度分析
 * @author: hxw
 * @date: 2018/10/16 21:56
 */
public class ComplexityAnalysis {


    // array 表示一个长度为 n 的数组
    // 代码中的 array.length 就等于 n
    int[] array = new int[100];
    int count = 0;

    void insert(int val) {
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }

        array[count] = val;
        ++count;
    }


}
