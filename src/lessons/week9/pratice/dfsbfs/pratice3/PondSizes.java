package lessons.week9.pratice.dfsbfs.pratice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0  面试题 16.19. 水域大小
 * @Description: 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。
 * 由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 *
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 * @author: bingyu
 * @date: 2023/3/8
 */
public class PondSizes {

    public static void main(String[] args) {
        PondSizes ps = new PondSizes();
        int[][] land = new int[][]{
                        {0,2,1,0},
                        {0,1,0,1},
                        {1,1,2,1},
                        {0,1,0,1}
                    };
        int[] sizes = ps.pondSizes(land);
        System.out.println(Arrays.toString(sizes));
    }


    /*
     解题思路:
     遇到0时，如果还没访问过加1，然后向上，向下，向左，向右，向对角线方向探测，并记录每个点已访问，探测期间遇到0，水域大小加1
       直到8个方向都无法探测时，返回起始点再顺序遍历。
     执行用时：12 ms, 在所有 Java 提交中击败了53.33%的用户
     内存消耗：75.9 MB, 在所有 Java 提交中击败了67.50%的用户
    */
    public int[] pondSizes(int[][] land) {
        int[][] directions = new int[][] {
                {-1,0},{1,0},{0,-1},{0,1}, //上、下、左、右
                {-1,-1},{1,-1},{-1,1},{1,1} //左上、左下、右上、右下
        };
        int m = land.length;
        int n = land[0].length;
        boolean[][] visited = new boolean[m][n];
        List<Integer> result = new ArrayList<>();
        for (int i = 0;i<m;i++) {
            for (int j = 0;j<n;j++) {
                if (!visited[i][j] && land[i][j] == 0) { //没访问过，并且遇到0
                    result.add(1);
                    int index = result.size()-1; //第一个水域0所在位置
                    dfs(i,j,land,directions,visited,result);
                    result.set(index,result.get(index)-1); //因为里面递归时，第1个0会重复加一次，因此这里我需要减去1
                }
            }
        }
        int[] p = new int[result.size()]; //再将集合转为数组
        for (int i = 0;i<p.length;i++) {
            p[i] = result.get(i);
        }
        Arrays.sort(p); //主要是用了快排会更快
        return p;
    }

    /*
     核心思路: 如何区分是第一个0，还是后面探测遇到的0
     */
    private void dfs(int i, int j, int[][] land, int[][] directions, boolean[][] visited, List<Integer> result) {
         //越界或者访问过，不向下进行遍历
        visited[i][j] = true;
        if (land[i][j] == 0) { //遇到0，末尾元素需要加1
            int index = result.size()-1;
            result.set(index,result.get(index)+1);
            //开始向8个方向进行深度遍历
            for (int k = 0;k < directions.length;k++) {
                int row = i + directions[k][0]; //行
                int col = j + directions[k][1]; //列
                if (row<0 || col<0 || row==land.length || col==land[0].length || visited[row][col]) continue;
                dfs(row,col,land,directions,visited,result);
            }
        }
    }

}
