package lessons.week9.pratice.dfsbfs.pratice1;

/**
 * @version 1.0 面试题 08.10. 颜色填充
 * @Description: 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
 * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
 * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
 * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
 *
 * 示例：
 *
 * 输入：
 * image = [
 *             [1,1,1],
 *             [1,1,0],
 *             [1,0,1]
 *         ]
 * sr = 1, sc = 1, newColor = 2
 * 输出：[
 *          [2,2,2],
 *          [2,2,0],
 *          [2,0,1]
 *      ]
 * 解释:初始坐标点位于图像的正中间，坐标(sr,sc)=(1,1)。初始坐标点周围区域上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，因为它不属于初始坐标点的周围区域。
 *
 * 提示：
 * image和image[0]的长度均在范围[1, 50]内。
 * 初始坐标点 (sr,sc) 满足0 <= sr < image.length 和0 <= sc < image[0].length 。
 * image[i][j]和newColor表示的颜色值在范围[0, 65535] 内。
 *
 * @author: bingyu
 * @date: 2023/3/2
 */
public class FloodFill {

    public static void main(String[] args) {
        FloodFill f = new FloodFill();
        int[][] image = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        f.floodFill(image,1,1,2);
        System.out.println();
    }

    /*
       执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
       内存消耗：42.2 MB, 在所有 Java 提交中击败了64.48%的用户
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int num = image[sr][sc];
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}}; //上、下、左、右
        dfs(sr,sc,num,image,directions,newColor);
        return image;
    }

    /**
     *
     * @param i 当前点所在行
     * @param j 当前点所在列
     * @param num 要替换的初始值
     * @param image
     * @param directions 上下左右四个方向
     * @param newColor 新的值
     */
    private void dfs(int i, int j, int num, int[][] image, int[][] directions, int newColor) {
        //i和j不能越界，还要当前点未访问过，然后当前点和初始值是一样的
        if (i<0 || j<0 || i==image.length || j==image[0].length || image[i][j]==newColor || image[i][j]!=num) return;
        //执行到这里说明当前点满足所有条件，将其位置替换为新的值，并且标记已访问
        image[i][j] = newColor;
        for (int k = 0;k < directions.length;k++) {
            int[] direction = directions[k];
            dfs(i + direction[0],j + direction[1],num,image,directions,newColor);
        }
    }

   /* TODO 优化--对原来的进行了剪枝，降低了递归空间
     解题思路: 从初始点开始上下左右进行深度遍历，遇到和初始点相同的值，就进行替换
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：42.1 MB, 在所有 Java 提交中击败了77.06%的用户
    */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int num = image[sr][sc];
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}}; //上、下、左、右
        dfs(sr,sc,num,image,directions,newColor);
        return image;
    }

    /**
     * TODO:注意这里不需要额外的visited，因为访问后会被替换为新的值，无需再标记
     * @param i 当前点所在行
     * @param j 当前点所在列
     * @param num 要替换的初始值
     * @param image
     * @param directions 上下左右四个方向
     * @param newColor 新的值
     */
    private void dfs2(int i, int j, int num, int[][] image, int[][] directions, int newColor) {
        //执行到这里说明当前点满足所有条件，将其位置替换为新的值，(最开始也是对的，因为开始就是给的初始值)
        image[i][j] = newColor;
        for (int k = 0;k < directions.length;k++) {
            int[] direction = directions[k];
            int i1 = i + direction[0];
            int j1 = j + direction[1];
            //TODO: 在这里进行剪枝，不继续递归，i和j不能越界，还要当前点未访问过，然后当前点和初始值是一样的
            if (i1<0 || j1<0 || i1==image.length || j1==image[0].length || image[i1][j1]!=num || image[i1][j1]==newColor) continue;
            dfs(i + direction[0],j + direction[1],num,image,directions,newColor); //访问上下左右四个方向的点
        }
    }

}
