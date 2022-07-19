package lessons.week4.pratice.sorted.pratice.pratice11;

import java.util.Arrays;

/**
 * @version 1.0 最小K个数
 * @Description: 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 *
 * 提示：
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 * @author: bingyu
 * @date: 2022/7/4
 */
public class SmallestK {

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,2,4,6,8};
        int[] ints = smallestK(arr, 4);
        System.out.println(Arrays.toString(ints));
    }

    /*
     和前面一题一样，使用快排
     执行用时：20 ms, 在所有 Java 提交中击败了32.68%的用户
     内存消耗：51.1 MB, 在所有 Java 提交中击败了5.04%的用户
    */
    public static int[] smallestK(int[] arr, int k) {
        quickSort(arr,0,arr.length-1);
        int[] ks = new int[k];
        for (int i = 0;i<k;i++) {
            ks[i] = arr[i];
        }
        return ks;
    }

    public static void quickSort(int[] nums,int start,int end) {
        if (start>=end) { //开始下标和结束下标重合，说明分界点不可再分，直接返回
            return;
        }
        int pivot = partition(nums,start,end); //获取分区点的下标
        quickSort(nums,start,pivot-1);
        quickSort(nums,pivot+1,end);
    }

    //开始进行划分，获取分界点下标并将小于分界点的元素放到左半段，大于分界点的元素放到右半段，这是快排的核心函数，使用双指针处理思路来实现
    public static int partition(int[] nums, int start,int end) {
        int pivotValue = nums[end]; //使用最后一个元素作为分界点元素，再处理完后返回当前分界点元素所在新的下标
        int i = start,j= end - 1; //初始化双指针，当前i碰到j后停止
        while (i<j) { //这里死循环了
            if (nums[i] <= pivotValue ) { //TODO 注意这里小于等于的边界条件，不加等于的话会导致死循环
                i++;
            }
            if (nums[j] > pivotValue) {
                j--;
            }
            //TODO (注意这里nums[j] <= pivotValue ，没有等于的话会导致死循环)当i指针的元素大于分界点，而且j指针的元素小于等于分界点，则进行交换
            if (i<j && nums[i] > pivotValue && nums[j] <= pivotValue) {
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //执行到这里说明分化完成，此时交换分界点和j指针指向的元素，因为此时分界点元素是最后一个
        while (nums[j] < pivotValue) {
            j++;
        }
        swap(nums,j,end);
        return j;
    }

    public static void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
