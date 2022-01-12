package lessons.week1.pratice.part2.pratice1;

/**
 * @version 1.0
 * @Description: 零矩阵
 * @author: bingyu
 * @date: 2022/1/12
 */
public class ZgSolved {

    public static void main(String[] args) {
        //二维数组快速初始化
        int[][] arr = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        print(arr);
        System.out.println("--------------");
        setZeroes(arr);
        print(arr);
    }

    //争哥的解法思路:使用一一映射数组这种数据结构
    public static void setZeroes(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
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
