package lessons.week4.pratice.sorted.example.example1;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 有一组无序数据，找出出现次数最多的数据
 * @author: bingyu
 * @date: 2022/7/4
 */
public class MaxCount {

    public static void main(String[] args) {
        int[] nums = {3,2,1,6,1,1,2,2,3,2};
        int i = maxCount(nums);
        System.out.println(i);
    }

    /*
     如果出现最多的次数都是相同的，我该取什么元素? 例如: [1,1,5,5,3]
     思路:先将数组进行排序，排序好后，统计相邻且相同的元素有多少个即可
    */
    public static int maxCount(int[] nums) {
        Arrays.sort(nums); //排序
        int n = nums.length;
        int count = 0;//当前相邻且相同元素的个数
        int prev = -1; //上个元素值
        int max = 0; //记录出现相同元素最多的次数
        int maxElement = -1; //目前出现最多次数的元素
        for (int i = 0;i<n;i++) {
            if (nums[i] == prev) { //当前元素和上一个元素相同，说明重复了
                count++;
                if (count > max) { //这个也必须在这个if里有
                    max = count;
                    maxElement = prev;
                }
            }else {
                //没重复，说明当前元素和上个元素不同，判断上个元素出现的次数是否是当前为止最多的
                count = 1; //重置count为1
                prev = nums[i];
                if (count > max) { //这个必须放在后面，否则放在前面的话，prev就直接成了-1
                    max = count;
                    maxElement = prev;
                }
            }
        }
        return maxElement;
    }

}
