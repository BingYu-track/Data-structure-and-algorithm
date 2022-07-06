package lessons.week4.pratice.sorted.pratice.pratice1;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 合并排序的数组
 * @author: bingyu
 * @date: 2022/6/16
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] A = {6,8,10,0,0,0};
        int[] B = {2,5,6};
        merge(A,3,B,3);
        System.out.println(Arrays.toString(A));
    }

    /*
     合并排序数组----争哥解法
     TODO: 推荐
     思路: 从2个数组的末尾指针开始比较，把大的放在A数组后面即可，这样可以避免覆盖未排序的数据，相关数组拷贝的问题基本都是这种套路
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：41.2 MB, 在所有 Java 提交中击败了67.58%的用户
    */
    public static void merge(int[] A, int m, int[] B, int n) {
        int k = A.length - 1;
        int i = m - 1; //A数组指针
        int j = n - 1; //B数组指针
        while (i>=0 && j>=0) {
            if (A[i] >= B[j]) {
                A[k] = A[i];
                i--;
                k--;
            }else {
                A[k]= B[j];
                j--;
                k--;
            }
        }
//        while (i>=0) { //说明B数组里的元素已经全部填充到A数组里了，由于A数组之前就是已经排好序的，因此这种情况不需要考虑
//
//        }
        while (j>=0) { //说明B数组元素比A数组的多，继续搬移到A数组
            A[k] = B[j];
            j--;
            k--;
        }
    }


}
