package sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 插入排序
 * @author: bingyu
 * @date: 2019/9/22 17:51
 */
public class InsertionSort {

    //直接插入排序
    public static void directInsertSort(int[] arr){
        int n = arr.length; //获取数组长度
        if (n <= 1) { //如果数组长度为1，无需排序，直接返回
            return;
        }
        int j,temp;
        for (int i = 1;i< arr.length;i++){ //外层循环用来控制插入排序的次数，共需要排序n-1次
            if (arr[i-1]>arr[i]){ //如果前面的元素比当前元素大
                temp = arr[i]; //将当前元素放入临时变量
                for (j = i - 1;j>=0 && arr[j]>temp;j--){ //内层循环用来控制有序序列元素移动的次数(将有序序列的元素依次与当前元素比较)，这里j>=0为了防止数组越界，
                    arr[j+1] = arr[j]; //将有序序列元素往后移动,直到arr[j]<temp
                }
                //内层循环执行完说明现在的arr[j]<temp，则现在的j+1就是temp当前元素所要插入的位置
                arr[j+1] = temp;
            }
        }
    }
    //比较次数(1+1)+(1+2)+。。。。+（1+n-1)=(n+2)/2 *(n-1)/2
    //移动次数

    // 插入排序方法2
    public static void insertionSort(int[] a) {
        int n = a.length; //获取数组长度
        if (n <= 1) { //如果数组长度为1，无需排序，直接返回
            return;
        }
        for (int i = 1; i < n; ++i) {
            int value = a[i]; //获取数组中的当前元素并保存到中间变量value(注意这个元素我们看作是未排序区间的第一个元素)
            int j = i - 1; //为了后面获取当前元素的前一个元素(这里假设第一个元素是初始已经排好序的区间，因此后面的)
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) { //将当前元素value和其前面的元素a[j]进行比较，如果前面的元素大于该元素，则前面的元素进行后移，直到找到正确的位置
                    a[j+1] = a[j];  // 前面的元素a[j]向后移动
                } else {
                    break; //执行到这里说明，两元素已经是有序的，无需移动。这里的break是为了防止后面循环结束前的j自减
                }
            } //内层循环完后，j会自减1
            a[j+1] = value; //因为j在内层循环减了1，这里j+1是为了保持原来的位置;如果上面的循环被break打断，则相当于自身赋值自身
        }//当前循环，中每执行一次，则有序区间增加1
    }


    //自己实现插入排序
    public static void insertSortBySelf(int[] arr){
        int i,j,temp;
        for (i=1;i<arr.length;i++){
            temp = arr[i]; //注意是将要排序的数字保存到临时变量
            j = i - 1;
            for (;j>=0 && temp<arr[j];--j){ //如果后面的元素小于前面元素，向后移动，还要注意的是j>=0这个判断条件是为了防止越界，因为temp如果交换的话每次都会前移
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }









    //插入排序的原理很简单，比如{a,b,c,d},就是从第二个元素开始与前面的一个元素进行比较，小于就将前面的元素a后移，变成{a,a,c,d},之前的b元素赋值到临时变量。再用临时
    //变量比较前面的元素，小于的话重复之前的操作。
    /*   1.插入排序是一个原地排序，因为它无需算法辅助空间，空间复杂度为常量
         2.插入排序是一个稳定排序，插入排序中只有元素交换时才会改变顺序，如果与前面的元素相同就插入后面，因此是稳定排序
         3.插入排序的时间复杂度分析--,最佳时间复杂度，即已经是有序时，n个元素需要执行n-1次比较即可，因此最佳时间复杂度是O(n);
           最差时间复杂度，即刚好是逆序，n个元素执行n-1+n-2+n-3+....+1=n(n+1)/2的比较;因此最差时间复杂度是0(n^2)
    */

    //冒泡排序和插入排序的比较，冒泡排序和插入排序都是"原地排序"、稳定排序，时间复杂度都是一样的，那么哪一个排序更好呢?
    //测试后发现插入排序明显要比冒泡排序快很多，那是因为冒泡排序有3个赋值操作，而插入排序只有一个赋值操作，这样，当对大量
    //数据进行排序时，插入排序与冒泡排序的差距就体现出来了


    public static void test(int[] arr){
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("插入排序开始---------------------");
        long l1 = System.currentTimeMillis();
        directInsertSort(ints);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("插入排序结束，花费时间："+ l + "毫秒");
    }


    public static void main(String[] args){
        int[] arr = {86,74,65,52,47,39};
        int[] arr2 = {1,2,3,4,5,6};

        directInsertSort(arr2);
        //ßinsertionSort(arr);
        //insertSortBySelf(arr);
        //System.out.println(Arrays.toString(arr));
        //test1(arr);
        //test2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
        冒泡排序开始---------------------
        冒泡排序结束，花费时间：466毫秒
        插入排序开始---------------------
        插入排序结束，花费时间：62毫秒
    */
    public static void test1(int[] arr){
        for (int i=10;i>arr.length;i--){
            System.out.println(i);
        }
    }

    public static void test2(int[] arr){
        for (int i=10;i>arr.length;--i){
            System.out.println(i);
        }
    }
    /**
     ++i与i++的区别是： ++i 是先执行 i=i+1 再使用 i 的值，而 i++ 是先使用 i 的值再执行 i=i+1（即++i是先加后用 i++是先用后加）
     在这样的循环体for (int i=0; i<10; i++){}和for (int i=0; i<10; ++i){}，++i和i++的作用是一样的，但是++i比i++耗时更少,因为在Java中
     i++语句是需要一个临时变量取存储返回自增前的值，而++i不需要。只有涉及到++i 或者i++ 直接对i进行赋值或取值的时候它们才有
     区别，这里只是对i增1，所以没有影响。同样上面的for循环中的i--和--i在使用上没有区别
     */
}
