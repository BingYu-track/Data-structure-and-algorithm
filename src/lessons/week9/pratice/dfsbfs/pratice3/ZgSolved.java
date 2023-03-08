package lessons.week9.pratice.dfsbfs.pratice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Description: 面试题 16.19. 水域大小----争哥解法
 * @author: bingyu
 * @date: 2023/3/8
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zgSolved = new ZgSolved();
        int[][] land = new int[][]{
                {0,2,1,0},
                {0,1,0,1},
                {1,1,2,1},
                {0,1,0,1}
        };
        int[] sizes = zgSolved.pondSizes(land);
        System.out.println(Arrays.toString(sizes));
    }


    /*
    争哥思路: 基本和我的一致，在我的基础上进行了剪枝,然后是用成员变量来记录0的次数变化，这样比我的要快
    执行用时：10 ms, 在所有 Java 提交中击败了71.25%的用户
    内存消耗：81.5 MB, 在所有 Java 提交中击败了5.41%的用户
    */
    private int count = 0;
    private int n;
    private int m;
    public int[] pondSizes(int[][] land) {
        n = land.length;
        m = land[0].length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (land[i][j] == 0) {
                    count = 0;
                    dfs(land, i, j);
                    result.add(count);
                }
            }
        }
        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            resultArr[i] = result.get(i);
        }
        Arrays.sort(resultArr);
        return resultArr;
    }
    private void dfs(int[][] land, int curi, int curj) {
        count++;
        land[curi][curj] = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1},
                {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        for (int d = 0; d < 8; ++d) {
            int newi = curi + dirs[d][0];
            int newj = curj + dirs[d][1];
            if (newi >= 0 && newi < n && newj >= 0 && newj < m && land[newi][newj] == 0) {
                dfs(land, newi, newj);
            }
        }
    }

}
