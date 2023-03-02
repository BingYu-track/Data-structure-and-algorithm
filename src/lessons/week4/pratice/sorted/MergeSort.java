package lessons.week4.pratice.sorted;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 归并排序
 * @author: bingyu
 * @date: 2019/10/3 19:57
 */
public class MergeSort {

    public static void main(String[] args){
        int[] data = new int[]{5,4,3,2,1};
        //data = new int[]{5,4,3,2,1};;
        mergeSort(data,data.length);
        System.out.println(Arrays.toString(data));
    }


    /**
     * 思路: 先找出中间位置，将数组分成2个子数组排序，排序完子数组后，再将2个排序好的子数组合并后排序即可!
     * @param arr 数组
     * @param length 数组长度
     */
    public static void mergeSort(int[] arr,int length){
        merge_sort(arr,0,length-1);
    }

    /** 5,4,3,2,1   r=4 p=2 [0,2]~[3,4]  、 r=2 p=1 [0,1]~[2] 、r=1 p=0 [0]~[1]
     * 开始归并排序
     * @param arr
     * @param a 数组起始位置下标
     * @param r 数组结束位置下标
     */
    public static void merge_sort(int[] arr, int a, int r) {
        int p = (a + r)/2; //得到中间位置(为什么是(a+r)/2而不是(r-a)/2？表达式实际上是a+(r-a)/2,化简后就成了(a+r)/2)
        if (p>=r) return; //终止条件--这里终止条件如何理解？ 当p==r时说明此时，数组只有一个元素了，所以终止
        merge_sort(arr,a,p); //排序左半边的数组
        merge_sort(arr,p+1,r); //排序右半边的数组
        merge(arr,a,r,p);
    }

    /**
     * 使用双指针法，合并2个排序的子数组 5,4,3,2,1
     * @param arr
     * @param a 子数组1的开始位置下标
     * @param r 子数组2的结束位置下标
     * @param p 中间位置下标
     */
    public static void merge(int[] arr, int a, int r, int p) {
        int[] tmp = new int[r-a+1]; //(注意，这里可能为0)开辟长度为从a到r的临时数组用来存储排序后的元素
        int i = a; //指针i,用来表示左边的数组位置
        int j = p + 1; //指针j用来表示右边数组位置
        int k = 0;//临时数组的下标
        while (i<=p && j<=r) {
            if (arr[i] <= arr[j]) { //i指向的元素小于j指向的元素，则将小的元素放入临时数组中，i指针向后移动
                tmp[k] = arr[i];
                k++;
                i++;
            }else {
                tmp[k] = arr[j];
                k++;
                j++;
            }
        }
        while (i<=p) {
            //执行到这里，说明只剩下左边的数组未合并完成
            tmp[k] = arr[i];
            k++;
            i++;
        }
        while (j<=r) {
            //执行到这里，说明只剩下右边的数组未合并完成
            tmp[k] = arr[j];
            k++;
            j++;
        }
        //执行到这里说明全部合并完成，开始把临时数组里的值全部放到原数组中
        int n = 0;
        while (n<tmp.length) {
            arr[a+n] = tmp[n]; //注意这行代码，赋值时必须从a位置开始
            n++;
        }

    }
    /*
      由于涉及递归，画出递归树来分析其时间复杂度和空间复杂度,再分析递归的时间复杂度时，要看"递"和"归"的总耗时，首先看
      "递"，"递"也就是分解，分解的过程中，我们只有计算中间值int p = (a + r)/2这行代码，因此"递"是不耗时的；我们
      再看"归"，"归"就是执行了merge_sort函数，然后又调用了merge合并函数，这是非常耗时的，因此我们主要分析"归"的
      过程即可！merge函数我们知道，这个合并是需要扫描知道a~r长度的数组，因此merge的耗时是和数组长度正相关的

        递推树：                              merge(n)
                                            /        \
                                           /          \
                                          /            \
                                    merge(n/2)       merge(n/2)
                                    /   \             /       \
                              merge(n/4) merge(n/4) merge(n/4) merge(n/4)
                               .............
                             merge(1)

     时间复杂度分析: 从递归树我们知道每个每层执行的次数是n(因为1个merge函数执行次数是与数组长度成正比)，那么只要得到高度，就算出了时间复杂度，
     高度h=log2N，即以2为底，n的对数，那么时间复杂度就是n*log2n = O(nlogn)

     空间复杂度分析: TODO 注意空间复杂度是求递归过程中消耗空间的峰值，而非累加值！这里空间与递归树的高度和临时数组有关，临时数组最大空间峰值就是
             最后一步归的过程，开辟了n个空间的数组，另外在递归的过程中调用递归函数需要消耗空间，递归消耗的空间只和递归树的高度有关，递归
             的空间就是log2n，那么总的空间消耗就是n+log2n，我们取大的，空间复杂度也就成了O(N);

     是否是原地排序: 不是原地排序，因为我们开辟了额外数组，没有在原空间操作

     是否是稳定排序算法: 是稳定排序算法，主要看代码代码里if (arr[i] <= arr[j])这行如果去掉=就会变成非稳定算法！
    */

    public static void test(int[] arr){
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("归并排序开始---------------------");
        long l1 = System.currentTimeMillis();
        mergeSort(ints,ints.length);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("归并排序结束，花费时间："+ l + "毫秒");
    }

}
