package leetcode.competions.season1.problem2;

import java.util.Arrays;

/**
 * @version 1.0 城墙防线
 * @Description: 在探险营地间，小扣意外发现了一片城墙遗迹，在探索期间，却不巧遇到迁徙中的兽群向他迎面冲来。情急之下小扣吹响了他的苍蓝笛，
 * 随着笛声响起，遗迹中的城墙逐渐发生了横向膨胀。
 * 已知 rampart[i] = [x,y] 表示第 i 段城墙的初始所在区间。当城墙发生膨胀时，将遵循以下规则：
 * 所有的城墙会同时膨胀相等的长度；
 * 每个城墙可以向左、向右或向两个方向膨胀。
 * 小扣为了确保自身的安全，需要在所有城墙均无重叠的情况下，让城墙尽可能的膨胀。请返回城墙可以膨胀的 最大值 。
 *
 * 注意：
 * 初始情况下，所有城墙均不重叠，且 rampart 中的元素升序排列；
 * 两侧的城墙可以向外无限膨胀。
 *
 * 示例 1：               1    2
 * 输入：rampart = [[0,3],[4,5],[7,9]]
 * 输出：3
 *
 * 解释：如下图所示：
 * rampart[0] 向左侧膨胀 3 个单位；
 * rampart[2] 向右侧膨胀 3 个单位；
 * rampart[1] 向左侧膨胀 1 个单位，向右膨胀 2 个单位。
 * 不存在膨胀更多的方案，返回 3。
 *
 * 示例 2：              3     3        3
 * 输入：rampart = [[1,2],[5,8],[11,15],[18,25]]
 * 输出：4
 *
 * 提示：
 * 3 <= rampart.length <= 10^4
 * rampart[i].length == 2
 * 0 <= rampart[i][0] < rampart[i][1] <= rampart[i+1][0] <= 10^8
 *
 * @author: bingyu
 * @date: 2023/5/7
 */
public class RampartDefensiveLine {

    public static void main(String[] args) {
//        int[][] rampart = {{1,2},{5,8},{11,15},{18,25}};
        //                      7       2       1       1       2
        int[][] rampart = {{3,5},{12,29},{31,38},{39,42},{43,44},{46,47}};
        RampartDefensiveLine rdl = new RampartDefensiveLine();
        int res = rdl.rampartDefensiveLine(rampart);
        System.out.println(res);
    }

    /*
     求膨胀的最大值，每次都有2种选择，向左或者向右；两边可以随时膨胀调整，关键是中间的怎么膨胀
    */
    public int rampartDefensiveLine(int[][] rampart) {
        int n = rampart.length;
        int num = n - 2; //中间墙的数量
        int[] blanks = new int[n];//用来记录每个墙所需的最多不可重叠的空格
        int count = 0;
        int sumBlank = 0; //空格总和
        for (int i = 0;i<n;i++) {
            if (i == 0 || i==n-1) { //开头和末尾的墙不需要空格
                blanks[i] = 0;
                continue;
            }
            int j = i + 1;
            int k = i - 1;
            int rightBlank = 0;
            int leftBlank = 0;
            if (j<n-1) {
                rightBlank = rampart[j][0] - rampart[i][1]; //当前墙与右边墙的空格
            }
            if (k>0) {
                leftBlank = rampart[i][0] - rampart[k][1]; //当前墙与左边墙的空格
            }
            blanks[i] = leftBlank + rightBlank;
            sumBlank += blanks[i];
            count++;
        }
        if (num == 1) return sumBlank; //如果中间墙只有一个，扩展空格总和的数量就是最大的
        Arrays.sort(blanks);
        int res = 0;

        return res;
    }

}
