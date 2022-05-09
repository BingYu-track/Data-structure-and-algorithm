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
//        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap(height);
        System.out.println(trap);
    }

    /**
     * 我的思路: 1. 2个长度高之间有均低于的2边的高度，说明中间是可以接雨水的，
     *         2. 这2个边界的雨水 = 2边界之间的单位 - 2边界之间的柱子单位(小于等于2边界中最低的高度)
     *         3.在确定两边界有坑后，在判断后面有没有边界要高于
     *  所以难度是确定左右边界
     *  先得到哪2个边界之间存在接雨水的坑
     *
     *  [0,1,0,2,1,0,1,3,2,1,2,1]
     *  bounderLeft:  [1,3,7]
     *  bounderRight: [3,7,10]
     */
    public static int trap(int[] height) {
        int length = height.length;
        Stack<Integer> bounderLeft = new Stack<>(); //左边界栈
        Stack<Integer> bounderRight = new Stack<>(); //右边界栈
        int i = 0;
        int j = 0;
        while (i < length - 2) {
            int left = i;
            int right = i + 2 + j;
            if (right > length - 1) {
                break;
            }
            boolean hasTrap = judgeHaveTrap(height,left,right); //判断2边界之间是否有坑
            if (hasTrap) { //有坑，用栈记录左边界，然后继续从right往右扫描
                bounderLeft.push(left);
                if (right == length - 1) { //右边界刚好是最后一个元素
                    bounderRight.push(right);
                    break;
                }
                //1.先找出比左边界高度大的边界,有的话就作为右边界
                int k = right;
                while (k < length) {
                    if (height[left] > height[k]) {
                        k++; //没有找到继续向后找
                    }else {
                        //执行到这说明比左边界高度大于等于，将其作为右边界即可
                        right = k;
                        bounderRight.push(right);
                        break;
                    }
                }
                //2.前面没有找到比左边界高度高的话，就去和后面的边界比较，找比当前右边界高的
                while (bounderLeft.size() > bounderRight.size() && right < length) {
                    if (right + 1 < length && height[right] > height[right + 1] || right == length - 1) { //如果当前右边界比后面的大，或者当前右边界就是最后一个边界，则就是我们要的右边界
                        bounderRight.push(right);
                        break;
                    } else { //右边界小于等于后面的元素，右边界继续右移,扩大坑的范围
                        right++;
                    }
                }
                //执行到这里说明找到了右边界，重新初始化左右边界
                i = right;
                j = 0;
            }else { //TODO:这里处理有问题，没有坑的话应该分2种情况
                  //1.左边界的高度为0，将左边界和右边界一起往右移动
                  //2.左边界的高度不为0，将右边界向后移动，看有没有坑
                int leftHeight = height[left];
                if (leftHeight <= height[left + 1]) { //TODO:注意这里如果左边界高度小于等于后面的高度，那么该左边界说明是没有用的，需要向后移动
                    i++;
                }else {
                    j++;
                }
            }
        }

        //再对坑进行容积计算：需要注意的是不仅仅是每对需要进行计算，一对计算完后，右边界还要对第一个左边界进行计算
        int volume = calculateRainVolume(height,bounderLeft,bounderRight);
        return volume;
    }

    private static boolean judgeHaveTrap(int[] height,int left, int right) {
        for (int i = left + 1; i< right;i++) {
            int middle = height[i]; //获取左右边界之间的高度
            if (middle < height[left] && middle < height[right]) { //只要中间的有比左右边界高度矮的，就说明有坑
                return true;
            }
        }
        return false;
    }

    /**
     * 计算接雨水的容积
     * 思路:
     *
     */
    private static int calculateRainVolume(int[] height, Stack<Integer> bounderLeft, Stack<Integer> bounderRight) {
        int volume = 0;
        while (!bounderLeft.isEmpty()) {
            int left = bounderLeft.pop();
            int right = bounderRight.pop();
            int low = 0;
            if(height[left] > height[right]) {
                volume = volume + height[right] * (right - left - 1);
                low = right;
            }else {
                volume = volume + height[left] * (right - left - 1);
                low = left;
            }
            //执行到这里得到了总容积后，还要减去中间的容积
            for (int i = left + 1;i < right;i++) {
                if (height[i] > height[low]) { //如果高于边界
                    volume = volume - height[low];
                }else {
                    volume = volume - height[i];
                }
            }
        }
        return volume;
    }

}
