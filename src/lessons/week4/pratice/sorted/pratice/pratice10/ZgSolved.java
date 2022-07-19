package lessons.week4.pratice.sorted.pratice.pratice10;

/**
 * @version 1.0
 * @Description: 数组中的第K个最大元素
 *  提示：
 *  1 <= k <= nums.length <= 10^4
 *  -10^4<= nums[i] <= 10^4
 * @author: bingyu
 * @date: 2022/6/28
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,5,6,4};
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int kthLargest = findKthLargest(nums2, 3);
        System.out.println(kthLargest);
    }

    /*

     争哥的思路: 使用快排的思想，选取其中一个元素作为分区点，将大于分区点的元素放到前面，小于的放到后面;如果分区点元素位置刚好是第K个，那么该分区点就是
      我们要求的;分界点如果大于K，则说明要求的在前面的分区点，继续递归前半部分的分区操作；如果分区点小于K，说明要求的在后面的分区，就递归后半部分分区
      TODO : 代码实现比较难，很复杂，需要考虑比较多的特殊情况

      执行用时：18 ms, 在所有 Java 提交中击败了15.68%的用户
      内存消耗：41.9 MB, 在所有 Java 提交中击败了10.33%的用户
    */
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length<k) return -1;
        return quickSort(nums,0,nums.length-1,k);
    }

    /**
     * 快速排序
     * @param nums 待排序数组
     * @param start 起始下标
     * @param end 结束下标
     * @param k 第k个
     * @return
     */
    private static int quickSort(int[] nums, int start, int end,int k) {
        if (start>=end) return nums[end];
        int pivot = partitions(nums,start,end); //划分3边界，将大于分界点的元素放到分界点左边，小于分界点的元素放到分界点右边，并返回分界点下标
        if (pivot+1-start==k) { //pivot+1-start表示分界点是在第几个
            return nums[pivot];
        }else if (pivot+1-start>k) { //区分点大于k，说明区分点在第k的后面，只需要在区分点前半段[start,pivot-1]处理即可
            return quickSort(nums,start,pivot-1,k);
        }else {
            //执行到这里说明区分点在第K的前面，只需要处理后半段[pivot+1,end]
            return quickSort(nums,pivot+1,end,k-(pivot+1-start)); //因为start变成了pivot+1，因此这里k就变成了pivot+1-start表示pivot+1在第几个
        }
    }

    /*
    划分区域:使用双指针
    1,5,2,4,3
    */
    private static int partitions(int[] nums, int start, int end) {
        int tmp = end;
        int pivotValue = nums[tmp]; //取最后一个元素作为分界点元素
        end = end - 1;
        while (start < end) {
            if (nums[start] >= pivotValue) {
                start++;
                continue;
            }
            if (nums[end] <= pivotValue) {
                end--;
                continue;
            }
            if (nums[start] < pivotValue && nums[end] > pivotValue) {
                swap(nums,start,end);
                start++;
                end--;
            }
        }
        //执行到这里说明start,end中选择比分界点元素大的进行交换
        if (nums[end]<pivotValue) {
            swap(nums,tmp,end);
        }else {
            end = end+1;
            swap(nums,tmp,end);
        }
        return end;
    }

    private static void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /** TODO: 待理解！
     * 这个代码比较难理解(和我前面写的partitions方法完成的功能是一样的)
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] nums, int start, int end) {
        int i = start-1; //第一个元素作为分界点?
        int j = start; //第一个元素下标
        while (j < end) { //start < end
            if (nums[j] > nums[end]) { //如果前面的元素大于后面的元素
                swap(nums, j, i+1);
                i++;
            }
            j++;
        }
        swap(nums, i+1, end);
        return i+1;
    }

}
