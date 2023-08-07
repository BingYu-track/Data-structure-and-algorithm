package lessons.week12.doublepointer.pratice.pratice7;

import java.util.Arrays;

/**
 * @version 1.0  移动零(快慢指针)
 * @Description: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 * 1 <= nums.length <= 10^4
 * -2^31<= nums[i] <= 2^31- 1
 *
 * @author: bingyu
 * @date: 2023/7/19
 */
public class MoveZeroes {

    public static void main(String[] args) {
//        int[] nums = {0,3,0,1,12};
        int[] nums = {2,1};
//        int[] nums = {1,0,1};
        MoveZeroes mz = new MoveZeroes();
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums)); //预期结果: [3,1,12,0,0]
    }

    /*
     保持非0元素相对顺序不变，将0全部移动到数组末尾，并且必须是原地操作
    TODO： 这里,由于题目要求相对非零元素位置不变，因此不能使用排序，我们需要用到快慢指针，i和j同时从起始位置出发，i指针遇到0时，j不动，
     i指针遇到非0数字时,nums[i]和nums[j]进行交换，并且j指针移动一位(目的是让j始终指向非数字区间的最后一个元素)

     执行用时：2 ms, 在所有 Java 提交中击败了32.20%的用户
     内存消耗：44.1 MB, 在所有 Java 提交中击败了25.67%的用户
    */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0;i<nums.length;i++) {
            if (nums[i]!=0) {
                if (i!=j) { //防止当i==j时，自己和自己交换,注意如果前面都是非0元素，那么j会和i一样向前移动，这样i和j就不会发生交换
                    //一旦i!=j了，就说明前面是遇到了0，因此需要进行交换
                    swap(i,j,nums); //交换
                }
                j++;
            }
        }
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = 0;
        tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }


}
