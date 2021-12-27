package lessons.week1.pratice.pratice9;

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
        int[] nums = {0,0,1,2,2,2,3,4,2,1,4,66,7};
        int length = removeDuplicates(nums);
        System.out.println(length);
    }


    //我的解法:
    //要求:不能使用额外的数组空间
    //1.首先提取出不重复的数字并排到前面
    //2.然后将前面不重复的数字进行重小到大排序
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        int count = 0; //用来记录不重复数字的个数
        int j = 0;
        for (int i=0;i<length;i++) {
            if (nums[j] == nums[i]) {
                //遇到重复的数字
                j++;
            }else {
                //执行到这说明遇到了不一样的数字
                //
            }
        }
        return 0;
    }

}
