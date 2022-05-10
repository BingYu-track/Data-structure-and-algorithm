package lessons.week3.pratice.part2.pratice6;

import java.util.Stack;

/**
 * @version 1.0 接雨水
 * @Description: 给定n个非负整数表示每个宽度为1的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]  []
 * 输出：9
 *
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 *
 * @author: bingyu
 * @date: 2022/4/22
 */
public class Trap {

    public static void main(String[] args) {
        int[] height = {2,8,5,5,6,1,7,4,5}; //1-2、 2-3、3-2
        //int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap2(height);
        System.out.println(trap);
    }

    /*
     解法1: 暴力解法--时间复杂度O(N^2)
     */
    public static int trap(int[] height) {
        int length = height.length;
        int totalVolume = 0; //总的雨水体积
        for (int i = 0;i<length;i++) {
            int leftHeight = 0;
            int rightHeight = 0;
            for(int j = i-1;j>=0;j--) { //从左边找到最高的高度
                if (height[j] > leftHeight) {
                    leftHeight = height[j];
                }
            }
            for (int j = i+1;j<length;j++) { //从右边找到最高的高度
                if (height[j] > rightHeight) {
                    rightHeight = height[j];
                }
            }
            //执行到这里说明，左边和右边的最高值都求出来了。开始计算当前柱子的接水量
            int min = Math.min(leftHeight, rightHeight);
            int currentVolume = min - height[i];
            if (currentVolume > 0) { //可能为负数，负数说明比当前柱子高度要小
                totalVolume += currentVolume; //加上当前柱子的接水量
            }
        }
        return totalVolume;
    }

    /*
     解法2: 前缀后缀统计解法
      该解法是建立在在解法一核心逻辑的基础上的，我们分别创建2个数组，分别存储每个柱子左边的最高值，以及每个柱子右边的最小值然后再计算每个柱子
       的接水量就好计算了，本质是一个空间换时间的算法
     */
    public static int trap2(int[] height) {
        int length = height.length;
        int totalVolume = 0;
        int leftMax = 0;
        int rightMax = 0;
        int[] leftMaxs = new int[length]; // 存储每个柱子左边的最高值
        int[] rightMaxs = new int[length]; //存储每个柱子右边的最高值
        for (int i = 0; i < length;i++) { //
            leftMaxs[i] = leftMax; //这里要先把之前的最高值放进去，再后面比较更新最高值
            if (leftMax < height[i]) {
                leftMax = height[i];
            }
        }
        for (int j = length - 1; j >= 0;j--) {
            rightMaxs[j] = rightMax;
            if (rightMax < height[j]) {
                rightMax = height[j];
            }
        }
        //执行到这里说明得到了所有柱子的左边最大值和右边最大值
        for (int k = 0;k<length;k++) {
            int currentVolume = Math.min(leftMaxs[k], rightMaxs[k]) - height[k];
            if (currentVolume > 0) {
                totalVolume += currentVolume;
            }
        }
        return totalVolume;
    }


    /*
     解法3: 单调栈解法
     */
    public static int trap3(int[] height) {

        return 0;
    }

}
