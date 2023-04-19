package lessons.week9.pratice.dfsbfs.pratice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0  面试题 16.19. 水域大小----重复练习
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
 * @date: 2023/4/18
 */
public class RpPondSizes {

    public static void main(String[] args) {
        RpPondSizes rp = new RpPondSizes();
        int[][] land = new int[][]{
                {0,2,1,0},
                {0,1,0,1},
                {1,1,0,1},
                {0,1,0,1}
        };
        int[] sizes = rp.pondSizes(land);
        System.out.println(Arrays.toString(sizes));
    }

    /*
    执行用时：9 ms, 在所有 Java 提交中击败了81.45%的用户
    内存消耗：76 MB, 在所有 Java 提交中击败了63.83%的用户
     */
    private int count;

    /*
     0表示水塘
    */
    public int[] pondSizes(int[][] land) {
        List<Integer> result = new ArrayList<>();
        int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}, //上下左右
                            {-1,-1},{1,-1},{-1,1},{1,1}}; //左上、左下、右上、右下
        boolean[][] visited = new boolean[land.length][land[0].length];
        for (int i = 0;i<land.length;i++) {
            for (int j = 0;j<land[i].length;j++) {
                int num = land[i][j];
                if (num == 0 && !visited[i][j]) {
                    dfs(i,j,direction,visited,land);
                    result.add(count);
                    count = 0;
                }
            }
        }
        int[] arr = new int[result.size()];
        for (int i = 0;i<arr.length;i++) {
            arr[i] = result.get(i);
        }
        Arrays.sort(arr);
        return arr;
    }

    private void dfs(int row, int col, int[][] direction, boolean[][] visited,int[][] land) {
        //调用该dfs时必须是land[row][col]为0，并且未访问的
        visited[row][col] = true;
        count++;
        for (int i = 0;i<direction.length;i++) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if (newRow < 0 || newCol < 0 || newRow>=land.length || newCol>=land[0].length) continue; //越界
            //这里还是要校验是否访问的，因为在dfs中，假设一列中有2个0挨着的，上面的0向下遍历，上面的0向下遍历就会导致死循环
            if (land[newRow][newCol] == 0 && !visited[newRow][newCol]) {
                dfs(newRow,newCol,direction,visited,land);
            }
        }
    }

}
