package sort;

import java.util.Random;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2019/10/3 18:47
 */
public class GenerateArray {

    public static int[] generateArray(int n) {
        int[] a=new int[n];
        Random r=new Random();
        for(int i=0;i<n;i++) {
            a[i]=r.nextInt(n);
        }
        return a;
    }
    /**
     * 默认产生10000个随机数
     * @return
     */
    public static int[] generateArray() {
        int[] a=new int[10000];
        Random r=new Random();
        for(int i=0;i<10000;i++) {
            a[i]=r.nextInt(10000);
        }
        return a;
    }

}
