package lessons.week5.example.binarysearch.example2;

/**
 * @version 1.0
 * @Description: 查找第一个大于等于x、最后一个小于等于x的数
 * @author: bingyu
 * @date: 2022/7/27
 */
public class Solution2 {

    public static void main(String[] args) {
        int[] nums = {1,3,5,5,7,8,9,10};
        int target = 6;
        int first = bSearchFirst(nums, target);
        int last = bSearchLast(nums, target);
        System.out.println("第一个大于等于目标值: " + first);
        System.out.println("最后一个小于等于目标值: " + last);
    }

    /*
     第1题-查找第一个大于等于x的元素
     我的思路: 先查找有没有等于x的，有的话找出第一个;如果没有就查找大于x的第一个元素

     TODO 注意: 如果按照上面的例子，找第一个大于等于5的，找第一个5的位置
    */
    public static int bSearchFirst(int[] nums,int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low<=high) {
            int mid = low + (high - low)/2;
            if (nums[mid] >= target) { //如果找到和目标值相同的元素
                if (mid == 0 || nums[mid - 1] < target) { //mid的上一个元素小于(即不等于，因为原数组是按顺序排列的)目标值，则mid就是我们要的
                    return mid;
                }else {
                    high = high - 1; //说明前面元素还有与目标值相同的，需要mid向前移动
                }
            }else {
                low = mid + 1;
            }
//            else { //TODO 这里我们看到，与前面nums[mid] == target的条件是一样的，处理方式也是一样的，因此可以进行合并
//                if (mid == 0 || nums[mid - 1]<target) {
//                    return mid;
//                }else {
//                    //这里说明当前mid位置不满足，还需要往前移动，因此需要high--
//                    high = high - 1;
//                }
//            }
        }
        return -1;
    }




    /*
     查找最后一个小于等于x的元素
    */
    public static int bSearchLast(int[] nums,int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low<=high) {
            int mid = low + (high - low)/2;
            if (nums[mid] <= target) {
                if (mid == nums.length-1 || nums[mid+1]>target) { //和目标值一样且是最后一个元素；或者不是最后一个元素但是mid后面的元素大于目标值
                    return mid;
                }else {
                    //执行到这里说明nums[mid+1]==target(因为nums[mid]==target)，需要后面计算的mid向后移动
                    low++;
                }
            }else if (nums[mid] > target) { //大于目标值，判断mid-1是否小于目标值，这样mid相当于是去的第一个
                high = mid - 1;
            }
//            else { //nums[mid] < target  TODO nums[mid]<target 合并 nums[mid]==target
//                if (mid == nums.length - 1 || nums[mid+1] > target) {
//                    return mid;
//                }else {
//                    low++; //执行到这里说明nums[mid+1]<target，需要后面计算的mid向后移动
//                }
//            }
        }
        return -1;
    }



}
