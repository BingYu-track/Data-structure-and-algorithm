package lessons.week1.pratice.part2.pratice1;

/**
 * @version 1.0 零矩阵
 * @Description: 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * 示例 1：
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 示例 2：
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * @author: bingyu
 * @date: 2021/12/31
 */
public class SetZeroes {

    public static void main(String[] args) {
        //二维数组快速初始化
        int[][] arr = {{1,1,1},{1,0,1},{1,1,1}};
        print(arr);
    }


    public static void setZeroes(int[][] matrix){

    }

    //打印二维数组
    public static void print(int[][] matrix) {
        for (int i =0;i<matrix.length;i++) {
            for (int j=0;j<matrix[i].length;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

}
