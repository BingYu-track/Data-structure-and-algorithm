package sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 快速排序
 * @author: bingyu
 * @date: 2021/3/16
 */
public class QuickSort {


    /**
     *
     * @param arr 待排序的数组
     * @param low 低位指针
     * @param high 高位指针
     */
    public static void quickSort(int[] arr,int low,int high){
        int pivot;
        if (low < high){ //如果low不小于high，说明2指针重合，这一轮排序结束
            pivot = partition(arr,low,high); //将序列一分为二，pivot为排好序的中心元素的位置
            quickSort(arr,low,pivot-1); //对低序列递归排序
            quickSort(arr,pivot+1,high); //对高序列递归排序
        }
    }

    //元素位置交换
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 找序列中心点的位置(设置了预留空间)
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int myPartition(int[] arr, int low, int high) {
        int pivotValue = arr[low]; //得到分界点的元素值
        swap(arr,0,low); //将分界点元素和预留的第一空间交换位置
        while (low < high){
            //如果分界点小于high指针指向的元素，则将high指针指向的元素放入low指针指向的空间中，并且，low指针向后移动一位;
            //如果不满足pivotValue < arr[high]，则high指针前移，直到满足条件，因此这必须是一个循环
            while (low < high && arr[high] >= pivotValue ){
                high--;  //每次执行到这里，意味着high指针没有找到比分界点小的元素，high指针需要向前移动一位
            }
            //执行到这里说明high指针找到了比分界点小的元素，将high指针指向的元素与low指针指向的空间进行交换，并且low指针向后移动一位
            if (low < high){ //这个if是为了防止当low和high重合时继续交换元素和指针移动
                swap(arr,low,high);
                low++;
            }
            //如果分界点大于low指针指向的元素，则将low指针指向的元素放入high指针指向的空间中，并且high指针向前移动一位
            while (low < high && arr[low] <= pivotValue){
                low++; //每次执行到这里，意味着low指针没有找到比分界点大的元素，low指针需要向后移动一位
            }
            //执行到这里说明low指针找到了比分界点大的元素，将low指针指向的元素与high指针指向的空间进行交换，并且high指针向前移动一位
            if (low < high){
                swap(arr,high,low);
                high--;
            }
        }
        //执行到这里说明已经重合，将分界点元素放入分界的位置
        swap(arr,high,0);
        return high;
    }

    /**
     * 找序列中心点的位置(不设置预留空间)
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] arr, int low, int high) {
        int pivotValue = arr[low]; //得到分界点的元素值
        while (low < high){
            //如果分界点小于high指针指向的元素，则将high指针指向的元素放入low指针指向的空间中，并且，low指针向后移动一位;
            //如果不满足pivotValue < arr[high]，则high指针前移，直到满足条件，因此这必须是一个循环
            while (low < high && arr[high] >= pivotValue ){
                high--;  //每次执行到这里，意味着high指针没有找到比分界点小的元素，high指针需要向前移动一位
            }
            //执行到这里说明high指针找到了比分界点小的元素，将high指针指向的元素与low指针指向的空间进行交换，并且low指针向后移动一位
            swap(arr,low,high);
            //如果分界点大于low指针指向的元素，则将low指针指向的元素放入high指针指向的空间中，并且high指针向前移动一位
            while (low < high && arr[low] <= pivotValue){
                low++; //每次执行到这里，意味着low指针没有找到比分界点大的元素，low指针需要向后移动一位
            }
            //执行到这里说明low指针找到了比分界点大的元素，将low指针指向的元素与high指针指向的空间进行交换，并且high指针向前移动一位
            swap(arr,high,low);
        }
        //执行到这里说明已经重合，将分界点元素放入分界的位置
        //swap(arr,high,0);
        return high;
    }


    public static void test(int[] arr){
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println("快速排序开始---------------------");
        long l1 = System.currentTimeMillis();
        quickSort(ints,0,ints.length-1);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("快速排序结束，花费时间："+ l + "毫秒");
    }



    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49};
       // int pivot = partition(arr2,0,arr2.length-1);
//        System.out.println(pivot);
//        quickSort(arr2,0,arr.length-1);
//        System.out.println(Arrays.toString(arr2));
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
