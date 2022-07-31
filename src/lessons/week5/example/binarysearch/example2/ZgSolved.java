package lessons.week5.example.binarysearch.example2;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/7/27
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] nums = {1,3,5,5,7,8,9,10};
        int target = 5;
        int first = bSearchFirst(nums, target);
        //int last = bSearchLast(nums, target);
        System.out.println("第一个等于等于目标值: " + first);
        //System.out.println("最后一个小于等于目标值: " + last);
    }

    /*
     第1题-查找第一个大于等于x的元素
     争哥解法: 我不太理解
    */
    public static int bSearchFirst(int[] nums,int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] >= target) { //找到大于等于目标值的元素
                if (mid == 0 || nums[mid - 1] < target) { //这是默认nums[mid]==target?
                    return mid;
                }else { //执行到这说明
                    high = high - 1; //说明前面元素还有与目标值相同的，需要mid向前移动
                }
            }else { //nums[mid] < target
                low = mid + 1;
            }
        }
        return -1;
    }




}
