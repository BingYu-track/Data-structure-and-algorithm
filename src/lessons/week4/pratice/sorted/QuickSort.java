package lessons.week4.pratice.sorted;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 快速排序
 * @author: bingyu
 * @date: 2021/3/16
 */
public class QuickSort {


    /** 5 4 3 2 1
     * 快排思路和归并相反，是"递"的时候很复杂，归的时候很简单，具体操作是，找一个分区点，一般是取最后一个元素作为分区点，然后分隔成三段
     * 分别为"左半段"、"右半段"和"中间“;左半段的元素都比分区点元素小，右半段的元素都比分区点元素大，就这样递归处理
     *
     * 递推公式: quickSort(p,r) = partition(p,r) + quickSort(p,q-1) + quickSort(q+1,r)  q是分区点
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
     * 核心思路：进行划分，并寻找中间分界点，获取分界点下标并将小于分界点的元素放到左半段，大于分界点的元素放到右半段，这是快排的核心函数，使用双指针处理思路来实现
     * 因此重要的是如何找到正确的分界点? 通常我们是先取最后一个元素先作为分界点，然后使用双指针查找分界点正确的位置
     *
     * @param nums
     * @param low 低位指针
     * @param high 高位指针
     * @return 返回的是分界点元素所在的下标
     */
    private static int partition(int[] nums, int low, int high) {
        int pivotValue = nums[high]; //TODO 1.通常使用最后一个元素作为分界点元素，再处理完后返回当前分界点元素所在的下标
        int i = low,j= high - 1; //初始化双指针，当前i碰到j后停止
        while (i<j) { //我这里快排写的有问题，会导致死循环
            if (nums[i] <= pivotValue ) { //低位指针小于等于分界点时，说明处在正确区间，继续向后移动探测
                i++;
            }
            if (nums[j] > pivotValue) { //高位指针大于分界点时，说明处在正确区间，向前移动探测
                j--;
            }
            //当i低位指针的元素大于分界点，并且j高位指针的元素小于分界点，说明两者都处于错误的区间范围，交换位置即可处于正确范围
            if (i<j && nums[i] > pivotValue && nums[j] <= pivotValue) {
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //TODO: 2.执行到这里说明分化完成，j的位置才是我们前面分界点真正的位置，此时交换分界点和j指针指向的元素，因为之前取的是最后一个元素作为分界点，因此
        // 将分界点实际所在位置high和分界点应该所在的位置j进行交换，这样分界点就处于正确的位置了
        while (nums[j] < pivotValue) {
            j++;
        }
        swap(nums,j,high);
        return j;
    }

    /*
      时间复杂度分析:
        先看递：递实际就是调用了partition函数，里面的while循环执行次数和数组的程度成正比，可以看到快排递的过程基本和归并归的过程
        是一样的，递归树每层都执行了n次，树的高度是log2n，因此快排的时间复杂度是O(nlogn)。
        再看空间复杂度，快排并没有额外开辟空间，因此空间消耗就是递归的空间log2n，因此快排空间复杂度为O(logn)
                                              partition(0,n)
                                          /                    \
                                         /                      \
                                        /                        \
                                       /                          \
                          partition(0,n/2)                  partition(n/2,n)
                          /              \                    /             \
                         /                \                  /               \
                   partition(0,n/4)  partition(n/4,n/2) partition(n/2,3n/4) partition(3n/4,n)
                   .............
                  partition(i,i)  ------------n个
        注意: 时间: 最好情况每次递归都平分数组，一共需要递归logn次，每次需要n时间，复杂度为O(n*logn)，
                  最坏情况每次都把数组分成1和n-1，一共需要递归n次，每次需要n时间，总体复杂度为O(n^2)。平均总体时间复杂度为O(nlogn)。
             空间: 和时间复杂度相关，每次递归需要的空间是固定的，总体空间复杂度即为递归层数，因此平均/最好空间复杂度为O(logn)，最坏空间复杂度为O(n)

    */


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
        int[] nums = {5,4,3,2,1};
        quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
