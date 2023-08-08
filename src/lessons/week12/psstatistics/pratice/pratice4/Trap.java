package lessons.week12.psstatistics.pratice.pratice4;

/**
 * @version 1.0  接雨水
 * @Description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 105
 * @author: bingyu
 * @date: 2023/8/1
 */
public class Trap {

    public static void main(String[] args) {
//        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height = {4,2,0,3,2,5};
        Trap t = new Trap();
        int res = t.trap(height);
        System.out.println(res);
    }

    /*
     TODO:
      核心思想: 每个柱子之上承载的水量 = Math.min(左侧最高柱子lh , 右侧最高柱子rh) - 这个柱子的高度h
              总的接水量 = 每个柱子之上承载水量的总和

      如何理解该思想？
      因为，当前位置能接雨水，必须是要能形成一个坑，如果要形成一个坑，左边柱子或者右边柱子比当前位置的柱子高才能形成坑；
      而当左右两边柱子都比当前柱子高时，承接雨水量取决于最短的那根柱子，因此我们要Math.min(左侧最高柱子lh , 右侧最高柱子rh)，然后再减去
      当前柱子的高度，如果为负数，说明无法形成坑，也就接不了雨水，如果大于0，那么可以形成坑。

      使用前缀后缀的思路，一个数组记录前面柱子的最大高度，一个数组记录后面柱子的最大高度，这样就能用上面的核心思想进行计算每个柱子的承水量了

      执行用时：1 ms, 在所有 Java 提交中击败了78.95%的用户
      内存消耗：43.4 MB, 在所有 Java 提交中击败了26.51%的用户
    */
    public int trap(int[] height) {
        int n = height.length;
        int[] lh = new int[n]; //用来存储所有柱子位置对应左边最大的柱子高度
        int[] rh = new int[n];
        for (int i = 0;i<n;i++) {
            if (i == 0) {
                lh[i] = 0;
            }else {
                lh[i] = Math.max(lh[i-1],height[i-1]);
            }
        }
        for (int i = n - 1;i>=0;i--) {
            if (i == n-1) {
                rh[i] = 0;
            }else {
                rh[i] = Math.max(rh[i+1],height[i+1]);
            }
        }
        int sumWater = 0;
        for (int i = 0;i<n;i++) {
            int curHeight = Math.min(lh[i],rh[i]) - height[i]; //当前柱子承接的水量
            if (curHeight < 0) continue;
            sumWater += curHeight;
        }
        return sumWater;
    }


}
