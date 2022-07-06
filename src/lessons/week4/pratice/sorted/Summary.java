package lessons.week4.pratice.sorted;

import java.util.Arrays;

/**
 * @version 1.0 排序总结
 * @Description:
 * @author: bingyu
 * @date: 2022/5/25
 */
public class Summary {

    /*
      TODO 排序算法的评价指标：
      1.原地or非原地
        原地--在原数据存储空间上完成排序操作
        非原地--需要额外的非常量级的数据存储空间才能完成排序

       TODO 空间复杂度跟原地性的关系:
       1.原地排序算法的空间复杂度并不一定是O(1);
       2.空间复杂度为O(1)的排序算法肯定是原地排序算法
        比如快排是原地排序，但是它的空间复杂度需要考虑递归调用函数栈的消耗！原地是不考虑递归的！

      TODO 排序算法的稳定性
           两个相同的元素，在排序前后，相对位置是否发生了改变，如果没改变就是"稳定排序"，否则就是"不稳定"排序


    TODO--重要 排序场景考题：
        1.特殊排序
        2.TOP K(经典问题，必须熟练)，一般是使用快排
        3.链表上的排序
        4.排序预处理 (注意这种题目是可以直接调用Arrays.sort()函数排序的，不用自己写的)
    */

    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        //insertionSort(arr);
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /*
     快速排序算法原理: 和归并排序相反，"递"的时候很复杂，"归"的时候啥都不做

     public static void quickSort(int[] a,int n) {
        quick_sort(a,0,n-1);
     }

     private static void quick_sort(int[] a, int p, int r) {
        if (p >= r) return;
        int q = partition(a,p,r); //获取分区点下标
        quick_sort(a,p,q-1);
        quick_sort(a,q+1,r);
    }

    */






    /*
      归并排序算法原理:
      归并排序利用了分治的思想，采用递归来实现。

      如果要排序一个数组，先把数组从中间分成前后两部分，然后，对前后两部分分别排序，再将排好序的两部分合并在一起，这样整个数组就都有序了。

      递推公式: merge_sort(p,r) = merge(merge_sort(p,q),merge_sort(q+1,r))
               q=(p+r)/2
      终止条件: p>=r不用再继续分解
      merge_sort就是排序子列表，排序完后就是merge合并，将2个有序的子列表合并成大的有序的列表
      merge的实现可以用双指针方法实现
     */
    /**
     * 思路: 先找出中间位置，将数组分成2个子数组排序，排序完后再将2个排序好的子数组合并后排序即可!
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
        int p = (a + r)/2; //得到中间位置
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
     选择排序算法:分有序区间和无序区间，先在无序区间中寻找最小的元素放入有序区间末尾;然后再找后面的无序区间找最小值，放入有序区间末尾，不断重复
    */
    public static void selectSort(int[] arr) {
        for (int i = 0;i<arr.length;i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0; //用来记录未排序区间最小值所在的位置
            for (int j = i;j<arr.length;j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (arr[i] != arr[minIndex]) { //如果当前有序区间末尾元素不是未排序区间最小值，才排序，这样可以避免已经全部排序完成后重复交换
                //此时找到最小值，将最小值和有序区间末尾交换，第一次就是第一个元素
                int temp = arr[i];
                arr[i] = min;
                arr[minIndex] = temp; //这里之所以要j-1是因为之前循环j++会导致这行代码越界
            }
        }
    }


    //插入排序,我的思路实现
    public static void insertSort(int[] arr) {
        if (arr.length == 1) return;
        for (int i = 1;i<arr.length;i++) {
            int j = i;
            //TODO；注意这里的j>0是核心边界条件
            for (;j>0 && arr[j]<arr[j-1];j--) { //向前不断遍历比较,小于前面的数就交换位置
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
            //执行到这里说明比较完成了
        }
    }

    //争哥的插入排序方法 5,4,3,2,1
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        if (n == 1) return;
        for (int i = 1;i<arr.length;i++) {
            int value = arr[i]; //未排序区间中需要寻找位置的元素
            int j = i - 1; //已排序区间末尾的位置
            for (;j>=0 && arr[j]>value;j--) { //注意这里第一次进来j就等于0；只会进行一次排序
                arr[j+1] = arr[j]; //后移后，还要将value插入到正确的位置
            }
            arr[j+1] = value; //这里需要进行一次j+1,因为前面循环完成后本来j的位置就是正确位置，但是因为循环j会--,因此需要补上1，否则会越界
        }
    }
}
