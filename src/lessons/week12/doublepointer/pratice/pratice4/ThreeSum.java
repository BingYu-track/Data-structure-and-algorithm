package lessons.week12.doublepointer.pratice.pratice4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0  三数之和 (双指针解法)TODO: 重点练习
 * @Description: 给你一个整数数组nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足i!=j、i!=k 且 j!=k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 * 提示：
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 * @author: bingyu
 * @date: 2023/7/14
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> result = ts.threeSum2(nums);
        System.out.println(result);
    }


    /*
    方法一:
    TODO: 这题关键是如何去除重复组合，去重的思路是，由于我们进行排序，这样重复的元素就会排到一起，我们只要保证只有(a,b,c)这个顺序会被枚举到，而
     (b,a,c)、(c,b,a)等等这些不会，这样就减少了重复，具体做法就是需要我们3层遍历a,b,c时都要和前面元素对比是否相等，相等的话，立马跳过!
     主要思路: 最先想到的是三次遍历，第一次遍历a，第2次遍历b时正常遍历；第3次遍历时如果遇到和前面一样的元素就跳过
        例如；排序后的[0, 1, 2, 2, 2, 3]
        第一个三元组为(0,1,2)，如果第三重循环继续枚举下一个元素，那么仍然是三元组 (0,1,2)，产生了重复。因此我们需要将第三重循环「跳到」
        下一个不相同的元素，即数组中的最后一个元素3，枚举三元组(0,1,3)

    */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
       for (int i = 0;i<nums.length;i++) { //遍历a
           if (i>0 && nums[i]==nums[i-1]) continue; //1.跳过重复的a
           for (int j = i+1;j<nums.length;j++) { //遍历b
               if (j-1>i && nums[j-1] == nums[j]) { //2.跳过重复的b,这里j-1>i是为了防止后面j-1取到的是a而不是b
                   continue;
               }
               for (int k = j+1;k<nums.length;k++) { //遍历c
                   int a = nums[i];
                   int b = nums[j];
                   int c = nums[k];
                   if (a>0 && b>0 && c>0) return result; //都大于0，不存在三数之和为0的情况，直接返回
                   if (k-1>j && nums[k-1] == c) continue; //3.跳过重复的c,同理，这里k-1>j是为了防止后面k-1取到的是c而不是b
                   if (a+b+c == 0) {
                       List<Integer> list = new ArrayList<>();
                       list.add(a);
                       list.add(b);
                       list.add(c);
                       result.add(list);
                   }
               }
           }
       }
        return result;
    }



    /*
     对上面方法一进行优化--思路: 先固定a,b两个值不动，为了达到a+b+c=0,就只有唯一的c，由于排序后的元素是从小到大排的，因此
     在第2层遍历b时，当前的b肯定是比前一个b数字更大，那么为了保证a+b+c=0，在第三层遍历时当前的c肯定就要比前一个c数字更小
     a+b+c>0，因此我们在上面方法一的基础上，在第3层遍历改为从大到小遍历

     总结就是在a固定不变的基础上，b和c向中间移动
    */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0;i<nums.length;i++) { //遍历a
            if (i>0 && nums[i]==nums[i-1]) continue; //1.跳过重复的a
            for (int j = i+1;j<nums.length;j++) { //遍历b
                if (j-1>i && nums[j-1] == nums[j]) { //2.跳过重复的b,这里j-1>i是为了防止后面j-1取到的是a而不是b
                    continue;
                }
                int k = nums.length - 1;
                int a = nums[i];
                int b = nums[j];
                while (k>j && a+b+nums[k]>0) { //这里是双指针操作，大于0，那么c指针只能往前移动，减少值
                    k--;
                }
                int c = nums[k];
                //执行到这里，要么k<=j，要么a+b+c<=0，如果a+b+c<0不能直接break，因为后面b会向后移动，增加数值
                if (k==j) break;
                if (a+b+c == 0) { //执行到这里说明k<j
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    result.add(list);
                }
            }
        }
        return result;
    }



}
