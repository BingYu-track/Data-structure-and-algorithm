package lessons.week4.pratice.sorted.pratice.pratice1;

import java.util.Arrays;

/**
 * @version 1.0 合并排序的数组
 * @Description: 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *               初始化A 和 B 的元素数量分别为m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出:[1,2,2,3,5,6]
 *
 * 说明: A.length == n + m
 *
 * @author: bingyu
 * @date: 2022/6/2
 */
public class Merge {

    public static void main(String[] args) {
        int[] A = {1,2,3,0,0,0};
        int[] B = {2,5,6};
        merge(A,3,B,3);
        System.out.println(Arrays.toString(A));
    }

    /*
     我自己独自的思路:
     TODO: 推荐
     将B的最小值和A的最大值作比较，如果大于A的最大值，则B直接顺序放在A后面元素即可
     ，如果小于A的最大值就必须继续向前比较此时用B的最大值与A的最大值进行比较，谁最大，谁就放在A数组的末尾，然后依次比较后从后往前放入

     1,3,5,0,0,0
     2,4,6
     TODO 注意: m = 0不一定表示是没有空间，而是指没有元素，例如特殊情况 输入A=[0],m=0,B=[1],n=1
          执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
          内存消耗：41.2 MB, 在所有 Java 提交中击败了76.01%的用户
          时间复杂度O(N)，空间复杂度O(1)
    */
    public static void merge(int[] A, int m, int[] B, int n) { //将B数组合并到A数组并排序
        if (A.length < (m + n) || B.length ==0) return; //A数组空间不足或者B数组没有元素，直接返回
         //A数组尾部的下标
        int maxA = 0; //用来A数组中的最大值
        if (m == 0) { //如果A数组没有元素，就假设A数组的最大值为int的最小值
            maxA = Integer.MIN_VALUE;
        }else { //有元素，取A数组中末尾的值(也就是当前A数组的最大值)
            maxA = A[m - 1];
        }
        int i = m - 1; //A数组末尾指针
        int minB = B[0]; //B数组中的最小值
        if (maxA <= minB) { //如果A数组中的最大值小于B数组的最小值，则直接将B中的元素按顺序放置进A即可
            for (int j = 0;j<n;j++) {
                A[++i] = B[j];
            }
        }else {  //TODO: 这里是核心思路--执行到这里说明minB < maxA ，开始不断取A数组和B数组中的最大值，并按顺序放入A数组中
            int count = m + n - 1; //A数组的末尾下标
            int max = 0;
            int k = n - 1; //从B数组的末尾索引开始
            while (count >= 0 && k>=0 && i>=0) { //从末尾开始遍历，比较B
                if (B[k] > A[i]) { //如果B的值大于A的值,就把B的值赋到后面的空间里
                    max = B[k];
                    A[count] = max; //B数组只需要进行赋值 ,注意这里必须加1，因为i肯定是A数组长度减去1的，k同样也是B数组长度减去1的，想要找到A数组的正确位置就必须要加1
                    k--;
                }else { //执行到这里，说明A的元素要大于B的元素
                    max = A[i];
                    A[i] = A[count]; //A数组间元素需要进行交换
                    A[count] = max;
                    i--;
                }
                count--;
            }
            while (k>=0) { //说明A数组已经全部处于正确位置了;B数组还没完全合并到A数组
                A[count] = B[k];
                k--;
                count--;
            }
        }


    }



}
