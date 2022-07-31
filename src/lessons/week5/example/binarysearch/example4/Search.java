package lessons.week5.example.binarysearch.example4;

/**
 * @version 1.0
 * @Description: 在循环有序数组中查询最小元素(没有重复数据)--对应leetcode的 "搜索旋转排序数组"这题(pratice8)
 * @author: bingyu
 * @date: 2022/7/28
 */
public class Search {


    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int search = search(nums);
        System.out.println(search);
    }

    /*


    */
    public static int search(int[] nums) {
        int min = Integer.MAX_VALUE; //记录最小值
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
//            if (nums[mid] ) {
//
//            }
        }
        return -1;
    }


}
