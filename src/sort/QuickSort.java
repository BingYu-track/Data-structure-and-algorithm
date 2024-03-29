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
     * 快排思路和归并相反，是"递"的时候很复杂，归的时候很简单，具体操作是，找一个分区点，一般是取最后一个元素作为分区点，然后分隔成三段
     * 分别为"左半段"、"右半段"和"中间“;左半段的元素都比分区点元素小，右半段的元素都比分区点元素大，就这样递归处理
     *
     * 递推公式: quickSort(p,r) = partition(p,r) + quickSort(p,q-1) + quickSort(q+1,r)
     * 终止条件: p>=r
     * @param nums 待排序的数组
     * @param low 低位指针
     * @param high 高位指针
     */
    public static void quickSort(int[] nums,int low,int high){
        if (low>=high) { //开始下标和结束下标重合，说明分界点不可再分，直接返回
            return;
        }
        int pivot = partition(nums,low,high); //获取分区点的下标，并左右分段
        quickSort(nums,low,pivot-1);
        quickSort(nums,pivot+1,high);
    }

    //元素位置交换
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 开始进行划分，获取分界点下标并将小于分界点的元素放到左半段，大于分界点的元素放到右半段，这是快排的核心函数，使用双指针处理思路来实现
     * @param nums
     * @param low
     * @param high
     * @return 返回的是分界点元素所在的下标
     */
    private static int partition(int[] nums, int low, int high) {
        int pivotValue = nums[high]; //使用最后一个元素作为分界点元素，再处理完后返回当前分界点元素所在新的下标
        int i = low,j= high - 1; //初始化双指针，当前i碰到j后停止
        while (i<j) { //我这里快排写的有问题，会导致死循环
            if (nums[i] <= pivotValue ) { //
                i++;
            }
            if (nums[j] > pivotValue) {
                j--;
            }
            if (i<j && nums[i] > pivotValue && nums[j] <= pivotValue) { //当i指针的元素大于分界点，而且j指针的元素小于分界点，则进行交换
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //问: 执行到这里说明low或者high就是分界点元素所在的位置，此时low>=high，如何知道这两个中，哪一个是我们分界点的正确下标呢?
        //答: 因为分界点正确位置要么是low,要么是high，此时high<=low，因此我们必须从high开始向后进行检测，只要nums[high]大于分界点
        //  说明high就是正确的分界点的位置；如果我们用low进行检测，就会漏掉low前面high这个下标位置，毕竟此时low要大于等于high。
        while (nums[j] < pivotValue) {
            j++;
        }
        swap(nums,j,high);
        return j;
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
        int[] nums = new int[]{6,1,2,7,9,3,4,5,10,8,11};
        quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
