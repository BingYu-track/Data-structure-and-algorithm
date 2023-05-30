package lessons.week10.dp.pratice.pratice7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0  三角形最小路径和
 * @Description: 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标+1的两个结点。也就是说，
 * 如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 *
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 * 你可以只使用 O(n) 的额外空间(n 为三角形的总行数)来解决这个问题吗？
 *
 * @author: bingyu
 * @date: 2023/5/29
 */
public class MinimumTotal {

    public static void main(String[] args) {
        MinimumTotal total = new MinimumTotal();
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l1 = Arrays.asList(2);
        List<Integer> l2 =  Arrays.asList(3,4);
        List<Integer> l3 =  Arrays.asList(6,5,7);
        List<Integer> l4 =  Arrays.asList(4,1,8,3);
        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);
        int res = total.minimumTotal2(triangle);
        System.out.println(res);
    }

    /*
     该题同样只有两种选择，阶段数和triangle.size一样;我使用的二维dp来进行解题
     执行用时：5 ms, 在所有 Java 提交中击败了22.80%的用户
     内存消耗：42.9 MB, 在所有 Java 提交中击败了10.15%的用户
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int cols = triangle.get(rows - 1).size(); //用三角形底部最大，的作为二维数组的列数
        int[][] dp = new int[rows][cols];
        //初始化
        for (int i = 0;i<dp.length;i++) {
            Arrays.fill(dp[i],20000);//因为 -10^4 <= triangle[i][j] <= 10^4
        }

        //初始化第0行
        dp[0][0] = triangle.get(0).get(0);

        //dp[i][j]要么来自dp[i-1][j]要么来自dp[i-1][j-1],从这当中取最小路径和
        for (int i = 1;i<dp.length;i++) { //从第1行开始
            for (int j = 0;j<triangle.get(i).size();j++) { //每行的列数通过triangle.get(i)控制
                int cur = triangle.get(i).get(j); //当前位置dp[i][j]的值
                if (dp[i-1][j]!=20000) { //从dp[i-1][j]进来的
                    dp[i][j] = cur + dp[i-1][j];
                }
                if (j-1>=0 && dp[i-1][j-1]!=20000) { //dp[i-1][j-1]
                    dp[i][j] = Math.min(dp[i][j],cur + dp[i-1][j-1]);
                }
            }
        }

        int[] arr = dp[rows - 1];
        Arrays.sort(arr);
        return arr[0];
    }


   /*
    使用一维dp解题
    执行用时：3 ms, 在所有 Java 提交中击败了78.84%的用户
    内存消耗：42.9 MB, 在所有 Java 提交中击败了14.67%的用户
  */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int cols = triangle.get(rows - 1).size(); //用三角形底部最大，的作为二维数组的列数
        int[] dp = new int[cols];
        Arrays.fill(dp,20000); //初始化

        dp[0] = triangle.get(0).get(0);

        for (int i = 1;i<rows;i++) {
            int size = triangle.get(i).size();
            for (int j = size-1;j>=0;j--) {
                int cur = triangle.get(i).get(j); //当前位置dp[i][j]的值
                if (dp[j]!=20000) { //从dp[i-1][j]进来的
                    dp[j] = cur + dp[j];
                }
                if (j-1>=0 && dp[j-1]!=20000) {
                    dp[j] = Math.min(dp[j],cur + dp[j-1]);
                }
            }
        }
        Arrays.sort(dp);
        return dp[0];
    }

}
