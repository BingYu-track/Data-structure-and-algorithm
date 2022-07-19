package lessons.week4.pratice.sorted.pratice.pratice12;

/**
 * @version 1.0
 * @Description: 数组中的逆序对
 * @author: bingyu
 * @date: 2022/7/4
 */
public class ZgSolved {


    public static void main(String[] args) {
        int[] nums = {7,5,6,4};
        int num = reversePairs(nums);
        System.out.println(num);
    }

    private static int reverseCount = 0; //用来记录总的下降的逆序度


    /**
     * TODO 前置知识--逆序对个数=逆序度，排序的过程就是不断的减⼩逆序度的过程，我们只要在排序的过程中，记录每步操作逆序度降低的个数，
     *      累加起来就能得到原始数据的逆序度。
     * 争哥思路: 求逆序对个数，就是求原数组的逆序度，我们进行排序的过程就是降低逆序度的过程，只要我们在排序的过程中记录每次操作降低的逆序度，
     * 排序完成后获得的降低逆序度的总和就是原数组的逆序度，也就是逆序对个数。使用的归并排序
     *
     *
     */
    public static int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length-1); //使用归并排序
        return reverseCount;
    }

    private static void mergeSort(int[] nums, int p, int r) {
        if (p >= r) return;
        int q = (p+r)/2;
        mergeSort(nums, p, q);
        mergeSort(nums, q+1, r);
        merge(nums, p, q, r);
    }

    private static int merge(int[] nums, int p, int q, int r) {
        int[] tmp = new int[r-p+1]; //初始化数组，容量为p到r之间的元素数量
        int i = p; //start
        int j = q+1; //mid
        int k = 0;
        while (i <= q && j <= r) {
            if (nums[j] < nums[i]) {
                reverseCount += (q-i+1); //TODO 这里是核心，q-i+1是计算当前比q大的所有元素，因为合并前的两个子数组都输有序的，
                                            // 因此求q和p之间元素个数就是当前操作降低的逆序度
                tmp[k++] = nums[j];
                j++;
            } else {
                tmp[k++] = nums[i];
                i++;
            }
        }
        while (j <= r) {
            tmp[k++] = nums[j];
            j++;
        }
        while (i <= q) {
            tmp[k++] = nums[i];
            i++;
        }
        for (i = 0; i < r-p+1; ++i) {
            nums[i+p] = tmp[i];
        }
        return reverseCount;
    }

}
