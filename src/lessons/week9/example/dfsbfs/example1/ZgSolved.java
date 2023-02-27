package lessons.week9.example.dfsbfs.example1;

/**
 * @version 1.0
 * @Description: 面试题13. 机器人的运动范围----争哥解法
 * @author: bingyu
 * @date: 2023/2/24
 */
public class ZgSolved {

    public static void main(String[] args) {
        MovingCount v = new MovingCount();
        int count = v.movingCount(11, 8, 16);
        System.out.println(count);
    }

    private boolean[][] visited;
    private int count = 0;

    /*
     争哥解法思路
    */
    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        dfs(0, 0, m, n, k);
        return count;
    }

    private void dfs(int i, int j, int m, int n, int k) {
        visited[i][j] = true;
        count++;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int di = 0; di < 4; ++di) {
            int newi = i + directions[di][0];
            int newj = j + directions[di][1];
            if (newi >= m || newi < 0 || newj >= n || newj < 0 || visited[newi][newj] == true || check(newi, newj, k) == false) {
                continue;
            }
            dfs(newi, newj, m, n, k);
        }
    }

    private boolean check(int i, int j, int k) {
        int sum = 0;
        while (i != 0) {
            sum += (i%10);
            i /= 10;
        }
        while (j != 0) {
            sum += (j%10);
            j /= 10;
        }
        return sum <= k;
    }

}
