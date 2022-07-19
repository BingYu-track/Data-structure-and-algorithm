package lessons.week4.pratice.sorted.pratice.pratice11;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 最小K个数--设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * @author: bingyu
 * @date: 2022/7/4
 */
public class ZgSolved {

    public static void main(String[] args) {
//        int[] arr = {1,3,5,7,2,4,6,8};
        int[] arr = {1,2,3};
        int[] ints = smallestK(arr, 0);
        System.out.println(Arrays.toString(ints));
    }


    private static int[] result; //结果数组
    private static int count = 0; //用来记录数组里当前的位置

    /*
     争哥解法: 争哥也是用的快排解法，不同的是用了数组在快排期间记录的
     执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：50.5 MB, 在所有 Java 提交中击败了21.51%的用户
    */
    public static int[] smallestK(int[] arr, int k) {
        int n = arr.length;
        result = new int[k]; //初始化数组
        if (k!=0) {
            quickSorted(arr,0,n-1,k);
        }
        return result;
    }

    //解法思路；一样是获取分界点，小的数全放在分界点前面，大的数放在分界点后面，再遍历期间记录其数据
    private static void quickSorted(int[] nums, int start, int end, int k) {
        if (start > end) return; //TODO 注意这里要大于才能返回，等于可以继续执行
        int pivot = partictions(nums,start,end);
        if (pivot+1-start == k) { //如果刚好是第k个，直接把数组里前面k个的所有元素[start,pivot]放入结果数组即可
            for (int i=start;i<=pivot;i++) {
                result[i] = nums[i];
            }
            return;
        }else if (pivot+1-start < k) { //说明分界点在第k的前面，先把前面的[start,pivot]放入结果数组中，然后继续快排后面的
              //(为什么不放后面[pivot+1,k]到数组里？ 是因为这个k，我们不知道是在哪一个具体的下标，我们并不清楚，所以我们需要继续后面快排直到找到k的位置下标)
            for (int i=start;i<=pivot;i++) {
                result[i] = nums[i];
            }
            quickSorted(nums,pivot+1,end,k-(pivot+1-start)); //快排后半段，继续寻找第k的位置
        }else { //执行到这里说明分界点在第k的后面，继续快排前面的[start,pivot]
            //5,4,3,2,1
            quickSorted(nums,start,pivot-1,k); //快排前半段，继续寻找第k的位置
        }
    }

    private static int partictions(int[] nums, int start, int end) {
        int pivotValue = nums[end]; //使用末尾元素作为分界点元素
        int tmp = end; //暂存分界点元素下标
        end = end - 1;
        while (start < end) {
            if (nums[start] <= pivotValue) {
                start++;
                continue;
            }
            if (nums[end] >= pivotValue) {
                end--;
                continue;
            }
            if (nums[start] > pivotValue && nums[end] < pivotValue) {
                swap(nums,start,end);
                start++;
                end--;
            }
        }
        //将分界点元素和其位置交换
        if (nums[end]>pivotValue) {
            swap(nums,tmp,end);
        }else {
            end = end + 1;
            swap(nums,tmp,end);
        }
        return end;
    }

    /*
    注意，这里快排和经典的快排不同，经典的快排分界时2边都会快排，而这题，快排分解时只会执行一个分支，因此时间复杂度是O(n)
    */
    private static void quickSort(int[] nums, int p, int r, int k) {
        if (p > r) {
            return;
        }
        int q = partition(nums, p, r);
        if (q-p+1 == k) { //q的位置刚好是第k个，则只遍历p~q的元素到数组
            for (int i = p; i <= q; ++i) {
                result[count++] = nums[i];
            }
        } else if (q-p+1 < k) { //q的位置小于第k，说明当前位置元素不够第k，除了把前面元素放入数组中，还要继续向后快排查找
            for (int i = p; i <= q; ++i) {
                result[count++] = nums[i];
            }
            quickSort(nums, q+1, r, k-(q-p+1));
        } else {
            quickSort(nums, p, q-1, k);
        }
    }

    private static int partition(int[] nums, int p, int r) {
        int i = p-1;
        int j = p;
        while (j < r) {
            if (nums[j] < nums[r]) {
                swap(nums, j, i+1);
                i++;
            }
            j++;
        }
        swap(nums, i+1, r);
        return i+1;
    }
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
