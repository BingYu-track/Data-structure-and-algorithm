package lessons.week4.pratice.sorted.pratice.pratice10;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0 数组中的第K个最大元素
 * @Description: 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 提示：
 * 1 <= k <= nums.length <= 10^4
 * -10^4<= nums[i] <= 10^4
 *
 * @author: bingyu
 * @date: 2022/6/28
 */
public class FindKthLargest {

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,5,6,4};
        int[] nums2 = {1}; //2次: 6、1、3
        int kthLargest = findKthLargest(nums2, 1);
        System.out.println(kthLargest);
    }

    /*
    TODO 需要多多练习！
    使用争哥的解法：我们要查找第K个最大元素，就最好是从大到小排列；将大于分界点的元素全部放到左边,小于分界点的元素全部放到左边，取最后一个元素作为分界点
    */
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length < k) return -1; //数组长度不够
        return quickSorted(nums, 0, nums.length-1, k);
    }

    //快速排序过程中寻找第K大元素 3,2,1,5,6,4
    public static int quickSorted(int[] nums, int start, int end, int k) {
        if(start >= end) return nums[end]; //TODO: 注意这里返回元素
        int pivot = partitions(nums,start,end); //获取分界点，并按照分界点划分排序
        if (pivot - start + 1 == k) { //如果的都的分界点下标与开始下标距离的长度刚好是K，那么说明，当前分界点的位置就是我们要找的元素
            return nums[pivot];
        } else if (pivot - start + 1 > k) { //如果大于k，说明分界点位置在K的后面，我们要到前半段去查询
           return quickSorted(nums,start,pivot-1,k); //在前半段查询第K大元素
        } else { //如果小于k，说明分界点位置在K的前面，我们要到后半段去查询,注意此时就不是第K大了，而是K-(pivot-start+1)
            return quickSorted(nums,pivot+1,end,k-(pivot-start+1)); //在后半段查询第K-(pivot-start+1)大的元素，因为现在开始元素是从pivot+1开始的
        }
    }

    //双指针法： 3,2,1,5,6,4
    private static int partitions(int[] nums, int start, int end) {
        int i = start; //左半段的指针
        int j = end-1; //右半段指针
        int pivotValue = nums[end]; //取最后一个元素作为分界点
        while (i < j) {
            if (i < j && nums[i] >= pivotValue) { //当左边指针的元素大于分界点元素，不用交换(因为就是要左大，右小)，左指针向后移动
                i++;
            }
            if (i < j && nums[j] <= pivotValue) { //当右边的指针元素小于等于分界点元素，不用交换，右指针向前移动即可
                j--;
            }
            if (i<j && nums[i] < pivotValue && nums[j] > pivotValue) { //左右指针元素交换
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //执行到这里说明左右都已经满足"左大右小"的原则了，现在要将分界点交换到正确的位置
        while (nums[j] > pivotValue) {
            j++;
        }
        swap(nums,j,end);
        return j;
    }

    /*
     使用快排的思想排序解题,开始超时了!后来发现是边界条件处理不对，导致死循环
     执行用时：89 ms, 在所有 Java 提交中击败了5.09%的用户
     内存消耗：41.5 MB, 在所有 Java 提交中击败了51.41%的用户
     TODO 发现执行的还是太慢，有什么其它的方法？
    */
    public static int findKthLargest2(int[] nums, int k) {
        quickSort(nums,0, nums.length-1); //使用快排进行排序
        return nums[nums.length - k];
    }


    /*
     3,2,1,5,6,4
     快排思路: 从数组中选取一个分界点，小于这个分界点的放在左边，大于分界点的放在右边;这样不断递归执行，当分界点等于自身时，说明不可再分，说明排序完毕
    */
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
            //TODO (注意这里nums[j] <= pivotValue ，如果没有等于的话，当nums[j]=pivotValue时会导致死循环)当i指针的元素大于分界点，而且j指针的元素小于等于分界点，则进行交换
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

    /*
     我的思路--注意:第K大，也就是在大的数中选第K个，我的思路是先按从大到小排列，然后直接去倒数第k个元素即可，不过这样就没有考察意义了，不推荐
    */
    public static int findKthLargest3(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
