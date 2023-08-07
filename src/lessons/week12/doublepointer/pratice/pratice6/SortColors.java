package lessons.week12.doublepointer.pratice.pratice6;

import java.util.Arrays;

/**
 * @version 1.0  颜色分类(双指针解法)
 * @Description: 给定一个包含红色、白色和蓝色、共n个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1和2分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * @author: bingyu
 * @date: 2023/7/18
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2,1,2,1,0,0};
        SortColors sc = new SortColors();
        sc.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
      TODO 争哥解法：就是上一道题奇偶分类的加强版(推荐!)
     */
    public void sortColors(int[] nums) {
        int p = 0;
        int q = nums.length-1;
        //1.这里第一个循环就是将非2的数放在数组前面，2数字放在数组末尾，
        while (p < q) {
            if (nums[p] != 2) {
                p++;
                continue;
            }
            if (nums[q] == 2) {
                q--;
                continue;
            }
            //执行到这里说明p==2并且q!=2，说明需要交换
            swap(nums, p, q);
            p++;
            q--;
        }
        //2.执行到这里，得到的区间[0,p-1]就是非2的数字，在区间[p,nums.length-1]就是存放的2
        int i = 0;
        int j = p;
        if (nums[j] == 2) j--;
        while (i < j) { //3.这里第二次遍历就是在区间[0,p-1]的范围内，将0放到前面，1放入区间末尾
            if (nums[i] == 0) {
                i++;
                continue;
            }
            if (nums[j] == 1) {
                j--;
                continue;
            }
            //执行到这里，说明i指针指向1，j指针指向0，需要进行交换
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int p, int q) {
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }

    /*
     要保证,0,1,2这个顺序，在不使用sort这个库的情况下，
    TODO: 那我直接写个快排就行，因为快排就是用的双指针，快排的思路重点算法就是找到分界点元素的下标这个算法
      1.先取数组最后一个元素作为分界点元素 然后使用双指针将小于分界点的元素放到前面，大于分界点的元素放到后面，
        直到双指针重合说明找到了分界点元素的下标pivot。(这是核心)
      2.再将(0,pivot)和(pivot,high)做出第1步同样的处理，得到新的分界点pivot1和pivot2
      3.重复上述1，2操作，直到所有的low和high重合，说明所有元素处理完毕
     TODO: 我总是把快排代码写错

     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：39.9 MB, 在所有 Java 提交中击败了67.89%的用户
    */
    public void sortColors2(int[] nums) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        quickSort(low,high,nums);
    }


    private void quickSort(int low, int high, int[] nums) {
        if (low>=high) return;
       int pivot = part(low,high,nums);
       quickSort(low,pivot-1,nums);
       quickSort(pivot+1,high,nums);
    }

    private int part(int low, int high, int[] nums) {
        //int pivotValue = nums[nums.length - 1]; //TODO: 注意4.这里最后一个元素作为分界点元素，不能用nums.length-1，
                                                   // 因为后面要递归调用，应该直接用high
        int pivotValue = nums[high]; //分界点元素
        int pivot = high; //分界点元素下标
        high = high - 1; //因为是取最后一个元素作为分界点，因此high需要减1
        while (low < high) {
            //TODO：注意5--这里不能用while替代if，因为使用while的话，会不断low++导致数组越界
            if (nums[low] <= pivotValue) { //这里low小于等于分界点，下面互换条件low就要大于分界点
                low++;
            }
            if (nums[high] > pivotValue) { //这里high大于分界点，下面互换条件high就要小于等于分界点
                high--;
            }
            //执行到这里，说明low指向的元素大于等于分界点元素；high指向的元素小于等于分界点元素，这两个指针指向的元素需要进行互换
            //TODO: 注意6--需要注意的是，如果nums[low]==pivotValue==nums[high]，这里就会死循环，需要进特殊处理
            if (low < high && nums[low] > pivotValue && nums[high] <= pivotValue) {
                swap(low,high,nums);
                low++;
                high--;
            }
        }
        /*
         问: 执行到这里说明low或者high就是分界点元素所在的位置，此时low>=high，如何知道这两个中，哪一个是我们分界点的正确下标呢?
         答: 因为分界点正确位置要么是low,要么是high，此时high<=low，因此我们必须从high开始向后进行检测，只要nums[high]大于分界点
            说明high就是正确的分界点的位置；如果我们用low进行检测，就会漏掉low前面high这个下标位置，毕竟此时low要大于等于high
         */
        while (nums[high] < pivotValue) {
            high++;
        }
        swap(high,pivot,nums); //交换分界点位置的元素和真正分界点元素
        return high;
    }

    private void swap(int low, int high, int[] nums) {
        int tmp = nums[low];
        nums[low] = nums[high];
        nums[high] = tmp;
    }

}
