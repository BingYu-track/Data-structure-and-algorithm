package lessons.week4.pratice.sorted.pratice.pratice12;


/**
 * @version 1.0 数组中的逆序对
 * @Description: 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 * 输入: [7,5,6,4] - [7,5],[7,6],[7,4],[5,4],[6,4]
 * 输出: 5
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * @author: bingyu
 * @date: 2022/7/4
 */
public class ReversePairs {

    public static void main(String[] args) {
        int[] nums = {7,5,6,4};
        int num = reversePairs(nums);
        System.out.println(num);
    }

    private static int reverseCount = 0; //用来记录总的下降的逆序度

    /*
       TODO: 需要多次复习
     [7,5,6,4] 属于topk类型的题目
     简单看了下leetcode题解：是在归并排序的过程中进行计数处理的，具体怎么弄不太清楚，为什么会用到归并排序？
     思路:在排序的过程中记录每次操作降低逆序度的次数
     执行用时：31 ms, 在所有 Java 提交中击败了68.55%的用户
     内存消耗：48.7 MB, 在所有 Java 提交中击败了84.23%的用户
    */
    public static int reversePairs(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        return reverseCount;
    }

    //归并排序，先计算出中间点，然后排序前半段和后半段，再进行合并
    private static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return; //当大于等于，说明已经分到不能再分了
        int mid = start + (end - start)/2; //TODO 注意点1: 必须注意归并排序的这个细节，int mid = start + (end - start)/2;化简就是(start+end)/2
        mergeSort(nums,start,mid);
        mergeSort(nums,mid+1,end);
        merge(nums,start,mid,end); //合并两个数组
    }

    //使用双指针将两数组合并  3,5,7, 2 4 8
    private static void merge(int[] nums,int start,int mid,int end) {
        int[] arr = new int[end - start + 1]; //初始化数组
        int p = start; //子数组1的起始下标
        int q = mid + 1; //子数组2的起始下标
        int i = 0;//合并后的结果数组指针
        while (p <= mid && end >=q) { //两数组[start,mid]、[mid+1,end]两数组比较大小并合并
            if (nums[p] > nums[q]) {
                arr[i] = nums[q];
                reverseCount = reverseCount + (mid - p+1);
                /*
                TODO 注意点2： q移动到了p的前面这个i位置，发生了顺序变化，计算q移动后的逆序度变化，
                      因为q既然比p小，那么q就比p后面的所有元素都小(因为2个子数组都是有序的)，因此可以计算出减小的逆序度为 mid-p+1,
                      即子数组中包含p后面的所有的元素个数
                */
                q++;
                i++;
            }else {
                arr[i] = nums[p]; //执行到这里说明p还是在q的前面，没有发生顺序变化
                p++;
                i++;
            }
        }
        //执行到这里，要么是子数组1遍历完毕，要么是子数组2遍历完毕
        while (p<=mid) { //子数组1没遍历完，就将其剩下的元素全部放入
            arr[i] = nums[p];
            i++;
            p++;
        }
        while (q<=end) { //同上
            arr[i] = nums[q];
            i++;
            q++;
        }
        //再将arr数组里的元素全部赋值到原数组
        for (int j = start;j<(start + arr.length);j++) { //TODO 注意点3: arr是从0开始，但是对应的nums位置是start，长度都是end-start
            nums[j] = arr[j-start];
        }
    }

}
