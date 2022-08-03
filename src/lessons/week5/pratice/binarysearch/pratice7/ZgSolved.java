package lessons.week5.pratice.binarysearch.pratice7;

/**
 * @version 1.0
 * @Description: 搜索旋转排序数组--争哥解法
 * @author: bingyu
 * @date: 2022/7/28
 */
public class ZgSolved {

    public static void main(String[] args) { //7
        int[] nums = {4,5,6,7,8,9,0,1}; //mid =3 -> 4,7 mid=5 -> 6,7 mid=6 -> 7,7 mid=7
        int target = 1;
        int search = search(nums, target);
        System.out.println(search);
    }

    /*
     争哥解法:
    */
    public static int search(int[] nums, int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                return mid;
            }else { //
                if (nums[low] <= nums[mid]) { //说明[low,mid]是有序，[mid+1,high]是循环有序
                    if (target < nums[mid] && target>nums[low]) { //如果目标值落在有序区间，就向左进行查找
                        high = mid - 1;
                    }else { //目标值落在有序循环区间,就向右进行查找，这里mid会不断折半进行右移
                        low = mid + 1;
                        /*TODO:疑问-落在有序循环区间为什么能这样处理,[mid+1,high]循环有序区间上的low和high并不是有序的，计算得到的mid不是错误的吗？
                           我是这样理解的，因为确定了目标值落在了循环有序区间，肯定是要到循环有序区间里去查找的，在查找过程中，low,high会进一步缩小可能成为
                           范围更小的有序区间或者有序循环区间，只要不断缩小范围，最终都会查到目标值
                        */
                    }
                } else { //nums[low] > nums[mid] 说明[low,mid]是循环有序，[mid+1,high]是有序的
                    if (target > nums[mid] && target <= nums[high]) {
                        low = mid + 1;
                    }else {
                        high = mid - 1; //这里mid是不断折半进行左移
                    }
                }
            }
        }
        return -1;
    }



}
