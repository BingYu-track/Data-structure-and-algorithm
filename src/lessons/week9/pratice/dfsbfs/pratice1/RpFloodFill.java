package lessons.week9.pratice.dfsbfs.pratice1;

/**
 * @version 1.0 面试题 08.10. 颜色填充----重复练习
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
 * @date: 2023/4/17
 */
public class RpFloodFill {

    public static void main(String[] args) {
        RpFloodFill f = new RpFloodFill();
        int[][] image = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        f.floodFill(image,1,1,2);
        System.out.println();
    }

    /*
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：42.5 MB, 在所有 Java 提交中击败了15.09%的用户
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int num = image[sr][sc];
        if (num == newColor) return image;
        int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; //上下左右
        dfs(sr,sc,image,direction,num,newColor);
        return image;
    }

    private void dfs(int row, int col, int[][] image, int[][] direction, int num,int newColor) {
        image[row][col] = newColor;
        for (int i = 0;i<direction.length;i++) {
            int newRow = row + direction[i][0];
            int newCol = col + direction[i][1];
            if (newRow >= 0 && newCol >= 0 && newRow < image.length && newCol < image[0].length) { //不越界
                if (image[newRow][newCol]==num && image[newRow][newCol]!=newColor) { //不等于newColor说明是未访问过的
                    dfs(newRow,newCol,image,direction,num,newColor);
                }
            }
        }
    }

}
