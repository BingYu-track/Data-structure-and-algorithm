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
        int[] height = {2,8,5,5,6,1,7,4,5}; //12
        //int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap4(height);
        System.out.println(trap);
    }

    /*
     解法1: 暴力解法--时间复杂度O(n^2)
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
       的接水量就好计算了，本质是一个空间换时间的算法; 空间复杂度:O(n) 时间复杂度O(n)
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
      思路: 就是用栈记录连续高度下降的柱子所在的下标，后面遍历柱子时只要碰到比栈顶高的，就开始计算其接水面积，直到栈顶栈顶元素高于当前柱子时；再遍历下一个柱子
     */
    public static int trap3(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int totalVolume = 0; //总雨水面积
        for (int i = 0;i<height.length;i++) {
            while (!stack.isEmpty() && (stack.peek()!=0 && height[stack.peek()] < height[i])) { //当前柱子比栈顶高(栈顶要排除是第一根柱子)，说明有坑，开始计算接水面积
                int top = stack.pop(); //获取栈顶的柱子
                int last = stack.peek(); //获取栈顶的上一个柱子
                int min = Math.min(height[last], height[i]); //比较栈顶左右两边的柱子看，取矮的那个
                int volume = (min - height[top]) * (i - last - 1); //计算接雨水的面积
                if (volume > 0) {
                    totalVolume += volume;
                }
            }
            stack.push(i);
        }
        return totalVolume;
    }



    /*
    TODO: 解法4: 双指针，也是最优解，空间复杂度O(1)、时间复杂度O(n)
         思路: 是先求出最高的那个柱子，然后分别用2个指针i和j分别从开头和末尾向中间出发靠拢，在遍历
         的过程中得到每个i左边的最大值和j右边的最大值即可
    */
    private static int trap4(int[] height) {
        int length = height.length;
        int totalVolume = 0;
        int leftMax = 0;
        int rightMax = 0;
        int highestIndex = 0; //最高柱子所在下标
        //执行到这里得到了最高柱子所在的下标，开始分别进行左右遍历
        for (int k = 0;k<length;k++) {
            if (height[k] >= height[highestIndex]) {
                highestIndex = k;
            }
        }
        //i从左向右
        for(int i = 0;i<highestIndex;i++) {
            if (i > 0) {
                int minHeight = Math.min(leftMax, height[highestIndex]); //比较左边的最大柱子和右边的最大柱子，取最小的
                int leftVolume = minHeight - height[i]; //比较后减去当前的高度就是当前柱子的接水量
                if (leftVolume > 0) {
                    totalVolume += leftVolume;
                }
            }
            if (leftMax < height[i]) { //遍历过程中依次更新左边最高的柱子
                leftMax = height[i];
            }
        }
        //j从右向左
        for (int j=length - 1;j>=highestIndex;j--) {
            if (j < length - 1) {
                int minHeight = Math.min(rightMax, height[highestIndex]);
                int rightVolume = minHeight - height[j];
                if (rightVolume > 0) {
                    totalVolume += rightVolume;
                }
            }
            if (rightMax < height[j]) {
                rightMax = height[j];
            }
        }
        return totalVolume;
    }


}
