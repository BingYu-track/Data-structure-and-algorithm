package lessons.week9.pratice.dfsbfs.pratice1;

/**
 * @version 1.0  争个解法
 * @Description: 面试题 08.10. 颜色填充
 * @author: bingyu
 * @date: 2023/3/2
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved f = new ZgSolved();
        int[][] image = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        f.floodFill(image,1,1,2);
        System.out.println();
    }

    /*
      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：42.2 MB, 在所有 Java 提交中击败了63.08%的用户
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length;
        int m = image[0].length;
        dfs(image, n, m, sr, sc, image[sr][sc], newColor);
        return image;
    }
    private void dfs(int[][] image, int n, int m, int sr, int sc, int color, int newColor) {
        image[sr][sc] = newColor;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int k = 0; k < 4; ++k) {
            int newr = sr + dirs[k][0];
            int newc = sc + dirs[k][1];
            if (newr < 0 || newr >= n || newc < 0 || newc >= m || image[newr][newc] != color
                    || image[newr][newc]==newColor) {
                continue;
            }
            dfs(image, n, m, newr, newc, color, newColor);
        }
    }
}
