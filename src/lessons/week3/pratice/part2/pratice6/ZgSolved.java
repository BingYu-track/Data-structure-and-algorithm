package lessons.week3.pratice.part2.pratice6;

import java.util.Stack;

/**
 * @version 1.0
 * @Description: 接雨水---争哥解法
 * @author: bingyu
 * @date: 2022/5/9
 */
public class ZgSolved {



    public static void main(String[] args) {
        int[] height = {2,8,5,5,6,1,7,4,5}; //1-2、 2-3、3-2
//      int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap(height);
        System.out.println(trap);
    }

    /**
     * TODO 解法一:暴力解法
     *      核心思想: 转化成求 1.每个柱子上承载的水量 = min(左侧最高柱子高度, 右侧最高柱子高度) - 当前柱子的高度
     *                      (我想知道这个规律是怎么思考来的，这个规律是最重要的核心--肯定是必须取左右最高中最低的，因为如果取矮的，水会淹没过矮的柱子)
     *                      2.总的接水量 = 每个柱子之上承载水量的总和
     */
    private static int trap(int[] height) {
        int n = height.length;
        int result = 0;
        // 遍历每个柱⼦h，查找它左边的最⾼柱⼦lh，和右边的最⾼柱⼦rh
        // 柱⼦上能承载的⾬⽔=min(lh, rh)-h
        for (int i = 1; i < n-1; ++i) {
            int lh = 0;
            for (int j = 0; j < i; ++j) { // 左侧最⾼lh
                if (height[j] > lh) lh = height[j];
            }
            int rh = 0;
            for (int j = i+1; j < n; ++j) { // 右侧最⾼rh
                if (height[j] > rh) rh = height[j];
            }
            int carry = Math.min(lh, rh) - height[i];
            if (carry < 0) carry = 0;
            result += carry;
        }
        return result;
    }

    /*
    TODO 解法二:前缀后缀统计解法
         该解法是建立在在解法一核心逻辑的基础上的，我们分别创建2个数组，分别存储每个柱子左边的最高值，以及每个柱子右边的最小值然后再计算每个柱子
         的接水量就好计算了，本质是一个空间换时间的算法
        虽然比较耗费空间，但是时间复杂度是O(n)的
    */
    private static int trap2(int[] height) {
        int n = height.length;
        // 前缀max
        int[] leftMax = new int[n];
        int max = 0;
        for (int i = 0; i < n; ++i) {
            leftMax[i] = Math.max(max, height[i]);
            max = leftMax[i];
        }
        // 后缀max
        int[] rightMax = new int[n];
        max = 0;
        for (int i = n-1; i >= 0; --i) {
            rightMax[i] = Math.max(max, height[i]);
            max = rightMax[i];
        }
        // 每个柱⼦之上承载的⾬⽔
        int result = 0;
        for (int i = 1; i < n-1; i++) {
            result += Math.min(leftMax[i], rightMax[i])-height[i];
        }
        return result;
    }

    /*
    TODO 解法三:单调栈解法--这个方法比较难想，是使用单调栈,水平一层层的计算水的面积
    */
    private static int trap3(int[] height) {
        int n = height.length;
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; ++i) {
            if (stack.isEmpty()) {
                stack.push(i); //存下标哈
                continue;
            }
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (height[top] >= height[i]) { //单调⼊栈
                    stack.push(i);
                    break;
                } else { // 找到凹槽了
                    top = stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                        break;
                    }
                    int left = stack.peek();
                    int h = Math.min(height[left], height[i])-height[top];
                    int w = i-left-1;
                    result += h*w;
                }
            }
        }
        return result;
    }

    /*
    TODO 解法四: 双指针，也是最优解，空间复杂度O(1)、时间复杂度O(n)
         思路: 是先求出最高的那个柱子，然后分别用2个指针i和j分别从开头和末尾向中间出发靠拢，在遍历
         的过程中得到每个i左边的最大值和j右边的最大值即可
    */
    private static int trap4(int[] height) {

        return 0;
    }
}
