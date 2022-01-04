package lessons.week1.pratice.part1.pratice9;

/**
 * @version 1.0 删除有序数组中的重复项
 * @Description: 给你一个有序数组nums,请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
 *              不要使用额外的数组空间，你必须在原地修改输入数组并在使用O(1)额外空间的条件下完成。
 *
 *
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 *      // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 *      int len = removeDuplicates(nums);
 *
 *      // 在函数里修改输入数组对于调用者是可见的。
 *      // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 *      for (int i = 0; i < len; i++) {
 *          print(nums[i]);
 *      }
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 * 0 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按升序排列
 *
 * @author: bingyu
 * @date: 2021/12/27
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {6,0,4,1,6,5,9,10,2,2,3,3,4};
        int length = removeDuplicates(nums);
        System.out.println(length);
    }


    //我的解法:(我的解法是可以处理无序的)
    //要求:不能使用额外的数组空间
    //思路:
    //1.首先找出最小值，然后我们取比数组里最小值还小的值，我们用这个值来代表重复值duplicate
    //2.从第一个元素开始依次与后面的元素进行比较，一旦重复就将其赋值为duplicate，后面只需要针对非duplicate值进行处理即可
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int count = 0; //用来记录不重复数字的个数
        int duplicate = binarySearchMin(nums) - 1; //这个值用来代表重复值
        for (int i = 0;i<nums.length;i++) {
            if (nums[i] == duplicate) continue; //如果遇到重复值就跳过
            for (int j = i+1;j<nums.length;j++) { //执行到这里说明当前i的元素值是非重复的，将其和后面的所有数值进行比较，是否有重复
                if (nums[i] != duplicate && nums[i] == nums[j]) {
                    nums[j] = duplicate; //进入这里说明遇到了重复值，需要将后面的值标记为duplicate
                }
            }
        }

        int temp = 0; //临时变量
        //将非重复数值全部移动到前面，并且记录非重复数字的数目
        for (int k = 0;k<nums.length;k++) {
            if (nums[k] != duplicate) {
                temp = nums[count];
                nums[count] = nums[k];
                nums[k] = temp;
                count++;
            }
        }

        //最后对其前面的非重复数字进行排序[0,4,1,6,2,3,-1,-1,-1,-1,-1]
        //quickSort(nums,0,count - 1);
        return count;
    }

    //二分法查找最小值
    public static int binarySearchMin(int[] nums) {
        int length = nums.length;
        int min = nums[0];
        for (int i = 0,j=length - 1; i<j;i++,j--) {
            if (nums[i] < nums[j] && nums[i] < min) {
                    min = nums[i];
            }else if (nums[i] > nums[j] && nums[j] < min) {
                min = nums[j];
            }
        }
        return min;
    }

    //快速排序(无预留空间)
    public static void quickSort(int[] nums,int low,int high) {
        int pivot;
        if (low < high){ //如果low不小于high，说明2指针重合，这一轮排序结束
            pivot = partition(nums,low,high); //将序列一分为二，pivot为排好序的中心元素的位置
            quickSort(nums,low,pivot-1); //对低序列递归排序，保证低序列的所有元素低于高序列的所有元素
            quickSort(nums,pivot+1,high); //对高序列递归排序，保证高序列的所有元素高于低序列的所有元素
        }

    }

    //思路: 使用pivot随便选择数组里的一个值作为pivotValue，用2个指针，low头指针,high尾指针，low指向的数值和pivotValue进行比较，
    //如果小于pivotValue，直接跳过，即low++，直到low的值大于pivotValue时停下来;然后high指针开始向前移动，high指向的数值
    //和pivotValue进行比较,如果大于pivotValue，直接跳过，即high--，直到high的值小于pivotValue，此时可以将low的值和high的值进行交换
    //注意:分界点的位置是不变的，交换的是low和high对应的元素
    public static int partition(int[] nums, int low, int high) {
        //[6,0,4,1,5,9,10,2,3]
        //注意这里取分界点的值时不能随便取，如果取的是low的值作为分界点，就必须先从high开始处理；如果取的high的值作为分界点，就必须先从low开始处理
        int pivotValue = nums[high];
        while (low < high) {
            while (low < high && nums[low] <= pivotValue) {
                //low的值小于pivotValue，直接跳过
                low++;
            }
            swap(nums,low,high); //TODO:为何这里要再进行一次与交换？ 因为low比分界点的值pivotValue大，所以要将low的值放到分界点的右边去

            //执行到这里说明low的值大于了pivotValue。开始处理high
            while (low < high && nums[high] >= pivotValue) {
                //high的值大于pivotValue，直接跳过
                high--;
            }
            //执行到这，说明low的值大于pivotValue，high的值小于pivotValue，可以将low和high的值进行互换
            //互换完后进行下一轮，直到low和high相遇
            swap(nums,low,high);
        }
        //执行到这里说明low和high相遇了，所以此时的low和high的位置都表示分界点
        return low;
    }

    /**
     *
     * @param nums
     * @param i 待交换的位置
     * @param pivot 分界点的位置
     * @return 分界点的新位置
     */
    public static void swap(int[] nums,int i,int pivot) {
        int temp = nums[i];
        nums[i] = nums[pivot];
        nums[pivot] = temp;
    }

    //二分法找最小值对应的下标
    public static int binarySearchMinIndex(int[] nums) {
        int index = 0;
        int length = nums.length;
        int min = nums[0];
        for (int i = 0,j=length - 1; i<j;i++,j--) {
            if (nums[i] < nums[j] && nums[i] < min) {
                min = nums[i];
                index = i;
            }else if (nums[i] > nums[j] && nums[j] < min) {
                min = nums[j];
                index = j;
            }
        }
        return index;
    }

    //二分法找最大值对应的下标
    public static int binarySearchMaxIndex(int[] nums) {
        int index = 0;
        int length = nums.length;
        int max = nums[0];
        for (int i = 0,j=length - 1; i<j;i++,j--) {
            if (nums[i] > nums[j] && nums[i] > max) {
                max = nums[i];
                index = i;
            }else if (nums[j] > nums[i] && nums[j] > max) {
                max = nums[j];
                index = j;
            }
        }
        return index;
    }


}
